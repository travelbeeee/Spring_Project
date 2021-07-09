package travelbeeee.jdbcTemplate.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import travelbeeee.jdbcTemplate.api.ApiUtils.ApiResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

import static travelbeeee.jdbcTemplate.api.ApiUtils.error;
import static travelbeeee.jdbcTemplate.api.ApiUtils.success;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberRepository memberRepository;

    @GetMapping("/members/{id}")
    public ApiResult<?> findMember(@PathVariable("id") Long id) {
        Member findMember = memberRepository.selectById(id);
        if(findMember == null) return error("없는 회원입니다.", HttpStatus.BAD_REQUEST);
        return success(findMember);
    }

    @PostMapping("/members")
    public ApiResult<?> uploadMember(@Validated @RequestBody Member member, BindingResult bindingResult) {
        memberRepository.insert(member);
        return success("회원 저장 성공");
    }

    @GetMapping("/members")
    public ApiResult<List<Member>> findAll(HttpServletResponse response) {
        log.info("findAll() : {}", memberRepository.findAll());
        response.addHeader("Content-Type", "application/json");
        return success(memberRepository.findAll());
    }

    @DeleteMapping("/members/{id}")
    public ApiResult<?> deleteMember(@PathVariable("id") Long id) {
        Member findMember = memberRepository.selectById(id);
        if(findMember == null) return error("없는 회원입니다.", HttpStatus.BAD_REQUEST);
        memberRepository.delete(id);
        return success("회원 삭제 성공");
    }
}
