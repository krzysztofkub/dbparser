package kubieniec.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "images")
@Data
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private SizeEnum size;
    @Column(nullable = false)
    private String url;
    @Column(nullable = false, unique = true)
    private String fileName;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(nullable = false)
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
