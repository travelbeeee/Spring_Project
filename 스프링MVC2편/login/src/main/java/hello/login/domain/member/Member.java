package hello.login.domain.member;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter @NoArgsConstructor
public class Member {

    private Long id;

    @NotEmpty
    private String loginId; //로그인 ID

    @NotEmpty
    private String name; //사용자이름

    @NotEmpty
    private String password;

    public Member(String loginId, String name, String password) {
        this.loginId = loginId;
        this.name = name;
        this.password = password;
    }
}
