package io.github.vanja.lavajatoapi.service;

import io.github.vanja.lavajatoapi.model.Cliente;
import io.github.vanja.lavajatoapi.model.Veiculo;
import io.github.vanja.lavajatoapi.repository.VeiculoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VeiculoService {

    private final VeiculoRepository repository;

    public Veiculo salvar(Veiculo veiculo){
        return repository.save(veiculo);
    }

    public Optional<Veiculo> obterPorId(UUID id){
        return repository.findById(id);
    }

    public void deletar(Veiculo veiculo){
        repository.delete(veiculo);
    }


    public List<Veiculo> pesquisa(
            String marca, String modelo){

            if(marca != null && modelo != null){
                return repository.findByMarcaAndModelo(marca, modelo);
            }

            if(modelo != null){
                return repository.findByModelo(modelo);
            }

            if(marca != null){
                return repository.findByMarca(marca);
            }

            return repository.findAll();
        }

    public void atualizar(Veiculo veiculo){
        if(veiculo.getId() == null){
            throw new IllegalArgumentException("Para atualizar, é necessário que o veiculo já esteja salvo na base. ");
        }
        repository.save(veiculo);
    }


//    List<Veiculo> fyndByCliente(Cliente cliente);

}
