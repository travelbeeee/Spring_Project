package travelbeeee.exception.servlet;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class ServletExController {

    @GetMapping("/error/404")
    public void error404(HttpServletResponse response) throws IOException {
        response.sendError(404);
    }

    @GetMapping("/error/500")
    public void error500(HttpServletResponse response) throws IOException {
        response.sendError(500);
    }

    @GetMapping("/error/ex")
    public void errorEx(HttpServletResponse response) throws IOException {
        throw new RuntimeException("예외 발생!");
    }
}
