package travelbeeee.jdbcTemplate.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.data.ConditionalOnRepositoryType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class MemberRepository {

    private final JdbcTemplate jdbcTemplate;

    public List<Member> findAll() {
        String sql = "select * from member";
        List<Member> members = jdbcTemplate.query(sql, new RowMapper<Member>() {
            @Override
            public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
                Member member = new Member();
                member.setId(rs.getLong("id"));
                member.setName(rs.getString("name"));
                member.setEmail(rs.getString("email"));
                return member;
            }
        });
        return members;
    }

    public Member selectById(Long id){
        String sql = "select * from member where id = ?";
        List<Member> members = jdbcTemplate.query(sql, new Object[]{id}, new RowMapper<Member>() {
            @Override
            public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
                Member member = new Member();
                member.setId(rs.getLong("id"));
                member.setName(rs.getString("name"));
                member.setEmail(rs.getString("email"));
                return member;
            }
        });
        log.info("members : {}", members);
        log.info("members.isEmpty : {}", members.isEmpty());
        if(members.isEmpty())
            return null;
        return members.get(0);
    }

    public void insert(Member member) {
        String sql = "insert into member(name, email) values(?, ?)";
        int result = jdbcTemplate.update(sql, new Object[]{member.getName(), member.getEmail()});
    }

    public void delete(Long id) {
        String sql = "delete from member where id = ?";
        jdbcTemplate.update(sql, new Object[]{id});
    }

    public void clearAll() {
        String sql = "delete from member";
        jdbcTemplate.update(sql);
    }
}
