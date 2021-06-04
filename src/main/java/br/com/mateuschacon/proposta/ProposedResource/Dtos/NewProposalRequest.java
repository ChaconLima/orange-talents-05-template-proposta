package br.com.mateuschacon.proposta.ProposedResource.Dtos;

import java.math.BigDecimal;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.mateuschacon.proposta.Configuration.Validation.Custom.CpfOrCnpj;
import br.com.mateuschacon.proposta.Configuration.Validation.Custom.UniqueValue;
import br.com.mateuschacon.proposta.ProposedResource.Models.Proposed;

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

    public NewProposalRequest(@NotBlank String document, @NotBlank String email, @NotBlank String name,
            @NotBlank String address,  @Positive @NotNull BigDecimal wage) {
        this.document = document;
        this.email = email;
        this.name = name;
        this.address = address;
        this.wage = wage;
    }

    public Proposed toModel(){
        return 
            new Proposed(
                    this.document, 
                    this.email, 
                    this.name, 
                    this.address, 
                    this.wage
                );
    }

}
