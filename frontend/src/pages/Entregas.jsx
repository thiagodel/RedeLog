import { useState } from "react";
import "./Entregas.css";

function Entregas() {
    const [filter, setFilter] = useState("Todos");
    const [search, setSearch] = useState("");
    const [showForm, setShowForm] = useState(false);
    const [clienteCadastrado, setClienteCadastrado] = useState(true);
    const [cliente, setCliente] = useState({
        nome: "",
        telefone: "",
        email: "",
        cep: "",
        endereco: "",
    });

    const [enderecoEntrega, setEnderecoEntrega] = useState({
        rua: "",
        numero: "",
        bairro: "",
        cidade: "",
        estado: "",
        cep: "",
        complemento: "",
    });

    const [filialOrigemId, setFilialOrigemId] = useState("");
    const [entregadorId, setEntregadorId] = useState("");
    const [clienteId, setClienteId] = useState("");

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

    const filteredDeliveries = deliveries.filter((delivery) => {
        const matchesStatus =
            filter === "Todos" || delivery.status === filter;

        const matchesSearch =
            delivery.code.toLowerCase().includes(search.toLowerCase()) ||
            delivery.client.toLowerCase().includes(search.toLowerCase());

        return matchesStatus && matchesSearch;
    });

    return (
        <div className="entregas-page">
            <div className="entregas-header">
                <div>
                    <h1>Entregas</h1>
                    <p>Acompanhe e gerencie as entregas do RedeLog.</p>
                </div>

                <button
                    className="new-delivery-button"
                    onClick={() => setShowForm(true)}
                >
                    + Nova entrega
                </button>
            </div>

            {showForm && (
                <div className="modal-overlay">
                    <div className="delivery-form">
                        <h2>Nova entrega</h2>

                        <button
                            type="button"
                            className={clienteCadastrado ? "active" : ""}
                            onClick={() => setClienteCadastrado(true)}
                        >
                            Cliente cadastrado
                        </button>

                        <button
                            type="button"
                            className={!clienteCadastrado ? "active" : ""}
                            onClick={() => setClienteCadastrado(false)}
                        >
                            Novo cliente
                        </button>

                        {!clienteCadastrado && (
                            <div className="new-client-form">
                                <h3>Dados do cliente</h3>

                                <input
                                    type="text"
                                    placeholder="Nome"
                                />

                                <input
                                    type="text"
                                    placeholder="Telefone"
                                />

                                <input
                                    type="email"
                                    placeholder="E-mail"
                                />

                                <input
                                    type="text"
                                    placeholder="CEP"
                                />

                                <input
                                    type="text"
                                    placeholder="Endereço"
                                />
                            </div>
                        )}

                        {clienteCadastrado && (
                            <div className="registered-client-form">
                                <h3>Selecionar cliente</h3>

                                <select
                                    value={clienteId}
                                    onChange={(event) => setClienteId(event.target.value)}
                                >
                                    <option value="">Selecione um cliente</option>
                                    <option value="1">João da Silva</option>
                                    <option value="2">Maria Oliveira</option>
                                    <option value="3">Carlos Souza</option>
                                </select>
                            </div>
                        )}

                        <button
                            type="button"
                            onClick={() => setShowForm(false)}
                        >
                            Cancelar
                        </button>
                    </div>
                </div>
            )}

            <div className="search-container">
                <input
                    type="text"
                    placeholder="Buscar por código ou cliente..."
                    value={search}
                    onChange={(event) => setSearch(event.target.value)}
                />
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

            <p className="delivery-count">
                {filteredDeliveries.length}{" "}
                {filteredDeliveries.length === 1
                    ? "entrega encontrada"
                    : "entregas encontradas"}
            </p>

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