package kubieniec.dao;

import kubieniec.utils.DBConnection;
import kubieniec.model.Card;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class CardDao {

    @PersistenceContext
    EntityManager em;

    private static DBConnection dbConnection = new DBConnection();

    public Card getCard(String id) {
        return em.getReference(Card.class, id);
    }

    public static void saveCard(Card card) {
        Session session = dbConnection.getSession();
        session.beginTransaction();
        session.saveOrUpdate(card);
        session.getTransaction().commit();
        session.close();
    }
}
