package travelbeeee.spring_core_concept.beanfind;

import static org.assertj.core.api.Assertions.*;

import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import travelbeeee.spring_core_concept.AppConfig;
import travelbeeee.spring_core_concept.member.MemberRepository;
import travelbeeee.spring_core_concept.member.MemoryMemberRepository;

public class ApplicationContextSameBeanTest {

    @Configuration
    static class SameBeanConfig {

        @Bean
        public MemberRepository memberRepository1() {
            return new MemoryMemberRepository();
        }

        @Bean
        public MemberRepository memberRepository2() {
            return new MemoryMemberRepository();
        }
    }

    AnnotationConfigApplicationContext ctx =
        new AnnotationConfigApplicationContext(SameBeanConfig.class);

    @Test
    @DisplayName("타입으로 조회 시 같은 타입이 둘 이상 있으면, 중복 오류가 발생한다.")
    void findBeanByTypeDuplicate() {
        Assertions.assertThrows(NoUniqueBeanDefinitionException.class,
            () -> ctx.getBean(MemberRepository.class)
        );
    }

    @Test
    @DisplayName("같은 타입의 빈이 N개라면 빈 이름으로 구분하자.")
    void findBeanByNameAndType() {
        MemberRepository memberRepository1
            = ctx.getBean("memberRepository1", MemberRepository.class);

        MemberRepository memberRepository2
            = ctx.getBean("memberRepository2", MemberRepository.class);

        assertThat(memberRepository1).isInstanceOf(MemberRepository.class);
        assertThat(memberRepository2).isInstanceOf(MemberRepository.class);
    }

    @Test
    @DisplayName("같은 타입의 빈 전체 조회")
    void findAllBeanByType() {
        Map<String, MemberRepository> beansOfType = ctx.getBeansOfType(MemberRepository.class);

        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key + " value = " + beansOfType.get(key));
        }
        System.out.println("beansOfType = " + beansOfType);

        assertThat(beansOfType.size()).isEqualTo(2);
    }
}
