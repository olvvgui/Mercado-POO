# Casos de Usos e Atores

---

## Catálogo de Produtos

* **Ator:** Gerente
* **Requisitos:** Cadastrar, Excluir e Editar produtos
* **Descrição:** Permite ao gerente inserir, editar e excluir produtos.

### Fluxos:

#### Adicionar produtos

1. O gerente solicita a inclusão de um novo;
2. O software requisita os dados (nome, valor, quantidade);
3. Os dados são inseridos pelo gerente;
4. O software valida os dados, criando um objeto do tipo Produtos e confirma a adição.

#### Excluir produto

1. O gerente solicita a exclusão de um produto;
2. O software requisita o nome do produto;
3. O gerente preenche o nome do produto;
4. O software valida o nome, exclui o arquivo e retorna mensagem de sucesso.

#### Editar produto

1. O gerente solicita a edição de um produto;
2. O software requisita o nome do produto;
3. O gerente preenche com o nome do produto;
4. O software pergunta se quer alterar o nome, quantidade e/ou valor;
5. O gerente seleciona a opção;
6. O software requisita os dados (nome, quantidade e/ou valor);
7. O gerente preenche os dados;
8. O software retorna uma mensagem de sucesso.

---

## Vendas

* **Ator:** Atendente / Caixa
* **Requisitos:** Registrar venda e atualizar o estoque
* **Descrição:** Permite ao Atendente realizar a venda do produto e atualiza o estoque automaticamente.

### Fluxos:

#### Venda

1. O atendente solicita uma nova venda;
2. O software instancia uma nova venda;
3. O software solicita o nome do produto e quantidade;
4. O atendente insere os dados;
5. O software verifica a disponibilidade do produto;
6. O atendente repete os passos 3 a 5 para todos os produtos;
7. O atendente seleciona o fechamento da venda;
8. O software valida os dados, dá baixa no estoque, calcula o valor da venda e retorna mensagem de sucesso.

---

## Estoque

* **Ator:** Gerente / Sistema
* **Requisitos:** Notificar quando produto no estoque está em falta
* **Descrição:** O sistema irá notificar se a quantidade de produto estiver pouca ou em falta.

### Fluxos:

#### Pós-venda

1. Após a venda o sistema irá verificar se o produto está em falta;
2. Caso o estoque esteja em falta, retornará uma mensagem contendo o nome do produto e a quantidade.

#### Análise do Gestor

1. O gerente irá requisitar uma análise dos produtos em estoque;
2. O sistema irá escanear todos os produtos em estoque;
3. Irá retornar todos os produtos que estiverem em falta e suas quantidades.