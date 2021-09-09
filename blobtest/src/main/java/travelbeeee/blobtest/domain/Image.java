package travelbeeee.blobtest.domain;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
@Getter
public class Image {

    @Id @GeneratedValue
    private Long id;

    @Lob
    private byte[] image;

    public Image() {
    }

    public Image(byte[] image) {
        this.image = image;
    }
}
