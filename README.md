# Spring Boot Microsserviços
Todo microsserviço tem um ponto de entrada (na maior parte das vezes chamado de Gateway), onde o lado do cliente faz requisições Rest para os microsserviços, geralmente cada microsserviço tem um banco de dados (ou um serviço de autenticação), nesse repositório todos os microsserviços terão apenas um. Para saber qual requisição será mandada para determinado microsserviço usamos o Service Discovery Server e o DMZ é uma zona protegida contra acessos externos. 

## Sobre o Projeto
O lado do cliente, faz uma requisição pelo Gateway para o serviço de autenticação, o qual retorna um token JWE em Base64, ele é guardado no lado do cliente para todas as requisições futuras, enviamos o token assinado ou assinado e criptografado.
