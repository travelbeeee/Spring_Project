package travelbeeee.spring_core_concept.singleton;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import travelbeeee.spring_core_concept.AppConfig;

public class ConfigurationSingletonTest {

    @Test
    void configurationDeep() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        AppConfig bean = ac.getBean(AppConfig.class);

        /**
         * 출력 결과
         * - bean.getClass() = class travelbeeee.spring_core_concept.AppConfig$$EnhancerBySpringCGLIB$$d43d0978
         * => 스프링에서 CGLIB 를 통해 바이트 코드를 조작해서 AppConfig 클래스가 아닌 스프링이 조작한 클래스가 등록된다.
         */
        System.out.println("bean.getClass() = " + bean.getClass());
    }
}
