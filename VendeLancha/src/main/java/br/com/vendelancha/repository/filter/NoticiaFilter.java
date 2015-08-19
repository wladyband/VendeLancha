package br.com.vendelancha.repository.filter;

import java.io.Serializable;
import java.util.Date;

public class NoticiaFilter implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String titulo;
	private Date dataNoticiaDe;
	private Date dataNoticiaAte;

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Date getDataNoticiaDe() {
		return dataNoticiaDe;
	}

	public void setDataNoticiaDe(Date dataNoticiaDe) {
		this.dataNoticiaDe = dataNoticiaDe;
	}

	public Date getDataNoticiaAte() {
		return dataNoticiaAte;
	}

	public void setDataNoticiaAte(Date dataNoticiaAte) {
		this.dataNoticiaAte = dataNoticiaAte;
	}

}
