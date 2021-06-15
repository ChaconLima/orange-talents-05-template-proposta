package br.com.mateuschacon.proposta.Configuration.OpenFeign.Api_ResouceCartao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.mateuschacon.proposta.CardResource.Dtos.BlockRequest;
import br.com.mateuschacon.proposta.CardResource.Dtos.BlockResponse;
import br.com.mateuschacon.proposta.CardResource.Dtos.NewTravelNoticeRequest;
import br.com.mateuschacon.proposta.CardResource.Dtos.TravelNoticeResponse;
import br.com.mateuschacon.proposta.ProposedResource.Dtos.CardAssociationResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(name = "card-resource" , url = "${contas}",  configuration = ResouceCartaoConfiguration.class)
public interface ResouceCartaoFeing {
    
    @GetMapping(value = "cartoes?idProposta={id}")
    CardAssociationResponse cardSearch(@PathVariable("id") String id_proposed);

    @PostMapping(value = "cartoes/{id}/bloqueios")
    BlockResponse blockCard(

        @PathVariable("id") String id_proposed,
        @RequestBody BlockRequest entity
        
    );

    @PostMapping(value = "cartoes/{id}/avisos")
    TravelNoticeResponse travelNotice(

        @PathVariable("id") String id_proposed,
        @RequestBody NewTravelNoticeRequest entity
        
    );
    
    

}
