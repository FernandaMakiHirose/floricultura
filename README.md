# Spring Boot Microsserviços
Todo microsserviço tem um ponto de entrada (na maior parte das vezes chamado de Gateway), onde o lado do cliente faz requisições Rest para os microsserviços, geralmente cada microsserviço tem um banco de dados (ou um serviço de autenticação), nesse repositório todos os microsserviços terão apenas um banco de dados. Para saber qual requisição será mandada para determinado microsserviço usamos o Service Discovery Server. O DMZ é uma zona protegida contra acessos externos. 

## Sobre o Projeto
![Screenshot_2](https://user-images.githubusercontent.com/72028645/153642553-dfcd7fdb-4c7f-419a-bb43-f1ffa96cd42f.png)

## Requisitos
- MariaDB, HeidiSQL
