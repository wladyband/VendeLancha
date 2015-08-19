package br.com.vendelancha.util.jsf;

import java.io.IOException;
import java.util.Iterator;

import javax.faces.FacesException;
import javax.faces.application.ViewExpiredException;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;

import br.com.vendelancha.service.NegocioException;

//Essa classe é uma classe de tratador de exeções 

public class JsfExceptionHandler extends ExceptionHandlerWrapper {

	// isso é tratador de exceções impacotados
	// O FrameWorks JSF já tem um tratador de exceção, e o que é feito nessa
	// classe
	// é impacotar do tratador de exceção do JSF.
	// Com essa classe ele trata todos tipo de exceção que você imaginar

	private ExceptionHandler wrapped;

	public JsfExceptionHandler(ExceptionHandler wrapped) {
		this.wrapped = wrapped;
	}

	@Override
	public ExceptionHandler getWrapped() {

		return this.wrapped;// e aqui retorna a variavel de instancia.
	}

	// esse método é chamado quando existe uma exceção.

	// OBS O FrameWorks JSF vai instancia a classe JsfExceptionHandler e depois
	// vai chamar o método handle.

	@Override
	public void handle() throws FacesException {
		// nesse pedaço do código ele pega todos os eventos infilerados com
		// método getUnhandledExceptionQueuedEvents()
		// e adiciona na variavel "events" que do tipo eventos de execeção =
		// ExceptionQueuedEvent.
		Iterator<ExceptionQueuedEvent> events = getUnhandledExceptionQueuedEvents()
				.iterator();

		// então while pegar a fila de eventos
		while (events.hasNext()) {

			// e aqui ele pega o proximo da fila de eventos.
			ExceptionQueuedEvent event = events.next();

			// aqui ele pega a origem da exceção, que é a exceção
			// contextualizado.
			ExceptionQueuedEventContext context = (ExceptionQueuedEventContext) event
					.getSource();

			// O método getException() ajuda a pegar a exceção especifica que
			// será representada pela
			// variavel context e diz que ela é uma Throwable, e o Throwable é
			// pai de todas
			// as exceções, na verdade essa linha de código é muito importante
			// porque ela
			// efetiva a ação de pegar qualquer tipo de exceção
			Throwable exception = context.getException();
			// ************************************************//*******************************************************//

			// Esse é o pedaço mais importante da classe, porque a gente já
			// pegou a exceção que é
			// a variavel "exception", e o que será feito com essa exceção?
			// Quando
			// receber algo iremos
			// retorna um email para alguém? E ao pegar a exeção jogaremos em um
			// arquivo de log, ou
			// receberemos um valor e retornaremos uma notificação para o
			// usuário?

			// Então o tratamento que é feito aqui é

			// o que eu quero é direcionar o usuário para a pagina inicial caso
			// tenha uma exceção do tipo
			// ViewExpiredException que seria qualquer tipo de tela de erro
			// apresentada para o usuário.

			// entrada para uma nova regra de exceção
			NegocioException negocioException = getNegocioException(exception);/* <==== */
																					/* || */
																					/* || */
			boolean handled = false; 												/* || */
																					/* || */
			try { 																	/* || */
				if (exception instanceof ViewExpiredException) {					/* || */
					redirect("/"); 													/* || */
				// seria uma mensagem para regra de negocios		  				/* || */
				} else if (negocioException != null) {/* <=============================  = */
					handled = true;
					FacesUtil.addErrorMessage(negocioException.getMessage());
				} else {
					// se não for um tipo ViewExpiredException redirecione para
					// a pagina de Erro.xhtml.
					handled = true;
					redirect("/Erro.xhtml");

				}
			} finally {
				if (handled) {
					events.remove();
				}
			}
		}
		getWrapped().handle();
	}

	private NegocioException getNegocioException(Throwable exception) {
		if (exception instanceof NegocioException) {
			return (NegocioException) exception;
		} else if (exception.getCause() != null) {
			return getNegocioException(exception.getCause());
		}

		return null;
	}

	private void redirect(String page) {
		try {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			ExternalContext externalContext = facesContext.getExternalContext();
			String contextPath = externalContext.getRequestContextPath();

			externalContext.redirect(contextPath + page);
			facesContext.responseComplete();
		} catch (IOException e) {
			throw new FacesException(e);
		}
	}

	// depois vamos criar uma fabrica de handler;

}
