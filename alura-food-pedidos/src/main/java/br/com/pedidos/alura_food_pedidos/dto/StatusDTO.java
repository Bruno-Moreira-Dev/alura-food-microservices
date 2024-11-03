package br.com.pedidos.alura_food_pedidos.dto;

import br.com.pedidos.alura_food_pedidos.model.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StatusDTO {
    private Status status;
}
