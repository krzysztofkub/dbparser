package kubieniec.dao;

import kubieniec.model.Image;
import kubieniec.utils.DBConnection;
import kubieniec.model.Card;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

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

    public static List<Card> findAll() {
        List cards = null;
        Session session = dbConnection.getSession();
        session.beginTransaction();
        cards = dbConnection.getSession().createQuery("from Card").list();
        session.getTransaction().commit();
        session.close();
        return cards;
    }

    public static long count() {
        Session session = dbConnection.getSession();
        long count = (long) session.createQuery("select count(*) from cards").uniqueResult();
        session.getTransaction().commit();
        session.close();
        return count;
    }
}
