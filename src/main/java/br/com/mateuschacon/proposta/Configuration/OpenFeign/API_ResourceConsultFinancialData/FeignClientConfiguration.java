package br.com.mateuschacon.proposta.Configuration.OpenFeign.API_ResourceConsultFinancialData;

import org.springframework.context.annotation.Bean;

import feign.codec.ErrorDecoder;

public class FeignClientConfiguration {
    
    @Bean
    public ErrorDecoder errorDecoder() {
        return new QueryingDataForPropostaError();
    }
}
