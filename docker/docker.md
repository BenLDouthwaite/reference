# Docker

## Exec into a container at a specifc directory

docker exec -it container-name bash -c 'cd /var/www/html; exec "${SHELL:-sh}"'

to place this into an alias

alias customDockerExe='docker exec -it container-name bash -c "cd /var/www/html/project; exec ${SHELL:-sh}"'
