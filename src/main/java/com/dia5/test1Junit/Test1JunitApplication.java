package com.dia5.test1Junit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.dia5.test1Junit.servicios.CarritoCompraServiceImpl;

@SpringBootApplication
public class Test1JunitApplication implements CommandLineRunner {
	
	@Autowired
	private CarritoCompraServiceImpl carro;
	

	public static void main(String[] args) {
		SpringApplication.run(Test1JunitApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		System.err.println("Precio:" + carro.totalPrecio());
		
	}

}
