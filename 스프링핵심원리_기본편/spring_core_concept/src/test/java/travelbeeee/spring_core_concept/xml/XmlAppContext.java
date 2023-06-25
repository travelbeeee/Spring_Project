package travelbeeee.spring_core_concept.xml;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import travelbeeee.spring_core_concept.member.MemberService;

public class XmlAppContext {

    @Test
    void xmlAppContext() {
        ApplicationContext ctx = new GenericXmlApplicationContext("appConfig.xml");

        MemberService memberService = ctx.getBean("memberService", MemberService.class);
        Assertions.assertThat(memberService).isInstanceOf(MemberService.class);
    }
}
