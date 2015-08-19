package br.com.vendelancha.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.vendelancha.model.Noticia;
import br.com.vendelancha.repository.Noticias;
import br.com.vendelancha.util.jpa.Transactional;

public class CadastroNoticiaService implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Inject
	private Noticias noticias;
	
	@Transactional
	public Noticia salvar(Noticia noticia){
		return noticias.guardar(noticia);
	}
	

}
