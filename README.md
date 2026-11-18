# Bulking API

API REST desenvolvida para o aplicativo **Bulking**, uma aplicação de acompanhamento alimentar e controle nutricional.

O backend é responsável pelo gerenciamento de refeições, alimentos e informações nutricionais, permitindo registrar o consumo diário e acompanhar calorias e macronutrientes.

## 🚀 Tecnologias

- Java 25
- Spring Boot 4.1.2
- Maven
- PostgreSQL 17
- Spring Data JPA
- Docker

## 📁 Arquitetura

O projeto segue uma arquitetura em camadas:

- **Controller:** gerenciamento dos endpoints REST.
- **Service:** regras de negócio.
- **Repository:** acesso e persistência dos dados.
- **Entity:** representação das entidades do banco de dados.
- **DTO:** transferência de dados entre as camadas.

## ⚙️ Como executar

### Pré-requisitos

- Java 25
- Docker e Docker Compose
- Git

### 1. Clone o repositório

```bash
git clone URL_DO_REPOSITORIO
cd bulking-api
```

### 2. Configure as variáveis de ambiente

Copie o arquivo `.env.example` para `.env` e ajuste as credenciais conforme necessário.

### 3. Inicie o PostgreSQL

```bash
docker compose up -d
```

### 4. Execute a aplicação

Configure as variáveis `DB_URL`, `DB_USERNAME` e `DB_PASSWORD` no ambiente de execução e execute:

```bash
./mvnw spring-boot:run
```

No Windows:

```powershell
.\mvnw.cmd spring-boot:run
```

A API estará disponível em:

`http://localhost:8080`

## 📌 Endpoints

### Refeições

| Método | Endpoint | Descrição |
|---|---|---|
| GET | `/meals/daily-summary` | Consulta o resumo nutricional diário |
| POST | `/meals` | Cria uma refeição |

### Alimentos nas refeições

| Método | Endpoint | Descrição |
|---|---|---|
| POST | `/meal-foods` | Adiciona alimento à refeição |
| PUT | `/meal-foods/{id}` | Atualiza alimento registrado |
| DELETE | `/meal-foods/{id}` | Remove alimento da refeição |

## 🧪 Testes

Para executar os testes:

```bash
./mvnw test
```

No Windows:

```powershell
.\mvnw.cmd test
```

## 🎯 Objetivo

O Bulking busca simplificar o acompanhamento alimentar, oferecendo uma experiência prática para registrar refeições e monitorar o consumo diário de calorias e macronutrientes.

---

Desenvolvido como projeto de aprendizado e desenvolvimento de software.
