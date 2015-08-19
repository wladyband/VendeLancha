package br.com.vendelancha.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;

import br.com.vendelancha.model.Noticia;
import br.com.vendelancha.service.CadastroNoticiaService;
import br.com.vendelancha.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroNoticiaBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Noticia noticia;
	private Part arquivo;
	private String nomeArquivoSaida;
	private String tipoArquivo;

	@Inject
	private CadastroNoticiaService cadastroNoticiaService;

	public CadastroNoticiaBean() {
		limpar();
	}

	public void limpar() {
		noticia = new Noticia();

	}

	public void salvar() {
		upload();
		limpar();
	}

	
	
	
	public void upload() {

		// http://www.guj.com.br/28536-salvar-imagem-na-pasta-resources-do-maven-
		//http://www.guj.com.br/java/278340-exibir-imagem-em-pgraphicimage
		
		
		nomeArquivoSaida = "C:/workspace Web/Projetos Profissionais/Fotos para teste/" + arquivo.getSubmittedFileName();

		tipoArquivo = arquivo.getContentType();

		if (tipoArquivo.equals("image/jpeg") ||tipoArquivo.equals("image/png")) {

			try (InputStream is = arquivo.getInputStream(); OutputStream out = new FileOutputStream(nomeArquivoSaida)) {

				int read = 0;
				byte[] bytes = new byte[1024];

				while ((read = is.read(bytes)) != -1) {
					out.write(bytes, 0, read);
				}
				//salvar imagem no banco
				this.noticia.setFoto_noticia(getNomeArquivoSaida());
				
				//salvar os outros atributos de noticias no banco
				this.noticia = cadastroNoticiaService.salvar(this.noticia);
				FacesUtil.addInfoMessage("Noticia salva com sucesso! ");

			} catch (IOException e) {
				FacesUtil.addErrorMessage("Erro ao enviar arquivo.");
			}
			

		}else{
			FacesUtil.addErrorMessage("É aceito somente upload de arquivos com essas extensões JPEG,PNG");
		}
	}

	public Noticia getNoticia() {
		return noticia;
	}
	
	public void setNoticia(Noticia noticia) {
		this.noticia = noticia;
	}

	public Part getArquivo() {
		return arquivo;
	}

	public void setArquivo(Part arquivo) {
		this.arquivo = arquivo;
	}

	public String getNomeArquivoSaida() {

		return nomeArquivoSaida;
	}

	public void setNomeArquivoSaida(String nomeArquivoSaida) {
		this.nomeArquivoSaida = nomeArquivoSaida;
	}

}
