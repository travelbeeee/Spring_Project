package travelbeeee.jdbcTemplate.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {
    private final MemberRepository memberRepository;

    @GetMapping("/members/{id}")
    public String findMember(@PathVariable("id") Long id, Model model) {
        Member findMember = memberRepository.selectById(id);
        if (findMember == null) model.addAttribute("member", new Member());
        else model.addAttribute("member", findMember);
        return "/member/member";
    }

    @GetMapping("/members")
    public String findMembers(Model model){
        model.addAttribute("members", memberRepository.selectAll());
        return "/member/members";
    }

}
