package travelbeeee.jdbcTemplate.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import travelbeeee.jdbcTemplate.api.ApiUtils.ApiResult;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static travelbeeee.jdbcTemplate.api.ApiUtils.error;
import static travelbeeee.jdbcTemplate.api.ApiUtils.success;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/rest")
public class MemberRestController {

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
        return success(memberRepository.selectAll());
    }


    @DeleteMapping("/members/{id}")
    public ApiResult<?> deleteMember(@PathVariable("id") Long id) {
        Member findMember = memberRepository.selectById(id);
        if(findMember == null) return error("없는 회원입니다.", HttpStatus.BAD_REQUEST);
        memberRepository.delete(id);
        return success("회원 삭제 성공");
    }
}
