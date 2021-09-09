package travelbeeee.blobtest.domain;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

@Data
public class ImageDto {

    public Long id;

    public byte[] image;

    public ImageDto(Long id, byte[] image) {
        this.id = id;
        this.image = image;
    }
}
