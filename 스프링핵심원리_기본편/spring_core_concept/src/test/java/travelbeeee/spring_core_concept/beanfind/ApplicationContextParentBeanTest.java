package travelbeeee.spring_core_concept.beanfind;

import static org.assertj.core.api.Assertions.*;

import java.util.Map;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import travelbeeee.spring_core_concept.bean.Bean1;
import travelbeeee.spring_core_concept.bean.Bean2;
import travelbeeee.spring_core_concept.bean.ParentBean;
import travelbeeee.spring_core_concept.member.MemberRepository;

/**
 * 스프링 특징 : 부모 빈을 조회
 * => 자식 빈이 모두 조회된다.
 */
public class ApplicationContextParentBeanTest {

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
    @DisplayName("부모 타입으로 조회 시, 자식이 둘 이상 있으면 중복 오류가 발생한다.")
    void findBeanmByParentTypeDuplicate() {
        org.junit.jupiter.api.Assertions.assertThrows(NoUniqueBeanDefinitionException.class,
            () -> ctx.getBean(ParentBean.class)
        );
    }

    @Test
    @DisplayName("부모 타입으로 조회 시, 자식이 둘 이상 있으면 빈 이름을 지정하면 된다.")
    public void 동일한_타입_빈_조회() throws Exception {
        ParentBean bean1 = ctx.getBean("bean1", ParentBean.class);
        ParentBean bean2 = ctx.getBean("bean2", ParentBean.class);
        Assertions.assertThat(bean1.getClass()).isEqualTo(Bean1.class);
        Assertions.assertThat(bean2.getClass()).isEqualTo(Bean2.class);
        System.out.println("bean1.getClass() = " + bean1.getClass());
        System.out.println("bean2.getClass() = " + bean2.getClass());
    }

    /**
     * 구체타입으로 지정하는 것은 좋지 않은 코드
     */
    @Test
    @DisplayName("특정 하위 타입과 이름으로 조회")
    public void 하위_빈_이름으로_조회() throws Exception{
        Bean1 bean1 = ctx.getBean("bean1", Bean1.class);
        System.out.println("bean1.getClass() = " + bean1.getClass());

        Bean2 bean2 = ctx.getBean("bean2", Bean2.class);
        System.out.println("bean2.getClass() = " + bean2.getClass());
    }

    /**
     * 구체타입으로 지정하는 것은 좋지 않은 코드
     */
    @Test
    @DisplayName("특정 하위 타입으로 조회하기")
    public void 하위_빈_타입으로_조회() throws Exception{
        Bean1 bean1 = ctx.getBean(Bean1.class);
        System.out.println("bean1.getClass() = " + bean1.getClass());

        Bean2 bean2 = ctx.getBean(Bean2.class);
        System.out.println("bean2.getClass() = " + bean2.getClass());
    }

    @Test
    @DisplayName("부모 타입으로 전체 조회")
    void findAllBeanByType() {
        Map<String, ParentBean> beansOfType = ctx.getBeansOfType(ParentBean.class);

        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key + " value = " + beansOfType.get(key));
        }
        System.out.println("beansOfType = " + beansOfType);

        assertThat(beansOfType.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("부모 타입으로 전체 조회 - Object")
    void findAllBeanByObjectType() {
        Map<String, Object> beansOfType = ctx.getBeansOfType(Object.class);

        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key + " value = " + beansOfType.get(key));
        }
        System.out.println("beansOfType = " + beansOfType);
    }
}
