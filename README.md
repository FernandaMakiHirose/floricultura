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

- Que um servidor de configuração é o lugar central para definir as configurações dos serviços.
- Que todas as configurações dos microsserviços devem ficar externalizadas e centralizadas.
- Que o Spring Config Server é uma implementação do servidor do projeto Spring Cloud.
- Sobre a integração dos microsserviços com o servidor de configuração.
  - Para tal, devemos configurar o nome do microsserviço, profile e URL do Config Server.
- Que existem várias formas de definir um repositório de configurações, entre elas o GitHub.

- Que o Client Side Load Balancing (CSLB) é o cliente HTTP que decide qual microsserviço recebe a requisição.
  - Isto é importante, pois pode ter várias instâncias do mesmo serviço no ar.
  - A configuração do CSLB é feita a partir da anotação @LoadBalanced, na criação do RestTemplate.
  - Como implementação do CSLB, usamos um projeto chamado Ribbon, que faz parte do Spring Cloud Netflix.
- Para descobrir quais clientes existem, podemos injetar a interface DiscoveryClient.
- Como alternativa ao RestTemplate, podemos usar o Spring Feign, que já usa o Ribbon para CSLB.
- O Spring Feign exige apenas uma interface, com a definição e mapeamento dos métodos que executarão a requisição.
  - Toda a implementação da interface é gerada pelo Spring Feign.

- Como se trata de uma arquitetura distribuída, temos logs distribuídos.
  - Ou seja, cada microsserviço (e instância dele) possui o seu log.
  - Isso dificulta o acompanhamento e rastreabilidade das requisições.
- Para unificar os logs, precisamos de agregadores de log.
  - Como implementação de um agregador, usamos o Papertrail, um agregador como serviço.
