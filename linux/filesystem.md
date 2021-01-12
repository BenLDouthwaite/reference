# Filesystem 

Linux is multi-user by design, we need security.

Access is defined by:
- File ownership
- File permission

Every file (or directory) has 3 kinds of owners:
- User
    - File creators become owners of the created file
    - One user can belong to many groups
- Group
    - Every user belongs to a group
    - Contains several users
    - `groups` shows the groups of the current user
    - Only one group can be the owner of a file at a time.
- Other
    - A super group with all users of the system
    
Every file and directory in Linux has the following three permissions for all the three kinds of owners:

Permissions for files
- Read – Can view or copy file contents
- Write – Can modify file content
- Execute – Can run the file (if its executable)

Permissions for directories
- Read – Can list all files and copy the files from directory
- Write – Can add or delete files into directory (needs execute permission as well)
- Execute – Can enter the directory

Use `stat` or `ls` to view file permissions

An example permission block would be: `rwxrw-r--`
- The first 3 characters for the User Owner (read, write and execute)
- The next 3 characters for the Group Owner (read and write only)
- The final 3 characters for Other (read only)

Precedence exists that user overrides group, which overrides other

## Common Problems

User / Group ID permissions for a directory:
`addgroup -g 123 group-name`
`adduser -u 456 -G group-name user-name`
`chown -R user-name:group-name /dir`

## Adding docker permissions

`docker info` requires root. Lets fix that.
`sudo groupadd docker`
`sudo usermod -aG docker $USER`
`newgrp docker`
`docker info` No longer requires root 