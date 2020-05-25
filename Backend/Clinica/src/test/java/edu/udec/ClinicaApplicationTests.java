package edu.udec;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class ClinicaApplicationTests {
	
	@Autowired
	private BCryptPasswordEncoder bcrypt;

	@Test
	void contextLoads() {
		System.out.println("*****************************************");
		System.out.println(bcrypt.encode("123"));
	}

}
