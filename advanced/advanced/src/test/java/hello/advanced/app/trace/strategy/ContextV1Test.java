package hello.advanced.app.trace.strategy;

import hello.advanced.app.trace.strategy.code.strategy.ContextV1;
import hello.advanced.app.trace.strategy.code.strategy.Strategy;
import hello.advanced.app.trace.strategy.code.strategy.StrategyLogic1;
import hello.advanced.app.trace.strategy.code.strategy.StrategyLogic2;
import hello.advanced.app.trace.template.code.AbstractTemplate;
import hello.advanced.app.trace.template.code.SubClassLogic1;
import hello.advanced.app.trace.template.code.SubClassLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ContextV1Test {

    @Test
    void contextStrategyV0(){
        logic1();
        logic2();
    }

    private void logic1(){
        long startTime = System.currentTimeMillis();

        // 비지니스 로직 실행
        log.info("비지니스 로직1 실행");
        // 비지니스 로직 종료

        long endTime = System.currentTimeMillis();
        log.info("resultTime = {}", endTime - startTime);
    }

    private void logic2(){
        long startTime = System.currentTimeMillis();

        // 비지니스 로직 실행
        log.info("비지니스 로직2 실행");
        // 비지니스 로직 종료

        long endTime = System.currentTimeMillis();
        log.info("resultTime = {}", endTime - startTime);
    }

    /**
     * 전략 패턴 사용
     */
    @Test
    void strategyV1(){
        StrategyLogic1 strategyLogic1 = new StrategyLogic1();
        ContextV1 context1 = new ContextV1(strategyLogic1);
        context1.execute();

        StrategyLogic2 strategyLogic2 = new StrategyLogic2();
        ContextV1 context2 = new ContextV1(strategyLogic2);
        context2.execute();
    }

    /**
     * 익명 내부 클래스
     */
    @Test
    void strategyV2(){
        Strategy strategy1 = new Strategy() {
            @Override
            public void call() {
                log.info("비지니스 로직1 실행");
            }
        };

        Strategy strategy2 = new Strategy() {
            @Override
            public void call() {
                log.info("비지니스 로직2 실행");
            }
        };

        ContextV1 context1 = new ContextV1(strategy1);
        ContextV1 context2 = new ContextV1(strategy2);

        context1.execute();
        context2.execute();
    }

    @Test
    void strategyV3(){
        ContextV1 context1 = new ContextV1(new Strategy() {
            @Override
            public void call() {
                log.info("비지니스 로직1 실행");
            }
        });

        ContextV1 context2 = new ContextV1( new Strategy() {
            @Override
            public void call() {
                log.info("비지니스 로직2 실행");
            }
        });

        context1.execute();
        context2.execute();
    }

    @Test
    void strategyV4(){
        ContextV1 context1 = new ContextV1(() -> log.info("비지니스 로직1 실행"));
        ContextV1 context2 = new ContextV1(() -> log.info("비지니스 로직2 실행"));

        context1.execute();
        context2.execute();
    }
}
