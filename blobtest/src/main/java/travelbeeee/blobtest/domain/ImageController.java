package travelbeeee.blobtest.domain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ImageController {

    private final ImageRepository imageRepository;

    @GetMapping("/")
    public String main(Model model) {
        List<Image> images = imageRepository.findAll();
//
//        List<ImageDto> imageDtos = new ArrayList<>();
//        for (Image image : images) {
//            ImageDto imageDto = new ImageDto(image.getId(),Base64.encodeBase64(image.getImage()));
//            imageDtos.add(imageDto);
//        }

        model.addAttribute("images", images);

        return "index";
    }

    @RequestMapping(value = "/image/{image_id}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getImage(@PathVariable("image_id") Long imageId, @PathVariable String image_id) throws IOException {

        byte[] imageContent = imageRepository.find(imageId).getImage();//get image from DAO based on id
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity<byte[]>(imageContent, headers, HttpStatus.OK);
    }

    @GetMapping("/upload")
    public String uploadForm(Model model) {
        model.addAttribute("imageForm", new ImageForm());

        return "upload";
    }

    @PostMapping("/upload")
    public String uploadImage(@ModelAttribute ImageForm imageForm) throws IOException {
        byte[] bytes = imageForm.getImage().getBytes();

        Image image = new Image(bytes);

        imageRepository.save(image);

        return "redirect:/";
    }
}
