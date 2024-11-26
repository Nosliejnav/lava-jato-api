package io.github.vanja.lavajatoapi.service.impl;

import io.github.vanja.lavajatoapi.domain.entity.Cliente;
import io.github.vanja.lavajatoapi.domain.entity.ItemPedido;
import io.github.vanja.lavajatoapi.domain.entity.Pedido;
import io.github.vanja.lavajatoapi.domain.entity.Veiculo;
import io.github.vanja.lavajatoapi.domain.repository.Clientes;
import io.github.vanja.lavajatoapi.domain.repository.ItemsPedido;
import io.github.vanja.lavajatoapi.domain.repository.Pedidos;
import io.github.vanja.lavajatoapi.domain.repository.Veiculos;
import io.github.vanja.lavajatoapi.exception.RegraNegocioException;
import io.github.vanja.lavajatoapi.rest.dto.ItemPedidoDTO;
import io.github.vanja.lavajatoapi.rest.dto.PedidoDTO;
import io.github.vanja.lavajatoapi.service.PedidoService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor // Elimina todos os construtors se tiver o argumento obrigatorio "private final"
public class PedidoServiceImpl implements PedidoService {

    private final Pedidos repository;
    private final Clientes clientesRepository;
    private final Veiculos veiculosRepository;
    private final ItemsPedido itemsPedidoRepository;

//    public PedidoServiceImpl( Pedidos repository, Clientes clientes){
//        this.repository = repository;
//        this.clientesRepository = clientes;
//    }

    @Override
    @Transactional
    public Pedido salvar(PedidoDTO dto) {
        Integer idCliente = dto.getCliente();
        Cliente cliente = clientesRepository
                .findById(idCliente).
                orElseThrow(() -> new RegraNegocioException("Código de cliente inválido."));

        Pedido pedido = new Pedido();
        pedido.setTotal(dto.getTotal());
        pedido.setDataPedido(LocalDate.now());
        pedido.setCliente(cliente);

        List<ItemPedido> itemsPedido = converterItems(pedido, dto.getItems());
        repository.save(pedido);
        itemsPedidoRepository.saveAll(itemsPedido);
        pedido.setItens(itemsPedido);
        return pedido;
    }

   private List<ItemPedido> converterItems(Pedido pedido, List<ItemPedidoDTO> items){
        if(items.isEmpty()){
            throw new RegraNegocioException("Não é possível realizar um pedido sem items.");
        }

        return items
                .stream()
                .map( dto -> {
                    Integer idVeiculo = dto.getVeiculo();
                    Veiculo veiculo = veiculosRepository
                            .findById(idVeiculo)
                            .orElseThrow(
                                    () -> new RegraNegocioException(
                                            "Código de Veiculo inválido: " + idVeiculo
                                    ));

                    ItemPedido itemPedido = new ItemPedido();
                    itemPedido.setQuantidade(dto.getQuantidade());
                    itemPedido.setPedido(pedido);
                    itemPedido.setVeiculo(veiculo);
                    return itemPedido;

                }).collect(Collectors.toList());
   }
}
