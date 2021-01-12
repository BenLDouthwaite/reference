# .htaccess

Allows configuration for Apache servers

Applies to entire directory it is placed in and all subdirectories

Ensure files are uploaded in ASCII mode

Ensure permissions set correctly, '755' or 'executable'

Pay attenttion to line returns, use unix line return not windows

## Error Document

To redirect on hitting a 404 error add

`
ErrorDocument 404 /error_pages/404.html
`

## Redirects

Redirect if a request to access a page in the folder is not from a specific page on the same domain

`
Redirect /old_dir/ http://www.yourdomain.com/new_dir/index.html
`