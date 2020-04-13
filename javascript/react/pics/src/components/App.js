import React from "react";
import SearchBar from "./SearchBar";

class App extends React.Component {
  state = { searchTerm: "" };

  render() {
    return (
      <div className="ui container" style={{ marginTop: 10 }}>
        <SearchBar
          onSubmit={searchTerm =>
            this.setState({ searchTerm }, () => console.log(this.state))
          }
        />
      </div>
    );
  }
}

export default App;
