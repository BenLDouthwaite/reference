# Filesystem 

User / Group ID permissions for a directory

`addgroup -g 123 group-name`

`adduser -u 456 -G group-name user-name`

`chown -R user-name:group-name /dir

## Adding docker

`docker info` requires root. Lets fix that.
`sudo groupadd docker`
`sudo usermod -aG docker $USER`
`newgrp docker`
`docker info` No longer requires root 