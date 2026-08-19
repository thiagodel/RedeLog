
import "./Sidebar.css";


function Sidebar() {
    return (
        <aside className="sidebar">
            <h2>RedeLog</h2>

            <nav>
                <a href="#">Dashboard</a>
                <a href="#">Entregas</a>
                <a href="#">Clientes</a>
                <a href="#">Filiais</a>
                <a href="#">Entregadores</a>
                <a href="#">Transferências</a>
            </nav>
        </aside>
    );
}

export default Sidebar;