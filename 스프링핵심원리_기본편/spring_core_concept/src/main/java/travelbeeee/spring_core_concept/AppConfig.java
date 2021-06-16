package travelbeeee.spring_core_concept;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import travelbeeee.spring_core_concept.discount.DiscountPolicy;
import travelbeeee.spring_core_concept.discount.RateDiscountPolicy;
import travelbeeee.spring_core_concept.member.MemberRepository;
import travelbeeee.spring_core_concept.member.MemberService;
import travelbeeee.spring_core_concept.member.MemberServiceImpl;
import travelbeeee.spring_core_concept.member.MemoryMemberRepository;
import travelbeeee.spring_core_concept.order.OrderService;
import travelbeeee.spring_core_concept.order.OrderServiceImpl;

@Configuration
public class AppConfig {

    @Bean
    public MemberService memberService(){
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public OrderService orderService(){
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }

    @Bean
    public DiscountPolicy discountPolicy(){
        return new RateDiscountPolicy();
    }
}
