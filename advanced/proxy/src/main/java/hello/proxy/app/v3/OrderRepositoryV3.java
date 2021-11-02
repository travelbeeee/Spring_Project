package hello.proxy.app.v3;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

@Repository
public class OrderRepositoryV3 {
    public void save(String itemId) {
        if (itemId.equals("ex")) {
            throw new IllegalStateException("예외 발생!");
        }
        sleep(1000);
    }

    private void sleep(int milias) {
        try {
            Thread.sleep(milias);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
