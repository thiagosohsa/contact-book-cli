# Contact Book CLI (Java)

Aplicação de linha de comando (CLI) para gerenciamento de contatos, desenvolvida em **Java puro**, com foco em **organização de código, separação de responsabilidades e testabilidade**, sem uso de frameworks.

O projeto foi construído de forma incremental, aplicando princípios básicos de engenharia de software em um contexto simples, priorizando clareza e controle do fluxo de dados.

## Funcionalidades

- Adicionar contatos com:
    - validação de formato de email
    - verificação de duplicidade por email
    - normalização de telefone
- Listar contatos em ordem alfabética
- Buscar contatos por nome (parcial)
- Remover contatos com confirmação
- Persistência em arquivo CSV
- Mensagens padronizadas para melhor experiência no terminal

## Arquitetura

O projeto segue uma separação clara de responsabilidades:

### App
Responsável **exclusivamente** pela interação com o usuário:
- leitura de dados do terminal
- exibição de mensagens
- controle do menu

Não contém regras de negócio.

### Service — `ContactService`
Camada de regras de negócio, responsável por:
- validação de email
- prevenção de contatos duplicados
- normalização de telefone
- ordenação e busca de contatos

Essa camada não depende de entrada/saída do terminal.

### Repository — `ContactRepository`
Abstração da persistência de dados.

Implementações:
- `ContactStorage` — persistência em CSV
- `InMemoryContactRepository` — utilizada em testes

Essa abordagem desacopla a lógica de negócio da infraestrutura, facilitando testes e manutenção.

## Testes

O projeto possui testes unitários para o `ContactService`, utilizando um repositório em memória, evitando dependência de arquivos ou operações de I/O durante os testes.

## Tecnologias Utilizadas

- Java
- JUnit 5
- Persistência simples em CSV
- Git para versionamento

## Como Executar:

1. Clone o repositório:
```bash
git clone https://github.com/thiagosohsa/contact-book-cli.git
```

2. Compile:
```bash
javac App.java
```

3. Execute:
```bash
java App
```
