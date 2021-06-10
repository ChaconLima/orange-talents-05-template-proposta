package br.com.mateuschacon.proposta.Configuration.Validation.Custom;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import static java.lang.annotation.ElementType.FIELD;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = {Base64Validador.class})
@Target({ FIELD })
@Retention(RUNTIME)
public @interface Base_64 {
    
    String message() default "Não está em base 64";

    Class<?>[] groups() default { };

    Class<? extends Payload >[] payload() default {};
}
