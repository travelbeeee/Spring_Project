package hello.advanced.app.trace.strategy;

import hello.advanced.app.trace.strategy.code.template.Callback;
import hello.advanced.app.trace.strategy.code.template.TimeLogTemplate;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class TemplateCallbackText {

    /**
     * 템플릿 콜백 패턴 - 익명 내부 클래스
     */
    @Test
    void callbackV1(){
        TimeLogTemplate timeLogTemplate = new TimeLogTemplate();
        timeLogTemplate.execute(new Callback() {
            @Override
            public void call() {
                log.info("비지니스 로직1 실행");
            }
        });

        timeLogTemplate.execute(new Callback() {
            @Override
            public void call() {
                log.info("비지니스 로직2 실행");
            }
        });
    }

    /**
     * 템플릿 콜백 패턴 - 람다
     */
    @Test
    void callbackV2(){
        TimeLogTemplate timeLogTemplate = new TimeLogTemplate();
        timeLogTemplate.execute(() -> log.info("비지니스 로직1 실행"));
        timeLogTemplate.execute(() -> log.info("비지니스 로직2 실행"));
    }
}
