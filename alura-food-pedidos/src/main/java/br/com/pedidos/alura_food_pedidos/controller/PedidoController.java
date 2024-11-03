package br.com.pedidos.alura_food_pedidos.controller;

import br.com.pedidos.alura_food_pedidos.dto.PedidoDTO;
import br.com.pedidos.alura_food_pedidos.dto.StatusDTO;
import br.com.pedidos.alura_food_pedidos.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService service;

    @GetMapping
    public List<PedidoDTO> listarTodos() {
        return service.obterTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoDTO> listarPorId(@PathVariable @NotNull Long id) {
        PedidoDTO dto = service.obterPorId(id);

        return ResponseEntity.ok(dto);
    }

    @GetMapping("/porta")
    public String retornaPorta(@Value("${local.server.port}") String porta) {
        return String.format("Requisicao respondida pela instancia executando na porta %s", porta);
    }

    @PostMapping
    public ResponseEntity<PedidoDTO> realizaPedido(@RequestBody @Valid PedidoDTO dto, UriComponentsBuilder uriBuilder) {
        PedidoDTO pedidoRealizado = service.criarPedido(dto);

        URI endereco = uriBuilder.path("/pedidos/{id}").buildAndExpand(pedidoRealizado.getId()).toUri();

        return ResponseEntity.created(endereco).body(pedidoRealizado);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<PedidoDTO> atualizaStatus(@PathVariable Long id, @RequestBody StatusDTO status) {
        PedidoDTO dto = service.atualizaStatus(id, status);

        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}/pago")
    public ResponseEntity<Void> aprovaPagamento(@PathVariable @NotNull Long id) {
        service.aprovaPagamentoPedido(id);

        return ResponseEntity.ok().build();
    }

}
