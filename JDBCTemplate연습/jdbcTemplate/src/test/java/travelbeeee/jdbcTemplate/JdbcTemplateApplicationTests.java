package travelbeeee.jdbcTemplate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JdbcTemplateApplicationTests {

	@Test
	void contextLoads() {
		Assertions.assertThrows(
				ArithmeticException.class, () -> {
					int a = (2 / 0);
				}
		);
	}

}
