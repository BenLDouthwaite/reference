# Git

Git is a version control system. It allows us to work collaboratively, manage changes to projects, and revert any mistakes easily

## Terminology
* Repository - A project, contains all your files.

## Commands
### Add
`git add [file]` 
Select which files to save. 
To save all files in the current directory (e.g. from the top level directory) 
`git add .`

### Clone
`git clone [url]`
Download a project and all of its history,

### Commit 
`git commit -m "Commit Message, make this informative`
Record the changes you have made, so you can reset to this point in time.
After committing, your files are saved but only on your local machine, you still need to [`push`](#push) them to the remote server.

### Diff
`git diff`
Show the differences that haven't been staged yet  

### Pull
`git pull` 
Download any changes to the project on the server.

### Push
`git push` / `git push [alias] [branch]`
Upload any commits to the remote server

### Status
`git status`
Show a range of information about the currently checked out branch, including:
* branch name 
* changes to be committed
* changes not staged for commit
* untracked files

## Submodules.
https://git-scm.com/docs/git-submodule

Git allows us to nest projects within each other

`git submodule add [url]`

E.g. The 'lessons' project, and the personal website project both use this 'reference' project as a submodule.

This allows us to keep a single source of data, and avoid repetition.

To update all git submodulesa t once, you can run 

`git submodule update --recursive --remote`

## Common Problems
### Changing author of the last commit.

git commit --amend --author="FIRSTNAME LASTNAME <email@address.com>"

### Changing the message of the last commit (before push)

`git commit --amend` and edit in vi based interface

### Changing the file mode to avoid conflicts between operating systems

To ignore file mode changes e.g

`diff --git a/file.sh b/file.sh
old mode 100755
new mode 100644`

Run 

`git config core.filemode false`

## Config 

When setting up on a linux machine, if prompted for username on each push, add to the `.git/config`

`url = https://username@repository-url.com`

## Documentation

https://git-scm.com/docs
