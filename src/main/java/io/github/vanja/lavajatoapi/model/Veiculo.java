package io.github.vanja.lavajatoapi.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "tb_veiculo")
@Data
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String marca;

    private String modelo;

    private String placa;

    @ManyToOne(
            cascade = CascadeType.ALL,
       fetch = FetchType.LAZY
    )

    @JoinColumn(name = "id_cliente")
    private Cliente cliente;
}
