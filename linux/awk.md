# awk

## Get single word from output
`echo "test test2 test3" | awk '{print $2;}'`
Will print 'test2'

Can combine with grep

To copy into clipboard

`echo "test test2 test3" | awk '{print $2;}' | xclip`
