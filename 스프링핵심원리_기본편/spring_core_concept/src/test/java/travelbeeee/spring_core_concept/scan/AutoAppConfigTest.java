package travelbeeee.spring_core_concept.scan;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import travelbeeee.spring_core_concept.AutoAppConfig;
import travelbeeee.spring_core_concept.member.MemberService;

public class AutoAppConfigTest {

    @Test
    void basicScan() {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AutoAppConfig.class);

        MemberService memberService = ctx.getBean(MemberService.class);
        Assertions.assertThat(memberService).isInstanceOf(MemberService.class);
    }
}
