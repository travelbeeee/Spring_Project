package travelbeeee.spring_core_concept.member;

public interface MemberService {
    void join(Member member);

    Member findMember(Long memberId);
}
