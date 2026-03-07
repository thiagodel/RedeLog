# RedeLog API

API backend do sistema **RedeLog**, uma plataforma de gerenciamento logístico para controle de entregas, motoristas e clientes.

O projeto foi desenvolvido utilizando **Java + Spring Boot** para fornecer uma API REST consumida por aplicações **desktop (Electron)** e **mobile (Flutter)**.

---

# Arquitetura do Sistema

```
Flutter App (Entregador)
        │
        ▼
Electron Dashboard (Admin / Filial)
        │
        ▼
        API REST
   (Spring Boot + Spring Web)
        │
        ▼
      MySQL Database
```

---

# Tecnologias Utilizadas

Backend

* Java 17
* Spring Boot
* Spring Web
* Spring Data JPA
* Lombok
* MySQL

Ferramentas

* Maven
* IntelliJ IDEA / VSCode
* Postman / Insomnia

---

# Estrutura do Projeto

```
src/main/java/com/redelog/api

controller     → Endpoints da API
service        → Regras de negócio
repository     → Acesso ao banco de dados
model          → Entidades JPA
dto            → Objetos de transferência
config         → Configurações do projeto
```

Estrutura completa:

```
redelog-api
│
├── controller
│     EntregaController.java
│     MotoristaController.java
│
├── service
│     EntregaService.java
│     MotoristaService.java
│
├── repository
│     EntregaRepository.java
│     MotoristaRepository.java
│
├── model
│     Entrega.java
│     Motorista.java
│     Cliente.java
│
├── dto
│     EntregaDTO.java
│
└── RedelogApiApplication.java
```

---

# Configuração do Banco de Dados

Arquivo:

```
src/main/resources/application.properties
```

Exemplo de configuração:

```
spring.datasource.url=jdbc:mysql://localhost:3306/redelog
spring.datasource.username=root
spring.datasource.password=123456

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

---

# Executando o Projeto

### 1 - Clonar o repositório

```
git clone https://github.com/seu-usuario/redelog-api.git
```

### 2 - Entrar no diretório

```
cd redelog-api
```

### 3 - Rodar o projeto

```
./mvnw spring-boot:run
```

Ou rodar diretamente pela IDE.

---

# Endpoints Principais

### Entregas

```
GET /entregas
GET /entregas/{id}
POST /entregas
PUT /entregas/{id}
```

### Motoristas

```
GET /motoristas
POST /motoristas
```

### Autenticação

```
POST /login
```

---

# Status de Entrega

O sistema utiliza os seguintes status:

```
PENDENTE
EM_ROTA
ENTREGUE
CANCELADO
```

---

# Integrações

O backend é consumido por:

* Aplicação Desktop (Electron)
* Aplicação Mobile para entregadores (Flutter)

---

# Objetivo do Projeto

O RedeLog tem como objetivo demonstrar uma arquitetura completa de sistema logístico, incluindo:

* Gestão de entregas
* Controle de motoristas
* Rastreamento de pedidos
* Confirmação de entrega
* Integração entre desktop e mobile

---

# Autor

Thiago Delmiro / Guilherme Augusto / Ruan Lucas
