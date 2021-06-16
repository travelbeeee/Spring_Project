package travelbeeee.spring_core_concept.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import travelbeeee.spring_core_concept.AppConfig;

public class MemberServiceTest {

    AppConfig appConfig = new AppConfig();
    MemberService memberService = appConfig.memberService();

    @Test
    public void test() throws Exception{
        // given
        Member memberA = new Member(1L, "memberA", Grade.VIP);

        // when
        memberService.join(memberA);
        Member findMember = memberService.findMember(1L);

        // then
        Assertions.assertThat(memberA).isEqualTo(findMember);
    }
}
