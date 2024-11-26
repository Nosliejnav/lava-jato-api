package io.github.vanja.lavajatoapi.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "item_pedido")
public class ItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "Pedido_id")
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "Veiculo_id")
    private Veiculo veiculo;

    @Column
    private Integer quantidade;
}
