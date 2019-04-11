package kubieniec.dao;

import kubieniec.model.Card;
import kubieniec.model.Image;
import kubieniec.utils.DBConnection;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class ImageDao {
    @PersistenceContext
    EntityManager em;

    private static DBConnection dbConnection = new DBConnection();

    public Card getImage(Long id) {
        return em.getReference(Card.class, id);
    }

    public static void saveImage(Image image) {
        Session session = dbConnection.getSession();
        session.beginTransaction();
        Long id = (Long) session.save(image);
        image.setId(id);
        session.getTransaction().commit();
        session.close();
    }

    public List<Image> findAll() {
        return dbConnection.getSession().createQuery("from Image").list();
    }
}
