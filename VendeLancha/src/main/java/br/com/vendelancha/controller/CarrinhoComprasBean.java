package br.com.vendelancha.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.vendelancha.model.Noticia;
import br.com.vendelancha.repository.Noticias;
import br.com.vendelancha.repository.filter.NoticiaFilter;
import br.com.vendelancha.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CarrinhoComprasBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private Noticias noticias;

	private NoticiaFilter filtro;
	private List<Noticia> noticiasFiltrados;
	

	private Noticia noticiaSelecionada;

	public CarrinhoComprasBean() {
		filtro = new NoticiaFilter();
		noticiasFiltrados = new ArrayList<>();
	}

	public void pesquisar() {

		noticiasFiltrados = noticias.filtrados(filtro);

	}
	
	public String adicionar(){
			return"CarrinhoCompras?faces-redirect=true";
}

	public void inicializar() {
		noticiasFiltrados = noticias.raizes();
	}

	public void excluir() {
		noticias.remover(noticiaSelecionada);
		noticiasFiltrados.remove(noticiaSelecionada);
		FacesUtil.addInfoMessage("Noticia " + noticiaSelecionada.getTitulo_noticia() + "excluída com sucesso");
	}

	public List<Noticia> getNoticiasFiltrados() {
		return noticiasFiltrados;
	}

	public NoticiaFilter getFiltro() {
		return filtro;
	}

	public Noticias getNoticias() {
		return noticias;
	}

	public Noticia getNoticiaSelecionada() {
		return noticiaSelecionada;
	}

	public void setNoticiaSelecionada(Noticia noticiaSelecionada) {
		this.noticiaSelecionada = noticiaSelecionada;
	}

}
