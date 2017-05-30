# selecao-walmart

## Proposito do projeto

Criado para solucao da solucao para Developer da Walmart. 
Utilizado na solução JAX-RS para geracao do token Jwt.

## Compilando o projeto

Projeto está utilizando o Maven, para compila-lo e empacota-lo execute o comando
`mvn clean compile`, `mvn clean package` ou `mvn clean install` na raiz do projeto.

## Execucao do projeto

* Execute o [WildFly](http://wildfly.org/), versao utilizada para o desenvolvomento: v10.1.0 Final
* Sera necessario configurar o datasource do WildFly para conexao com o banco de dados MySQL, clique [aqui](https://docs.jboss.org/author/display/WFLY10/DataSource+configuration) para configuracao
* Utilize o WAR gerado e faça o deploy no WildFly em execucao
* Ao carregar, acesse o link: http://localhost:8080/wallmar-selecao/pages

## Tecnologias Utilizadas

* AngularJS v1.4.3
* Bootstrap v3.3.5
* JavaEE 7
* Json Web Token v0.7.0
* EJB 3
* Maven
* WildFly v10.1.0 Final
* MySQL v5
