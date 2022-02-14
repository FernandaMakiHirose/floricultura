package br.com.alura.microservice.fornecedor.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alura.microservice.fornecedor.model.InfoFornecedor;
import br.com.alura.microservice.fornecedor.repository.InfoRepository;

@Service
public class InfoService {
	
	private static final Logger log = LoggerFactory.getLogger(InfoService.class);

	@Autowired
	private InfoRepository infoRepository;
	
	public InfoFornecedor getInfoPorEstado(String estado) {
		log.info("informações buscadas");
		return infoRepository.findByEstado(estado);
	}
}
