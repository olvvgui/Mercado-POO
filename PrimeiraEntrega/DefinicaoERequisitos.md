# Definição do problema e Lista de Requisitos

## Definição do Problema

O gerenciamento manual ou descentralizado de um estabelecimento comercial, como um mercado, resulta em falhas
operacionais graves, incluindo a falta de controle de estoque, inconsistência financeira, atrasos em pedidos com
fornecedores e atendimento ineficiente ao cliente. A ausência de um sistema integrado impede que a gerência tome
decisões baseadas em dados em tempo real.

Para solucionar essas dores, é necessária uma aplicação de software baseada no paradigma de Orientação a Objetos. O
sistema deve centralizar o fluxo de informações, garantir a consistência das entidades comerciais (Clientes,
Fornecedores, Funcionários e Produtos) e utilizar um Banco de Dados Relacional para assegurar a persistência,
integridade e segurança dos dados históricos do mercado.

---

## Escopo do Trabalho

O projeto consiste no desenvolvimento de um sistema de gerenciamento de mercado baseado em arquitetura em camadas (como
MVC - Model-View-Controller) utilizando Java SE e conexão JDBC ou JPA/Hibernate para persistência de dados.

### Incluído no Escopo

* **Modelagem Orientada a Objetos:** Implementação de classes abstratas, herança (ex: classe Pessoa estendida por
  Cliente e Funcionário), encapsulamento e polimorfismo.
* **Módulos de Cadastro (CRUD):** Criação, leitura, atualização e exclusão para as entidades de Clientes, Fornecedores,
  Funcionários e Produtos.
* **Módulo de Operações (Vendas):** Interface para realização de vendas, vinculando produtos, aplicando descontos e
  registrando o funcionário responsável.
* **Persistência Relacional:** Criação do esquema do banco de dados (tabelas, chaves primárias e estrangeiras) e
  execução de comandos SQL via Java.

### Fora do Escopo

* Integração com hardware de automação comercial (balanças, impressoras fiscais, leitores de código de barras físicos).
* Módulo de e-commerce ou interface web/mobile.
* Sistema complexo de contabilidade e emissão de notas fiscais eletrônicas (NF-e).

---

## Lista de Requisitos

### Requisitos Funcionais (RF)

| Identificador | Nome                          | Descrição                                                                                                                                                                                   |
|---------------|-------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **RF-001**    | Gerenciamento de Produtos     | O sistema deve permitir o cadastro, alteração, exclusão física ou lógica e listagem de produtos, contendo código, nome, preço de custo, preço de venda, quantidade em estoque e fornecedor. |
| **RF-002**    | Gerenciamento de Clientes     | O sistema deve cadastrar clientes contendo CPF, nome, telefone, e-mail e pontos de fidelidade.                                                                                              |
| **RF-003**    | Gerenciamento de Funcionários | O sistema deve manter o registro de funcionários com CPF, nome, cargo, salário e credenciais de acesso ao sistema.                                                                          |
| **RF-004**    | Gerenciamento de Fornecedores | O sistema deve registrar fornecedores com CNPJ, razão social, telefone e categoria de produtos fornecidos.                                                                                  |
| **RF-005**    | Registro de Vendas            | O sistema deve realizar a baixa de produtos no estoque ao finalizar uma venda, calculando o valor total e associando a transação a um cliente e a um funcionário.                           |
| **RF-006**    | Alerta de Estoque             | O sistema deve emitir um aviso visual ou relatório quando um produto atingir a quantidade mínima estipulada em estoque.                                                                     |

### Requisitos Não Funcionais (RNF)

* **RNF-001 (Persistência):** Os dados devem ser armazenados de forma persistente em um banco de dados relacional (
  MySQL, PostgreSQL ou SQLite).
* **RNF-002 (Paradigma):** O código-fonte deve aplicar rigorosamente os conceitos de POO: herança, polimorfismo,
  encapsulamento e abstração.
* **RNF-003 (Robustez):** O sistema deve conter tratamento de exceções adequado para falhas de conexão com o banco de
  dados (`SQLException`) e entradas de dados inválidas.
* **RNF-004 (Segurança):** Senhas de funcionários devem ser armazenadas de forma segura no banco de dados (utilizando
  funções de hash) ou o sistema deve validar níveis de acesso por cargo.