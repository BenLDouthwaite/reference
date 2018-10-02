# Docker

## Exec into a container at a specifc directory

docker exec -it container-name bash -c 'cd /var/www/html; exec "${SHELL:-sh}"'

to place this into an alias

alias customDockerExe='docker exec -it container-name bash -c "cd /var/www/html/project; exec ${SHELL:-sh}"'

## Connect to host machine from within container

use `host.docker.internal` on docker for windows version 18.01 +

https://stackoverflow.com/questions/24319662/from-inside-of-a-docker-container-how-do-i-connect-to-the-localhost-of-the-mach