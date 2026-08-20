import "./RecentDeliveries.css";

function RecentDeliveries() {
  const deliveries = [
    {
      code: "#00124",
      client: "Empresa A",
      destination: "Araxá",
      status: "Em andamento",
      date: "20/08/2026",
    },
    {
      code: "#00123",
      client: "Empresa B",
      destination: "Uberaba",
      status: "Entregue",
      date: "20/08/2026",
    },
    {
      code: "#00122",
      client: "Empresa C",
      destination: "Patos de Minas",
      status: "Atrasada",
      date: "19/08/2026",
    },
  ];

  return (
    <section className="recent-deliveries">

      <div className="section-header">
        <h2>Entregas recentes</h2>
        <button>Ver todas</button>
      </div>

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
          {deliveries.map((delivery) => (
            <tr key={delivery.code}>
              <td>{delivery.code}</td>
              <td>{delivery.client}</td>
              <td>{delivery.destination}</td>
              <td>{delivery.status}</td>
              <td>{delivery.date}</td>
            </tr>
          ))}
        </tbody>
      </table>

    </section>
  );
}

export default RecentDeliveries;