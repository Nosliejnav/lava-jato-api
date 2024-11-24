package io.github.vanja.lavajatoapi.controller;

import io.github.vanja.lavajatoapi.controller.dto.ClienteDTO;
import io.github.vanja.lavajatoapi.model.Cliente;
import io.github.vanja.lavajatoapi.service.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("clientes")
public class ClienteController {

    private final ClienteService service;

    public ClienteController(ClienteService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Void> salvar(@RequestBody ClienteDTO cliente){
        Cliente clienteEntidade = cliente.mapearParaCliente();
        service.salvar(clienteEntidade);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(clienteEntidade.getId())
                .toUri();

        return  ResponseEntity.created(location).build();
    }

    @GetMapping("{id}")
    public ResponseEntity<ClienteDTO> obterDetalhes(@PathVariable("id") String id){
        var idCliente = UUID.fromString(id);
        Optional<Cliente> clienteOptional  = service.obterPorId(idCliente);
        if(clienteOptional.isPresent()){
            Cliente cliente = clienteOptional.get();
            ClienteDTO dto = new ClienteDTO(
                    cliente.getId(),
                    cliente.getNome(),
                    cliente.getCpf(),
                    cliente.getEndereco(),
                    cliente.getTelefone());
            return ResponseEntity.ok(dto);
        }

        return ResponseEntity.notFound().build();

    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletar(@PathVariable("id") String id){
        var idCliente = UUID.fromString(id);
        Optional<Cliente> clienteOptional  = service.obterPorId(idCliente);

        if(clienteOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        service.deletar(clienteOptional.get());

        return  ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> pesquisar(
            @RequestParam(value = "nome", required = false) String nome,
            @RequestParam(value = "cpf", required = false) String cpf){
        List<Cliente> resultado = service.pesquisa(nome, cpf);
        List<ClienteDTO> lista = resultado
                .stream()
                .map(cliente -> new ClienteDTO(
                cliente.getId(),
                cliente.getNome(),
                cliente.getCpf(),
                cliente.getEndereco(),
                cliente.getTelefone())
                ).collect(Collectors.toList());

        return ResponseEntity.ok(lista);
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> atualizar(
            @PathVariable("id") String id, @RequestBody ClienteDTO dto){

        var idCliente = UUID.fromString(id);
        Optional<Cliente> clienteOptional  = service.obterPorId(idCliente);

        if(clienteOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        var cliente = clienteOptional.get();
        cliente.setNome(dto.nome());
        cliente.setCpf(dto.cpf());
        cliente.setEndereco(dto.endereco());
        cliente.setTelefone(dto.telefone());

        service.atualizar(cliente);

        return ResponseEntity.noContent().build();

    }
}
