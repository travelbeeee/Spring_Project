package travelbeeee.spring_core_concept.beandefinition;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import travelbeeee.spring_core_concept.AppConfig;

/**
 * Annotation 기반, Xml 기반 혹은 다른 정보 기반이던
 * 모두 BeanDefinition 추상화를 만들고 이걸 이용해서 스프링 컨테이너에 빈을 등록한다.
 */
public class BeanDefinitionTest {

//     AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
    GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("appConfig.xml");

     @Test
     @DisplayName("빈 설정 메타정보 확인")
     void findApplicationBean() {
         String[] beanDefinitionNames = ctx.getBeanDefinitionNames();
         for (String beanDefinitionName : beanDefinitionNames) {
             BeanDefinition beanDefinition = ctx.getBeanDefinition(beanDefinitionName);

             if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                 System.out.println("beanDefinitionName = " + beanDefinitionName +
                     " beanDefinition = " + beanDefinition);
             }
         }
     }
}
