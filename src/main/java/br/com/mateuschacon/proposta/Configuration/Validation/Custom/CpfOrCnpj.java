package br.com.mateuschacon.proposta.Configuration.Validation.Custom;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

import org.hibernate.validator.constraints.ConstraintComposition;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static java.lang.annotation.ElementType.FIELD;
import static org.hibernate.validator.constraints.CompositionType.OR;


@Documented
@ConstraintComposition(OR)
@CPF
@CNPJ
@Constraint(validatedBy = {})
@Target({ FIELD })
@Retention(RUNTIME)
public @interface CpfOrCnpj {

    String message() default "o campo deve ser formatado como cpf ou cnpj";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @Documented
    @Target({FIELD})
    @Retention(RUNTIME)
    @interface List {
        CpfOrCnpj[] value();
    }
}
