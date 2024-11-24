package io.github.vanja.lavajatoapi.repository;

import io.github.vanja.lavajatoapi.model.Cliente;
import io.github.vanja.lavajatoapi.model.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.UUID;

public interface VeiculoRepository extends JpaRepository<Veiculo, UUID> {

//    List<Veiculo> fyndByCliente(Cliente cliente);

    List<Veiculo> findByMarca(String marca);
    List<Veiculo> findByModelo(String modelo);

    List<Veiculo> findByMarcaAndModelo(String marca, String modelo);

}
