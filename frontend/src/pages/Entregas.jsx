import { useState } from "react";
import "./Entregas.css";

function Entregas() {
    const [filter, setFilter] = useState("Todos");

    const deliveries = [
        {
            code: "ENT-001",
            client: "João da Silva",
            destination: "Uberaba - MG",
            status: "Em andamento",
            date: "20/08/2026",
        },
        {
            code: "ENT-002",
            client: "Maria Oliveira",
            destination: "Araxá - MG",
            status: "Entregue",
            date: "20/08/2026",
        },
        {
            code: "ENT-003",
            client: "Carlos Souza",
            destination: "Uberlândia - MG",
            status: "Atrasada",
            date: "19/08/2026",
        },
        {
            code: "ENT-004",
            client: "Ana Santos",
            destination: "Patos de Minas - MG",
            status: "Em andamento",
            date: "19/08/2026",
        },
    ];

    const filteredDeliveries =
        filter === "Todos"
            ? deliveries
            : deliveries.filter((delivery) => delivery.status === filter);

    return (
        <div className="entregas-page">
            <div className="entregas-header">
                <div>
                    <h1>Entregas</h1>
                    <p>Acompanhe e gerencie as entregas do RedeLog.</p>
                </div>

                <button className="new-delivery-button">
                    + Nova entrega
                </button>
            </div>

            <div className="delivery-filters">
                <button
                    className={filter === "Todos" ? "active" : ""}
                    onClick={() => setFilter("Todos")}
                >
                    Todas
                </button>

                <button
                    className={filter === "Em andamento" ? "active" : ""}
                    onClick={() => setFilter("Em andamento")}
                >
                    Em andamento
                </button>

                <button
                    className={filter === "Entregue" ? "active" : ""}
                    onClick={() => setFilter("Entregue")}
                >
                    Entregues
                </button>

                <button
                    className={filter === "Atrasada" ? "active" : ""}
                    onClick={() => setFilter("Atrasada")}
                >
                    Atrasadas
                </button>
            </div>

            <div className="deliveries-table-container">
                <table>
                    <thead>
                        <tr>
                            <th>Código</th>
                            <th>Cliente</th>
                            <th>Destino</th>
                            <th>Status</th>
                            <th>Data</th>
                        </tr>
                    </thead>

                    <tbody>
                        {filteredDeliveries.map((delivery) => (
                            <tr key={delivery.code}>
                                <td>{delivery.code}</td>
                                <td>{delivery.client}</td>
                                <td>{delivery.destination}</td>
                                <td>
                                    <span className={`status ${delivery.status.toLowerCase().replace(" ", "-")}`}>
                                        {delivery.status}
                                    </span>
                                </td>
                                <td>{delivery.date}</td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            </div>
        </div>
    );
}

export default Entregas;