package kubieniec;

import kubieniec.dao.CardDao;
import kubieniec.model.Card;
import kubieniec.model.Image;
import kubieniec.model.SizeEnum;
import org.codehaus.jackson.map.ObjectMapper;
import org.hibernate.metamodel.model.relational.spi.Size;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.sym.error;

public class Serializer {

    public void readFile() {
        try (FileReader reader = new FileReader("C:\\Users\\krzys\\IdeaProjects\\dbparser\\test.json")) {
            JSONParser parser = new JSONParser();
            JSONArray arr = (JSONArray) parser.parse(reader);
            for (Object o : arr) {
                JSONObject cardJson = (JSONObject) o;
                parse(cardJson);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void parse(JSONObject cardJson) {
        ObjectMapper mapper = new ObjectMapper();
        String string = cardJson.toJSONString();
        try {
            Card card = mapper.readValue(string, Card.class);

            Map imagesMap = (Map) cardJson.get("image_uris");
            List<Image> images = parseImages(imagesMap);

            //TODO

            card.setImages(images);


            CardDao.saveCard(card);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private List<Image> parseImages(Map<String, String> imagesMap) {
        List<Image> images = new ArrayList<>();
        imagesMap.keySet().stream().map(key -> new Image(getImageSize(key.toString()), imagesMap.get(key.toString()))).forEach(images::add);
        return images;
    }

    private SizeEnum getImageSize(String size) {
        SizeEnum sizeEnum;
        switch (size) {
            case "small":
                sizeEnum = SizeEnum.SMALL;
                break;
            case "normal":
                sizeEnum = SizeEnum.NORMAL;
                break;
            case "large":
                sizeEnum = SizeEnum.LARGE;
                break;
            case "png":
                sizeEnum = SizeEnum.PNG;
                break;
            case "art_crop":
                sizeEnum = SizeEnum.ART_CROP;
                break;
            case "border_crop":
                sizeEnum = SizeEnum.BORDER_CROP;
                break;
            default:
                sizeEnum = null;
                break;
        }
        return sizeEnum;
    }

}