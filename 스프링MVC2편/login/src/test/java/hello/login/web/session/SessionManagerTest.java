package hello.login.web.session;

import hello.login.domain.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.jupiter.api.Assertions.*;

class SessionManagerTest {

    SessionManager manager = new SessionManager();

    @Test
    void sessionTest(){
        // 세션 생성
        MockHttpServletResponse response = new MockHttpServletResponse();
        Member member = new Member();
        manager.createSession(member, response);

        // 요청에 응답 쿠키 저장
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setCookies(response.getCookies());

        //then
        Object result = manager.getSession(request);
        Assertions.assertThat(result.getClass()).isEqualTo(Member.class);
        Assertions.assertThat(result).isEqualTo(member);

        manager.expire(request);

        Object expired = manager.getSession(request);
        Assertions.assertThat(expired).isEqualTo(null);
    }

}