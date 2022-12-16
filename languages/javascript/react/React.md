# React

JavaScript library to make UIs and handle user interaction

React is split into 2 libraries:

- React - How to use components
- ReactDOM - How to render components into the screen

## Hooks

- `useEffect`:

  - example:

  ```
    useEffect(() => {
        console.log("a test log")
    }, [test])
  ```

  - Perform side effects in function components
  - Second parameter is the fields whose changed will cause the function to execute.
  - Empty array second parameter means to run function only once.

## Glossary

- Component : A Function or a Class, to produce html to the user and/or handle user interaction
  -- Can be nested, reused, and configured
  -- Named in upper case e.g. MyComponent
  -- When nesting components they are available in props.children
  -- Can provide defaultProps
- State
  -- Can only be updated using `setState`
- Constructor
  -- Specific to JS, not react

## Best practices

- Do not have multiple return statements in a render method, can create `renderContent` method if needed
- Put component at the top of the file
- imports for libraries go above imports for other files from the project

## Syntax

- Can use ternary expressions in jsx `return <div>{boolExpr ? 'true' : 'false'</div>`
- Can initialise state directly at the top of a component as an instance variable, constructor is not needed.

## Lifecycle methods / react methods

- render : return JSX
- componentDidMount : Data loading
- componentDidUpdate : - Data load per props / state change
- componentWillUnmount :

## Class components vx functional components

- Functional Components : Show simple data to the user
- Class components : everything else!
  -- Easier code organisation
  -- Can use state to handle user input
  -- Understand lifecycle events

- Webstorm can auto-refactor from functional component to class component.

## Async await.

- Async before the call, set response as avalue with await

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

## HTTP Libs. axios vs fetch

- axios is chill.

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
