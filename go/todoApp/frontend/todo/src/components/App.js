import React from "react";
import TodoList from "./TodoList";

class App extends React.Component {
  state = {
    todo: [
      { id: 1, text: "allow option to hide completed", completed: true },
      { id: 2, text: "test completed", completed: true },
      { id: 3, text: "persist to local DB", completed: false },
    ],
    hideCompleted: false,
    input: "",
  };

  setCompleted = (id, completed) => {
    this.setState({
      todo: this.state.todo.map((el) =>
        el.id === id ? { ...el, completed } : el
      ),
    });
  };

  submitTodo = (event) => {
    console.log("Submit: ", this.state.input);
    var currentTasks = this.state.todo;
    currentTasks.push({
      id: this.state.todo.length + 1, // TODO Will generate ID from api.
      text: this.state.input,
      completed: false,
    });
    this.setState({ todo: currentTasks });
  };

  hideCompleted = (event) => {
    console.log(event);
    this.setState({ hideCompleted: true });
  };

  showCompleted = (event) => {
    console.log(event);
    this.setState({ hideCompleted: false });
  };

  toRender = () => {
    if (!this.state.hideCompleted) {
      return this.state.todo;
    }

    return this.state.todo
      .filter((todo) => todo.completed === false)
      .map((item) => item);
  };

  render() {
    var todos = this.toRender();
    return (
      <div className="ui container" style={{ marginTop: "10px" }}>
        <h1>TODO</h1>
        <input
          type="text"
          value={this.state.input}
          onChange={(e) => this.setState({ input: e.target.value })}
        />
        <button onClick={this.submitTodo}>Submit</button>
        <TodoList todoList={todos} setCompleted={this.setCompleted} />
        <button onClick={this.hideCompleted}>Hide Completed</button>
        <button onClick={this.showCompleted}>Show Completed</button>
      </div>
    );
  }
}

export default App;
