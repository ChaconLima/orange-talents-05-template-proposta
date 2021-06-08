package br.com.mateuschacon.proposta.Configuration.Scheduled;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.mateuschacon.proposta.Configuration.OpenFeign.Api_ResouceCartao.ResouceCartaoFeing;
import br.com.mateuschacon.proposta.ProposedResource.Dtos.CardAssociationResponse;
import br.com.mateuschacon.proposta.ProposedResource.Models.Proposed;
import br.com.mateuschacon.proposta.ProposedResource.Models.Enums.AssociationStatusEnum;
import br.com.mateuschacon.proposta.ProposedResource.Models.Enums.ProposalStatusEnum;
import br.com.mateuschacon.proposta.ProposedResource.Repositorys.ProposedRepository;
import feign.FeignException;


@Component @EnableScheduling 
public class CardCheckerScheduled {
    
  
    private static final String TIME_ZONE = "America/Sao_Paulo";

    @Autowired
    ProposedRepository proposedRepository;

    @Autowired
    ResouceCartaoFeing resouceCartaoFeing;

    private List<Proposed>proposeds = new ArrayList<>();

    @Scheduled(cron = "0 0/1 * * * *", zone = TIME_ZONE) 
    public void verification(){

        this.proposeds = 
            this.proposedRepository
            .findByAssociationStatusEnumAndProposalStatusEnum(
                AssociationStatusEnum.NAO_FEITO, ProposalStatusEnum.ELEGIVEL
            );

        if(!this.proposeds.isEmpty()){
            
            for (Proposed proposed :this.proposeds) {

                try {

                    CardAssociationResponse fResponse = 
                        this.resouceCartaoFeing.cardSearch(proposed.getId());

                    proposed.cardAssociation(fResponse);
                    this.proposedRepository.save(proposed);

                } catch (FeignException e) {
                    System.out.println(e.getMessage());
                }
            }
        }

    }
}
