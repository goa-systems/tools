package goa.systems.tools;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ToolsApplicationTests {

	@Value("${spring.application.name}")
	private String appname;

	@Test
	void contextLoads() {
		assertEquals("tools", appname);
	}

}
