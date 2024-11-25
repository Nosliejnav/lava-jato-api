<div align="center">
  <img src="https://github.com/ralflima/java_spring/blob/master/icone.png" width="30%">

<h1 style="border-bottom:none">Rotas da API</h1>
  <img src="/.github/SpringInitializr.png" width="100%">

##

  <h3> CRUD ( clientes ) </h3>

##

  <div align="justify">
  <h3>1. Cadastrar um cliente</h3>

  ```http
  POST /clientes
```

  ```json
{
    "nome": "Fulano",
    "cpf": "987.654.321-00",
    "telefon": "(61) 9 9876-5432",
    "endereco": "Quadra 01 Casa 01",
}
```


<h3>2. Atualizar os dados de um cliente</h3>

  ```http
  PUT /clientes/:id
```

  ```json
{
    "nome": "Cliclano",
    "cpf": "789.654.321-00",
    "telefon": "(61) 9 8765-5432",
    "endereco": "Quadra 02 Casa 02",
}
```

• Permitir editar qualquer dado de um cliente já cadastrado.

<h3>3. Deletar um cliente</h3>


```http
  DELETE /clientes/:id
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `id`      | `long` ou `UUID` | **Deleta o Cliente do ID que foi passado**. |

<!--• Remover o cliente e seus veículos associados. -->

<h3>4. Buscar um cliente</h3>
• A busca deve ser feita usando CPF (para pessoa física) ou CNPJ (para pessoa jurídica).

<h3>Extras (Opcional):</h3>

• Cadastro e gerenciamento de veículos para cada cliente:

• Dados do veículo: Marca, Modelo e Placa.

• Validação de CPF e CNPJ no momento do cadastro.
  </div>
</div>
