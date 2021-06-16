package travelbeeee.spring_core_concept.member;

public interface MemberRepository {

    void save(Member member);
    Member findById(Long memberId);
}
