package travelbeeee.spring_core_concept;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

/**
 * 컴포넌트 스캔 디폴트
 * - 해당 클래스가 있는 패키지 하위가 모두 자동으로 컴포넌트 스캔의 대상이 된다.
 * - 스프링부트에서는 @SpringBootApplication 에 포함되어있다.
 */
@Configuration
@ComponentScan(
//    basePackages = "travelbeeee.spring_core_concept", 지정한 경로부터 컴포넌트 스캔 시작
//    basePackageClasses = AutoAppConfig.class, 지정한 클래스부터 컴포넌트 스캔 시작
    excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

}
