import { useState } from "react";
import { BrowserRouter, NavLink, Link, Routes, Route } from "react-router-dom";
import AuthPage from "./pages/Auth.jsx";
import "./App.css";

const initial = {
    deliveries: [
        { id: 124, code: "RDL-000124", client: "João da Silva", city: "Uberaba", state: "MG", branch: "Uberaba - Centro", courier: "Marcos Lima", status: "EM_ROTA", date: "2026-08-20" },
        { id: 123, code: "RDL-000123", client: "Maria Oliveira", city: "Araxá", state: "MG", branch: "Uberaba - Centro", courier: "Paulo Souza", status: "ENTREGUE", date: "2026-08-20" },
        { id: 122, code: "RDL-000122", client: "Carlos Souza", city: "Uberlândia", state: "MG", branch: "Araxá", courier: "Marcos Lima", status: "FALHA", date: "2026-08-19" },
        { id: 121, code: "RDL-000121", client: "Ana Santos", city: "Patos de Minas", state: "MG", branch: "Araxá", courier: "Paulo Souza", status: "ENVIADA", date: "2026-08-19" }
    ],
    clientes: [
        { id: 1, nome: "João da Silva", telefone: "(34) 99912-3456", email: "joao@email.com", endereco: "Rua das Flores, 120", cep: "38010-120" },
        { id: 2, nome: "Maria Oliveira", telefone: "(34) 99876-5432", email: "maria@email.com", endereco: "Av. Leopoldino, 450", cep: "38020-010" },
        { id: 3, nome: "Carlos Souza", telefone: "(34) 99123-4567", email: "carlos@email.com", endereco: "Rua Goiás, 88", cep: "38400-100" }
    ],
    filiais: [
        { id: 1, nome: "Uberaba - Centro", numeroFilial: "FIL-001", cnpj: "12345678000190", cidade: "Uberaba", estado: "MG", endereco: "Av. Santos Dumont, 1200" },
        { id: 2, nome: "Araxá", numeroFilial: "FIL-002", cnpj: "12345678000270", cidade: "Araxá", estado: "MG", endereco: "Rua Presidente Olegário, 250" }
    ],
    entregadores: [
        { id: 1, nome: "Marcos Lima", telefone: "(34) 99911-2233", email: "marcos@redelog.com", placaVeiculo: "ABC1D23", status: "ATIVO" },
        { id: 2, nome: "Paulo Souza", telefone: "(34) 99822-3344", email: "paulo@redelog.com", placaVeiculo: "DEF4G56", status: "ATIVO" },
        { id: 3, nome: "Fernanda Alves", telefone: "(34) 99733-4455", email: "fernanda@redelog.com", placaVeiculo: "HIJ7K89", status: "INATIVO" }
    ]
};

const labels = {
    CRIADA: "Criada",
    ENVIADA: "Enviada",
    EM_ROTA: "Em rota",
    ENTREGUE: "Entregue",
    FALHA: "Falha",
    ATIVO: "Ativo",
    INATIVO: "Inativo"
};

const nav = [
    ["/", "Visão geral", "⌂"],
    ["/entregas", "Entregas", "↗"],
    ["/clientes", "Clientes", "♙"],
    ["/filiais", "Filiais", "⌖"],
    ["/entregadores", "Entregadores", "♧"]
];

function Side() {
    return (
        <aside className="sidebar">
            <Link to="/" className="brand">
                <i>R</i>
                <span>rede<strong>log</strong><small>GESTÃO LOGÍSTICA</small></span>
            </Link>
            <small className="nav-title">MENU PRINCIPAL</small>
            <nav>
                {nav.map(([url, title, icon]) => (
                    <NavLink
                        end={url === "/"}
                        key={url}
                        to={url}
                        className={({ isActive }) => `navlink ${isActive ? "active" : ""}`}
                    >
                        <i>{icon}</i>
                        {title}
                    </NavLink>
                ))}
            </nav>
            <div className="side-bottom">
                <div className="help">
                    <b>Precisa de ajuda?</b>
                    <small>Consulte o suporte RedeLog</small>
                    <a>Falar com suporte ↗</a>
                </div>
                <div className="user">
                    <i>AD</i>
                    <span><b>Administrador</b><small>Operação logística</small></span>
                </div>
            </div>
        </aside>
    );
}

