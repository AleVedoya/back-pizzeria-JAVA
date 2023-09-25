package com.ideas.pizzeria;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "PIZZERÍA DON REMOLO",
				version = "1.0.0",
				description = "Menú de comidas para realizar pedidos online. Gestor de pedidos para el local."
		)
)
public class PizzeriaApplication {

	public static void main(String[] args) {
		SpringApplication.run(PizzeriaApplication.class, args);


	}

}
