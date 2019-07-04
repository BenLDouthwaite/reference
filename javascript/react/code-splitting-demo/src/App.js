import React, { Component } from 'react';

class App extends Component {

handleClick = () => {
    import('./moduleA')
      .then(({ moduleA }) => {
        alert(moduleA);
      })
      .catch(err => {
        // Handle failure
      });
  };

  render() {
    return (
      <div className="App">
          <button onClick={this.handleClick}>Load</button>
      </div>
    );
  }
}

export default App;
