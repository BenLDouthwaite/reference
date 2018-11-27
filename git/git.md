# GIT

## Changing author of the last commit.

git commit --amend --author="FIRSTNAME LASTNAME <email@address.com>"

## Changing the message of the last commit (before push)

`git commit --amend` and edit in vi based interface
 
## Changing the file mode to avoid conflicts between operating systems

To ignore file mode changes e.g

`diff --git a/file.sh b/file.sh
old mode 100755
new mode 100644`

Run 

`git config core.filemode false`