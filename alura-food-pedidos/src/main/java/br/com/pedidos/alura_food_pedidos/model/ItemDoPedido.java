package br.com.pedidos.alura_food_pedidos.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "item_do_pedido")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemDoPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Integer quantidade;

    private String descricao;

    @ManyToOne(optional = false)
    private Pedido pedido;

}
