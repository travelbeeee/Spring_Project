package travelbeeee.spring_core_concept.order;

import travelbeeee.spring_core_concept.discount.DiscountPolicy;
import travelbeeee.spring_core_concept.discount.FixDiscountPolicy;
import travelbeeee.spring_core_concept.discount.RateDiscountPolicy;
import travelbeeee.spring_core_concept.member.Member;
import travelbeeee.spring_core_concept.member.MemberRepository;
import travelbeeee.spring_core_concept.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
