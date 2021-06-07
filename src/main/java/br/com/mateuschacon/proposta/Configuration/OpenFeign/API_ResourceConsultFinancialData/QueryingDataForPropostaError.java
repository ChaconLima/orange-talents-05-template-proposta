package br.com.mateuschacon.proposta.Configuration.OpenFeign.API_ResourceConsultFinancialData;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.mateuschacon.proposta.ProposedResource.Dtos.FinancialAssessmentResponse;
import feign.Response;
import feign.codec.ErrorDecoder;

public class QueryingDataForPropostaError implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {

        String json = response.body().toString();

        ObjectMapper mapper = new ObjectMapper();
        FinancialAssessmentResponse responseBody;
      
            try {
                
                responseBody = mapper.readValue( json , FinancialAssessmentResponse.class);
                return new FeignUnprocessableResponseException( responseBody);

            } catch (JsonProcessingException e) {
                e.printStackTrace();
                return new Exception(" BUG in System "); 
            }
 
    }
}
