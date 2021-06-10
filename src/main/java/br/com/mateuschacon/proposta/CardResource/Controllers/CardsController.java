package br.com.mateuschacon.proposta.CardResource.Controllers;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.mateuschacon.proposta.CardResource.Dtos.NewBiometryRequest;
import br.com.mateuschacon.proposta.CardResource.Models.Biometry;
import br.com.mateuschacon.proposta.CardResource.Models.Card;
import br.com.mateuschacon.proposta.CardResource.Repositorys.CardRepository;

import org.springframework.web.bind.annotation.RequestBody;


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
    
}
