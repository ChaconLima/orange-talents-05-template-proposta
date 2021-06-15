package br.com.mateuschacon.proposta.CardResource.Controllers;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.mateuschacon.proposta.CardResource.Dtos.BlockRequest;
import br.com.mateuschacon.proposta.CardResource.Dtos.BlockResponse;
import br.com.mateuschacon.proposta.CardResource.Dtos.NewBiometryRequest;
import br.com.mateuschacon.proposta.CardResource.Dtos.NewTravelNoticeRequest;
import br.com.mateuschacon.proposta.CardResource.Models.Biometry;
import br.com.mateuschacon.proposta.CardResource.Models.Block;
import br.com.mateuschacon.proposta.CardResource.Models.Card;
import br.com.mateuschacon.proposta.CardResource.Models.Travel;
import br.com.mateuschacon.proposta.CardResource.Repositorys.CardRepository;
import br.com.mateuschacon.proposta.Configuration.OpenFeign.Api_ResouceCartao.BlackCardException;
import br.com.mateuschacon.proposta.Configuration.OpenFeign.Api_ResouceCartao.ResouceCartaoFeing;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping(value = "/api/card")
public class CardsController {

    @Autowired
    CardRepository cardRepository;

    @PostMapping(value="/{id}/biometry")
    public ResponseEntity<?> biometryRegistration(

        @PathVariable("id") String id_card,
        @RequestBody @Valid NewBiometryRequest newBiometryRequest,
        UriComponentsBuilder uriBuilder
    
    ) throws URISyntaxException{
        
        Optional<Card> cardIsValid = this.cardRepository.findById(id_card);

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

    /**
     * HttpServletRequest.
     */
    @Autowired
    private HttpServletRequest request;

    /**
     * Feign.
     */
    @Autowired
    private ResouceCartaoFeing resouceCartaoFeing;

    @GetMapping(value="/{id}/block")
    public ResponseEntity<?> blockRegistration(

        @PathVariable("id") String id_card,
        UriComponentsBuilder uriBuilder
    
    ) throws URISyntaxException{
        
        Optional<Card> cardIsValid = this.cardRepository.findById(id_card);

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
        
            }catch(BlackCardException e) {
                
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

        Optional<Card> cardIsValid = this.cardRepository.findById(id_card);

        if( cardIsValid.isPresent() ){

            Card card = cardIsValid.get();
            Travel travel = newTravelNoticeRequest.toModel(card, this.request);

            card.addTravel(travel);

            this.cardRepository.save(card);

            return ResponseEntity.ok().build();


        }
        return ResponseEntity.notFound().build();
    }
    
    
}