function Heading({ title, sub, action }) {
    return (
        <div className="heading">
            <div>
                <small>{sub}</small>
                <h1>{title}</h1>
                <p>{sub}</p>
            </div>
            {action}
        </div>
    );
}

function Badge({ value }) {
    return (
        <span className={`badge ${value.toLowerCase()}`}>
            <i />
            {labels[value] || value}
        </span>
    );
}

function Table({ rows, cols, open, actions }) {
    return (
        <div className="table-wrap">
            <table>
                <thead>
                    <tr>
                        {cols.map((c) => <th key={c[0]}>{c[1]}</th>)}
                        {(open || actions) && <th />}
                    </tr>
                </thead>
                <tbody>
                    {rows.map(r => (
                        <tr key={r.id} onClick={() => open?.(r)}>
                            {cols.map(([k]) => (
                                <td key={k}>
                                    {k === "status" ? <Badge value={r[k]} /> : k === "code" ? <b className="code">{r[k]}</b> : r[k] || "—"}
                                </td>
                            ))}
                            {(open || actions) && <td>{actions ? actions(r) : <button className="more">•••</button>}</td>}
                        </tr>
                    ))}
                </tbody>
            </table>
            {!rows.length && <div className="empty">Nenhum registro encontrado.</div>}
        </div>
    );
}

function Modal({ title, close, save, children, submit = "Salvar cadastro" }) {
    return (
        <div className="overlay" onMouseDown={e => e.target === e.currentTarget && close()}>
            <form className="modal" onSubmit={save}>
                <div className="modal-head">
                    <div>
                        <small>REDELOG · DEMONSTRAÇÃO</small>
                        <h2>{title}</h2>
                    </div>
                    <button type="button" onClick={close}>×</button>
                </div>
                <div className="modal-body">{children}</div>
                <div className="modal-foot">
                    <span>Dados locais desta sessão.</span>
                    <button type="button" className="secondary" onClick={close}>Cancelar</button>
                    <button className="primary">{submit}</button>
                </div>
            </form>
        </div>
    );
}

function Field({ name, label, required = false, type = "text", value, change, placeholder }) {
    return (
        <label className="field">
            <span>{label}{required && <b> *</b>}</span>
            <input name={name} type={type} required={required} value={value} onChange={change} placeholder={placeholder || label} />
        </label>
    );
}

function Dashboard({ data }) {
    let ds = data.deliveries;
    let cards = [
        ["Entregas cadastradas", ds.length, "No período selecionado", "↗", "blue"],
        ["Em andamento", ds.filter(x => ["ENVIADA", "EM_ROTA"].includes(x.status)).length, "Aguardando conclusão", "◷", "purple"],
        ["Entregues", ds.filter(x => x.status === "ENTREGUE").length, "Concluídas com sucesso", "✓", "green"],
        ["Com ocorrência", ds.filter(x => x.status === "FALHA").length, "Precisam de acompanhamento", "!", "orange"]
    ];

    return (
        <>
            <Heading title="Visão geral" sub="OPERAÇÃO LOGÍSTICA" action={<button className="secondary">◷ Últimos 30 dias ⌄</button>} />
            <section className="metrics">
                {cards.map(([t, v, d, i, c]) => (
                    <article className="metric" key={t}>
                        <div>{t}<i className={c}>{i}</i></div>
                        <b>{v}</b>
                        <small>{d}</small>
                    </article>
                ))}
            </section>
            <section className="panel">
                <header>
                    <div><b>Entregas recentes</b><small>Acompanhe a movimentação mais recente.</small></div>
                    <Link to="/entregas">Ver todas as entregas →</Link>
                </header>
                <Table rows={ds.slice(0, 5)} cols={[["code", "CÓDIGO"], ["client", "CLIENTE"], ["city", "DESTINO"], ["courier", "ENTREGADOR"], ["status", "STATUS"], ["date", "DATA"]]} />
            </section>
            <div className="lower">
                <section className="panel">
                    <header><div><b>Resumo da operação</b><small>Visão rápida da sua equipe.</small></div></header>
                    {cards.slice(1).map(([t, v, d]) => (
                        <div className="summary" key={t}>
                            <b>{v} {t.toLowerCase()}</b>
                            <small>{d}</small>
                        </div>
                    ))}
                </section>
                <section className="panel">
                    <header><div><b>Acesso rápido</b><small>Atalhos para tarefas frequentes.</small></div></header>
                    <Link className="shortcut" to="/entregas">＋ Nova entrega →</Link>
                    <Link className="shortcut" to="/clientes">♙ Cadastrar cliente →</Link>
                </section>
            </div>
        </>
    );
}

