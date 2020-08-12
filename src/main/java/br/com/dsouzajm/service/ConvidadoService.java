package br.com.dsouzajm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.dsouzajm.model.Convidado;
import br.com.dsouzajm.repository.ConvidadoRepository;

@Service
public class ConvidadoService {

	@Autowired
	private ConvidadoRepository convidadoRepository;

	public Iterable<Convidado> buscaTodos() {

		return (Iterable<Convidado>) convidadoRepository.findAll();
	}

	public void salvar(Convidado convidado) {

		convidadoRepository.save(convidado);
	}
}
