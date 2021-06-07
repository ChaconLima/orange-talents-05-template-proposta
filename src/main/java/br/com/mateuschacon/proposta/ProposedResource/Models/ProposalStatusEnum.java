package br.com.mateuschacon.proposta.ProposedResource.Models;

import java.util.HashMap;
import java.util.Map;

public enum ProposalStatusEnum {
    NAO_ELEGIVEL("COM_RESTRICAO"),
    ELEGIVEL( "SEM_RESTRICAO"  );

    private String value;

    private static final Map<String, ProposalStatusEnum> functionByValue = new HashMap<>();

    static{
        for( ProposalStatusEnum proposalStatusEnum: ProposalStatusEnum.values()){
            functionByValue.put( proposalStatusEnum.getValue() , proposalStatusEnum);
        }
    }

    ProposalStatusEnum(String value){
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }

    public static ProposalStatusEnum getProposalStatusEnumByValue( String value){
        return functionByValue.get(value);
    }
}
