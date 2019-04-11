package kubieniec;

import kubieniec.dao.ImageDao;
import kubieniec.model.Image;

public class Main {
    public static void main(String[] args) {
        Serializer serializer = new Serializer();
        serializer.readFile();


//        ImageService imageService = new ImageService();
//        ImageDao imageDao = new ImageDao();
//        System.out.println( imageDao.findAll());
//        for (Image image : imageDao.findAll()) {
//            imageService.saveImageFileFromUrl(image.getUrl(),image.getCard().getOur_id());
//        }
//        System.out.println( imageDao.findAll());
    }
}
