package br.com.mateuschacon.proposta.ProposedResource.Dtos;

import java.math.BigDecimal;
import java.util.UUID;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.mateuschacon.proposta.Configuration.Validation.Custom.CpfOrCnpj;
import br.com.mateuschacon.proposta.Configuration.Validation.Custom.UniqueValue;
import br.com.mateuschacon.proposta.ProposedResource.Models.Proposed;
import br.com.mateuschacon.proposta.ProposedResource.Models.Enums.ProposalStatusEnum;

public class NewProposalRequest {
    
    @NotBlank @CpfOrCnpj @UniqueValue(domainClass = Proposed.class, fieldName = "document")
    private String document;

    @NotBlank @Email
    public String email;

    @NotBlank
    private String name;

    @NotBlank
    private String address;

    @NotNull @Positive
    private BigDecimal wage;

    @NotNull 
    private String id;

    public NewProposalRequest(@NotBlank String document, @NotBlank String email, @NotBlank String name,
            @NotBlank String address,  @Positive @NotNull BigDecimal wage) {
        this.document = document;
        this.email = email;
        this.name = name;
        this.address = address;
        this.wage = wage;
        this.id = UUID.randomUUID().toString().replace("-", "");
    }

    public Proposed toModel( FinancialAssessmentResponse fResponse){
        
        ProposalStatusEnum statusProposal = 
            ProposalStatusEnum.getProposalStatusEnumByValue( fResponse.getResultadoSolicitacao() );

        return 
            new Proposed(
                    this.id,
                    this.document, 
                    this.email, 
                    this.name, 
                    this.address, 
                    this.wage,
                    statusProposal
                );
    }

    public FinancialAssessmentRequest toRequest() {
        return 
            new FinancialAssessmentRequest(
                this.document,
                this.name,
                this.id
            );
    }
}
