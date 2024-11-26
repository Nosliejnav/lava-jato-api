package io.github.vanja.lavajatoapi.rest.controller;

import io.github.vanja.lavajatoapi.domain.entity.Veiculo;
import io.github.vanja.lavajatoapi.domain.repository.Veiculos;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("veiculos")
public class VeiculoController {

    private Veiculos repository;

    public VeiculoController(Veiculos repository) {
        this.repository = repository;
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Veiculo save( @RequestBody Veiculo veiculo ){
        return repository.save(veiculo);
    }

    @PutMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void update( @PathVariable Integer id, @RequestBody Veiculo veiculo ){
        repository
                .findById(id)
                .map( p -> {
                    veiculo.setId(p.getId());
                    repository.save(veiculo);
                    return veiculo;
                }).orElseThrow( () ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Veiculo não encontrado."));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void delete(@PathVariable Integer id){
        repository
                .findById(id)
                .map( p -> {
                    repository.delete(p);
                    return Void.TYPE;
                }).orElseThrow( () ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Veiculo não encontrado."));
    }

    @GetMapping("{id}")
    public Veiculo getById(@PathVariable Integer id){
        return repository
                .findById(id)
                .orElseThrow( () ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Veiculo não encontrado."));
    }

    @GetMapping
    public List<Veiculo> find(Veiculo filtro ){
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(
                        ExampleMatcher.StringMatcher.CONTAINING );

        Example example = Example.of(filtro, matcher);
        return repository.findAll(example);
    }
}