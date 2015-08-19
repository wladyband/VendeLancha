package br.com.vendelancha.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.vendelancha.model.Noticia;
import br.com.vendelancha.repository.Noticias;
import br.com.vendelancha.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Noticia.class)
public class NoticiaConverter implements Converter {

	private Noticias noticias;

	public NoticiaConverter() {
		noticias = CDIServiceLocator.getBean(Noticias.class);
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Noticia retorno = null;

		if (value != null) {
			Long id = new Long(value);
			retorno = noticias.porId(id);
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Noticia noticia = (Noticia) value;
			return noticia.getId() == null ? null : noticia.getId().toString();

		}

		return "";
	}

}
