# IncidentHub API

Projeto desenvolvido para gerenciamento de ocorrências, contendo cadastro de clientes, endereços, ocorrências e upload de evidências em imagem.

---

## 📌 Objetivo

Criar uma API REST capaz de:

* Gerenciar clientes (Pessoa)
* Gerenciar endereços
* Registrar ocorrências com evidências (imagens)
* Listar ocorrências com filtros, paginação e ordenação
* Finalizar ocorrências (sem permitir alteração após finalização)
* Armazenar dados em banco PostgreSQL
* Armazenar imagens no MinIO (Object Storage)
* Proteger endpoints com autenticação

---

## 🚀 Tecnologias utilizadas

* Java 17
* Spring Boot 4
* Spring Data JPA
* Spring Security
* OAuth2 Resource Server (JWT)
* PostgreSQL
* Flyway (migrations)
* MinIO (armazenamento de arquivos)
* Docker + Docker Compose
* JUnit + Mockito

---

## 📁 Estrutura do projeto

```
src/main/java/br/com/pradela/incidenthub

api/                -> Controllers e DTOs
api/exception/      -> Tratamento de erros
domain/model/       -> Entidades (JPA)
domain/repository/  -> Interfaces de acesso ao banco
domain/service/     -> Regras de negócio
domain/specification/ -> Filtros dinâmicos (Specification)
security/           -> Configuração de segurança (JWT)
```

---

## ⚙️ Como rodar o projeto

### 1. Subir os containers

```bash
docker compose up -d
```

Isso irá subir:

* PostgreSQL
* MinIO
* Aplicação Java

---

### 2. Acessos

* API: http://localhost:8081
* MinIO: http://localhost:9000
  usuário: admin
  senha: admin123

---

### 3. Rodar a aplicação manualmente (opcional)

```bash
mvn spring-boot:run
```

---

## 🔐 Autenticação

A API utiliza autenticação via JWT.

### Login

```http
POST /auth/login
```

Body:

```
username=admin
senha=admin123
```

Retorno:

```
TOKEN JWT
```

---

### Usar token

Adicionar no header:

```
Authorization: Bearer SEU_TOKEN
```

---

## ⏱ Expiração do token

* Token expira em **30 minutos**

---

## 📌 Endpoints principais

### 👤 Pessoa

* POST `/pessoas`
* GET `/pessoas`

---

### 📍 Endereço

* POST `/enderecos`
* GET `/enderecos`

---

### 🚨 Ocorrência

#### Criar ocorrência (com imagem)

```
POST /ocorrencias
```

* multipart/form-data
* inclui dados + imagem

---

#### Listar ocorrências

```
GET /ocorrencias
```

Filtros disponíveis:

* nome
* cpf
* cidade
* data

Paginação:

```
?page=0&size=10
```

Ordenação:

```
?sort=dataOcorrencia,desc
?sort=cidade,asc
```

---

#### Finalizar ocorrência

```
PUT /ocorrencias/{id}/finalizar
```

Regra:

* Não pode alterar após finalizada

---

## 🗄 Banco de dados

* PostgreSQL rodando via Docker
* Migrations feitas com Flyway

---

## 📦 Armazenamento de arquivos

* Utiliza MinIO
* Upload automático ao registrar ocorrência
* Retorna URL pública da imagem

---

## 🧪 Testes

Foi implementado teste unitário simples utilizando:

* JUnit 5
* Mockito

Arquivo:

```
src/test/java/.../OcorrenciaServiceTest.java
```

---

## 🐳 Docker

O projeto utiliza Docker Compose para orquestrar:

* Banco de dados
* Storage (MinIO)
* Aplicação

Arquivo:

```
docker-compose.yml
```

---

## ⚠️ Observações

* Algumas implementações foram feitas de forma mais simples para garantir a entrega completa do projeto
* Estrutura preparada para evoluções futuras

---

## 📈 Melhorias futuras

* Implementar autenticação com provider externo (Keycloak)
* Melhorar tratamento de exceções
* Adicionar testes de integração
* Melhorar validações
* Criar front-end para consumo da API

---

## 👨‍💻 Autor

Fernando Pradela
