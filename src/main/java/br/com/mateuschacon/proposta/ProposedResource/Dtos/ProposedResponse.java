package br.com.mateuschacon.proposta.ProposedResource.Dtos;

import javax.validation.constraints.NotNull;

import br.com.mateuschacon.proposta.ProposedResource.Models.Enums.ProposalStatusEnum;

public class ProposedResponse {

    private String status;
    public ProposedResponse(@NotNull ProposalStatusEnum proposalStatusEnum) {
        this.status = proposalStatusEnum.toString();
    }

    public String getStatus() {
        return this.status;
    }
}