- Usamos a biblioteca Logback para gerar e enviar os logs ao agregador.
  - O Logback possui um appender, que possibilita o envio dos logs (criamos um arquivo logback.xml.
- Para acompanhar uma transação nos logs, usamos uma correlation-id.
  - A correltation-id é um identificador da transação, que é passada de requisição pra requisição.
  - Dessa forma, podemos entender quais requisições fazem parte da mesma transação.
- A biblioteca Spring Sleuth, que é usada para gerar a correlation-id.

## Consolidando o seu conhecimento
### O que é síncrona? 
A cada requisição, a execução da aplicação para, esperando uma resposta.

### Como adicionar projetos dentro de um diretório raiz do jeito certo?
- Para adicionar um projeto, antes exclua a pasta `.idea`, adicione o novo projeto, feche e abra a janela do diretório raiz. [Solução](https://qastack.com.br/programming/11454822/import-maven-dependencies-in-intellij-idea).

### Por que foi necessário separar a configuração do spring-config em outro arquivo (bootstrap.yml)?
Para que todas as configurações, principalmente as de banco de dados, estejam disponíveis antes do início do carregamento do contexto da nossa aplicação.

### Temos algumas tecnologias trabalhando em conjunto para prover a funcionalidade de Load Balancing. Como elas estão integradas?
O Eureka Client é usado não apenas para se registrar no Eureka Server, mas também para buscar as outras instâncias que já se registraram. O Ribbon utiliza essas informações para aplicar o seu algoritmo de Load Balancing no momento em que o RestTemplate efetua as suas requisições para uma outra aplicação.

### Load Balancing é o processo de distribuir as requisições vindas dos usuários para as várias instâncias disponíveis. Como funciona o Client Side Load Balancing que estamos utilizando nas requisições da loja?
A aplicação cliente conhece as instâncias disponíveis e, usando algum algoritmo de rotação do Ribbon, acessa uma instância diferente a cada requisição do usuário.

### Como a geração dos logs são impactados com a arquitetura em microsserviços?
Assim como nos sistemas monolíticos, temos logs separados em máquinas diferentes, mas, apenas nos microsserviços, a lógica de negócio também está quebrada em logs diferentes.

### O que ganhamos com agregação de logs e a geração de ID de correlação?
Além da facilidade de acessar em um único local todo o log gerado pela aplicação, temos também a possibilidade de filtrar os logs em uma única transação. Com isso, através da formatação adequada do log, sabemos não só onde os erros foram gerados, mas em que momento aconteceu, pois os logs são escritos com os dados de milissegundos logo no início da linha.

## Conclusão 
O microsserviço não vai compartilhar o seu banco de dados com outros microsserviços, ele tem realmente a implementação de uma funcionalidade, de certa forma independente das outras. Nós implementamos o microsserviço da loja e do fornecedor, e nos preocupamos com a comunicação entre os dois.

Como é que um microsserviço encontra um outro microsserviço, dado que temos várias instâncias de microsserviços, podemos pode ter várias instâncias? Então aprendemos a configurar o Eureka e implementamos a configuração dos nossos microssserviços para, ao subirem, eles se registrarem no Eureka, que é um service registry, um service discovery também.

Agora que os microsserviços conseguem se comunicar, como é que, ao fazer uma requisição entre um microsserviço e outro, ele consegue utilizar esses dados do Eureka? Nós vimos como fazer isso, mais para o final, usando o Spring Feign, então ele faz isso automaticamente para nós. O termo que nós usamos é chamado de client-side load-balancing.

Então quando um microsserviço faz uma requisição para outro microsserviço, ele tem, dado as informações do Eureka, ele tem as várias instâncias disponíveis, ele vai selecionar uma e vai rotacionar, para fazer esse load balance para nós. Uma outra coisa é que dado um volume maior ou menor de requisições dos usuários, vamos ter mais instâncias ou menos instâncias de microsserviços.

Apesar de que essa configuração, essa gestão de microsserviços não é tema do treinamento, mas os nossos microsserviços precisam lidar bem com isso. Nós não fazemos configuração de microsserviço na mão, nós exportamos para outro servidor, então além do Eureka, nós temos um servidor de configuração, onde temos todas as configurações, que na verdade não estão neste servidor de configuração, mas colocamos no GitHub.

É legal que as configurações, até percebemos que conseguimos fazer a configuração de desenvolvimento, certificação, uma configuração default, de produção e etc. E isso versionado, já que está no GitHub, é fácil inclusive de você gerar uma versão nova, você acessa rápido às configurações e está tudo ok. É bem prático lidar com novas configurações.

Depois disso, o que temos são os nossos microsserviços se comunicando bem, subindo, e conseguindo criar várias instâncias deles, eles sabendo buscar as informações de outras instâncias e buscar as configurações próprias que eles precisam para subir, para se comunicar com o banco de dados.

Nós percebemos que vários microsserviços, várias instâncias de microsserviços, geram logs independentes um do outro, e precisamos entender a função do agregador de logs, que no nosso caso foi o Papertrail. Então configuramos os nossos microsserviços para eles mandarem todas as informações de log para um servidor central, que nós usamos na internet.

Depois disso, vimos os logs serem gerados, e pensamos: como é que eu faço para identificar os logs gerados, distribuídos em vários microsserviços, por uma transação fruto de uma requisição do usuário? Então o usuário fez uma requisição, essa requisição gerou várias outras requisições entre os microsserviços, que geraram vários logs e eu gostaria de entender, de identificar esses logs, para acompanhar essas transações.

Foi onde implementamos o Spring Sleuth. Então conseguimos já entender não só o que é o microsserviço, configurar, expor essas configurações, exportar essas configurações, configurar os microsserviços de forma se encontrem automaticamente, e também conseguimos monitorar os nossos microsserviços com o agregador de log e com aquele correlation id, que é gerado pelo Spring Sleuth. Todas essas tecnologias do Spring Cloud.

Esse foi o assunto desse treinamento. Mas não para por aí, nós temos um treinamento a seguir, que vai lidar com questões mais de segurança e tratamento de erro, então quando uma comunicação entre um microsserviço e outro, ela tem um problema, como é que nos recuperamos? Porque uma requisição lenta, imagine uma enxurrada de requisições para um microsserviço, está demorando alguns segundos para processar cada uma dessas requisições.

Ele vai perdendo as threads dele, desse microsserviço, até o momento em que ele não consegue mais responder. Então como fazemos para lidar com isso? E, dado que um erro aconteça, como é que respondemos da melhor forma para o usuário? tudo isso nós vamos ver no próximo repositório, assim como vamos aprender a dividir threads entre um endpoint e outro endpoint dentro de um próprio microsserviços.

Vamos entender uma lógica diferente de tratar erros, porque não estamos lidando com uma transação, essa transação que temos dentro de um monolito, de uma aplicação que, dado uma requisição do usuário, ela vai fazendo alterações em banco, vai lendo mensagem na fila de MQ e etc., e no final ou tudo dá certo ou tudo volta, ele dá rollback.

Aqui, com microsserviços, se você começa a fazer a requisição, a metade dela pode dar certo e no meio, ela dá um pau. Como é que eu vou desfazer isso? Como é que eu sei em que estado está um processamento ou ficou um processamento de uma requisição? Como é que eu desfaço isso? Como é que eu lido com transações com um microsserviço, com essa tecnologia? Isso nós vamos ver na próxima.

Depois que temos isso, conseguimos fazer esse tratamento todo de erros, como é que expomos na internet? Porque os microsserviços conseguem se comunicar usando o Eureka e se enxergar, mas se você expõe na internet, os browsers sabem se cadastrar no Eureka, sei lá, e entender quais são os microsserviços que tem, fazer as requisições? Eles não sabem fazer isso.

Então como expomos a nossa aplicação de forma segura, inclusive, na internet? Como lidamos com autenticação e autorização? Eu fiz uma autenticação no servidor da empresa, é uma aplicação só, meus dados de usuário ficam na sessão e todas as requisições que eu fizer, vai um cookie na requisição e a minha aplicação lembra de mim, lembra quem eu sou, e ela verifica se eu tenho a permissão de acesso ou não.

No caso de micorsserviços, você tem várias instâncias que não se conhecem, elas não compartilham estado, elas nem tem estado na verdade, isso é uma característica de microsserviço. Então como lidamos com autenticação e autorização com microsserviço? Tudo isso em um próximo repositório. Até lá, galera.
