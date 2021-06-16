package br.com.mateuschacon.proposta.CardResource.Models.Enums;

import java.util.HashMap;
import java.util.Map;

public enum DigitalWalletEnum {
    PAYPAL("paypal"), SAMSUNG_PAY("samsung-pay");

    private String value;

    private static final Map<String, DigitalWalletEnum> functionByValue = new HashMap<>();

    static{
        for( DigitalWalletEnum digitalWalletEnum: DigitalWalletEnum.values()){
            functionByValue.put( digitalWalletEnum.getValue() , digitalWalletEnum);
        }
    }

    DigitalWalletEnum(String value){
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }

    public static DigitalWalletEnum getDigitalWalletEnumByValue( String value){
        System.out.println(functionByValue.get(value));
        return functionByValue.get(value);
    }
}
