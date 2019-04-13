package kubieniec;

import kubieniec.dao.CardDao;
import kubieniec.dao.ImageDao;
import kubieniec.model.Card;
import kubieniec.model.Image;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Serializer serializer = new Serializer();
        serializer.readFile();

//        List<Card> cards = CardDao.findAll();
//
//        ImageService imageService = new ImageService();
//        cards.forEach(imageService::saveImageFileFromCard);
    }
}
