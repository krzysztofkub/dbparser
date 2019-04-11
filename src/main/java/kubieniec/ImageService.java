package kubieniec;

import kubieniec.model.Card;
import kubieniec.model.Image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ImageService {

    public void saveImageFileFromCard(Card card) {
        for (Image image : card.getImages()) {
            try {
                URL url = new URL(image.getUrl());
                BufferedImage bufferedImage = ImageIO.read(url);


                if (!Files.exists(Paths.get("images/" + card.getId()))) {
                    new File("images/" + card.getId()).mkdirs();
                }

                ImageIO.write(bufferedImage, "jpg", new File("images/" + card.getId() + "/" + image.getFileName() + ".jpg"));
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
