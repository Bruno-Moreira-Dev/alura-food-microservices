package br.com.pedidos.alura_food_pedidos.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemDoPedidoDTO {
    private Long id;
    private Integer quantidade;
    private String descricao;
}
