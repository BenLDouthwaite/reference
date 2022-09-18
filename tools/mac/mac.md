# Mac

## Shortcuts

* `cmd + ctrl + f` = Full screen toggle

## Find process on port

### El Capitan
sudo lsof -i tcp:3000

## Kill process on port

kill ${PID}
sudo kill ${PID}
sudo kill -9 ${PID}

lsof -t -i tcp:8080 | xargs kill

## Force Quit Apps

'command' + 'option' + esc

## Stop mysqld

/usr/local/mysql/bin/mysqladmin -u root -p shutdown 

## Tab between windows of the same app

Command + ` 
