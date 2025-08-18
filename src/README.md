# TODO List Java

Projeto desenvolvido para o desafio ZG-Hero (K1-T3): Java  
Uma aplicação **TODO-List** (backend) em **Java**.

## Funcionalidades

- Adicionar, listar, remover tarefas
- Listar tarefas por categoria, prioridade ou status (ToDo, Doing, Done)
- Atualizar status de tarefas
- Reorganiza automaticamente pela prioridade das tarefas
- Persistência simples em arquivo CSV (`tasks.csv`), sem perder dados ao fechar o programa
- Menu interativo no terminal

## Estrutura do Projeto
    src/
    ├── model/
    │ └── Task.java
    ├── repository/
    │ └── TaskRepository.java
    ├── service/
    │ └── TaskService.java
    └── Main.java

## Tecnologias utilizadas

- **Java**
- Manipulação nativa de arquivos (`java.io`)
- Classes utilitárias (`java.util`, `java.time`)

## Como executar

1. Compile o arquivo main:
   javac Main.java 
2. Execute:
   java Main

O programa irá criar e utilizar o arquivo `tasks.csv` na raiz do projeto automaticamente para armazenar as tarefas.

## Observações

- Para reiniciar as tarefas do zero, basta apagar o arquivo `tasks.csv`.