function Deliveries({ data, setData }) {
    let [filter, F] = useState("TODOS");
    let [search, S] = useState("");
    let [modal, M] = useState(false);
    let [chosen, C] = useState(null);
    let [newClient, N] = useState(false);

    let rows = data.deliveries.filter(x => (filter === "TODOS" || x.status === filter) && `${x.code} ${x.client} ${x.city}`.toLowerCase().includes(search.toLowerCase()));

    let update = (id, status) => {
        setData(d => ({ ...d, deliveries: d.deliveries.map(x => x.id === id ? { ...x, status } : x) }));
        C(x => x?.id === id ? { ...x, status } : x);
    };

    let save = e => {
        e.preventDefault();
        let f = new FormData(e.currentTarget);
        let isNew = f.get("mode") === "new";
        let client = isNew ? { nome: f.get("clientName"), telefone: f.get("clientPhone"), email: f.get("clientEmail") } : data.clientes.find(x => x.id === +f.get("clienteId"));
        let branch = data.filiais.find(x => x.id === +f.get("filial"));
        let courier = data.entregadores.find(x => x.id === +f.get("courier"));

        setData(d => ({
            ...d,
            clientes: isNew ? [{ ...client, id: Date.now() }, ...d.clientes] : d.clientes,
            deliveries: [{
                id: Date.now(),
                code: `RDL-${String(125 + d.deliveries.length).padStart(6, "0")}`,
                client: client.nome,
                city: f.get("cidade"),
                state: f.get("estado"),
                branch: branch.nome,
                courier: courier.nome,
                status: "CRIADA",
                date: new Date().toISOString().slice(0, 10)
            }, ...d.deliveries]
        }));
        M(false);
    };

    return (
        <>
            <Heading title="Entregas" sub="Gerencie envios, acompanhe o andamento e consulte ocorrências." action={<button className="primary" onClick={() => M(true)}>＋ Nova entrega</button>} />
            <div className="toolbar">
                <input placeholder="⌕ Buscar código, cliente ou cidade..." value={search} onChange={e => S(e.target.value)} />
                <button className="secondary" onClick={() => { S(""); F("TODOS"); }}>↻ Limpar filtros</button>
            </div>
            <div className="filters">
                {[
                    ["TODOS", "Todas"],
                    ["CRIADA", "Criadas"],
                    ["ENVIADA", "Enviadas"],
                    ["EM_ROTA", "Em rota"],
                    ["ENTREGUE", "Entregues"],
                    ["FALHA", "Com falha"]
                ].map(([key, label]) => (
                    <button className={filter === key ? "active" : ""} key={key} onClick={() => F(key)}>
                        {label} <i>{key === "TODOS" ? data.deliveries.length : data.deliveries.filter(d => d.status === key).length}</i>
                    </button>
                ))}
            </div>
            <section className="panel">
                <div className="list-caption">{rows.length} entregas encontradas</div>
                <Table rows={rows} open={C} cols={[["code", "CÓDIGO"], ["client", "CLIENTE"], ["city", "DESTINO"], ["courier", "ENTREGADOR"], ["status", "STATUS"], ["date", "DATA"]]} />
            </section>
            {modal && (
                <Modal title="Nova entrega" close={() => M(false)} save={save}>
                    <div className="choice">
                        <button type="button" className={!newClient ? "chosen" : ""} onClick={() => N(false)}>Cliente cadastrado</button>
                        <button type="button" className={newClient ? "chosen" : ""} onClick={() => N(true)}>Novo cliente</button>
                    </div>
                    <input type="hidden" name="mode" value={newClient ? "new" : "registered"} />
                    <div className="form-grid">
                        {newClient ? (
                            <>
                                <Field name="clientName" label="Nome do cliente" required />
                                <Field name="clientPhone" label="Telefone" required />
                                <Field name="clientEmail" label="E-mail" type="email" />
                            </>
                        ) : (
                            <label className="field">
                                <span>Cliente cadastrado *</span>
                                <select name="clienteId" required>
                                    <option value="">Selecione o cliente</option>
                                    {data.clientes.map(x => <option key={x.id} value={x.id}>{x.nome}</option>)}
                                </select>
                            </label>
                        )}
                        <Field name="cidade" label="Cidade de destino" required />
                        <Field name="estado" label="Estado" required value="MG" />
                        <label className="field">
                            <span>Filial de origem *</span>
                            <select name="filial" required>
                                <option value="">Selecione uma filial</option>
                                {data.filiais.map(x => <option key={x.id} value={x.id}>{x.nome}</option>)}
                            </select>
                        </label>
                        <label className="field">
                            <span>Entregador *</span>
                            <select name="courier" required>
                                <option value="">Selecione um entregador</option>
                                {data.entregadores.filter(x => x.status === "ATIVO").map(x => <option key={x.id} value={x.id}>{x.nome}</option>)}
                            </select>
                        </label>
                        <Field name="rua" label="Rua / Avenida" required />
                        <Field name="numero" label="Número" required />
                        <Field name="bairro" label="Bairro" required />
                        <Field name="cep" label="CEP" />
                    </div>
                </Modal>
            )}
            {chosen && (
                <Modal title={chosen.code} close={() => C(null)} save={e => e.preventDefault()} submit="Fechar">
                    <div className="detail">
                        <b>CLIENTE</b><span>{chosen.client}</span>
                        <b>DESTINO</b><span>{chosen.city} {chosen.state}</span>
                        <b>FILIAL</b><span>{chosen.branch}</span>
                        <b>ENTREGADOR</b><span>{chosen.courier}</span>
                        <b>STATUS</b><Badge value={chosen.status} />
                    </div>
                    <h3>Ações de acompanhamento</h3>
                    <div className="action-row">
                        {chosen.status === "CRIADA" && <button className="secondary" onClick={() => update(chosen.id, "ENVIADA")}>Despachar</button>}
                        {chosen.status === "ENVIADA" && <button className="secondary" onClick={() => update(chosen.id, "EM_ROTA")}>Saiu para entrega</button>}
                        {["ENVIADA", "EM_ROTA"].includes(chosen.status) && <button className="primary" onClick={() => update(chosen.id, "ENTREGUE")}>Finalizar entrega</button>}
                        {!["FALHA", "ENTREGUE"].includes(chosen.status) && <button className="danger" onClick={() => update(chosen.id, "FALHA")}>Registrar falha</button>}
                    </div>
                </Modal>
            )}
        </>
    );
}

