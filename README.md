# Problema de Segurança em APIs: Falha BOLA

Trabalho de conclusão de curso apresentado ao Centro Educacional Tutores como requisito parcial para
aprovação no curso Técnico em Informática.

Carine Silva

Kauã Matheus

Wendel Gabriel
  
Orientador Prof. Hudson Luiz

------------------------------

### Este é um simples projeto em que usuários realizam cadastro e acessam seus perfis, desenvolvido para exemplificar de forma prática e simples como ocorre a falha BOLA. Há duas branches disponíveis, uma versão com o sistema sem veríficação de autorização, ou seja, com a vulnerabilidade BOLA, e outra com um mecanismo de verificação de autorização implementado.

------------------------------

## Conceitos trabalhados:

- BOLA - Broken Object Level Authorization (Quebra de Autorização a Nível de Objeto): falha de API devido falta de mecanismos de autorização para acesso a objetos, tornando o sistema vulnerável ao acesso de informações por usuários não autorizados.
- OWASP - Open Worldwide Application Security Project: fundação sem fins lucrativos, de atuação global e comunidade aberta, cujo objetivo é monitorar e propor melhorias de segurança para aplicações web e softwares em geral.
- Autenticação: verificação de credenciais para liberação de acesso ao sistema.
- Autorização: verificação de permissão de acesso do usuásio ao objeto requerido.
- Front-end: interface onde o usuário realiza comandos
- Back-end: lógica do software
- API: técnica usada para realizar a conexão entre sistemas
- Banco de Dados: local de armazenamento dos dados
- Spring: ecossistema que reúne frameworks e ferramentas que facilitam o desenvolvimento de aplicações Java.
```
Ecossistema Spring
│
├── Spring Framework
├── Spring Boot
├── Spring Data JPA
├── Spring Security
├── Spring Web
└── outros módulos
```
- Spring Boot: framework que facilita configuração e execução de aplicações Java.
- Spring Data JPA: módulo específico do ecossistema Spring para tratar de persistência de dados.
- DAO (Data Access Object): padrão usado para separar o acesso ao banco de dados da lógica do sistema.
O DAO encapsula os comandos SQL e centraliza o CRUD em uma classe específica, evitando que comandos do
banco fiquem espalhados pelo sistema. Usando Spring, os DAO's são criados automaticamente pelo Spring Data JPA.

```
SQL ← DAO ← Spring Data JPA
          ↑
     usado dentro do Spring Boot
```

## Ferramentas utilizadas:

- IDE's
  - VS Code
  - Elipse: IDE utilizada para desenvolvimento Java
  - MySQL Workbench: IDE para desenvolvimento de SQL e administração

- Front-end:
  - HTML (linguagem de marcação)
  - CSS (linguagem de estilização)
  - JavaScript (linguagem lógica)

- Back-end:
  - Java (linguagem lógica)

- Banco de dados:
  - SQL: (Structured Query Language) - linguagem padrão de consulta e manipulação de dados
  - MariaDB: SGBD (Sistema Gerenciador de Banco de Dados) = software que guarda os dados e executa os comandos da linguagem

- API:
  - Spring Initializr: ferramenta para criação de API Java de forma simplificada e rápida. Gera código pronto com as configurações iniciais. <https://start.spring.io/>

- Arquivo:
  - JSON 
  

 ```
 |‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾| |‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾|
 |             SQL                           JAVA - SPRING BOOT            | | HTML - CSS - JAVASCRIPT   |
 |                                                                         | |                           |
 |        Banco de Dados                         API REST                  | |        FRONT-END          |
 |                                                                         | | |‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾|  | 
 |        |‾‾‾‾‾‾‾‾‾‾‾‾|   <------      GET       A       GET      ------> | | |       USUÁRIO        |  |
 |        |____________|               POST       P       POST             | | |  |‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾|  |  |
 |        |            |    ------>     PUT       I       PUT      <------ | | |  |________________|  |  | 
 |        |            |             DELETE               DELETE           | | |                      |  |
 |        |‾‾‾‾‾‾‾‾‾‾‾‾|                                                   | | |        SENHA         |  |
 |        |____________|                                                   | | |  |‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾|  |  |
 |                                                                         | | |  |________________|  |  |
 | |‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾|      Usuario=                          | | |                      |  |
 | |           TABELA             |      {                                 | | |    |‾‾‾‾‾‾‾‾‾‾‾‾‾|   |  |
 | |‾‾‾‾‾‾‾‾‾‾‾‾|‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾|        "id": 1,                        | | |    |   ENTRAR    |   |  |
 | | ID         | 1               |        "nome": "jose",                 | | |    |_____________|   |  | 
 | |‾‾‾‾‾‾‾‾‾‾‾‾|‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾|        "email": "email@gmail.com",     | | |______________________|  |
 | | Nome       | jose            |        "senha": "***",                 | |                           |
 | |‾‾‾‾‾‾‾‾‾‾‾‾|‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾|        "tel": "000"                    | |                           |
 | | Email      | email@gmail.com |       }                                | |                           |
 | |‾‾‾‾‾‾‾‾‾‾‾‾|‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾|          JSON                          | |                           |
 | | Senha      | ***             |                                        | |                           |
 | |‾‾‾‾‾‾‾‾‾‾‾‾|‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾|                                        | |                           |
 | | Tel        | 000             |                                        | |                           |
 |  ‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾        BACK-END                         | |        FRONT-END          |
 |_________________________________________________________________________| |___________________________|
```
------------------------------

