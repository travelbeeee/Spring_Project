package travelbeeee.spring_core_concept.beanfind;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import travelbeeee.spring_core_concept.bean.Bean1;
import travelbeeee.spring_core_concept.bean.Bean2;
import travelbeeee.spring_core_concept.bean.ParentBean;

import java.util.Map;

public class ApplicationContextInfoTest {

    @Configuration
    static class MyAppConfig {
        @Bean
        public ParentBean bean1() {
            return new Bean1();
        }

        @Bean
        public ParentBean bean2() {
            return new Bean2();
        }
    }

    AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(MyAppConfig.class);

    @Test
    @DisplayName("동일 타입 빈 조회")
    public void 동일한_타입_빈_조회() throws Exception{
        ParentBean bean1 = ctx.getBean("bean1", ParentBean.class);
        ParentBean bean2 = ctx.getBean("bean2", ParentBean.class);
        Assertions.assertThat(bean1.getClass()).isEqualTo(Bean1.class);
        Assertions.assertThat(bean2.getClass()).isEqualTo(Bean2.class);
        System.out.println("bean1.getClass() = " + bean1.getClass());
        System.out.println("bean2.getClass() = " + bean2.getClass());
    }

    @Test
    @DisplayName("특정타입 빈 모두 조회")
    public void 특정타입_빈_모두조회() throws Exception {
        Map<String, ParentBean> beansOfType = ctx.getBeansOfType(ParentBean.class);
        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key + " value = " + beansOfType.get(key));
        }
    }
    @Test
    @DisplayName("모든 빈 출력하기")
    public void 모든_빈_출력하기() throws Exception{
        String[] beanDefinitionNames = ctx.getBeanDefinitionNames();
        for (String beanName : beanDefinitionNames) {
            Object bean = ctx.getBean(beanName);
            System.out.println("bean.getClass() = " + bean.getClass());
        }
    }

    @Test
    @DisplayName("애플리케이션 빈 출력하기")
    public void 애플리케이션_빈_출력하기() throws Exception{
        String[] beanDefinitionNames = ctx.getBeanDefinitionNames();
        for (String beanName : beanDefinitionNames) {
            BeanDefinition beanDefinition = ctx.getBeanDefinition(beanName);
            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                Object bean = ctx.getBean(beanName);
                System.out.println("bean.getClass() = " + bean.getClass());
            }
        }
    }

    @Test
    @DisplayName("빈 이름으로 조회하기")
    public void 빈_이름으로_조회() throws Exception{
        Bean1 bean1 = ctx.getBean("bean1", Bean1.class);
        System.out.println("bean1.getClass() = " + bean1.getClass());

        Bean2 bean2 = ctx.getBean("bean2", Bean2.class);
        System.out.println("bean2.getClass() = " + bean2.getClass());
    }

    @Test
    @DisplayName("빈 타입으로 조회하기")
    public void 빈_타입으로_조회() throws Exception{
        Bean1 bean1 = ctx.getBean(Bean1.class);
        System.out.println("bean1.getClass() = " + bean1.getClass());

        Bean2 bean2 = ctx.getBean(Bean2.class);
        System.out.println("bean2.getClass() = " + bean2.getClass());
    }

    @Test
    @DisplayName("빈 설정정보 출력하기")
    public void 빈_설정정보_출력하기() throws Exception{
        String[] beanDefinitionNames = ctx.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = ctx.getBeanDefinition(beanDefinitionName);
            System.out.println("beanDefinition = " + beanDefinition);
        }
    }
}
