package io.github.vanja.lavajatoapi.controller.dto;

import io.github.vanja.lavajatoapi.model.Cliente;
import io.github.vanja.lavajatoapi.model.Veiculo;

import java.util.UUID;

public record ResultadoPesquisaVeiculoDTO(
        UUID id,
        String marca,
        String modelo,
        String placa
//        ClienteDTO cliente


        ) {

    public Veiculo mapearParaVeiculo(){
        Veiculo veiculo = new Veiculo();
        veiculo.setMarca(this.marca);
        veiculo.setModelo(this.modelo);
        veiculo.setPlaca(this.placa);


        return veiculo;
    }
}