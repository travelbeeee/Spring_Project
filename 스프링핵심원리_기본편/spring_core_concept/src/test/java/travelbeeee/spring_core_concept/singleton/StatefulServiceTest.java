package travelbeeee.spring_core_concept.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class StatefulServiceTest {

    static class TestConfig {
        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }

    @Test
    @DisplayName("Stateful 서비스 테스트")
    void statefulServiceSingleton() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(TestConfig.class);

        StatefulService statefulService1 = ctx.getBean("statefulService", StatefulService.class);
        StatefulService statefulService2 = ctx.getBean("statefulService", StatefulService.class);

        // A 사용자 주문 10,000
        statefulService1.order("userA", 10000);

        // B 사용자 주문 20,000
        statefulService2.order("userB", 20000);

        int price1 = statefulService1.getPrice();
        int price2 = statefulService2.getPrice();

        Assertions.assertThat(price1).isEqualTo(price2);
    }

}
