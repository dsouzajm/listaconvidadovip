package br.com.dsouzajm.controllers;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.dsouzajm.model.Convidado;
import br.com.dsouzajm.service.ConvidadoService;
import emailSender.EmailSender;

@Controller
public class ConvidadoController {

	@Autowired
	private ConvidadoService convidadoService;
	
	@PersistenceContext
	private EntityManager em;
	
	@RequestMapping("/")
	public String index() {
		return "index";
	}
	
	@RequestMapping("/listaconvidados")
	public String listaconvidados(Model model) {
		
		Iterable<Convidado> convidados = convidadoService.buscaTodos();
		model.addAttribute("convidados", convidados);
		
		return "listaconvidados";
	}	

	@RequestMapping(value="/salvar", method = RequestMethod.POST)
	public String salvar(Model model
						 , @RequestParam String nome
						 , @RequestParam String email
						 , @RequestParam String telefone) {
		
		
		Convidado convidado = new Convidado(nome, email, telefone);
		convidadoService.salvar(convidado);
		
		System.out.println("Convidado: " + convidado);
		
		enviarEmail(convidado);
		
		Iterable<Convidado> convidados = convidadoService.buscaTodos();
		model.addAttribute("convidados", convidados);
		
		return "listaconvidados";
	}		
	
	public void enviarEmail(Convidado convidado) {
		
		EmailSender.emailSender(convidado.getNome(), convidado.getEmail());
				
	}
	
}
