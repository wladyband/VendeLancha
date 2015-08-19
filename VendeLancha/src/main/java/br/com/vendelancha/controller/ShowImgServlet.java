package br.com.vendelancha.controller;

import java.io.IOException;

import javax.media.jai.JAI;
import javax.media.jai.RenderedOp;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.media.jai.codec.ImageCodec;
import com.sun.media.jai.codec.ImageEncoder;

public class ShowImgServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ShowImgServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("image/jpeg");
		ServletOutputStream outimg = response.getOutputStream();

		String nomeFoto = request.getParameter("nomeFoto");
		String caminho = "";// Aqui você coloca o caminho do diretório onde
							// as imagens estão sendo armazenadas no
							// servidor
		String foto = caminho + nomeFoto;

		RenderedOp src = JAI.create("fileload", foto);
		ImageEncoder encoder = ImageCodec.createImageEncoder("JPEG", outimg, null);
		encoder.encode(src);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}
}