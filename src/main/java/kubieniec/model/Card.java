package kubieniec.model;


import lombok.Data;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cards")
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Card {
    @Id
    private String id;
    private String oracle_id;
    private String multiiverse_ids;
    private String tcgplayer_id;
    private String name;
    private String lang;
    private String released_at;
    private String uri;
    private String scryfall_uri;
    private String layout;
    private boolean highres_image;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL
            ,mappedBy = "card"
            ,fetch = FetchType.EAGER)
    private List<Image> images = new ArrayList<>();
    private String mana_cost;
    private String cmc;
    private String type_line;
    @Column(length = 10000)
    private String oracle_text;
    private String power;
    private String toughness;
    @JsonIgnore
    private String colors;
    @JsonIgnore
    private String color_identity;
    @JsonIgnore
    private String all_parts;
    @JsonIgnore
    private String legalities;
    @JsonIgnore
    private String games;
    private boolean reversed;
    private boolean foil;
    private boolean nonfoil;
    private boolean oversized;
    private boolean promo;
    private boolean reprint;
    private String set_something;
    private String set_name;
    private String set_uri;
    private String set_search_uri;
    private String scryfall_set_uri;
    private String rulings_uri;
    private String print_search_uri;
    private String collector_number;
    private boolean digital;
    private String rarity;
    private String illustration_id;
    private String artist;
    private String border_color;
    private String frame;
    private boolean full_art;
    private boolean story_spotlight;
    @JsonIgnore
    private String related_uris;

}
