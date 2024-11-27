package io.github.vanja.lavajatoapi.service;


import io.github.vanja.lavajatoapi.domain.entity.Pedido;
import io.github.vanja.lavajatoapi.rest.dto.PedidoDTO;

public interface PedidoService {
    Pedido salvar (PedidoDTO dto);
}
