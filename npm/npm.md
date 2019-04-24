# NPM - Node Package Manager

Uses a `package.json` file to manage dependencies.

## What is the difference between `dependencies` and `devDependencies`?

Running `npm install --save {dep name}` will add a dependency to the `package.json`

Running `npm install --save-dev {dep-name}` will add a dev-dependency to the `package.json`

https://medium.com/@dylanavery720/npmmmm-1-dev-dependencies-dependencies-8931c2583b0c

To summarise, a dependency is needed at runtime. As dev dependency is only needed during development.

Examples of dependencies are :
* React
* Redux
* Express
* Angular

Examples of devDependencies are : 
* Babel
* ESLint
* Chai
* Mocha 