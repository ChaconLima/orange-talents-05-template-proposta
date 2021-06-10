package br.com.mateuschacon.proposta.Configuration.Validation.Custom;



import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.codec.binary.Base64;


public class Base64Validador implements ConstraintValidator<Base_64,Object> {

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {

        if(value==null){ return true; }

        String tochek = value.toString();
        return Base64.isBase64(tochek.getBytes());
    }
    
}
