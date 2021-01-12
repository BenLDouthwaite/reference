import React from "react";
import TodoItem from "./TodoItem";

class TodoList extends React.Component {
  render() {
    console.log(this.props);
    const todos = this.props.todoList.map((todo, index) => {
      return (
        <TodoItem
          item={todo}
          key={todo.id}
          id={todo.id}
          text={todo.text}
          completed={todo.completed}
          setCompleted={this.props.setCompleted}
        />
      );
    });

    return <div>{todos}</div>;
  }
}

export default TodoList;
