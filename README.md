# 📚 Literalura

Aplicação console Java que consome a API do [Gutendex](https://gutendex.com), persistindo dados de livros e autores em um banco de dados PostgreSQL usando Spring Boot e JPA. O sistema permite buscar livros pelo título, listar autores, verificar autores vivos em determinado ano, listar livros por idioma, ver os livros mais populares e buscar autores por nome.

---

## 🛠 Tecnologias Utilizadas

- Java 21+
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Jackson (para serialização/deserialização JSON)
- API externa: [Gutendex](https://gutendex.com)
- Maven
- JPA/Hibernate

---

## 🔍 Funcionalidades

- 🔎 Buscar livros pelo título e salvá-los no banco de dados
- 📚 Listar todos os livros registrados
- 👤 Listar todos os autores registrados
- ⏳ Listar autores vivos em determinado ano
- 🌐 Listar livros por idioma
- 🏆 Listar os 10 livros mais baixados
- 🧑‍💼 Buscar autores por nome

---

## ⚙️ Configuração

### Pré-requisitos

- Java 21 ou superior
- PostgreSQL
- Maven

### Variáveis de Ambiente

Configure as variáveis de ambiente no seu sistema ou use um arquivo `.env` com:

```env
DB_NAME=nome_do_banco
DB_USER=seu_usuario
DB_PASSWORD=sua_senha
```

No seu `application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5433/${DB_NAME}
spring.datasource.username=${DB_USER}
spring.datasource.password=${DB_PASSWORD}
spring.datasource.driver-class-name=org.postgresql.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.format_sql=true
```

---

## 🚀 Como Executar

1. Clone o repositório:

```bash
git clone https://github.com/JefersonFreire/literalura-challenge-java.git
cd literalura
```

2. Crie o banco de dados PostgreSQL:

```sql
CREATE DATABASE literalura;
```

3. Execute a aplicação:

```bash
./mvnw spring-boot:run
```

4. O menu interativo será exibido no terminal:

```text
---------------LiterAlura---------------
1- Buscar livro pelo título
2- Listar livros registrados
3- Listar autores registrados
4- Listar autores vivos em um determinado ano
5- Listar livros em um determinado idioma
6- Top 10 livros mais baixados
7- Buscar autor por nome
0- Sair
```

---

## 🧠 Estrutura do Projeto

```
literalura
├── client                # Cliente HTTP para consumo da API Gutendex
├── dto                   # Data Transfer Objects (DTOs)
├── model                 # Entidades JPA (Livro, Autor)
├── repository            # Interfaces JPA Repository
├── service               # Regras de negócio
├── LiteraluraApplication.java  # Classe principal com método main
└── Menu.java             # Interface de menu via console
```

---

## 🧪 Lógica de Validação

- Antes de salvar um livro:
    - Verifica se o livro já existe no banco
    - Verifica se o autor já está salvo e reutiliza
    - Permite que o usuário confirme se deseja salvar o livro

- Ao listar autores:
    - Exibe dados do autor
    - Exibe o título dos livros associados

---

## 📄 Exemplo de Entrada/Saída

```text
Digite o título do livro que deseja buscar
Dom Casmurro

---------------Livro---------------
Título: Dom Casmurro
Autor: Machado de Assis
Idioma: pt
Número de downloads: 1024

Deseja salvar? Digite 'S' para sim, 'N' para não.
S
Livro salvo com sucesso!
```

---

## 📚 Créditos

- [API Gutendex](https://gutendex.com)
- Desenvolvido por Jeferson Freire

---

## 📝 Licença

Este projeto é de uso educacional. Sinta-se à vontade para adaptar e melhorar!
