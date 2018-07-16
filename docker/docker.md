# Docker

## Exec into a container at a specifc directory

docker exec -it container-name bash -c 'cd /var/www/html/project; exec "${SHELL:-sh}"'