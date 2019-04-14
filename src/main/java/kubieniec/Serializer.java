package kubieniec;

import com.sun.deploy.util.StringUtils;
import kubieniec.dao.CardDao;
import kubieniec.model.Card;
import kubieniec.model.Image;
import kubieniec.model.SizeEnum;
import org.abego.treelayout.internal.util.java.lang.string.StringUtil;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Serializer {

    public void readFile() {
        try (FileReader reader = new FileReader("C:\\Users\\krzys\\IdeaProjects\\dbparser\\scryfall-default-cards.json")) {
            JSONParser parser = new JSONParser();
            JSONArray arr = (JSONArray) parser.parse(reader);

            int counter = 0;

            long cardRows = CardDao.count();

            for (Object o : arr) {
                System.out.println(counter);
                if (counter > cardRows) {
                    JSONObject cardJson = (JSONObject) o;
                    parse(cardJson);
                }
                counter++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void parse(JSONObject cardJson) {
        System.out.println(cardJson.get("id"));
        ObjectMapper mapper = new ObjectMapper();
        String jsonStriing = cardJson.toJSONString();
        try {
            Card card = mapper.readValue(jsonStriing, Card.class);

            JSONArray arr = (JSONArray) cardJson.get("colors");
            if (arr != null) {
                String string = arr.toJSONString();
                card.setColors(string);
            }

            arr = (JSONArray) cardJson.get("color_identity");
            if (arr != null) {
                String string = arr.toJSONString();
                card.setColor_identity(string);
            }

            arr = (JSONArray) cardJson.get("all_parts");
            if (arr != null) {
                String string = arr.toJSONString();
                card.setAll_parts(string);
            }

            Map<String, String> map = (Map) cardJson.get("legalities");
            if (map != null) {
                Set<String> set = map.keySet();
                StringBuilder sb = new StringBuilder();
                set.stream().forEach(key -> sb.append(key + ":" + map.get(key)));
                String legalities = sb.toString();
                if (!legalities.isEmpty()) {
                    card.setLegalities(legalities);
                }
            }

            arr = (JSONArray) cardJson.get("games");
            if (arr != null) {
                String string = arr.toJSONString();
                card.setGames(string);
            }

            Map imagesMap = (Map) cardJson.get("image_uris");
            if (imagesMap != null) {
                List<Image> images = parseImages(imagesMap);
                images.stream().forEach(image -> {
                    image.setCard(card);
                    card.setImages(images);
                });
            }

            CardDao.saveCard(card);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Image> parseImages(Map<String, String> imagesMap) {
        List<Image> images = new ArrayList<>();
        imagesMap.keySet().stream().map(key -> new Image(getImageSize(key), imagesMap.get(key))).forEach(images::add);
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