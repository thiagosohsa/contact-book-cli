# Contact Book CLI (Java)

Aplicação simples de linha de comando (CLI) para gerenciamento de contatos, desenvolvida em Java puro, com foco em boas práticas de organização, separação de responsabilidades e testabilidade.

## Funcionalidades

- Adicionar contatos (com validação de email e normalização de telefone)
- Listar contatos em ordem alfabética
- Buscar contatos por nome
- Remover contatos com confirmação
- Persistência em arquivo CSV
- Mensagens padronizadas para melhor experiência no terminal

## Arquitetura

O projeto segue uma separação simples de responsabilidades:

- **App**  
  Responsável apenas pela interação com o usuário (entrada e saída no terminal).

- **Service (ContactService)**  
  Contém as regras de negócio:
    - validação de email
    - verificação de duplicidade
    - normalização de telefone
    - ordenação e busca

- **Repository (ContactRepository)**  
  Abstrai o mecanismo de persistência.  
  Possui duas implementações:
    - `ContactStorage` (CSV)
    - `InMemoryContactRepository` (usado em testes)

Essa abordagem desacopla a lógica de negócio da infraestrutura e facilita testes e manutenção.

## Testes

O projeto possui testes unitários para o `ContactService`, utilizando um repositório em memória, evitando dependência de arquivos ou I/O durante os testes.

## Tecnologias utilizadas

- Java
- JUnit 5
- Persistência simples em CSV
- Git para versionamento

## Como executar

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