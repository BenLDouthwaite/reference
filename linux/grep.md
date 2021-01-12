# Grep

## Find lines before and after match

man grep | grep "context" --after-context=1 --before-context=2
man grep | grep "context" -A 1 -B 2

man grep | grep "context" --context=2
man grep | grep "context" -C 2

## Search recursively through a directory

grep -r "recursive" .

## Case insensitive search

grep -i "search term" .

## Don't show the filename in results

grep -h -r "search term" .