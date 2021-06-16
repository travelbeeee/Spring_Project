package travelbeeee.spring_core_concept.discount;

import travelbeeee.spring_core_concept.member.Grade;
import travelbeeee.spring_core_concept.member.Member;

public class FixDiscountPolicy implements DiscountPolicy {

    private int discountFixAmount = 1000;

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return discountFixAmount;
        }
        return 0;
    }
}
