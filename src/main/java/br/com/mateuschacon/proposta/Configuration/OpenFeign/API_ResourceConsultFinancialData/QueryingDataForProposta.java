package br.com.mateuschacon.proposta.Configuration.OpenFeign.API_ResourceConsultFinancialData;

import javax.validation.Valid;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.mateuschacon.proposta.ProposedResource.Dtos.FinancialAssessmentRequest;
import br.com.mateuschacon.proposta.ProposedResource.Dtos.FinancialAssessmentResponse;

@FeignClient(name = "querying-applicant-data" , url = "http://localhost:9999/api", configuration = FeignClientConfiguration.class)
public interface QueryingDataForProposta {
    
    @RequestMapping(method = RequestMethod.POST, value = "/solicitacao", consumes = "application/json")
    FinancialAssessmentResponse getFinancialAssessmentResponse(
        @RequestBody @Valid FinancialAssessmentRequest fRequest
    );

}

