# Commands

Info on common linux commands, and questions I end up googling repeatedly
Link out to individual files where extra detail needed

### chsh - Change Login Shell

How to make `zsh` the default shell?
`chsh -s $(which zsh)`

### df - File system disk usage

### feh

`feh` is used to make backgrounds for window managers lacking this ability. e.g i3
to set a background image: `feh --bg-scale /path`

### gcloud - Google Cloud Platform CLI



### kubectl - Kubernetes 

* See [Kubernetes](../kubernetes/kubernetes.md#commands)
* Official Cheatsheet: https://kubernetes.io/docs/reference/kubectl/cheatsheet/

### openvpn3 - Open VPN

`openvpn3 session-start --config ${CONFIG_FILE}`
- Username does not include email domain.

### rsync - Remote file copying tool

- Only transfer files that have changed.

`rsync -t *.c foo:src/`
- Copy all `.c` files in the current dir to the `src` folder on machine `foo`

- Be careful of syntax with trailing slashes or not
    - With a trailing slash on a dir = the contents of the dir
    - Without a trailing slash on a dir = the dir itself to be moved 

### touch 

Create a new file
`touch myFile.txt`

### watch

Repeat the given command and pipe the output to the screen:
`watch "kubectl get pods | grep my-pod-name"`