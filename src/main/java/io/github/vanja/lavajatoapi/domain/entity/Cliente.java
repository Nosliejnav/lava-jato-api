package io.github.vanja.lavajatoapi.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
//    private UUID id;

    @Column(name = "nome", length = 100)
    private String nome;

    @Column(name = "cpf", length = 11)
    private String cpf;

    @Column(name = "endereco", length = 100)
    private String endereco;

    @Column(name = "telefone", length = 15)
    private String telefone;

    @JsonIgnore
    @OneToMany( mappedBy = "cliente", fetch = FetchType.LAZY)
    private Set<Pedido> pedidos;

//    public Cliente(UUID id, String nome) {
//        this.id = id;
//        this.nome = nome;
//    }

        public Cliente(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }
}
