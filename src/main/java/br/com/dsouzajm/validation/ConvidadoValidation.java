package br.com.dsouzajm.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import br.com.dsouzajm.model.Convidado;


public class ConvidadoValidation implements Validator {

	public boolean supports(Class<?> clazz) {				
		return Convidado.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {

		/*ValidationUtils.rejectIfEmpty(errors, "nome", "field.required");
		ValidationUtils.rejectIfEmpty(errors, "email", "field.required");
		ValidationUtils.rejectIfEmpty(errors, "telefone", "field.required");*/
		
		Convidado convidado = (Convidado) target;
		
	    if (convidado.getNome() == null
	    	|| convidado.getNome().trim().equals("")) {
	        errors.rejectValue("nome", "Nome não pode ser vazio.");
	    }
		
	}

}
