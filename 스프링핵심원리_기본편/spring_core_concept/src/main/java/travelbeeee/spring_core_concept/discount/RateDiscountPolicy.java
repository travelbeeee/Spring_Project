package travelbeeee.spring_core_concept.discount;

import travelbeeee.spring_core_concept.member.Grade;
import travelbeeee.spring_core_concept.member.Member;

public class RateDiscountPolicy implements DiscountPolicy {

    private int discountPercent = 10;
    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP){
            return price * discountPercent / 100;
        }
        return 0;
    }
}
