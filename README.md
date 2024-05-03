# Projeto de Cotação de Moedas com Spring Boot

Este projeto é uma aplicação Spring Boot desenvolvida para interagir
com a [API de Moedas](https://docs.awesomeapi.com.br/api-de-moedas) e proporcionar funcionalidades relacionadas
à cotação de moedas em tempo real, gerenciamento de usuários
e manipulação de favoritos.

## *** Funcionalidades ***

### Criação de Usuario

É possível criar um novo usuário utilizando o seguinte endpoint:

   + POST /api/create

Enviar o seguinte JSON no corpo da requisição:

    {
    "username": "usuario",
    "password": "senha"
    }


O novo usuário será adicionado ao banco de dados.

####  * Conversão de moeda

Realiza a conversão entre duas moedas e retorna o valor convertido,
salvando o resultado no banco de dados.

   + GET /api/quotes/convert/{moeda1}/{moeda2}/{quantidade}

Substitua {moeda1}, {moeda2} e {quantidade} pelos valores desejados.

#### * adicinando cotação em favoritos no usuario

Permite adicionar uma cotação específica aos favoritos de um usuário.

   + POST /api/{userId}/favoriteCurrencies/{quoteId}

Substitua {userId} pelo ID do usuário e {quoteId} pelo ID da cotação desejada.

### Configuração e Execução

#### Pré-requisitos

+ JDK 16 ou superior
+ Maven para gerenciamento e construção do projeto
+ Postman ou qualquer cliente HTTP para testar a API

#### Executando o Projeto

1. Clone o repositório:

        git clone URL_DO_REPOSITORIO

2. Entre no diretório do projeto:

        cd NOME_DO_DIRETORIO


3. Execute o projeto com Maven:

        mvn spring-boot:run

4. Acesse http://localhost:8080 para começar a utilizar as funcionalidades via API.