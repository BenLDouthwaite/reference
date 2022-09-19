import { Link } from "react-router-dom";

export default function Home() {
  return (
    <div>
      <h2>React Sandbox</h2>
      <ul>
        <li>
          <Link to="/">Home</Link>
        </li>
        <li>
          <Link to="/seasons">Seasons</Link>
        </li>
      </ul>
    </div>
  );
}
