package kubieniec;

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

    public String saveImageFileFromUrl(String urlString, Long our_id) {
        BufferedImage image = null;
        URL url = null;
        String fileTitle = "";
        try {
            url = new URL(urlString);
            image = ImageIO.read(url);

            fileTitle = urlString.substring(8);
            fileTitle = fileTitle.replaceAll("/", "_").replaceAll("\\.", "")
                    .replaceAll("\\?", "");

            if (!Files.exists(Paths.get("images/" + our_id))) {
                new File("images/" + our_id).mkdirs();
            }

            ImageIO.write(image, "jpg", new File("images/" + our_id + "/" + fileTitle + ".jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fileTitle;
    }


}
