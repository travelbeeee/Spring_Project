package travelbeeee.jdbcTemplate.member;

import lombok.*;

@Getter @Setter @ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
    private Long id;
    private String name;
    private String email;

    public Member(String username, String email) {
        this.name = username;
        this.email = email;
    }
}