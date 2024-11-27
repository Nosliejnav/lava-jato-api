package io.github.vanja.lavajatoapi.repository;

import io.github.vanja.lavajatoapi.model.Cliente;
import io.github.vanja.lavajatoapi.model.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ClienteRepository extends JpaRepository<Cliente, UUID> {

    List<Cliente> findByNome(String nome);
    List<Cliente> findByCpf(String cpf);
    List<Cliente> findByNomeAndCpf(String nome, String cpf);

//    List<Cliente> fyndByVeiculo(Veiculo veiculo);

}
