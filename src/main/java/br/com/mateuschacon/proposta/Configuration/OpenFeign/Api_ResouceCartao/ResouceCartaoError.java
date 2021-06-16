package br.com.mateuschacon.proposta.Configuration.OpenFeign.Api_ResouceCartao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.mateuschacon.proposta.CardResource.Dtos.DigitalWalletResponse;
import br.com.mateuschacon.proposta.CardResource.Dtos.TravelNoticeResponse;
import br.com.mateuschacon.proposta.CardResource.Models.Enums.StatusBlockEnum;
import feign.Response;
import feign.codec.ErrorDecoder;

public class ResouceCartaoError implements ErrorDecoder {

    private String travelNotice = "ResouceCartaoFeing#travelNotice(String,NewTravelNoticeRequest)";
    private String blockCard = "ResouceCartaoFeing#blockCard(String,BlockRequest)";
    private String digitalWallet = "ResouceCartaoFeing#digitalWallet(String,DigitalWalletRequest)";

    @Override
    public Exception decode(String methodKey, Response response) {

        if(methodKey.equals(this.blockCard)){

            return new ResouceCartaoException(StatusBlockEnum.NAO_BLOQUEADO.toString());

        }else if(methodKey.equals(this.travelNotice)){

            System.out.println(methodKey);

            String json = response.body().toString();

            ObjectMapper mapper = new ObjectMapper();
            TravelNoticeResponse travelNoticeResponse = new TravelNoticeResponse();
      
            try {
                
                if(response.status()!=500){
                    travelNoticeResponse = mapper.readValue( json , TravelNoticeResponse.class);
                }
                return new ResouceCartaoException(travelNoticeResponse);

            } catch (JsonProcessingException e) {
                e.printStackTrace();
                return new Exception(" BUG in System "); 
            }
        }else if(methodKey.equals(this.digitalWallet)){

            String json = response.body().toString();

            ObjectMapper mapper = new ObjectMapper();
            DigitalWalletResponse digitalWalletResponse = new DigitalWalletResponse();
      
            try {
                
                if(response.status()!=500){
                    digitalWalletResponse = mapper.readValue( json , DigitalWalletResponse.class);
                }
                return new ResouceCartaoException(digitalWalletResponse);

            } catch (JsonProcessingException e) {
                e.printStackTrace();
                return new Exception(" BUG in System "); 
            }
        }

        return new Exception("Bug in System");

    }
    
}
