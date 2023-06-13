package hello.jdbc.repository;

import java.sql.SQLException;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import hello.jdbc.domain.Member;

class MemberRepositoryV0Test {

    MemberRepositoryV0 repository = new MemberRepositoryV0();

    @Test
    void crud() throws SQLException {
        String memberId = "memberV1";

        repository.delete(memberId);

        Member member = new Member(memberId, 10000);
        repository.save(member);
        repository.update(memberId, 20000);

        Member findMember = repository.findById(memberId);

        Assertions.assertThat(member.getMemberId()).isEqualTo(findMember.getMemberId());
        Assertions.assertThat(findMember.getMoney()).isEqualTo(20000);

    }

}
