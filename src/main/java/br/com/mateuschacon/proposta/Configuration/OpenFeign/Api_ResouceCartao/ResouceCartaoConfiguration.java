package br.com.mateuschacon.proposta.Configuration.OpenFeign.Api_ResouceCartao;

import org.springframework.context.annotation.Bean;

import feign.codec.ErrorDecoder;

public class ResouceCartaoConfiguration {

    @Bean
    public ErrorDecoder errorDecoder() {
        return new ResouceCartaoError();
    }
    
}