const configs = {
    clientes: {
        title: "Clientes",
        sub: "Consulte e mantenha os dados dos clientes da operação.",
        cols: [["nome", "NOME"], ["telefone", "TELEFONE"], ["email", "E-MAIL"], ["endereco", "ENDEREÇO"]],
        fields: [["nome", "Nome completo", 1], ["telefone", "Telefone", 1], ["email", "E-mail", 0, "email"], ["cep", "CEP"], ["endereco", "Endereço"]]
    },
    filiais: {
        title: "Filiais",
        sub: "Gerencie as unidades e os endereços de origem dos envios.",
        cols: [["nome", "NOME DA FILIAL"], ["numeroFilial", "CÓDIGO"], ["cidade", "CIDADE"], ["cnpj", "CNPJ"]],
        fields: [["nome", "Nome da filial", 1], ["numeroFilial", "Código da filial"], ["cnpj", "CNPJ · 14 dígitos", 1], ["cidade", "Cidade", 1], ["estado", "Estado", 1], ["endereco", "Endereço", 1]]
    },
    entregadores: {
        title: "Entregadores",
        sub: "Acompanhe os profissionais e a disponibilidade da equipe.",
        cols: [["nome", "NOME"], ["telefone", "TELEFONE"], ["email", "E-MAIL"], ["placaVeiculo", "PLACA"], ["status", "STATUS"]],
        fields: [["nome", "Nome completo", 1], ["telefone", "Telefone", 1], ["email", "E-mail", 0, "email"], ["placaVeiculo", "Placa do veículo", 1]]
    }
};

