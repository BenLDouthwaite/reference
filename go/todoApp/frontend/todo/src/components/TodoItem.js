import React from "react";

class TodoItem extends React.Component {
  handleChange = (event) => {
    this.props.setCompleted(this.props.id, event.target.checked);
  };

  render() {
    const text = this.props.completed ? (
      <s>{this.props.text}</s>
    ) : (
      this.props.text
    );

    return (
      <div>
        <input
          type="checkbox"
          checked={this.props.completed}
          onChange={this.handleChange}
        />
        {text}
      </div>
    );
  }
}

export default TodoItem;
