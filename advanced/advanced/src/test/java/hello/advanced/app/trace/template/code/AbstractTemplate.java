package hello.advanced.app.trace.template.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractTemplate {

    public void execute(){
        long startTime = System.currentTimeMillis();

        // 비지니스 로직 실행
        call();
        // 비지니스 로직 종료

        long endTime = System.currentTimeMillis();
        log.info("resultTime = {}", endTime - startTime);
    }

    protected abstract  void call();
}
