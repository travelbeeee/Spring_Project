package hello.advanced.app.trace.strategy.code.strategy;

import lombok.extern.slf4j.Slf4j;

/**
 * 필드에 전략을 보관하는 방식
 */
@Slf4j
public class ContextV1 {

    private Strategy strategy;

    public ContextV1(Strategy strategy) {
        this.strategy = strategy;
    }

    public void execute(){
        long startTime = System.currentTimeMillis();

        // 비지니스 로직 실행
        strategy.call();
        // 비지니스 로직 종료

        long endTime = System.currentTimeMillis();
        log.info("resultTime = {}", endTime - startTime);
    }

}
