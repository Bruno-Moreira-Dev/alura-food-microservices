package br.com.pedidos.alura_food_pedidos.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Configuracao {

    @Bean
    public ModelMapper obterModelMapper() {
        return new ModelMapper();
    }
}
