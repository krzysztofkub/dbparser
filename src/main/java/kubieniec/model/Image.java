package kubieniec.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "images")
@Data
public class Image {
    @Id
    @GeneratedValue
    private Long id;
    private SizeEnum size;
    private String url;
    @Column(unique = true)
    private String fileName;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Card card;

    public Image(SizeEnum size, String url) {
        this.size = size;
        this.url = url;
        this.fileName = url.substring(8).replaceAll("/", "_").replaceAll("\\.", "")
                .replaceAll("\\?", "");
    }

    public Image() {
    }
}
