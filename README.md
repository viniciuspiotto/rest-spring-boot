# Aplicação API RESTful - Spring boot

## Sumário

- [Visão Geral](#visão-geral)
- [Estrutura de Diretórios](#estrutura-de-diretórios)
- [Configuração e Execução](#configuração-e-execução)
- [Funcionalidades Implementadas](#funcionalidades-implementadas)
- [Endpoints da API](#endpoints-da-api)
- [Tecnologias Utilizadas](#tecnologias-utilizadas)

## Visão Geral

Esta aplicação foi desenvolvida utilizando Java Spring Boot para a construção de uma API Restful. O projeto segue boas práticas de arquitetura, com uma separação clara entre camadas para garantir um baixo acoplamento e alta coesão.

## Estrutura de Diretórios

A estrutura principal do projeto é a seguinte:

```
├── docker-compose.yml
├── pom.xml
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com.piotto.apigateway
│   │   │       ├── config
│   │   │       ├── controller
│   │   │       ├── dto
│   │   │       ├── exceptions
│   │   │       ├── mapper
│   │   │       ├── model
│   │   │       ├── repositories
│   │   │       ├── serialization
│   │   │       ├── services
│   │   │       ├── Startup.java
│   │   │       └── util
│   │   └── resources
│   │       ├── application.yml
│   │       └── db/migration
│   └── test
│       └── java
│           └── com.piotto.apigateway
└── target
```

## Configuração e Execução

### Pré-requisitos

- Java 11 ou superior
- Docker e Docker Compose
- Maven

### Passos para Configuração e Execução

1. **Clonar o repositório:**
   ```bash
   git clone https://github.com/viniciuspiotto/rest-spring-boot.git
   cd /rest-spring-boot.git
   ```

2. **Construir a aplicação:**
   ```bash
   ./mvnw clean install
   ```

3. **Iniciar os serviços com Docker Compose:**
   ```bash
   docker-compose up
   ```

4. **Acessar a aplicação:**
   - A API estará disponível em `http://localhost:8080`
   - A documentação Swagger pode ser acessada em `http://localhost:8080/swagger-ui.html`

## Funcionalidades Implementadas

- **CRUD de Livros e Pessoas:**
  - Criação, leitura, atualização e deleção de registros de livros e pessoas.
  
- **Padrão DTO:**
  - Uso do MapStruct para conversão entre entidades e DTOs.

- **Migrations com Flyway:**
  - Controle de versão do banco de dados utilizando Flyway.

- **Serialização:**
  - Suporte à serialização de dados em JSON, XML e YAML.

- **HATEOAS:**
  - Implementação de HATEOAS para enriquecer as respostas da API.

- **Swagger:**
  - Integração com Swagger para documentação da API.

## Endpoints da API

### BookController

- **GET /books** - Retorna a lista de livros.
- **GET /books/{id}** - Retorna um livro específico por ID.
- **POST /books** - Cria um novo livro.
- **PUT /books/{id}** - Atualiza um livro existente por ID.
- **DELETE /books/{id}** - Deleta um livro por ID.

### PersonController

- **GET /persons** - Retorna a lista de pessoas.
- **GET /persons/{id}** - Retorna uma pessoa específica por ID.
- **POST /persons** - Cria uma nova pessoa.
- **PUT /persons/{id}** - Atualiza uma pessoa existente por ID.
- **DELETE /persons/{id}** - Deleta uma pessoa por ID.

## Tecnologias Utilizadas

- **Java Spring Boot:** Framework principal para a construção da API Restful.
- **Docker:** Para conteinerização da aplicação.
- **Docker Compose:** Para orquestração de múltiplos contêineres.
- **Maven:** Gerenciamento de dependências e automação de build.
- **Flyway:** Migração e versionamento do banco de dados.
- **MapStruct:** Mapeamento de objetos (DTOs).
- **Swagger:** Documentação interativa da API.
- **HATEOAS:** Hypermedia as the Engine of Application State, para enriquecer as respostas da API.

## Contribuição

1. **Fork o repositório**
2. **Crie uma branch para sua feature** (`git checkout -b feature/nova-feature`)
3. **Commit suas alterações** (`git commit -m 'Adicionando nova feature'`)
4. **Faça o push para a branch** (`git push origin feature/nova-feature`)
5. **Abra um Pull Request**

## Licença

Este projeto está licenciado sob a licença MIT - veja o arquivo [LICENSE](LICENSE) para mais detalhes.

## Contato

Para mais informações, entre em contato com o mantenedor do projeto:

- **Nome:** Vinícius Piotto
- **Email:** viniciuspiotto.dev@gmail.com
- **LinkedIn:** https://www.linkedin.com/in/viniciushpiotto/
