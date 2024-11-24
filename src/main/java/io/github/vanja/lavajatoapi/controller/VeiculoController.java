package io.github.vanja.lavajatoapi.controller;

import io.github.vanja.lavajatoapi.controller.dto.CadastroVeiculoDTO;
import io.github.vanja.lavajatoapi.controller.dto.ResultadoPesquisaVeiculoDTO;
import io.github.vanja.lavajatoapi.model.Veiculo;
import io.github.vanja.lavajatoapi.service.VeiculoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("veiculos")
@RequiredArgsConstructor
public class VeiculoController {

    private final VeiculoService service;

    @PostMapping
    public ResponseEntity<Void> salvar(@RequestBody CadastroVeiculoDTO veiculo){
        Veiculo veiculoEntidade = veiculo.mapearParaVeiculo();
        service.salvar(veiculoEntidade);

        // http: //localhost:8080/autores/80d19e56-89ab-4e14-992e-8c1145074141
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(veiculoEntidade.getId())
                .toUri();

        return  ResponseEntity.created(location).build();
//        return new ResponseEntity("Cliente Salvo com sucesso! " + cliente, HttpStatus.CREATED);
    }

}
