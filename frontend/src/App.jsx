import Sidebar from "./components/Sidebar";
import Header from "./components/Header";
import SummaryCard from "./components/SummaryCard";
import RecentDeliveries from "./components/RecentDeliveries";

import { BrowserRouter, Routes, Route } from "react-router-dom";

import Entregas from "./pages/Entregas";

import "./App.css";

function Dashboard() {
  return (
    <>
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

      <RecentDeliveries />
    </>
  );
}

function App() {
  return (
    <BrowserRouter>
      <div className="app">
        <Sidebar />

        <div className="main">
          <Header />

          <main className="content">
            <Routes>
              <Route path="/" element={<Dashboard />} />
              <Route path="/entregas" element={<Entregas />} />
            </Routes>
          </main>
        </div>
      </div>
    </BrowserRouter>
  );
}

export default App;