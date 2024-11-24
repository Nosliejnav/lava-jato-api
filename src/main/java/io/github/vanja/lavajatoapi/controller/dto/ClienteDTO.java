package io.github.vanja.lavajatoapi.controller.dto;

import io.github.vanja.lavajatoapi.model.Cliente;

import java.util.UUID;

public record ClienteDTO(
        UUID id,
        String nome,
        String cpf,
        String endereco,
        String telefone) {

    public Cliente mapearParaCliente(){
        Cliente cliente = new Cliente();
        cliente.setNome(this.nome);
        cliente.setCpf(this.cpf);
        cliente.setEndereco(this.endereco);
        cliente.setTelefone(this.telefone);
        return cliente;
    }
}