function Resource({ type, data, setData }) {
    let cfg = configs[type];
    let [search, S] = useState("");
    let [form, F] = useState(null);
    let [del, D] = useState(null);

    let rows = data[type].filter(x => Object.values(x).join(" ").toLowerCase().includes(search.toLowerCase()));

    let save = e => {
        e.preventDefault();
        let values = Object.fromEntries(new FormData(e.currentTarget));
        setData(d => ({
            ...d,
            [type]: form?.id ? d[type].map(x => x.id === form.id ? { ...x, ...values } : x) : [{ id: Date.now(), ...values, ...(type === "entregadores" ? { status: "ATIVO" } : {}) }, ...d[type]]
        }));
        F(null);
    };

    return (
        <>
            <Heading title={cfg.title} sub={cfg.sub} action={<button className="primary" onClick={() => F({})}>＋ Novo {cfg.title.slice(0, -1).toLowerCase()}</button>} />
            <div className="toolbar">
                <input placeholder={`⌕ Buscar ${cfg.title.toLowerCase()}...`} value={search} onChange={e => S(e.target.value)} />
                <span>{rows.length} registros</span>
            </div>
            <section className="panel">
                <Table rows={rows} cols={cfg.cols} actions={r => (
                    <div className="row-actions">
                        <button onClick={() => F(r)}>Editar</button>
                        {type === "entregadores" && (
                            <button onClick={() => setData(d => ({ ...d, entregadores: d.entregadores.map(x => x.id === r.id ? { ...x, status: x.status === "ATIVO" ? "INATIVO" : "ATIVO" } : x) }))}>
                                {r.status === "ATIVO" ? "Desativar" : "Ativar"}
                            </button>
                        )}
                        <button className="delete" onClick={() => D(r)}>Excluir</button>
                    </div>
                )} />
            </section>
            {form !== null && (
                <Modal title={`${form.id ? "Editar" : "Novo"} ${cfg.title.slice(0, -1).toLowerCase()}`} close={() => F(null)} save={save} submit={form.id ? "Salvar alterações" : "Salvar cadastro"}>
                    <div className="form-grid">
                        {cfg.fields.map(([n, l, req, t]) => (
                            <Field key={n} name={n} label={l} required={!!req} type={t || "text"} value={form[n] || ""} change={e => F({ ...form, [n]: e.target.value })} />
                        ))}
                    </div>
                </Modal>
            )}
            {del && (
                <Modal title="Excluir cadastro?" close={() => D(null)} save={e => { e.preventDefault(); setData(d => ({ ...d, [type]: d[type].filter(x => x.id !== del.id) })); D(null); }} submit="Excluir">
                    <p>Remover {del.nome} desta demonstração?</p>
                </Modal>
            )}
        </>
    );
}

function Workspace({ data, setData }) {
    return (
        <div className="app">
            <Side />
            <div className="main">
                <header className="top">
                    <span>RedeLog / <strong>Gestão</strong></span>
                    <span>● &nbsp;Ambiente de demonstração &nbsp;<i>AD</i></span>
                </header>
                <main className="content">
                    <Routes>
                        <Route path="/" element={<Dashboard data={data} />} />
                        <Route path="/entregas" element={<Deliveries data={data} setData={setData} />} />
                        {Object.keys(configs).map(type => <Route key={type} path={`/${type}`} element={<Resource type={type} data={data} setData={setData} />} />)}
                        <Route path="*" element={<Dashboard data={data} />} />
                    </Routes>
                </main>
                <footer>© 2026 RedeLog <span>Gestão logística inteligente · Dados de demonstração</span></footer>
            </div>
        </div>
    );
}

function AppRoutes() {
    const [data, setData] = useState(initial);
    return (
        <Routes>
            <Route path="/login" element={<AuthPage />} />
            <Route path="/cadastro" element={<AuthPage mode="cadastro" />} />
            <Route path="/*" element={<Workspace data={data} setData={setData} />} />
        </Routes>
    );
}

function App() {
    return <BrowserRouter><AppRoutes /></BrowserRouter>;
}

export default App;
