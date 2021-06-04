package br.com.mateuschacon.proposta.Configuration.Validation.Custom;


import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import static java.lang.annotation.ElementType.FIELD;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = {UniqueValueValidator.class})
@Target({ FIELD })
@Retention(RUNTIME)
public @interface UniqueValue {
    
    Class<?> domainClass();
    
    String fieldName() ;

    String message() default "Já existe esse recurso cadastrado";

    Class<?>[] groups() default { };

    Class<? extends Payload >[] payload() default {};

}
