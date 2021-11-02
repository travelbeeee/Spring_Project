package hello.advanced.app.trace.threadlocal;

import hello.advanced.app.trace.threadlocal.code.FieldService;
import hello.advanced.app.trace.threadlocal.code.ThreadLocalService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ThreadLocalServiceTest {

    private ThreadLocalService fieldService = new ThreadLocalService();

    @Test
    void field(){
        log.info("main Start");

        Runnable userA = () -> {
            fieldService.logic("userA");
        };

        Runnable userB = () -> {
            fieldService.logic("userB");
        };

        Thread threadA = new Thread(userA);
        threadA.setName("thread-A");

        Thread threadB = new Thread(userB);
        threadB.setName("thread-B");

        threadA.start();
        sleep(100);
        threadB.start();
        sleep(2000);

        log.info("main Exit");
    }

    private void sleep(int milias) {
        try {
            Thread.sleep(milias);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
