package io.github.vanja.lavajatoapi.controller.dto;

import io.github.vanja.lavajatoapi.model.Veiculo;

import java.util.UUID;

public record CadastroVeiculoDTO(

        String marca,
        String modelo,
        String placa,

        UUID idCliente
        ) {


        public Veiculo mapearParaVeiculo() {
                Veiculo veiculo = new Veiculo();
                veiculo.setMarca(this.marca);
                veiculo.setModelo(this.modelo);
                veiculo.setPlaca(this.placa);
                return veiculo;
        }
}
