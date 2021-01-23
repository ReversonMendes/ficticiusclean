# ficticiusclean

Projeto desenvolvido para processo de seleção da TOTVS.


## Recursos utilizados

 - Maven --> para gerenciar dependências e compilar.
 - Spring(base/core, jpa)  --> para facilitar: a injeção de dependência, criação do serviço rest.
 - H2 Database --> para base de dados.
 - lombok --> para facilitar os getters e setters

## Utilizando a aplicacao - Back-end

Projeto baseado em Maven importar as dependências

mvn clean install

**listagem de veiculos cadastrados:**

get -> http://localhost:8080/


**listagem de veiculos cadastrados por id:**

get -> http://localhost:8080/{{id}}


**cadastro de novo veículo:**

post -> http://localhost:8080/veiculos/cadastrar

exemplo de body:
  {
    "nome": "Chevrolet Onix Plus",
    "marca": "Chevrolet",
    "modelo": "Versão 1.0 LT manual",
    "dataFabricacao": "2019-05-03",
    "consumoCidade": 14.30,
    "consumoRodovia": 17.70
  }


**atualizar cadastro:**

put -> http://localhost:8080/veiculos/atualizar/{{id}}

exemplo de body:
{
    "nome": "Renault Kwid",
    "marca": "Renault",
    "modelo": "Versão 1.0 Life manual",
    "dataFabricacao": "2019-05-03",
    "consumoCidade": 13.90,
    "consumoRodovia": 15.60
}

**deletar cadastro:**

delete -> http:localhost:8080/veiculos/deletar/{{id}}

**rankear os veiculos:**

post -> http://localhost:8080/veiculos/ranking

exemplo de body:
{
	"precoGasolina":4.5,
	"kmCidade": 10.23,
	"kmRodovia": 10.12
}

exemplo de response:
[
  {
    "nome": "Chevrolet Onix Plus",
    "marca": "Chevrolet",
    "modelo": "Versão 1.0 LT manual",
    "ano": 2019,
    "qtdCombustivelGasto": 1.29,
    "valorTotalGasto": 5.80
  }
]
