package br.com.vendelancha.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.com.vendelancha.service.NegocioException;

@Named
@RequestScoped
public class CadastroEmbarcacaoBean {
	
	public void salvar(){
//		throw new RuntimeException("Teste de exceção");
		throw new NegocioException("salvar ainda não foi implementado");
	}

}
