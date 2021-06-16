package travelbeeee.spring_core_concept;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import travelbeeee.spring_core_concept.member.Grade;
import travelbeeee.spring_core_concept.member.Member;
import travelbeeee.spring_core_concept.member.MemberService;
import travelbeeee.spring_core_concept.member.MemberServiceImpl;
import travelbeeee.spring_core_concept.order.Order;
import travelbeeee.spring_core_concept.order.OrderService;
import travelbeeee.spring_core_concept.order.OrderServiceImpl;

public class OrderApp {

    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberService memberService = ctx.getBean(MemberService.class);
        OrderService orderService = ctx.getBean(OrderService.class);

        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);

        System.out.println("order = " + order);
    }
}
