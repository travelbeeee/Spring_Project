package travelbeeee.spring_core_concept.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import travelbeeee.spring_core_concept.AppConfig;
import travelbeeee.spring_core_concept.member.MemberService;

public class SingletonTest {

    /**
     * 순수한 DI 컨테이너
     * - 객체를 호출할 때 마다 생성한다.
     */
    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer() {
        AppConfig appConfig = new AppConfig();

        // 1. 조회
        MemberService memberService1 = appConfig.memberService();
        MemberService memberService2 = appConfig.memberService();

        // 2. 객체 비교
        Assertions.assertThat(memberService1).isNotSameAs(memberService2);
        Assertions.assertThat(memberService1).isNotEqualTo(memberService2);
    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletonServiceTest() {
        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();

        Assertions.assertThat(singletonService1).isSameAs(singletonService2);
        Assertions.assertThat(singletonService1).isEqualTo(singletonService2);
    }

    @Test
    @DisplayName("스프링 컨테이너")
    void springContainer() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

        // 1. 조회
        MemberService memberService1 = ctx.getBean("memberService", MemberService.class);
        MemberService memberService2 = ctx.getBean("memberService", MemberService.class);

        // 2. 객체 비교
        Assertions.assertThat(memberService1).isSameAs(memberService2);
        Assertions.assertThat(memberService1).isEqualTo(memberService2);
    }
}
