package travelbeeee.spring_core_concept.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import travelbeeee.spring_core_concept.discount.DiscountPolicy;
import travelbeeee.spring_core_concept.discount.FixDiscountPolicy;
import travelbeeee.spring_core_concept.discount.RateDiscountPolicy;
import travelbeeee.spring_core_concept.member.Member;
import travelbeeee.spring_core_concept.member.MemberRepository;
import travelbeeee.spring_core_concept.member.MemoryMemberRepository;

@Service
public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    @Autowired
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
