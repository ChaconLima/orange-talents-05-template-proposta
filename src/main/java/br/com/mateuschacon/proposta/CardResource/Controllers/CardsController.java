package br.com.mateuschacon.proposta.CardResource.Controllers;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.mateuschacon.proposta.CardResource.Dtos.BlockRequest;
import br.com.mateuschacon.proposta.CardResource.Dtos.BlockResponse;
import br.com.mateuschacon.proposta.CardResource.Dtos.DigitalWalletResponse;
import br.com.mateuschacon.proposta.CardResource.Dtos.NewBiometryRequest;
import br.com.mateuschacon.proposta.CardResource.Dtos.NewDigitalWalletRequest;
import br.com.mateuschacon.proposta.CardResource.Dtos.NewTravelNoticeRequest;
import br.com.mateuschacon.proposta.CardResource.Dtos.TravelNoticeResponse;
import br.com.mateuschacon.proposta.CardResource.Models.Biometry;
import br.com.mateuschacon.proposta.CardResource.Models.Block;
import br.com.mateuschacon.proposta.CardResource.Models.Card;
import br.com.mateuschacon.proposta.CardResource.Models.DigitalWallet;
import br.com.mateuschacon.proposta.CardResource.Models.Travel;
import br.com.mateuschacon.proposta.CardResource.Models.Enums.DigitalWalletEnum;
import br.com.mateuschacon.proposta.CardResource.Repositorys.CardRepository;
import br.com.mateuschacon.proposta.Configuration.Cryptography.MD5;
import br.com.mateuschacon.proposta.Configuration.OpenFeign.Api_ResouceCartao.ResouceCartaoException;
import br.com.mateuschacon.proposta.Configuration.OpenFeign.Api_ResouceCartao.ResouceCartaoFeing;
import io.opentracing.Span;
import io.opentracing.Tracer;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping(value = "/api/card")
public class CardsController {

     /**
     * Repository
     */
    private final CardRepository cardRepository;
     /**
     * HttpServletRequest.
     */
    private final HttpServletRequest request;
    /**
     * Feign.
     */
    private final ResouceCartaoFeing resouceCartaoFeing;
    /**
     * OpenTracing
     */
    private final Tracer tracer;
    /**
     * Cryptography
     */
    private final MD5 md5;

    public CardsController(
        CardRepository cardRepository, 
        HttpServletRequest request,
        ResouceCartaoFeing resouceCartaoFeing,
        Tracer tracer,
        MD5 md5
    ) {
        this.cardRepository = cardRepository;
        this.request = request;
        this.resouceCartaoFeing = resouceCartaoFeing;
        this.tracer = tracer;
        this.md5 = md5;
    }


    @PostMapping(value="/{id}/biometry")
    public ResponseEntity<?> biometryRegistration(

        @PathVariable("id") String id_card,
        @RequestBody @Valid NewBiometryRequest newBiometryRequest,
        UriComponentsBuilder uriBuilder
    
    ) throws URISyntaxException{
        
        /**
         * OpenTracing
         */
        Span activeSpan = tracer.activeSpan();
        activeSpan.setTag("card.biometry.id",id_card);

        Optional<Card> cardIsValid = this.cardRepository.findById( this.md5.md5(id_card) );

        if(cardIsValid.isPresent()){

            Card card = cardIsValid.get();

            Biometry biometry = newBiometryRequest.toModel(card);
            card.addBiometry(biometry);

            this.cardRepository.save(card);
            
            URI uri = uriBuilder.path("/api/card/biometry/{id}").buildAndExpand(biometry.getId()).toUri();
            return ResponseEntity.created(uri).build(); 
        }
        
        return ResponseEntity.notFound().build();
    }

