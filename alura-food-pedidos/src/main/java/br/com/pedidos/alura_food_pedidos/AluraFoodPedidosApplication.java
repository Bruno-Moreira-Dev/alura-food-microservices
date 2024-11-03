package br.com.pedidos.alura_food_pedidos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class AluraFoodPedidosApplication {

	public static void main(String[] args) {
		SpringApplication.run(AluraFoodPedidosApplication.class, args);
	}

}
