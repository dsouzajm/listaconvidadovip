package br.com.dsouzajm.controllers;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.dsouzajm.model.Convidado;
import br.com.dsouzajm.service.ConvidadoService;
import br.com.dsouzajm.validation.ConvidadoValidation;
import emailSender.EmailSender;

@Controller
public class ConvidadoController {

	@Autowired
	private ConvidadoService convidadoService;
	
	@PersistenceContext
	private EntityManager em;
	
	@InitBinder
	private void initBinder(WebDataBinder dadoBinder) {
		
		dadoBinder.addValidators(new ConvidadoValidation());		
	}
	
	@RequestMapping("/")
	public String index() {
		
		return "index";
	}
	
	@RequestMapping("/listaconvidados")
	public String listaconvidados(Model model) {
		
		Iterable<Convidado> convidados = convidadoService.buscaTodos();
		model.addAttribute("convidados", convidados);

		Convidado convidado = new Convidado();
		model.addAttribute("convidado", convidado);
		
		return "listaconvidados";
	}	

	@RequestMapping(value="/", method = RequestMethod.POST)
	public String salvar(@Valid Convidado convidado
						 , BindingResult bindingResult
						 , Model model) {
		
		System.out.println("bindingResult.hasErrors()");
		System.out.println(bindingResult.hasErrors());
		
		System.out.println(convidado.getNome());
		System.out.println(convidado.getEmail());
		System.out.println(convidado.getTelefone());
		
		if(bindingResult.hasErrors()) {
			
			
			/*List<FieldError> fieldErrors = bindingResult.getFieldErrors();
			
			for (FieldError fieldError : fieldErrors) {
				System.out.println("Field error: " + fieldError.getField());
			}*/
			
			Iterable<Convidado> convidados = convidadoService.buscaTodos();		
			convidado = new Convidado();
			
			model.addAttribute("convidados", convidados);
			model.addAttribute("convidado", convidado);			
			
			return "listaconvidados";
			
		} else {
		
			convidadoService.salvar(convidado);
			
			System.out.println("Convidado: " + convidado);
			
			enviarEmail(convidado);
		
			Iterable<Convidado> convidados = convidadoService.buscaTodos();		
			convidado = new Convidado();
			
			model.addAttribute("convidados", convidados);
			model.addAttribute("convidado", convidado);		
			
			return "listaconvidados";
		}
	}		
	
	public void enviarEmail(Convidado convidado) {
		
		EmailSender.emailSender(convidado.getNome(), convidado.getEmail());
				
	}
	
}
