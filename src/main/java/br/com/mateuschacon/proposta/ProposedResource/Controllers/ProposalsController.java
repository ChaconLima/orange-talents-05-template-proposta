package br.com.mateuschacon.proposta.ProposedResource.Controllers;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.mateuschacon.proposta.Configuration.OpenFeign.API_ResourceConsultFinancialData.FeignUnprocessableResponseException;
import br.com.mateuschacon.proposta.Configuration.OpenFeign.API_ResourceConsultFinancialData.QueryingDataForProposta;
import br.com.mateuschacon.proposta.ProposedResource.Dtos.FinancialAssessmentRequest;
import br.com.mateuschacon.proposta.ProposedResource.Dtos.FinancialAssessmentResponse;
import br.com.mateuschacon.proposta.ProposedResource.Dtos.NewProposalRequest;
import br.com.mateuschacon.proposta.ProposedResource.Dtos.ProposedResponse;
import br.com.mateuschacon.proposta.ProposedResource.Models.Proposed;
import br.com.mateuschacon.proposta.ProposedResource.Repositorys.ProposedRepository;



@RestController
@RequestMapping(value = "/api/proposals")
public class ProposalsController {
    
    @Autowired
    ProposedRepository proposedRepository;

    @Autowired
    QueryingDataForProposta queryingData;

    /**
     * Proposed Resource : 
     *  -End Point Proposed Creation
     */
    @PostMapping
    public ResponseEntity<?> proposedCreation(
        
        @RequestBody @Valid NewProposalRequest request, 
        UriComponentsBuilder uriBuilder

    ) throws URISyntaxException{

        FinancialAssessmentRequest fRequest = request.toRequest();

        FinancialAssessmentResponse fResponse;
        try {

            fResponse = this.queryingData.getFinancialAssessmentResponse(fRequest);

        } catch ( FeignUnprocessableResponseException e) {

            fResponse = e.getfResponse();
        }
        
        Proposed proposed =  request.toModel( fResponse );
        this.proposedRepository.save(proposed);

        URI uri = uriBuilder.path("/api/proposals/{id}").buildAndExpand(proposed.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
    


    @GetMapping(value="/{id}")
    public ResponseEntity<ProposedResponse> getProposed( @PathVariable("id") String id_proposed ) {
        
        Optional<Proposed> proposed=
            this.proposedRepository.findById(id_proposed);

        if( proposed.isPresent() ){

            ProposedResponse proposedResponse = proposed.get().toRequest();

            return ResponseEntity.ok(proposedResponse);
        }
        return ResponseEntity.notFound().build();
        
    }
    
}
