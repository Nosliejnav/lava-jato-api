package io.github.vanja.lavajatoapi.repository;

import io.github.vanja.lavajatoapi.model.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface VeiculoRepository extends JpaRepository<Veiculo, UUID> {
}
