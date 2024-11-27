package io.github.vanja.lavajatoapi.domain.repository;

import io.github.vanja.lavajatoapi.domain.entity.Cliente;
import io.github.vanja.lavajatoapi.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Pedidos extends JpaRepository<Pedido, Integer> {

    List<Pedido> findByCliente(Cliente cliente);
}
