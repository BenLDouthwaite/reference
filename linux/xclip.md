# xclip

xclip is used to manage the clipboard on linux.

we can pipe files into xclip. e.g. `cat filename | xclip`

and then view the output using `xclip -o`

TO be able to paste elsewhere, such as direct from the terminal use:

`echo "test123" | xclip -selection clipboard`