package br.com.pedidos.alura_food_pedidos.service;

import br.com.pedidos.alura_food_pedidos.dto.PedidoDTO;
import br.com.pedidos.alura_food_pedidos.dto.StatusDTO;
import br.com.pedidos.alura_food_pedidos.model.Pedido;
import br.com.pedidos.alura_food_pedidos.model.Status;
import br.com.pedidos.alura_food_pedidos.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoService {

    @Autowired
    private PedidoRepository repository;

    @Autowired
    private final ModelMapper modelMapper;

    public List<PedidoDTO> obterTodos() {
        return repository.findAll().stream()
                .map(p -> modelMapper.map(p, PedidoDTO.class))
                .collect(Collectors.toList());
    }

    public PedidoDTO obterPorId(Long id) {
        Pedido pedido = repository.findById(id).orElseThrow(EntityNotFoundException::new);
        return modelMapper.map(pedido, PedidoDTO.class);
    }

    public PedidoDTO criarPedido(PedidoDTO dto) {
        Pedido pedido = modelMapper.map(dto, Pedido.class);

        pedido.setDataHora(LocalDateTime.now());
        pedido.setStatus(Status.REALIZADO);
        pedido.getItens().forEach(item -> item.setPedido(pedido));
        repository.save(pedido);

        return modelMapper.map(pedido, PedidoDTO.class);
    }

    public PedidoDTO atualizaStatus(Long id, StatusDTO dto) {
        Pedido pedido = repository.porIdComItens(id);

        if (pedido == null) {
            throw new EntityNotFoundException();
        }

        pedido.setStatus(dto.getStatus());
        repository.atualizaStatus(dto.getStatus(), pedido);

        return modelMapper.map(pedido, PedidoDTO.class);
    }

    public void aprovaPagamentoPedido(Long id) {
        Pedido pedido = repository.porIdComItens(id);

        if (pedido == null) {
            throw new EntityNotFoundException();
        }

        pedido.setStatus(Status.PAGO);
        repository.atualizaStatus(Status.PAGO, pedido);
    }

}
