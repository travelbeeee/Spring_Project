package travelbeeee.spring_core_concept.discount;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import travelbeeee.spring_core_concept.annotation.MainDiscountPolicy;
import travelbeeee.spring_core_concept.member.Grade;
import travelbeeee.spring_core_concept.member.Member;

/**
 * Primary vs Qualifier
 * => Qualifier 가 우선권을 가진다.
 */
@Component
//@Qualifier("rateDiscountPolicy") // 빈 이름이 바뀌는 것은 아니다.
@Primary // 같은 타입의 빈 중에서 얘가 우선권을 가지게 된다.
//@MainDiscountPolicy
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
