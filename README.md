📚 FórumHub API
API REST desenvolvida em Java com Spring Boot como parte do challenge da Alura.
O projeto simula um fórum, permitindo criação, listagem, atualização e remoção de tópicos, além de autenticação com JWT.

✨ Funcionalidades
✅ Criar um novo tópico
✅ Listar todos os tópicos
✅ Consultar detalhes de um tópico
✅ Atualizar um tópico
✅ Remover um tópico
✅ Autenticação e autorização com JWT
✅ Persistência em banco relacional com MySQL
✅ Migrações de banco usando Flyway
✅ Tratamento de erros e validações

🏗 Estrutura do Projeto
📂 Controller → Endpoints da API
📂 DTO → Objetos de transferência de dados (entrada/saída)
📂 Model → Entidades do banco de dados
📂 Repository → Repositórios JPA
📂 Security → Configuração do Spring Security + JWT
📂 Infra/Exception → Tratamento de erros personalizados
📂 Migration → Scripts de criação e alteração do banco (Flyway)

🚀 Tecnologias Utilizadas
☕ Java 17+
🌱 Spring Boot
🗄 Spring Data JPA
🔐 Spring Security + JWT
🛠 Flyway
🐬 MySQL
📦 Maven

⚙️ Configuração do Projeto
1️⃣ Banco de Dados
Crie um banco no MySQL chamado:

CREATE DATABASE forumhub_db;


2️⃣ Variáveis de Ambiente
Antes de rodar a aplicação, defina as variáveis:
Linux / Mac
export DB_USER=seuUsuario
export DB_PASS=suaSenha
export JWT_SECRET=umSegredoBemSeguroAqui

Windows (PowerShell)
$env:DB_USER="seuUsuario"
$env:DB_PASS="suaSenha"
$env:JWT_SECRET="umSegredoBemSeguroAqui"

3️⃣ Arquivo application.properties

spring.datasource.url=jdbc:mysql://localhost:3306/forumhub_db
spring.datasource.username=${DB_USER}
spring.datasource.password=${DB_PASS}
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

spring.flyway.enabled=true

api.security.token.secret=${JWT_SECRET}


4️⃣ Rodando a Aplicação
No terminal, execute:
mvn spring-boot:run


A aplicação ficará disponível em:
👉 http://localhost:8080

📝 CRUD de Tópicos
POST /topicos → cria um tópico
GET /topicos → lista todos os tópicos
GET /topicos/{id} → detalhes de um tópico
PUT /topicos/{id} → atualiza um tópico
DELETE /topicos/{id} → remove um tópico
⚠️ Todas essas rotas exigem **Bearer Token JWT
