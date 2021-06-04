package br.com.mateuschacon.proposta.Configuration.Validation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MessageReponse {
    
    @Autowired
    private MessageSource messageSource;

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorsResponse handle(MethodArgumentNotValidException methodArgumentNotValidException){
        
        List<FieldError> fieldErrors = methodArgumentNotValidException.getBindingResult().getFieldErrors();
        List<ObjectError>globalErrors= methodArgumentNotValidException.getBindingResult().getGlobalErrors();

        return this.buildErrors(fieldErrors, globalErrors);
    }

    private ErrorsResponse buildErrors( List<FieldError> fieldErrors, List<ObjectError> globalErrors){
        
        ErrorsResponse errorsResponse = new ErrorsResponse();

        fieldErrors.forEach(e->{
            String mensagem = this.messageSource.getMessage(e, LocaleContextHolder.getLocale());
            ErrorValidator errorValidator = new ErrorValidator(e.getField() ,mensagem);
            errorsResponse.addErrorValidator(errorValidator);
        });

        globalErrors.forEach(e->{
            String mensagem = this.messageSource.getMessage(e, LocaleContextHolder.getLocale());
            errorsResponse.addGlobalErrors(mensagem);
        });

        return errorsResponse;
    }
}
