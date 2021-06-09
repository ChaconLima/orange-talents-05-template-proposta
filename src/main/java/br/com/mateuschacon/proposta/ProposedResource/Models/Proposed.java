package br.com.mateuschacon.proposta.ProposedResource.Models;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.mateuschacon.proposta.ProposedResource.Dtos.CardAssociationResponse;
import br.com.mateuschacon.proposta.ProposedResource.Dtos.ProposedResponse;
import br.com.mateuschacon.proposta.ProposedResource.Models.Enums.AssociationStatusEnum;
import br.com.mateuschacon.proposta.ProposedResource.Models.Enums.ProposalStatusEnum;

@Entity
public class Proposed {

    @Id
    @Column(name = "id", columnDefinition = "VARCHAR(32)")
    @NotBlank
    private String id;

    @NotBlank
    private String document;

    @NotBlank
    private String email;

    @NotBlank
    private String name;

    @NotBlank
    private String address;

    @NotNull
    private BigDecimal wage;

    @NotNull @Enumerated(EnumType.STRING)
    private ProposalStatusEnum proposalStatusEnum;

    @NotNull @Enumerated(EnumType.STRING)
    private AssociationStatusEnum associationStatusEnum;

    @OneToOne (cascade = CascadeType.ALL)
    private Card card;

    
    @Deprecated
    Proposed(){}

    public Proposed(@NotBlank String id, @NotBlank String document, @NotBlank String email, @NotBlank String name,
            @NotBlank String address, @NotNull BigDecimal wage, @NotNull ProposalStatusEnum proposalStatusEnum) {
        this.id = id;
        this.document = document;
        this.email = email;
        this.name = name;
        this.address = address;
        this.wage = wage;
        this.proposalStatusEnum = proposalStatusEnum;
        this.associationStatusEnum = AssociationStatusEnum.NAO_FEITO;
    }

    public void cardAssociation(@Valid CardAssociationResponse cardAssociationResponse){
        this.card = cardAssociationResponse.toModel();
        this.associationStatusEnum = AssociationStatusEnum.FEITO;
    }
    
    public ProposedResponse toRequest(){
        return new ProposedResponse(this.proposalStatusEnum);
    }

    public String getId() {
        return this.id;
    }
}
