package br.com.vendelancha.controller;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;


@Named
@RequestScoped
public class PesquisaEmbarcacoesBean {
	
	private List<Integer> embarcacaoFiltrados;
	
	public PesquisaEmbarcacoesBean(){
		embarcacaoFiltrados = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			embarcacaoFiltrados.add(i);	
		}
	}
	
	public List<Integer> getEmbarcacaoFiltrados() {
		return embarcacaoFiltrados;
	}

}
