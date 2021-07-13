package travelbeeee.jdbcTemplate;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import travelbeeee.jdbcTemplate.member.Member;
import travelbeeee.jdbcTemplate.member.MemberRepository;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class TestDataInit {

    private final MemberRepository memberRepository;
    /**
     * 테스트용 데이터 추가
     */
    @PostConstruct
    public void init() {
        memberRepository.clearAll();
        memberRepository.insert(new Member("member1", "email1"));
        memberRepository.insert(new Member("member2", "email2"));
    }

}