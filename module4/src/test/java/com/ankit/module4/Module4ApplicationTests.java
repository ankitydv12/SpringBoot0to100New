package com.ankit.module4;

import com.ankit.module4.clients.EmployeeClientImp;
import com.ankit.module4.config.RestClientConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Module4ApplicationTests {

	@Autowired
	private EmployeeClientImp employeeClientImp;
	@Test
	void getEmp() {
		employeeClientImp.getAllEmployees();
	}

}
