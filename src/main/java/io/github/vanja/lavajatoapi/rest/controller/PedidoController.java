package io.github.vanja.lavajatoapi.rest.controller;

import io.github.vanja.lavajatoapi.domain.entity.Pedido;
import io.github.vanja.lavajatoapi.rest.dto.PedidoDTO;
import io.github.vanja.lavajatoapi.service.PedidoService;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("pedidos")
//@NoArgsConstructor
public class PedidoController {


    private PedidoService service;

    //
    public PedidoController(PedidoService service) {
        this.service = service;
    }
    //

    @PostMapping
    @ResponseStatus(CREATED)
    public Integer save(@RequestBody PedidoDTO dto){
        Pedido pedido = service.salvar(dto);
        return pedido.getId();
    }

}
