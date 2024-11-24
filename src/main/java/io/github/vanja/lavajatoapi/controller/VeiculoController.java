package io.github.vanja.lavajatoapi.controller;

import io.github.vanja.lavajatoapi.controller.dto.CadastroVeiculoDTO;
import io.github.vanja.lavajatoapi.controller.dto.ClienteDTO;
import io.github.vanja.lavajatoapi.controller.dto.ResultadoPesquisaVeiculoDTO;
import io.github.vanja.lavajatoapi.model.Cliente;
import io.github.vanja.lavajatoapi.model.Veiculo;
import io.github.vanja.lavajatoapi.service.VeiculoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("veiculos")
@RequiredArgsConstructor
public class VeiculoController {

    private final VeiculoService service;

    @PostMapping
    public ResponseEntity<Void> salvar(@RequestBody CadastroVeiculoDTO veiculo){
        Veiculo veiculoEntidade = veiculo.mapearParaVeiculo();
        service.salvar(veiculoEntidade);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(veiculoEntidade.getId())
                .toUri();

        return  ResponseEntity.created(location).build();
    }


    @GetMapping("{id}")
    public ResponseEntity<ResultadoPesquisaVeiculoDTO> obterDetalhes(
            @PathVariable("id") String id) {
        var idVeiculo = UUID.fromString(id);
        Optional<Veiculo> veiculoOptional = service.obterPorId(idVeiculo);

        if (veiculoOptional.isPresent()) {
            Veiculo veiculo = veiculoOptional.get();
            ResultadoPesquisaVeiculoDTO dto = new ResultadoPesquisaVeiculoDTO(
                    veiculo.getId(),
                    veiculo.getMarca(),
                    veiculo.getModelo(),
                    veiculo.getPlaca());
            return ResponseEntity.ok(dto);
        }

        return ResponseEntity.notFound().build();

    }


    @DeleteMapping("{id}")
    public ResponseEntity<Object> deletar(
            @PathVariable("id") String id) {

        return service.obterPorId(UUID.fromString(id))
                .map(Veiculo -> {
                    service.deletar(Veiculo);
                    return ResponseEntity.noContent().build();

                }).orElseGet(() -> ResponseEntity.notFound().build());

    }
//        var idVeiculo = UUID.fromString(id);
//        Optional<Veiculo> veiculoOptional = service.obterPorId(idVeiculo);
//
//        if(veiculoOptional.isEmpty()){
//            return ResponseEntity.notFound().build();
//        }
//
//        service.deletar(veiculoOptional.get());
//
//        return  ResponseEntity.noContent().build();
//    }


    @GetMapping
    public ResponseEntity<List<ResultadoPesquisaVeiculoDTO>> pesquisar(
            @RequestParam(value = "marca", required = false) String marca,
            @RequestParam(value = "modelo", required = false) String modelo){
        List<Veiculo> resultado = service.pesquisa(marca, modelo);
        List<ResultadoPesquisaVeiculoDTO> lista = resultado
                .stream()
                .map(veiculo -> new ResultadoPesquisaVeiculoDTO(
                        veiculo.getId(),
                        veiculo.getMarca(),
                        veiculo.getModelo(),
                        veiculo.getPlaca())
                ).collect(Collectors.toList());

        return ResponseEntity.ok(lista);
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> atualizar(
            @PathVariable("id") String id, @RequestBody CadastroVeiculoDTO dto){

        var idVeiculo = UUID.fromString(id);
        Optional<Veiculo> veiculoOptional  = service.obterPorId(idVeiculo);

        if(veiculoOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        var veiculo = veiculoOptional.get();
        veiculo.setMarca(dto.marca());
        veiculo.setModelo(dto.modelo());
        veiculo.setPlaca(dto.placa());

        service.atualizar(veiculo);

        return ResponseEntity.noContent().build();

    }
}
