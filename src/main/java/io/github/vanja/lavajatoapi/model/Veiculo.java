package io.github.vanja.lavajatoapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "veiculo")
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String marca;
    private String modelo;
    private String placa;


    @ManyToOne(
//            cascade = CascadeType.ALL,
       fetch = FetchType.LAZY
    )
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;


}
