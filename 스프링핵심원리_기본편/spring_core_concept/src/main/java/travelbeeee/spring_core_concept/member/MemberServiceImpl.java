package travelbeeee.spring_core_concept.member;

public class MemberServiceImpl implements MemberService {

    /**
     * V1 -> 관리자 없이 직접 주입
     */
//    private final MemberRepository memberRepository = new MemoryMemberRepository();
//
//    public MemberServiceImpl() {
//    }

    /**
     * V2 => 관리자가 MemberRepository 의존성 관리
     */
    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

}
