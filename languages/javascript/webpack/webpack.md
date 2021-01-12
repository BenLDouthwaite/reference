# Webpack

## Versioning differences

4.0 Uses 'rules' not 'loaders'

## Adding [name]

Allows code splitting.

setting 
`
        chunkFilename: './src/main/resources/static/built/[name].bundle.js',
`

specified where to put the file. But then the code will use that path too, instead of just looking in './built'

output: {
        path: path.join(__dirname, 'src/main/resources/static'),
        filename: 'built/[name].bundle.js',
        chunkFilename: 'built/[name].bundle.js',
    },