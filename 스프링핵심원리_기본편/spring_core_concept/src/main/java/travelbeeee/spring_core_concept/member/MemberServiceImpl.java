package travelbeeee.spring_core_concept.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
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

//    @Autowired(required = false) 필수값이 아니라고 명시도 가능 => false 면 주입할 대상이 없어도 에러 X
    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 수정자 주입 (Setter 주입)
//    @Autowired
//    public void setMemberRepository(MemberRepository memberRepository) {
//        this.memberRepository =  memberRepository;
//    }

    // 필드 주입
//    @Autowired
//    private MemberRepository memberRepository;

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

}
