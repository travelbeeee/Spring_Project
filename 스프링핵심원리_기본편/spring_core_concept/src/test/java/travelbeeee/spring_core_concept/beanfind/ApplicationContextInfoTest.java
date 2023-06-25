package travelbeeee.spring_core_concept.beanfind;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import travelbeeee.spring_core_concept.AppConfig;
import travelbeeee.spring_core_concept.bean.Bean1;
import travelbeeee.spring_core_concept.bean.ParentBean;

import java.util.Map;

public class ApplicationContextInfoTest {

    AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

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
    public void 애플리케이션_빈_출력하기() throws Exception {
        String[] beanDefinitionNames = ctx.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = ctx.getBeanDefinition(beanDefinitionName);

            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                Object bean = ctx.getBean(beanDefinitionName);
                System.out.println("name = " + beanDefinitionName + " object = " + bean);
            }
        }
    }

    @Test
    @DisplayName("없는 빈 이름으로 조회하기X")
    public void 빈_이름으로_조회X() throws Exception{
        org.junit.jupiter.api.Assertions.assertThrows(NoSuchBeanDefinitionException.class,
            () -> ctx.getBean("NoBeanName", Bean1.class)
        );
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
