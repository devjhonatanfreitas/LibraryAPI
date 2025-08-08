# 📚 Library API

API REST desenvolvida em **Java** com **Spring Boot** para gerenciamento de livros em uma biblioteca.  
Este projeto foi criado **apenas para fins de estudo**, servindo como prática para conceitos de programação Java e desenvolvimento de APIs.

---

## 🚀 Funcionalidades

- **Adicionar** um novo livro
- **Listar** todos os livros
- **Buscar** livro por ID
- **Atualizar** dados de um livro
- **Excluir** livro
- **Alugar** um livro
- **Devolver** um livro
- **Pesquisar** livros por título, autor, gênero ou ISBN

---

## 📂 Endpoints

| Método   | Rota                                           | Descrição                                        |
|----------|------------------------------------------------|--------------------------------------------------|
| `POST`   | `/books/addnew`                                | Adiciona um novo livro                           |
| `GET`    | `/books/getall`                                | Lista todos os livros                            |
| `GET`    | `/books/book/{id}`                             | Busca livro pelo ID                              |
| `PUT`    | `/books/update/{id}`                           | Atualiza dados de um livro                       |
| `DELETE` | `/books/delete/{id}`                           | Remove um livro                                  |
| `PATCH`  | `/books/rent/{id}`                             | Marca livro como alugado                         |
| `PATCH`  | `/books/return/{id}`                           | Marca livro como devolvido                       |
| `GET`    | `/books/search?type={campo}&q={valor}`         | Pesquisa por título, autor, gênero ou ISBN       |

---

## 🛠️ Tecnologias e Conceitos Utilizados

### **Linguagem e Framework**
- **Java 17+** – Linguagem de programação principal
- **Spring Boot** – Framework para desenvolvimento rápido de APIs REST

### **Persistência de dados**
- **JPA (Jakarta Persistence API)** – Mapeamento objeto-relacional
- **Spring Data JPA** – Implementação simplificada do JPA
- **Anotações**:  
  - `@Entity`, `@Table`, `@Id`, `@GeneratedValue`, `@Column`
- **Banco de dados**: H2 Database (ou outro compatível para testes)

### **Lombok**
- `@Data`, `@AllArgsConstructor`, `@NoArgsConstructor` – Redução de código repetitivo

### **Arquitetura**
- **Controller** (`BookController`) – Lida com as requisições HTTP
- **Service** (`BookService`) – Contém a lógica de negócio
- **Repository** (`BookRepository`) – Acesso ao banco de dados

### **Tratamento de exceções**
- Exceção customizada: **`BookException`** com enum `ErrorType`
- Mapeamento de erros para **HTTP Status** adequados

### **Recursos REST**
- `@RestController` e `@RequestMapping` – Definição dos endpoints
- `@GetMapping`, `@PostMapping`, `@PutMapping`, `@PatchMapping`, `@DeleteMapping`
- `@RequestBody`, `@PathVariable`, `@RequestParam`
- `ResponseEntity` para retorno com status HTTP

### **CORS**
- `@CrossOrigin(origins = "*")` – Permite requisições de qualquer domínio 
---

## 📚 Conceitos Java praticados

- [x] **Programação Orientada a Objetos (POO)**  
  - [x] Encapsulamento (atributos privados com getters/setters)  
  - [x] Construtores sobrecarregados (`@AllArgsConstructor`, `@NoArgsConstructor`)  
  - [x] Separação de responsabilidades (Controller, Service, Repository)  
- [x] **Collections e Generics**  
  - [x] Uso de `List<BookModel>` para retorno de coleções tipadas  
- [x] **Optional**  
  - [x] Tratamento seguro para evitar `NullPointerException` ao buscar registros  
- [x] **Enums**  
  - [x] Enum interno `BookException.ErrorType` para tipagem de erros  
- [x] **Exceções customizadas**  
  - [x] Classe `BookException` que estende `RuntimeException`  
- [x] **Injeção de dependências**  
  - [x] Uso de `final` e injeção por construtor no Spring (`BookService`, `BookController`)  
- [x] **Anotações Lombok**  
  - [x] `@Data` para gerar getters, setters, `equals`, `hashCode` e `toString`  
- [x] **API REST com Spring Boot**  
  - [x] Uso de `@RestController`, `@RequestMapping` e mapeamentos HTTP (`@GetMapping`, `@PostMapping`, etc.)  

