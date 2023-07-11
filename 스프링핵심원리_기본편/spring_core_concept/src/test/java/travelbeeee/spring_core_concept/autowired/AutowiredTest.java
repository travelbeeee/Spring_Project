package travelbeeee.spring_core_concept.autowired;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import travelbeeee.spring_core_concept.member.Member;

public class AutowiredTest {

    @Test
    void autowiredOption() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
    }

    static class TestBean {
        // 자동 주입할 대상이 없으면 수정자 메서드 자체가 호출 안된다.
        @Autowired(required = false)
        public void setNoBean1(Member member) {
            // Member 는 Spring Bean 이 아니라 호출이 안된다.
            System.out.println("member1 = " + member);
        }

        // 자동 주입할 대상이 없으면 Null 이 입력된다.
        @Autowired
        public void setNoBean2(@Nullable Member member) {
            // Member 는 Spring Bean 이 아니라 호출이 안된다.
            System.out.println("member2 = " + member);
        }

        // 자동 주입할 대상이 없으면 Optional.empty 가 입력된다.
        @Autowired
        public void setNoBean3(Optional<Member> member) {
            // Member 는 Spring Bean 이 아니라 호출이 안된다.
            System.out.println("member3 = " + member);
        }
    }

}
