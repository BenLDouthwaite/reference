# React ( & friends)

Javascript library to make UIs and handle user interaction

## Glossary

- Component : A Function or a Class, to produce html to the user and/or handle user interaction
-- Can be nested, reused, and configured
-- Named in upper case e.g. MyComponent
-- When nesting components they are available in props.children

## JSX vs HTML

- JSX: `<div style={{ backgroundColor: 'red' }}</div>` vs HTML `<div style="background-color: red;"></div>`

## Create React App

https://github.com/facebook/create-react-app

create-react-app is a single line command to quickly generate a react project.
`npx create-react-app myapp`

### Directory Structure

- src : source code
- public : static files
- node_modules : project dependencies
- package.json : record dependencies & configure project
- package-lock.json : Record exact version of installed packages
- README.md : Instructions for the project

## Misc Notes

- `import` refers to ES2015 Modules, `require` refers to CommonJS Modules

# Other frontend tools

## Babel

https://babeljs.io/

Command line tool to convert between versions of javascript
`ES2015 JS -> Babel -> ES5 JS`

Included in `create-react-app`

## Yarn

## Prettier

Used for 'opinionated' styling

- Format current dir : `prettier --write .`

## Semantic UI

UI components : https://semantic-ui.com/ 
