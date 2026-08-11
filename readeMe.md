# Concessionária

## Tecnologias usadas

- Java 21
- Spring Boot 4.0.7
- Spring Data JPA (Hibernate)
- MySQL
- Lombok (pra não precisar escrever getter/setter na mão)
- springdoc-openapi (gera a documentação Swagger automaticamente)
- Maven

## Estrutura do projeto

```
src/main/java/com/concessionaria
controller/ -> endpoins (/carro, /cliente)
model/ -> entidades JPA (Carro, Cliente, Condição, Status)
repository/ -> interface JpaRepository
```

## Como rodar o projeto

### Pré-requisitos
- Java 21 instalado
- MySQL rodando na sua máquina (ou em container)
## Endpoints principais

### Carro (`/carro`)
- /carro/cadastrar - cadastra carro novo (post)
- /carro/todos - lista todos os carros (get)
- /carro/{id} - busca pelo id (get)
- /carro/{id} - atualiza o carro (put)
- /carro/{id} - exclui carro (delete)

### Cliente (`/cliente`)
- /cliente/cadastrar - cadastra cliente novo (post)
- /cliente/todos - lista todos os clientes (get)
- /cliente/{id} - busca pelo id (get)
- /cliente/{id} - atualiza o cliente (put)
- /cliente/{id} - exclui cliente (delete)

# Decisões

## Variáveis

### Cliente
``
