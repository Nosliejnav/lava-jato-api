package io.github.vanja.lavajatoapi.service;

import io.github.vanja.lavajatoapi.model.Cliente;
import io.github.vanja.lavajatoapi.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClienteService {

    private final ClienteRepository repository;

    public ClienteService(ClienteRepository repository){
        this.repository = repository;
    }

    public Cliente salvar(Cliente cliente){
        return repository.save(cliente);
    }

    public void atualizar(Cliente cliente){
        if(cliente.getId() == null){
            throw new IllegalArgumentException("Para atualizar, é necessário que o autor já esteja salvo na base. ");
        }
        repository.save(cliente);
    }

    public Optional<Cliente> obterPorId(UUID id){
        return repository.findById(id);
    }

//    public Optional<Cliente> obterPorCpf(String cpf){
//        return repository.findByCpf(cpf);
//    }


    public void deletar(Cliente cliente){
        repository.delete(cliente);
    }

    public List<Cliente> pesquisa(String nome, String cpf){
        if(nome != null && cpf != null){
            return repository.findByNomeAndCpf(nome, cpf);
        }

        if(nome != null){
            return repository.findByNome(nome);
        }

        if(cpf != null){
            return repository.findByCpf(cpf);
        }

        return repository.findAll();
    }
}
