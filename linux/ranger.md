# ranger

ranger is a command line file browser.

to init config files, including rifle.conf, run `ranger --copy-config=all`

## Set vim as default terminal

find
```
#-------------------------------------------
# Misc
#-------------------------------------------
# Define the "editor" for text files as first action
mime ^text,  label editor = $EDITOR -- "$@"
```

and change `$EDITOR` to `vim`
