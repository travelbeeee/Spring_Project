package hello.proxy.postprocessor;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
public class BeanPostProcessorTest {

    @Test
    void basicConfig(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(BeanPostProcessorConfig.class);

        // beanA 이름으로 B 객체가 빈으로 등록된다.
        B b = context.getBean("beanA", B.class);
        b.helloB();

        // A는 빈으로 등록되지 않는다.
        Assertions.assertThrows(NoSuchBeanDefinitionException.class, () -> context.getBean(A.class));
    }

    @Configuration
    static class BeanPostProcessorConfig {
        @Bean(name = "beanA")
        public A a(){
            return new A();
        }

        @Bean
        public AtoBPostProcessor helloPostProcessor(){
            return new AtoBPostProcessor();
        }
    }

    static class A{
        public void helloA(){
            log.info("hello A");
        }
    }

    static class B{
        public void helloB(){
            log.info("hello B");
        }
    }

    static class AtoBPostProcessor implements BeanPostProcessor {

        @Override
        public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
            log.info("beanName = {}, bean = {}", beanName, bean);
            if (bean instanceof A) {
                return new B();
            }
            return bean;
        }
    }
}
