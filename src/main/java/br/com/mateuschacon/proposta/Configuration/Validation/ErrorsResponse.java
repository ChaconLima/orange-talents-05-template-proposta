package br.com.mateuschacon.proposta.Configuration.Validation;

import java.util.ArrayList;
import java.util.List;

public class ErrorsResponse {

    List<ErrorValidator> errorValidators = new ArrayList<>();
    List<String> globalErrors = new ArrayList<>();

    public void addErrorValidator(ErrorValidator errorValidator){
        this.errorValidators.add(errorValidator);
    }
    public void addGlobalErrors(String error){
        this.globalErrors.add(error);
    }

    public List<ErrorValidator> getErrorValidators() {
        return errorValidators;
    }
    public List<String> getGlobalErrors() {
        return globalErrors;
    }

}
