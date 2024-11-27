package io.github.vanja.lavajatoapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;


@NoArgsConstructor
@AllArgsConstructor

@Data
@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String nome;

    private String cpf;

    private String endereco;

    private String telefone;

//    @JsonIgnore
    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY
    // , cascade = CascadeType.ALL,
    )
    private List<Veiculo> veiculos;

    @Column(name = "id_usuario")
    private UUID idUsuario;

}
