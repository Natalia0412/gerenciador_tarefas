# gerenciador_tarefas

# Comandos Docker
## Para criar ambiente Docker multi-container para um aplicativo que usa MongoDB e Spring Boot(docker-compose.yml).
Basta ir no terminal onde contém o diretório com docker-compose.yml e digitar o codigo abaixo:

docker compose up -d

E pronto, a aplicação estará rodando no docker, e agora só acessar pela interface de preferência, sugiro Postman.

Também é importante criar uma pasta chamada db no mesmo repositório de docker-compose.yml, pois ela é responsável por armazenar o volume 