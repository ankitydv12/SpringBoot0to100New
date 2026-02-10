package com.ankit.module3;

import com.ankit.module3.entities.EmployeeEntity;
import com.ankit.module3.entities.Products;
import com.ankit.module3.repository.EmployeeRepo;
import com.ankit.module3.repository.ProductRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@SpringBootTest
class Module3ApplicationTests {

	@Autowired
	ProductRepo productRepo;

	@Autowired
	EmployeeRepo employeeRepo;
	@Test
	void contextLoads() {
	}

	@Test
	void TestRepo()
	{
		Products products = Products.builder()
				.sku("nestle231")
				.title("Nestle chocolate")
				.price(BigDecimal.valueOf(123.4))
				.quantity(323)
				.build();
		System.out.println(productRepo.save(products));

	}

	@Test
	void getProducts()
	{
		List<Products> entities = productRepo.findAll();
		System.out.println(entities);
	}

	@Test
	void customquerygetProdut()
	{
		Products products = productRepo.findByTitle("Parle");
		System.out.println(products);
	}

	@Test
	void customquerygetProdutByDate()
	{
		List<Products> productsList = productRepo.findByCreatedAtAfter(LocalDateTime.of(2026,01,01,02,00,20));
		productsList.forEach(x->System.out.println(x));
	}

	@Test
	void customquerygetProdutByPriceQuantity()
	{
		List<Products>	productsByPriceQuan;
//		List<Products>	productsByPriceQuan = productRepo.findByPriceAndQuantity(BigDecimal.valueOf(123.4),323);
//		System.out.println(productsByPriceQuan);
//		productsByPriceQuan = productRepo.findByPriceGreaterThanAndQuantityGreaterThan(BigDecimal.valueOf(123.4),323);
//		System.out.println(productsByPriceQuan);
//		productsByPriceQuan = productRepo.findByTitleLike("Parle");
//		System.out.println(productsByPriceQuan);

//		productsByPriceQuan = productRepo.findByTitleContaining("Parle");
//		System.out.println(productsByPriceQuan);

//		productsByPriceQuan = productRepo.findByTitleContainingIgnoreCase("pArLe");
//		System.out.println(productsByPriceQuan);

		Optional<Products> productsOptional = productRepo.findByTitleAndPrice("Nestle chocolate",BigDecimal.valueOf(123.4));
		productsOptional.ifPresent(System.out::println);
	}


	@Test
	void PracticeQueryOnEmployee()
	{
		EmployeeEntity emp;
		List<EmployeeEntity> emplist;

//		emp = employeeRepo.findByName("ankit");

//		List<EmployeeEntity> emps = employeeRepo.findByNameLike("%a%");
//		System.out.println(emps);

//		emp = employeeRepo.findByEmail("ankit@email.com");

//		emp = employeeRepo.findByNameAndAge("ankit",24);

//		emp = employeeRepo.findByNameOrEmail("ankit","null");

//		emplist = employeeRepo.findByAgeGreaterThan(25);

//		emplist = employeeRepo.findByAgeBetween(25,30);

//		emplist = employeeRepo.findByIsActiveTrue();

//		Optional<EmployeeEntity> optional = employeeRepo.findByEmailNull();

//		List<Optional<EmployeeEntity>> optional = employeeRepo.findByEmailNotNull();


//		emplist = employeeRepo.findByNameContaining("an");
//		emplist = employeeRepo.findByNameStartingWith("an");

//		emplist = employeeRepo.findByAgeIn(List.of(2,24,56,25));
//		emplist = employeeRepo.findByOrderBySalaryAsc();
//		emplist = employeeRepo.findByOrderBySalaryDesc();

//		emplist = employeeRepo.findByIsActiveTrueOrderBySalaryDesc();

//		Integer count  = employeeRepo.countByIsActiveTrue();
//
//		emplist = employeeRepo.existsByEmail("ankit@email.com");

//		employeeRepo.deleteByIsActiveFalse();

//		emplist = employeeRepo.findTop3ByOrderBySalaryDesc();

//		emp = employeeRepo.findFirstByOrderByJoiningDateAsc();

//		System.out.println(emplist);



	}
}