    @GetMapping(value="/{id}/block")
    public ResponseEntity<?> blockRegistration(

        @PathVariable("id") String id_card,
        UriComponentsBuilder uriBuilder
    
    ) throws URISyntaxException{
        /**
         * OpenTracing
         */
        Span activeSpan = tracer.activeSpan();
        activeSpan.setTag("card.block.id",this.md5.md5(id_card));

        Optional<Card> cardIsValid = this.cardRepository.findById(this.md5.md5(id_card));

        if(!cardIsValid.isPresent()){
            return ResponseEntity.notFound().build();
        }

        Card card = cardIsValid.get();
        if(cardIsValid.isPresent() && card.isNotBlocked()){

            String userAgent = request.getHeader("User-Agent");
            String ip = request.getRemoteAddr();
    
            BlockResponse blockResponse;
            try {

                blockResponse = this.resouceCartaoFeing.blockCard( id_card, new BlockRequest("Sistema de Proposta"));
        
            }catch(ResouceCartaoException e) {
                /**
                 * OpenTracing
                 */
                activeSpan.log(e.getMessage());
                blockResponse = e.getBlockResponse();
            }


            Block block = new Block(ip, userAgent, card, blockResponse);
           
            card.addBlock(block);

            this.cardRepository.save(card);

            return ResponseEntity.ok().build();

        }

        return ResponseEntity.status( HttpStatus.UNPROCESSABLE_ENTITY).build();
    }

    /**
     * 
     * 
     */
    @PostMapping(value="/{id}/travel")
    public ResponseEntity<?> travelRegistration(

        @PathVariable("id") String id_card,
        @RequestBody @Valid NewTravelNoticeRequest newTravelNoticeRequest

    ){
        /**
         * OpenTracing
         */
        Span activeSpan = tracer.activeSpan();
        activeSpan.setTag("card.travel.id",this.md5.md5(id_card));

        Optional<Card> cardIsValid = this.cardRepository.findById(this.md5.md5(id_card));

        if( !cardIsValid.isPresent() ){
            return ResponseEntity.notFound().build();
        }

        TravelNoticeResponse fResponse;
        try {

            fResponse =  this.resouceCartaoFeing.travelNotice(id_card, newTravelNoticeRequest);
    
        }catch(ResouceCartaoException e) {
            /**
             * OpenTracing
             */
            activeSpan.log(e.getMessage());
            fResponse = e.getTravelNoticeResponse();

        }
        
        Card card = cardIsValid.get();
        if(card.isValidTravelNotice(fResponse)){

            Travel travel = newTravelNoticeRequest.toModel(card, this.request);

            card.addTravel(travel);

            this.cardRepository.save(card);

        }
        return ResponseEntity.ok().build();
        

     
    }
    
    /**
     * 
     * 
     */
    @PostMapping(value="/{id}/{wallet}")
    public ResponseEntity<?> digitalWalletRegistration(

        @PathVariable("id") String id_card,
        @PathVariable("wallet") String wallet,
        @RequestBody @Valid NewDigitalWalletRequest newDigitalWalletPaypalRequest,
        UriComponentsBuilder uriBuilder
    
    ) throws URISyntaxException{   
        /**
         * OpenTracing
         */
        Span activeSpan = tracer.activeSpan();
        activeSpan.setTag("card."+wallet+".id",this.md5.md5(id_card));

        DigitalWalletEnum digitalWalletEnum =
            DigitalWalletEnum.getDigitalWalletEnumByValue(wallet);

        Optional<Card> cardIsValid = this.cardRepository.findById(this.md5.md5(id_card));

        if( !cardIsValid.isPresent() ||  digitalWalletEnum == null ){
            return ResponseEntity.notFound().build();
        }

        Card card = cardIsValid.get();
        if(!card.isNotAssociated(digitalWalletEnum)){
            return ResponseEntity.status( HttpStatus.UNPROCESSABLE_ENTITY).build();
        }

        DigitalWalletResponse fResponse;
        try {

            fResponse =  
                this.resouceCartaoFeing.digitalWallet(
                    id_card, 
                    newDigitalWalletPaypalRequest.toRequest(digitalWalletEnum)    
                );

        }catch(ResouceCartaoException e) {
            /**
             * OpenTracing
             */
            activeSpan.log(e.getMessage());
            fResponse = e.getDigitalWalletResponse();
            return ResponseEntity.status( HttpStatus.UNPROCESSABLE_ENTITY).build();
        }

        DigitalWallet digitalWallet = 
            newDigitalWalletPaypalRequest.toModel(
                fResponse.getId(),
                digitalWalletEnum,
                card
            );

        card.addDigitalWallet(digitalWallet);

        this.cardRepository.save(card);


        URI uri = uriBuilder.path("/api/card/{wallet}/{id}").buildAndExpand(wallet,digitalWallet.getId()).toUri();
        return ResponseEntity.created(uri).build(); 
    }
    
    
    
}
