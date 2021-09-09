package travelbeeee.blobtest.domain;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ImageForm {

    private MultipartFile image;

    public ImageForm() {
    }
}
