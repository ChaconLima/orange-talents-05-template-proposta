package br.com.mateuschacon.proposta.Configuration.OpenFeign.Api_ResouceCartao;

import br.com.mateuschacon.proposta.CardResource.Models.Enums.StatusBlockEnum;
import feign.Response;
import feign.codec.ErrorDecoder;

public class BlackCardError implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        return new BlackCardException(StatusBlockEnum.NAO_BLOQUEADO.toString());
    }
    
}
