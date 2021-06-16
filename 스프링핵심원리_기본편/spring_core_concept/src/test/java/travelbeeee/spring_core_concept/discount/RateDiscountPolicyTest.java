package travelbeeee.spring_core_concept.discount;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import travelbeeee.spring_core_concept.AppConfig;
import travelbeeee.spring_core_concept.member.Grade;
import travelbeeee.spring_core_concept.member.Member;

import static org.junit.jupiter.api.Assertions.*;

class RateDiscountPolicyTest {
    RateDiscountPolicy rateDiscountPolicy = new RateDiscountPolicy();
    
    @Test
    @DisplayName("VIP는 10% 할인이 적용되어야 한다")
    public void VIP_성공() throws Exception{
        // given
        Member memberVIP = new Member(1L, "memberVIP", Grade.VIP);

        // when
        int discount = rateDiscountPolicy.discount(memberVIP, 10000);

        // then
        Assertions.assertThat(discount).isEqualTo(1000);
    }

    @Test
    @DisplayName("VIP가 아니면 10% 할인이 적용X")
    public void BASIC_성공() throws Exception{
        // given
        Member memberVIP = new Member(1L, "memberVIP", Grade.BASIC);

        // when
        int discount = rateDiscountPolicy.discount(memberVIP, 10000);

        // then
        Assertions.assertThat(discount).isEqualTo(0);
    }

}