## Camadas

Projeto organizado seguindo o padrão de Camadas (Layered Architecture). Cada camada possui uma responsabilidade única e bem definida para manter o código limpo, testável e fácil de manter.  

### 1. Controller
É a porta de entrada da sua API. Ela recebe as requisições HTTP do cliente (como o frontend ou o Postman) e devolve a resposta final. 

* O que faz: Mapeia os endpoints (URLs), define os verbos HTTP (@GetMapping, @PostMapping), valida os dados de entrada rudimentares e chama a camada de serviço.
* Regra de ouro: Não deve conter lógica de negócio complexa.

### 2. Service
É o "cérebro" da aplicação, onde fica toda a lógica de negócio. 

* O que faz: Processa as regras da sua aplicação (ex: calcular descontos, verificar se um e-mail já existe, disparar notificações). Ela busca e envia dados para o repositório e decide o que fazer com eles.
* Regra de ouro: É aqui que a mágica acontece. Se houver um cálculo ou validação complexa, fica aqui. 

### 3. Repository
É a camada de persistência de dados. Ela faz a ponte direta com o banco de dados. 

* O que faz: Traduz as operações do Java para comandos SQL (ou NoSQL). Geralmente estende interfaces do Spring Data (como JpaRepository) para herdar métodos prontos de salvar, buscar, deletar e atualizar.
* Regra de ouro: Só deve conter código relacionado a consultas e manipulação do banco.

### 4. Model (ou Domain/Entity)
Representa as estruturas de dados e as entidades do mundo real dentro do sistema. 

* O que faz: São classes Java simples (POJOs) mapeadas como tabelas do banco de dados (usando @Entity, por exemplo). Elas definem quais campos um objeto tem (como id, nome, email).
* Regra de ouro: Define o que o dado é, mas não dita as regras de como ele é processado. 

### 5. Application
Geralmente contém a classe principal que inicializa o Spring Boot (a classe com a anotação @SpringBootApplication) e configurações globais do sistema. 

* O que faz: Serve como o ponto de partida do projeto e o container de configurações estruturais (como Beans de segurança, CORS ou propriedades do sistema). 


## Como Elas se Relacionam (Fluxo de uma Requisição)
O fluxo de dados segue uma linha reta e hierárquica. Uma camada superior só conversa com a camada imediatamente abaixo dela. Veja o fluxo de um cliente criando um novo cadastro: 

```
[ Cliente ] -> Envia requisição HTTP (POST /usuarios)
     ↓
[ 1. Controller ] -> Recebe os dados brutos e chama o Service.
     ↓
[ 2. Service ] -> Executa as regras de negócio (ex: "esse e-mail é válido?").
     ↓
[ 3. Repository ] -> Recebe o objeto e salva no banco de dados.
     ↓
[ Banco de Dados ] (Salva a informação)
```

### _Durante todo esse caminho, o Model é a estrutura do dado que está sendo transportada e transformada entre as camadas._

## Estrutra de Pastas

