package br.com.mateuschacon.proposta.ProposedResource.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.mateuschacon.proposta.ProposedResource.Dtos.NewProposalRequest;
import br.com.mateuschacon.proposta.ProposedResource.Models.Proposed;
import br.com.mateuschacon.proposta.ProposedResource.Repositorys.ProposedRepository;

import java.net.URI;
import java.net.URISyntaxException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping(value = "/api/proposals")
public class ProposalsController {
    
    @Autowired
    ProposedRepository proposedRepository;

    @PostMapping
    public ResponseEntity<?> postMethodName(
        
        @RequestBody @Valid NewProposalRequest request, 
        UriComponentsBuilder uriBuilder

    ) throws URISyntaxException {

        Proposed proposed =  request.toModel();
        this.proposedRepository.save(proposed);


        URI uri = uriBuilder.path("/api/proposals/{id}").buildAndExpand(proposed.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
    
}
