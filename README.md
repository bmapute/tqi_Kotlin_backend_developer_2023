# Order API  TQI kotlin Developer 2023

Desenvolvimento de uma API de gestão de pedidos com SpringBoot+Kotlin


## Tecnologias / Frameworks / IDE

-   SpringBoot 3.1.2
-  Kotlin
-   Maven
-   SpringData JPA
-   PostgreSQL
-   Flyway
-   Docker
-   Java 17
  - Junit
-  Intellij

### Design Arquitetural

Para o desenvolvimento do projeto foi utilizado a padrão arquitetural **ports and adapters - arquitetura hexagonal**
-   Camada de Aplicação (controllers, request e response dtos, bean validations)
-   Camada de Domínio (modelos, interface repositório, serviços)
-   Camada de Infraestrutura (configs, exception handle, jpa repository, entidade, proxys que implementam interfaces de repositório e utilizam repositório  jpa para comunicação com banco de dados)
-   Testes

###  Funcionalidades

A solução contem as seguintes funcionalidades/Recursos

- Cadastro de gestão de categorias de produtos (esta é a funcionalidade que permite criar, atualizar, listas todas, buscar uma especifica e deletar)
- Cadastro de produto (permite cadastro, atualização de todos atributos, atualização parcial caso seja necessário atualizar algum atributo em especifico, listar todos produtos cadastrados, buscar um produto em especifo e deletar )
- Gestão de pedido (Esta funcionalidade é a responsavel criar  pedido o mesmo é inicialmente criado num estado inicial de **CREATED**,  o pedido neste estado permite adicionar mais itens/produtos, deletar produtos caso necessário, o pedido pode ser alterado para finalizado uma vez finalizado o não é possivel adiconar ou remover itens/produtos)

### Instrução de Uso
No terminal/Console
1. Faça um clone do projeto para sua máquina: `https://github.com/bmapute/tqi_Kotlin_backend_developer_2023.git`
2. Entre na pasta raiz do projeto `cd`
3. Executar o comando `docker-compose up` para subir a base de dados
4. Executar o comando `./mvnw spring-boot:run`
5. Para facilitar nos testes a aplicação disponibiliza swagger ui, para usar abir o navegador e passar a seguinte url `http://localhost:8080/swagger-ui/index.html`

****Visando facilitar a demostração da aplicação é  recomendado usar IDE como por exemplo o IntelliJ IDEA e executar o projeto através da IDE** **


