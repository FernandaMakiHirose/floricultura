# Spring Boot Microsserviços
Todo microsserviço tem um ponto de entrada (na maior parte das vezes chamado de Gateway), onde o lado do cliente faz requisições Rest para os microsserviços, geralmente cada microsserviço tem um banco de dados (ou um serviço de autenticação), nesse repositório todos os microsserviços terão apenas um banco de dados. Para saber qual requisição será mandada para determinado microsserviço usamos o Service Discovery Server. O DMZ é uma zona protegida contra acessos externos. 

## Sobre o Projeto
![Screenshot_2](https://user-images.githubusercontent.com/72028645/153642553-dfcd7fdb-4c7f-419a-bb43-f1ffa96cd42f.png)

### Dependências
- loja: Spring Boot Starter Web
- fornecedor: JPA, Spring Boot Starter Web, MySQL Driver
- eureka-server: Eureka
- config-server: 

## Requisitos
- MariaDB, HeidiSQL.
- Java.
- IDE/Terminal.

## O que aprendemos nesse projeto?
- Teremos três microsserviços: Fornecedor, Loja e Transportador.
- Uma apresentação da modelagem focado em DDD (Domain Driven Design).
  - Quebraremos o domínio em contextos menores (bounded context).
  - Um microsserviço é a implementação de um contexto.
- O uso do Postman como cliente HTTP.
- A criação do microsserviço Loja usando Spring Boot.

- A integração entre microsserviços com RestTemplate.
  - O RestTemplate do Spring permite chamadas HTTP de alto nível.
- Um introdução ao Service discovery e Service registry.
  - Service registry é um servidor central, onde todos os microsserviços ficam cadastrados (nome e IP/porta).
  - Service discovery é um mecanismo de descoberta do IP do microsserviço pelo nome. Sem ele a aplicação não consegue encontrar os microsserviço.
  - Dessa forma, nenhum microsserviço fica acoplado ao outro pelo IP/porta.
- A implementação do service registry através do Eureka Server.
- Como registrar da Loja e do Fornecedor no Eureka Server.
- A resolução do IP/porta através do nome do microsserviço nas requisições.

## O que é síncrona? 
A cada requisição, a execução da aplicação para, esperando uma resposta.

## Como adicionar projetos dentro de um diretório raiz do jeito certo?
- Para adicionar um projeto, antes exclua a pasta `.idea`, adicione o novo projeto, feche e abra a janela do diretório raiz. [Solução](https://qastack.com.br/programming/11454822/import-maven-dependencies-in-intellij-idea).
