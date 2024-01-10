# Gerenciador de Tarefas

<p style="text-align: center; font-size: 18px;">🛠Tecnologias utilizadas: </p>

<div style="text-align: center;">

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![MongoDB](https://img.shields.io/badge/MongoDB-%234ea94b.svg?style=for-the-badge&logo=mongodb&logoColor=white)
![JUnit5](https://img.shields.io/badge/JUnit5-%23ED8B00.svg?style=for-the-badge&logo=&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)

</div>

## API para gerenciar tarefas 

O gerenciador de tarefas é uma api onde se pode inserir, alterar, listar e excluir tarefas e usuários.
Também pode associar usuário a tarefa e tarefa ao usuário.

## Descrição para uso do  Docker
Se já possui o Docker instalado  basta seguir os passos abaixo:
1.  Abra o terminal ou prompt de comando
    - Navegue até o diretório onde você baixou o projeto usando o comando cd.
2. Execute o build do Docker:
   ```bash
    docker build -t gerenciadordetarefas-app .

    ```
3. Execute o Docker Compose:
    ```bash
    docker-compose up -d

    ```
4. Acesse o aplicativo:
   - Após o processo de inicialização ser concluído, você deve poder acessar o aplicativo no navegador ou na porta especificada no arquivo docker-compose.yml.

## Test

### Teste unitário

Para rodar o teste unitário basta ir no diretório **src**, depois  diretório **test**
e escolher o arquivo que deseja testar e rodar o teste, no  caso do Intellijei
basta ctrl+shift+f10 e clicar na seta de run.

## Postman
## User
### POST de usuário
Para inserir usuário:

Url da request:
```bash
http://localhost:6868/api/v1/users
```
Body da request:
```bash
      {
         "name":"Gloria da Silva"   
      }
  ```
Response:
````bash
  {
    "name": "Gloria da Silva",
    "assignedTasks": [],
    "id": "659e036f726f8e72ecd60da3"
  }
````
### POST  de usuário com tarefa
Para inserir usuário associado a uma tarefa:

Url da request:
```bash
http://localhost:6868/api/v1/users
```
Body da request:
```bash
      {
         "name":"João da Silva",
         "assignedTasks": [
              "Por id da tarefa aqui"
        ] 
      }
  ```
Response:
````bash
{
    "name": "Gean",
    "assignedTasks": [
        "65504ed5feda4224df58252f"
    ],
    "id": "659e0796726f8e72ecd60da4"
}

````
### POST usuário com id de tarefa que não existe 
Ao inserir usuário com um id de tarefa que não existe gerará um erro como saída para o usuário.

Url da request:
```bash
http://localhost:6868/api/v1/users
```
Body da request:
```bash
      {
         "name":"Vitor da Silva",
         "assignedTasks": [
              "654af883906bbc5909788469"
        ] 
      }
  ```
Response:
````bash
{
    "timestamp": "2024-01-10T02:43:38.216516770Z",
    "status": 404,
    "error": "Not Found",
    "message": "Task not found, id: 654af883906bbc5909788469",
    "path": "/api/v1/users"
}
````
### PUT de usuário
Para alterar alguma informação do  usuário:

Url:
```bash
http://localhost:6868/api/v1/users/idUsuario
```
Body da request:
```bash
      {
         "name":"João da Silva Santos",
         "assignedTasks": [
              "Por id da tarefa aqui"
        ] 
      }
  ```
Response:
````bash
{
    "name": "João da Silva Santos",
    "assignedTasks": [
        "65504ed5feda4224df58252f"
    ],
    "id": "659e0796726f8e72ecd60da4"
}
````
Se por id de tarefa que não existe, irá surgir o seguinte erro

Response:
````bash
{
    "timestamp": "2024-01-10T02:43:38.216516770Z",
    "status": 404,
    "error": "Not Found",
    "message": "Task not found, id: 123el773906hhc5111788122",
    "path": "/api/v1/users/659e0796726f8e72ecd60da4"
}
````
Se por um id de usuário que não existe, irá surgir o seguinte erro

Response:
````bash
{
    "timestamp": "2024-01-10T02:43:38.216516770Z",
    "status": 404,
    "error": "Not Found",
    "message": "User not found, id: 654af883906bbc5909788469",
    "path": "/api/v1/users/654af883906bbc5909788469"
}
````
### DELETE de usuário
Para deletar usuário:

Url da request:
```bash
http://localhost:6868/api/v1/users/idUsuario
```
Se por um id de usuário que não existe, irá surgir o seguinte erro

Response:
````bash
{
    "timestamp": "2024-01-10T02:43:38.216516770Z",
    "status": 404,
    "error": "Not Found",
    "message": "User not found, id: 654af883906bbc5909788469",
    "path": "/api/v1/users/654af883906bbc5909788469"
}
````

### GET  de  apenas um usuário
Para exibir informação de apenas um  usuário:

Url da request:
```bash
http://localhost:6868/api/v1/users/idUsuario
```
Response:
````bash
{
    "name": "Vivian Marques Silva",
    "assignedTasks": [
        "65504ed5feda4224df58252f"
    ],
    "id": "655046b7feda4224df58252e"
}
````
Se por um id de usuário que não existe, irá surgir o seguinte erro

Response:
````bash
{
    "timestamp": "2024-01-10T02:43:38.216516770Z",
    "status": 404,
    "error": "Not Found",
    "message": "User not found, id: 654af883906bbc5909788469",
    "path": "/api/v1/users/654af883906bbc5909788469"
}
````

### GET  de usuário
Para trazer todos os  usuário cadastrados:

Url da request :
```bash
http://localhost:6868/api/v1/users
```
Response :
```bash
    [
      {
        "name": "Gloria Silva",
        "assignedTasks": [
          "Limpar a casa"
        ]
      },
      {
        "name": "João da Silva Santos",
        "assignedTasks": [
        "Estudar Matématica por uma hora "
        ]
      }
    ]
```
## Department
### POST de Department
Para inserir departamento:

Url da request:
```bash
http://localhost:6868/api/v1/department
```
Body da request:
```bash
{ 
  "name": "Vendas",
  "description": "Departmaneto de Vendas" 
}
  ```
Response:
````bash
{
    "id": "659e095c726f8e72ecd60da5",
    "name": "Vendas",
    "description": "Departmaneto de Vendas",
    "users": []
}
````
### POST  de departamento com usuário
Para inserir departamento associado a um usuário:

Url da request:
```bash
http://localhost:6868/api/v1/department
```
Body da request:
```bash
      {
        "name": "Guarnição",
        "description": "Departmaneto para testar com users ja inserido ",
        "users":["id do usuário aqui"]
      }
  ```
Response:
````bash
{
    "id": "659e0a6e726f8e72ecd60da6",
    "name": "RH",
    "description": "Departmaneto para testar com users ja inserido ",
    "users": [
        {
            "id": "655046b7feda4224df58252e",
            "name": "Vivian Marques Silva"
        }
    ]
}

````
### POST departamento com id de usuário que não existe
Ao inserir departmento com um id de usuário que não existe gerará um erro como saída para o usuário.

Url da request:
```bash
http://localhost:6868/api/v1/department
```
Body da request:
```bash
      {
          "name": "Guarnição",
          "description": "Departmaneto para testar com users ja inserido ",
          "users":["655046b7feda4224df58252p"]

      }
  ```
Response:
````bash
{
    "timestamp": "2024-01-10T03:11:11.302024147Z",
    "status": 404,
    "error": "Not Found",
    "message": "User not found, id: 655046b7feda4224df58252p",
    "path": "/api/v1/department/659e0a6e726f8e72ecd60da6"
}
````
### PUT de departamento
Para alterar alguma informação do  departamento:

Url:
```bash
http://localhost:6868/api/v1/department/idDepartment
```
Body da request:
```bash
      {
         "name": "RH",
          "description": "Departmaneto para testar com users ja inserido ",
          "users":["id de usuário aqui"]
      }
  ```
Response:
````bash
{
    "id": "659e0a6e726f8e72ecd60da6",
    "name": "RH",
    "description": "Departmaneto para testar com users ja inserido",
    "users": [
        {
            "id": "655046b7feda4224df58252e",
            "name": "Vivian Marques Silva"
        }
    ]
}
````
Se por id de departamento que não existe, irá surgir o seguinte erro

Response:
````bash
{
    "timestamp": "2024-01-10T03:11:11.302024147Z",
    "status": 404,
    "error": "Not Found",
    "message": "Department not found, id: 655046b7feda4224df58252p",
    "path": "/api/v1/department/655046b7feda4224df58252p"
}
````
Se por um id de usuário que não existe, irá surgir o seguinte erro

Response:
````bash
{
    "timestamp": "2024-01-10T02:43:38.216516770Z",
    "status": 404,
    "error": "Not Found",
    "message": "User not found, id: 654af883906bbc5909788469",
    "path": "/api/v1/department/659e0a6e726f8e72ecd60da6"
}
````
### DELETE de departamento
Para deletar departamento:

Url da request:
```bash
http://localhost:6868/api/v1/department/idDepartment
```
Se por um id de departamento que não existe, irá surgir o seguinte erro

Response:
````bash
{
    "timestamp": "2024-01-10T02:43:38.216516770Z",
    "status": 404,
    "error": "Not Found",
    "message": "Department not found with id: 65336f1acf5a6c466218da0a",
    "path": "/api/v1/department/65336f1acf5a6c466218da0a"
}
````

### GET  de  apenas um departamento
Para exibir informação de apenas um  departamento:

Url da request:
```bash
http://localhost:6868/api/v1/department/idDepartment
```
Response:
````bash
{
    "id": "659e0a6e726f8e72ecd60da6",
    "name": "RH",
    "description": "Departmaneto para testar com users ja inserido",
    "users": [
        {
            "id": "655046b7feda4224df58252e",
            "name": "Vivian Marques Silva"
        }
    ]
}
````
Se por um id de departamento que não existe, irá surgir o seguinte erro

Response:
````bash
{
    "timestamp": "2024-01-10T02:43:38.216516770Z",
    "status": 404,
    "error": "Not Found",
    "message": "Department not found with id: 65336f1acf5a6c466218da0a",
    "path": "/api/v1/department/65336f1acf5a6c466218da0a"
}
````

### GET  de departamento
Para trazer todos os  departamentos cadastrados:

Url da request :
```bash
http://localhost:6868/api/v1/department
```
Response :
```bash
    [
      {
        "name": "Gloria Silva",
        "assignedTasks": [
          "Limpar a casa"
        ]
      },
      {
        "name": "João da Silva Santos",
        "assignedTasks": [
        "Estudar Matématica por uma hora "
        ]
      }
    ]
```
## Task
### POST de tarefa
Para inserir tarefa, detalhe que está faltando o campo "assigne",portanto,
irá gerar erro na tela do usuário:

Url da request:
```bash
http://localhost:6868/api/v1/task
```
Body da request:
```bash
{ 
  "title": "Desenvolver Banner",
  "description": "Esta tarefa serve para desenvolver novos banners",
  "dateOfCreation":"21/10/2023",
  "dueDate":"22/10/2023",
  "priority":"Média"
}
  ```
Response:
````bash
{
    "assigne": "Assigne is mandatory"
}
````
### POST  de Tarefa
Para inserir tarefa  associado a um usuário:

Url da request:
```bash
http://localhost:6868/api/v1/task
```
Body da request:
```bash
      {
        "title": "Desenvolver Banner",
        "description": "Esta tarefa serve para desenvolver novos banners",
        "dateOfCreation":"21/10/2023",
        "dueDate":"22/10/2023",
        "priority":"Média",
        "assigne":["id do usuário aqui"]
      }
  ```
Response:
````bash
{
    "id": "659e123f726f8e72ecd60da7",
    "title": "Desenvolver Banner",
    "dateOfCreation": "2023-10-21",
    "dueDate": "2023-10-22",
    "priority": "Média",
    "assigne": [
        {
            "id": "655ce5365f5c892244e7f388",
            "name": "Maria Silva"
        }
    ]
}

````
### POST tarefa com id de usuário que não existe
Ao inserir tarefa com um id de usuário que não existe gerará um erro como saída para o usuário.

Url da request:
```bash
http://localhost:6868/api/v1/task
```
Body da request:
```bash
      {
        "title": "Desenvolver Banner",
        "description": "Esta tarefa serve para desenvolver novos banners",
        "dateOfCreation":"21/10/2023",
        "dueDate":"22/10/2023",
        "priority":"Média",
        "assigne":["655046b7feda4224df58252p"]
      }
  ```
Response:
````bash
{
    "timestamp": "2024-01-10T03:11:11.302024147Z",
    "status": 404,
    "error": "Not Found",
    "message": "User not found, id: 655046b7feda4224df58252p",
    "path": "/api/v1/task"
}
````
### PUT de tarefa
Para alterar alguma informação do  tarefa:

Url:
```bash
http://localhost:6868/api/v1/task/idTask
```
Body da request:
```bash
      {
        "title": "Desenvolver Banner para aumentar as vendas",
        "description": "Esta tarefa serve para desenvolver novos banners",
        "dateOfCreation":"21/10/2023",
        "dueDate":"22/10/2023",
        "priority":"Média",
        "assigne":["id do usuário aqui"]
      }
  ```
Response:
````bash
{
    "id": "659e123f726f8e72ecd60da7",
    "title": "Desenvolver Banner para aumentar as vendas",
    "description": "Esta tarefa serve para desenvolver novos banners",
    "dateOfCreation": "2023-10-21",
    "dueDate": "2023-10-22",
    "priority": "Média",
    "assigne": [
        {
            "id": "655ce5365f5c892244e7f388",
            "name": "Maria Silva"
        }
    ]
}
````
Se por id de tarefa que não existe, irá surgir o seguinte erro

Response:
````bash
{
    "timestamp": "2024-01-10T03:47:31.384687127Z",
    "status": 404,
    "error": "Not Found",
    "message": "Task not found, id: 654af883906bbc5909788469",
    "path": "/api/v1/task/654af883906bbc5909788469"
}
````
Se por um id de usuário que não existe, irá surgir o seguinte erro

Response:
````bash
{
    "timestamp": "2024-01-10T02:43:38.216516770Z",
    "status": 404,
    "error": "Not Found",
    "message": "User not found, id: 655ce5365f5c892244e7f38e",
    "path": "/api/v1/task/659e123f726f8e72ecd60da7"
}
````
### DELETE de tarefa
Para deletar tarefa:

Url da request:
```bash
http://localhost:6868/api/v1/task/idTask
```
Se por um id de task que não existe, irá surgir o seguinte erro

Response:
````bash
{
    "timestamp": "2024-01-10T02:43:38.216516770Z",
    "status": 404,
    "error": "Not Found",
    "message": "Task not found, id: 655ce5365f5c892244e7f38e",
    "path": "/api/v1/task/655ce5365f5c892244e7f38e
}
````

### GET  de  apenas uma tarefa
Para exibir informação de apenas uma tarefa:

Url da request:
```bash
http://localhost:6868/api/v1/task/idTask
```
Response:
````bash
{
    "id": "659e123f726f8e72ecd60da7",
    "title": "Desenvolver Banner para aumentar as vendas",
    "dateOfCreation": "2023-10-21",
    "dueDate": "2023-10-22",
    "priority": "Média",
    "assigne": [
        {
            "id": "655ce5365f5c892244e7f388",
            "name": "Maria Silva"
        }
    ]
}
````
Se por um id de tarefa que não existe, irá surgir o seguinte erro

Response:
````bash
{
    "timestamp": "2024-01-10T02:43:38.216516770Z",
    "status": 404,
    "error": "Not Found",
    "message": "Task not found, id: 654af883906bbc5909788469",
    "path": "/api/v1/task/654af883906bbc5909788469"
}
````

### GET  de tarefas
Para trazer todos as tarefas cadastradas:

Url da request :
```bash
http://localhost:6868/api/v1/task
```
Response :
```bash
[
    {
        "title": "Teste para ver se esta tudo ok",
        "description": "tarefas a fazer 3 ",
        "dateOfCreation": "2023-10-16",
        "dueDate": "2023-10-22",
        "priority": "Média",
        "assigne": [
            {
                "name": "Gloria da Silva"
            }
        ]
    },
    {
        "title": "Desenvolver Banner para aumentar as vendas",
        "description": "Esta tarefa serve para desenvolver novos banners",
        "dateOfCreation": "2023-10-21",
        "dueDate": "2023-10-22",
        "priority": "Média",
        "assigne": [
            {
                "name": "Maria Silva"
            }
        ]
    }
]
```
## Project
### POST de projeto
Url da request:
```bash
http://localhost:6868/api/v1/project
```
Body da request:
```bash
{
    "name":"Mudar o logo da empresa",
    "description": "Esse projeto serve para refazer logo da empresa, esse projeto foi criado sem lista"
}
  ```
Response:
````bash
{
    "id": "659e17cd726f8e72ecd60da8",
    "name": "Mudar o logo da empresa",
    "description": "Esse projeto serve para refazer logo da empresa, esse projeto foi criado sem lista",
    "taskList": []
}
````
### POST  de projeto
Para inserir projeto  associado a uma tarefa:

Url da request:
```bash
http://localhost:6868/api/v1/project
```
Body da request:
```bash
{
    "name":"Aumentar venda",
    "description": "Esse projeto esta sendo criado com lista de task",
    "assignedTasks": [
         "659e123f726f8e72ecd60da7"        
    ]
    
}
  ```
Response:
````bash
{
    "id": "659e18ad726f8e72ecd60da9",
    "name": "Aumentar venda",
    "description": "Esse projeto esta sendo criado com lista de task",
    "taskList": [
        {
            "idTask": "659e123f726f8e72ecd60da7",
            "title": "Desenvolver Banner para aumentar as vendas",
            "assigne": [
                {
                    "id": "655ce5365f5c892244e7f388",
                    "name": "Maria Silva"
                }
            ]
        }
    ]
}
````
### POST projeto com id de tarefa que não existe
Ao inserir projeto com um id de tarefa que não existe gerará um erro como saída para o usuário.

Url da request:
```bash
http://localhost:6868/api/v1/project
```
Body da request:
```bash
{
    "name":"Aumentar venda",
    "description": "Esse projeto esta sendo criado com lista de task",
    "assignedTasks": [
         "655046b7feda4224df58252p"        
    ]
    
}
```
Response:
````bash
{
    "timestamp": "2024-01-10T04:12:01.696076865Z",
    "status": 404,
    "error": "Not Found",
    "message": "Task not found, id: 655046b7feda4224df58252p",
    "path": "/api/v1/project"
}
````
### PUT de projeto
Para alterar alguma informação do  projeto:

Url:
```bash
http://localhost:6868/api/v1/project/idProject
```
Body da request:
```bash
{
    "name":"Aumentar venda de panelas",
    "description": "Esse projeto esta sendo criado com lista de task",
    "assignedTasks": [
         "659e123f726f8e72ecd60da7"        
    ]
    
}
  ```
Response:
````bash
{
    "id": "659e18ad726f8e72ecd60da9",
    "name": "Aumentar venda de panelas",
    "description": "Esse projeto esta sendo criado com lista de task",
    "taskList": [
        {
            "idTask": "659e123f726f8e72ecd60da7",
            "title": "Desenvolver Banner para aumentar as vendas",
            "assigne": [
                {
                    "id": "655ce5365f5c892244e7f388",
                    "name": "Maria Silva"
                }
            ]
        }
    ]
}
````
Se por id de projeto que não existe, irá surgir o seguinte erro

Response:
````bash
{
    "timestamp": "2024-01-10T04:16:02.003749790Z",
    "status": 404,
    "error": "Not Found",
    "message": "Project not found with id: 659e18ad726f8e72",
    "path": "/api/v1/project/659e18ad726f8e72"
}
````
Se por um id de tarefa que não existe, irá surgir o seguinte erro

Response:
````bash
{
    "timestamp": "2024-01-10T02:43:38.216516770Z",
    "status": 404,
    "error": "Not Found",
    "message": "Task not found, id: 655ce5365f5c892244e7f38e",
    "path": "/api/v1/project/659e18ad726f8e72ecd60da9"
}
````
### DELETE de projeto
Para deletar projeto:

Url da request:
```bash
http://localhost:6868/api/v1/project/idProject
```
Se por um id de projeto que não existe, irá surgir o seguinte erro

Response:
````bash
{
    "timestamp": "2024-01-10T02:43:38.216516770Z",
    "status": 404,
    "error": "Not Found",
    "message": "Project not found, id: 655ce5365f5c892244e7f38e",
    "path": "/api/v1/task/655ce5365f5c892244e7f38e"
}
````

### GET  de  apenas um projeto
Para exibir informação de apenas um  projeto:

Url da request:
```bash
http://localhost:6868/api/v1/project/idProject
```
Response:
````bash
{
    "id": "659e18ad726f8e72ecd60da9",
    "name": "Aumentar venda de panelas",
    "description": "Esse projeto esta sendo criado com lista de task",
    "taskList": [
        {
            "idTask": "659e123f726f8e72ecd60da7",
            "title": "Desenvolver Banner para aumentar as vendas",
            "assigne": [
                {
                    "id": "655ce5365f5c892244e7f388",
                    "name": "Maria Silva"
                }
            ]
        }
    ]
}
````
Se por um id de projeto que não existe, irá surgir o seguinte erro

Response:
````bash
{
    "timestamp": "2024-01-10T02:43:38.216516770Z",
    "status": 404,
    "error": "Not Found",
    "message": "Project not found, id: 659e123f726f8e72ecd60da7",
    "path": "/api/v1/project/659e123f726f8e72ecd60da7"
}
````

### GET  de Projeto
Para trazer todos os  uprojetos cadastrados:

Url da request :
```bash
http://localhost:6868/api/v1/project
```
Response :
```bash
[
    {
        "id": "659e17cd726f8e72ecd60da8",
        "name": "Mudar o logo da empresa",
        "description": "Esse projeto serve para refazer logo da empresa, esse projeto foi criado sem lista",
        "taskList": []
    },
    {
        "id": "659e18ad726f8e72ecd60da9",
        "name": "Aumentar venda de panelas",
        "description": "Esse projeto esta sendo criado com lista de task",
        "taskList": [
            {
                "idTask": "659e123f726f8e72ecd60da7",
                "title": "Desenvolver Banner para aumentar as vendas",
                "assigne": [
                    {
                        "id": "655ce5365f5c892244e7f388",
                        "name": "Maria Silva"
                    }
                ]
            }
        ]
    }
]
```
Deploy: Deploy was done in the render
- https://challengefour.onrender.com
- https://challengefour.onrender.com/api/v1/clients

## Installation

```bash
$ docker build -t nome_da_imagem .

```

## Running the app

```bash
# development
$ npm run start

# watch mode
$ npm run start:dev

# production mode
$ npm run start:prod
```





## Entrar em contato

- Author - [Natália Barros](https://github.com/Natalia0412)
