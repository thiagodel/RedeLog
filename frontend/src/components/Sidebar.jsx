
import "./Sidebar.css";

import { Link } from "react-router-dom";

function Sidebar() {
  return (
    <aside className="sidebar">
      <h2>RedeLog</h2>

      <nav>
        <Link to="/dashboard">Dashboard</Link>
        <Link to="/entregas">Entregas</Link>
        <Link to="/clientes">Clientes</Link>
        <Link to="/filiais">Filiais</Link>
        <Link to="/entregadores">Entregadores</Link>
        <Link to="/transferencias">Transferências</Link>
      </nav>
    </aside>
  );
}

export default Sidebar;