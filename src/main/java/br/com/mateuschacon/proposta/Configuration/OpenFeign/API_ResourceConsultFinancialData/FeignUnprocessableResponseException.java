package br.com.mateuschacon.proposta.Configuration.OpenFeign.API_ResourceConsultFinancialData;

import br.com.mateuschacon.proposta.ProposedResource.Dtos.FinancialAssessmentResponse;

public class FeignUnprocessableResponseException extends RuntimeException{
    

    private FinancialAssessmentResponse fResponse;

    public FeignUnprocessableResponseException(FinancialAssessmentResponse responseBody){
        this.fResponse = responseBody;
    }

    public FinancialAssessmentResponse getfResponse() {
        return this.fResponse;
    }

}
