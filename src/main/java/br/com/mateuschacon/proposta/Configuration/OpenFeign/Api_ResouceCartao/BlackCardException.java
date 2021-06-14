package br.com.mateuschacon.proposta.Configuration.OpenFeign.Api_ResouceCartao;

import javax.validation.constraints.NotBlank;

import br.com.mateuschacon.proposta.CardResource.Dtos.BlockResponse;

public class BlackCardException extends RuntimeException{
    
    private BlockResponse blockResponse = new BlockResponse();


    BlackCardException( @NotBlank String result){
        this.blockResponse.setResultado(result);
    }

    public BlockResponse getBlockResponse() {
        return this.blockResponse;
    }
}

