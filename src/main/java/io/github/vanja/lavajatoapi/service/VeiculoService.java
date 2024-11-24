package io.github.vanja.lavajatoapi.service;

import io.github.vanja.lavajatoapi.model.Veiculo;
import io.github.vanja.lavajatoapi.repository.VeiculoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VeiculoService {

    private final VeiculoRepository repository;

    public Veiculo salvar(Veiculo veiculo){
        return repository.save(veiculo);
    }

}
