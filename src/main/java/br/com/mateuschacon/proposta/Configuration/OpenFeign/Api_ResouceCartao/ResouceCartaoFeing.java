package br.com.mateuschacon.proposta.Configuration.OpenFeign.Api_ResouceCartao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.mateuschacon.proposta.ProposedResource.Dtos.CardAssociationResponse;

@FeignClient(name = "card-resource" , url = "${contas}")
public interface ResouceCartaoFeing {
    
    @GetMapping(value = "cartoes?idProposta={id}")
    CardAssociationResponse cardSearch(@PathVariable("id") String id_proposed);
    

}
