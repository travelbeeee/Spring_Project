package travelbeeee.spring_core_concept;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import travelbeeee.spring_core_concept.member.Grade;
import travelbeeee.spring_core_concept.member.Member;
import travelbeeee.spring_core_concept.member.MemberService;
import travelbeeee.spring_core_concept.member.MemberServiceImpl;

public class MemberApp {

    public static void main(String[] args) {
        /**
         * V1 -> 직접 주입
         */
//        MemberService memberService = new MemberServiceImpl();

//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();

        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = ctx.getBean(MemberService.class);

        Member memberA = new Member(1L, "memberA", Grade.VIP);
        memberService.join(memberA);

        Member findMember = memberService.findMember(1L);

        System.out.println("findMember.getName() = " + findMember.getName());
        System.out.println("memberA.getName() = " + memberA.getName());
    }
}
