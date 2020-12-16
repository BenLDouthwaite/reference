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

### watch

Repeat the given command and pipe the output to the screen:
`watch "kubectl get pods | grep my-pod-name"`