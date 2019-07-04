# Disown

disown can be used to run commands from a terminal and detach so that closing the terminal will not kill the related process

Example

```
$ firefox &
$ disown
```

This will open the firefox program from the terminal, however, when closing the terminal the firefox browser will not be closed too