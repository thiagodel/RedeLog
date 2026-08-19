import Sidebar from "./components/Sidebar";
import Header from "./components/Header";
import SummaryCard from "./components/SummaryCard";
import "./App.css";

function App() {
  return (
    <div className="app">

      <Sidebar />

      <div className="main">

        <Header />

        <main className="content">

          <div className="dashboard-intro">
            <h2>Bem-vindo ao RedeLog</h2>
            <p>Acompanhe suas entregas por aqui.</p>
          </div>

          <div className="summary-cards">

            <SummaryCard
              title="Total de entregas"
              value="124"
              description="Entregas cadastradas"
            />

            <SummaryCard
              title="Em andamento"
              value="18"
              description="Entregas em transporte"
            />

            <SummaryCard
              title="Entregues"
              value="96"
              description="Entregas concluídas"
            />

            <SummaryCard
              title="Atrasadas"
              value="10"
              description="Precisam de atenção"
            />

          </div>

        </main>
      </div>

    </div>
  );
}

export default App;