package br.com.vendelancha.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.vendelancha.model.Noticia;
import br.com.vendelancha.repository.filter.NoticiaFilter;
import br.com.vendelancha.service.NegocioException;
import br.com.vendelancha.util.jpa.Transactional;

public class Noticias implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	@SuppressWarnings("unchecked")
	public List<Noticia> filtrados(NoticiaFilter filtro) {
		Session session = this.manager.unwrap(Session.class);

		Criteria criteria = session.createCriteria(Noticia.class);

		if (StringUtils.isNotBlank(filtro.getTitulo())) {
			criteria.add(Restrictions.ilike("titulo_noticia", filtro.getTitulo(), MatchMode.ANYWHERE));
		}

		if (filtro.getDataNoticiaDe() != null) {
			criteria.add(Restrictions.ge("data_noticia", filtro.getDataNoticiaDe()));
		}

		if (filtro.getDataNoticiaAte() != null) {
			criteria.add(Restrictions.le("data_noticia", filtro.getDataNoticiaAte()));
		}

		return criteria.addOrder(Order.asc("titulo_noticia")).list();
	}

	public Noticia guardar(Noticia noticia) {
		return manager.merge(noticia);

	}

	public Noticia porId(Long id) {

		return manager.find(Noticia.class, id);
	}

	@Transactional
	public void remover(Noticia noticia) {
		try {
			noticia = porId(noticia.getId());
			manager.remove(noticia);
			manager.flush();
		} catch (PersistenceException e) {
			throw new NegocioException("Produto não pode ser excluído.");
		}
	}
	
	
	
	public List<Noticia> raizes() {
		return manager.createQuery("from Noticia", 
				Noticia.class).getResultList();
	}


}
