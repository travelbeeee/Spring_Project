package travelbeeee.spring_core_concept.discount;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import travelbeeee.spring_core_concept.member.Grade;
import travelbeeee.spring_core_concept.member.Member;

@Component
@Qualifier("fixDiscountPolicy") // 빈 이름이 바뀌는 것은 아니다.
public class FixDiscountPolicy implements DiscountPolicy {

    private int discountFixAmount = 2000;

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return discountFixAmount;
        }
        return 0;
    }
}
