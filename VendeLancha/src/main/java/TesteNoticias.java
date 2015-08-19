import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import br.com.vendelancha.model.Noticia;

public class TesteNoticias {
	public static void main(String[] args) {

		EntityManagerFactory factory = Persistence.createEntityManagerFactory("EmbarcacaoPU");
		EntityManager manager = factory.createEntityManager();
		
		EntityTransaction trx = manager.getTransaction();
		trx.begin();
		
		Noticia noticia = new Noticia();
		
		noticia.setData_noticia(new Date());
		noticia.setDesc_noticia("noticias no ar");
		noticia.setPreco(new BigDecimal(23.3));
		
		manager.persist(noticia);
		trx.commit();
	}
}
