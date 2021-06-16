package travelbeeee.spring_core_concept.order;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import travelbeeee.spring_core_concept.AppConfig;
import travelbeeee.spring_core_concept.member.Grade;
import travelbeeee.spring_core_concept.member.Member;
import travelbeeee.spring_core_concept.member.MemberService;
import travelbeeee.spring_core_concept.member.MemberServiceImpl;

public class OrderServiceTest {
    AppConfig appConfig = new AppConfig();
    MemberService memberService = appConfig.memberService();
    OrderService orderService = appConfig.orderService();

    @Test
    public void test() throws Exception{
        // given
        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        // when
        Order order = orderService.createOrder(member.getId(), "itemA", 10000);

        //then
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}
