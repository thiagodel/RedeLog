import "./Header.css";

function Header() {
    return(
        <header className="header">

            <div>
                <h1>Dashboard</h1>
                <p>Visão geral das entregas</p>
            </div>

            <div className="header-user">
                <span className="notification">🔔</span>
                <span className="avatar">US</span>
                <span>USUÁRIO</span>
            </div>

        </header>
    );
}

export default Header;