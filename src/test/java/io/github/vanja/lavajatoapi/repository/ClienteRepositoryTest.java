package io.github.vanja.lavajatoapi.repository;


import io.github.vanja.lavajatoapi.model.Cliente;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ClienteRepositoryTest {

    @Autowired
    ClienteRepository repository;

    @Test
    public void salvarTest(){
        Cliente cliente = new Cliente();
        cliente.setNome("lucas");
        cliente.setCpf("05636444255");
        cliente.setEndereco("Casa 53");
        cliente.setTelefone("995167453");

        var clienteSalvo = repository.save(cliente);
        System.out.println("Autor salvo: " + clienteSalvo);

    }
}