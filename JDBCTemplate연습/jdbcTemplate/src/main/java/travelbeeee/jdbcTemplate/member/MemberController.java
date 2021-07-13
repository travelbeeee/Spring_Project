package travelbeeee.jdbcTemplate.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/member")
public class MemberController {
    private final MemberRepository memberRepository;

    @GetMapping("/{id}")
    public String findMember(@PathVariable("id") Long id, Model model) {
        Member findMember = memberRepository.selectById(id);
        if (findMember == null) model.addAttribute("member", new Member());
        else model.addAttribute("member", findMember);
        return "/member/member";
    }

    @GetMapping("/members")
    public String findMembers(Model model) {
        model.addAttribute("members", memberRepository.selectAll());
        return "/member/members";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("member", new Member());
        return "/member/form";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("member") MemberDto memberDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/member/form";
        }
        memberRepository.insert(new Member(memberDto.getName(), memberDto.getEmail()));
        return "/member/member";
    }
}
