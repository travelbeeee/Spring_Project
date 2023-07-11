package travelbeeee.spring_core_concept.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import travelbeeee.spring_core_concept.annotation.MainDiscountPolicy;
import travelbeeee.spring_core_concept.discount.DiscountPolicy;
import travelbeeee.spring_core_concept.member.Member;
import travelbeeee.spring_core_concept.member.MemberRepository;

@Service
public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    /**
     * 같은 타입의 빈이 N개 일 때, 파라미터 혹은 필드명으로 빈 이름을 입력해주면 된다.
     * - ex) DiscountPolicy rateDiscountPolicy
     */
//    @Autowired
//    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy rateDiscountPolicy) {
//        this.memberRepository = memberRepository;
//        this.discountPolicy = rateDiscountPolicy;
//    }

    /**
     * @Qualifier 를 이용해서 해결할 수도 있다.
     * - @Qualifier 지정된 빈이 없다면, 해당 값으로 등록된 빈을 찾는다.
     *  ex) @Qualifier("main") => @Qualifier("main") 이 붙은 빈을 찾는다. => main 이라는 이름으로 등록된 빈을 찾는다.
     */
//    @Autowired
//    public OrderServiceImpl(MemberRepository memberRepository, @Qualifier("mainDiscountPolicy") DiscountPolicy discountPolicy) {
//        this.memberRepository = memberRepository;
//        this.discountPolicy = discountPolicy;
//    }

    /**
     * 직접 애노테이션을 만들어서 우선권 주기
     */
//    @Autowired
//    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
//        this.memberRepository = memberRepository;
//        this.discountPolicy = discountPolicy;
//    }

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
