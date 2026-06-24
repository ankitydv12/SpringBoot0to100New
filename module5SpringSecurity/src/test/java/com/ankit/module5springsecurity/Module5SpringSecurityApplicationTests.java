package com.ankit.module5springsecurity;

import com.ankit.module5springsecurity.entities.User;
import com.ankit.module5springsecurity.services.Impl.JwtService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;

@SpringBootTest
class Module5SpringSecurityApplicationTests {

	@Autowired
	private JwtService jwtService;
	@Test
	void contextLoads() {
		User user = new User(1L,"ankit@gmail.com","123");
		String token = jwtService.generateJwtToken(user);
		System.out.println(token);

		Long id = jwtService.getIdFromJwtToken(token);
		System.out.printf("id: %d",id);
	}

}
