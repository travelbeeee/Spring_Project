package travelbeeee.spring_core_concept.discount;

import travelbeeee.spring_core_concept.member.Member;

public interface DiscountPolicy {
    /**
     * @return 할인 대상 금액
     */
    int discount(Member member, int price);
}