```
── tcc
    ├── Apresentacao_TCC
    ├── frontend
    │   ├── cadastrar-usuario
    │   │   ├── index.html
    │   │   ├── script.js
    │   │   └── style.css
    │   ├── index.html
    │   ├── login
    │   │   ├── index.html
    │   │   ├── script.js
    │   │   └── style.css
    │   └── perfil
    │       ├── index.html
    │       ├── script.js
    │       └── style.css
    ├── projeto_tcc
    │   ├── HELP.md
    │   ├── mvnw
    │   ├── mvnw.cmd
    │   ├── pom.xml
    │   ├── README.md
    │   ├── src
    │   │   ├── main
    │   │   │   ├── java
    │   │   │   │   └── br
    │   │   │   │       └── com
    │   │   │   │           └── criandoapi
    │   │   │   │               └── projeto_tcc
    │   │   │   │                   ├── controller
    │   │   │   │                   │   └── UsuarioController.java
    │   │   │   │                   ├── model
    │   │   │   │                   │   └── Usuario.java
    │   │   │   │                   ├── ProjetoApplication.java
    │   │   │   │                   ├── repository
    │   │   │   │                   │   └── UsuarioRepository.java
    │   │   │   │                   └── service
    │   │   │   │                       └── UsuarioService.java
    │   │   │   └── resources
    │   │   │       ├── application-example.properties
    │   │   │       ├── application.properties
    │   │   │       ├── static
    │   │   │       └── templates
    │   │   └── test
    │   │       ├── java
    │   │       │   └── br
    │   │       │       └── com
    │   │       │           └── criandoapi
    │   │       │               ├── projeto
    │   │       │               └── projeto_tcc
    │   │       │                   └── ProjetoApplicationTests.java
    │   │       └── resources
    │   └── target
    │       ├── classes
    │       │   ├── application-example.properties
    │       │   ├── application.properties
    │       │   ├── br
    │       │   │   └── com
    │       │   │       └── criandoapi
    │       │   │           └── projeto_tcc
    │       │   │               ├── controller
    │       │   │               │   └── UsuarioController.class
    │       │   │               ├── model
    │       │   │               │   └── Usuario.class
    │       │   │               ├── ProjetoApplication.class
    │       │   │               ├── repository
    │       │   │               │   └── UsuarioRepository.class
    │       │   │               └── service
    │       │   │                   └── UsuarioService.class
    │       │   └── META-INF
    │       │       ├── MANIFEST.MF
    │       │       └── maven
    │       │           └── br.com.criandoapi
    │       │               └── projeto_tcc
    │       │                   ├── pom.properties
    │       │                   └── pom.xml
    │       ├── generated-sources
    │       │   └── annotations
    │       ├── maven-status
    │       │   └── maven-compiler-plugin
    │       │       └── compile
    │       │           └── default-compile
    │       │               ├── createdFiles.lst
    │       │               └── inputFiles.lst
    │       └── test-classes
    │           └── br
    │               └── com
    │                   └── criandoapi
    │                       ├── projeto
    │                       └── projeto_tcc
    │                           └── ProjetoApplicationTests.class
    ├── projeto.zip
    └── README
```


### Raiz do Projeto (tcc)

* Apresentacao_TCC: Pasta ou arquivo com os slides da sua banca de TCC.
* projeto.zip: Arquivo compactado com o backup do código-fonte.
* README: Arquivo de texto geral com o resumo do projeto acadêmico.


### Frontend (tcc/frontend)
Camada visual feita em HTML, CSS e JavaScript puro que roda no navegador do usuário.

 Função de Cada Camada index.html (na raiz do front): Tela principal ou portal de entrada da sua aplicação.
* cadastrar-usuario/, login/, perfil/: Pastas que dividem as páginas do sistema.
* index.html: Estrutura visual e os campos daquela tela específica.
   * style.css: Estilização visual (cores, fontes, alinhamentos) da tela.
   * script.js: Comportamento da página. Captura os dados digitados e faz as requisições (fetch/axios) para a sua API Spring Boot.


### Backend (tcc/projeto_tcc)
API desenvolvida em Java com Spring Boot.

### Arquivos de Configuração da Raiz do Backend:

* pom.xml: Arquivo do Maven. Ele gerencia as dependências do projeto (ex: Spring Web, Banco de Dados, Lombok).
* mvnw e mvnw.cmd: Scripts utilitários do Maven Wrapper para rodar o projeto sem precisar ter o Maven instalado globalmente na máquina.
* HELP.md e README.md: Guias gerados pelo Spring Initializr ou escritos por você com instruções de execução da API.

### Código Fonte principal (src/main/java/.../projeto_tcc)

* ProjetoApplication.java: A classe principal que liga o motor do Spring Boot. É ela que você executa para rodar o backend.
* controller/UsuarioController.java: Recebe os dados das requisições vindas do script.js (como login e cadastro) e devolve as respostas em formato JSON.
* model/Usuario.java: A classe que define o modelo do Usuário. Ela mapeia como as colunas (id, nome, senha) se estruturam no banco de dados.
* repository/UsuarioRepository.java: Interface que executa os comandos no banco de dados (inserir, buscar por email, deletar).
* service/UsuarioService.java: Contém as regras de negócio de usuários (ex: criptografar a senha antes de salvar ou validar se o login está correto).

### Arquivos de Recursos (src/main/resources)

* application.properties: Arquivo onde você configura o banco de dados (URL, usuário, senha) e a porta onde a API vai rodar.
* application-example.properties: Um modelo fictício para que outras pessoas saibam quais variáveis de ambiente configurar sem expor suas senhas reais.
* static/ e templates/: Pastas padrão do Spring. Ficam vazias porque seu frontend está separado na raiz do projeto.

### Testes (src/test)

* ProjetoApplicationTests.java: Arquivo onde são escritos os testes automatizados para verificar se as funcionalidades do sistema não vão quebrar no futuro.

### Artefatos de Compilação (target/)

* Todas as classes .class: São os seus arquivos Java originais traduzidos em bytecode para que o computador consiga executá-los. Essa pasta é gerada automaticamente sempre que você compila ou roda o projeto.

