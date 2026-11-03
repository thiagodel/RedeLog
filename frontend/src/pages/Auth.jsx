import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import "./Auth.css";

function Brand() {
  return (
    <Link to="/login" className="auth-brand">
      <span className="auth-brand-mark">R</span>
      <span>rede<strong>log</strong><small>GESTÃO LOGÍSTICA</small></span>
    </Link>
  );
}

function AuthPage({ mode = "login" }) {
  const isSignup = mode === "cadastro";
  const [notice, setNotice] = useState("");
  const [showPassword, setShowPassword] = useState(false);
  const navigate = useNavigate();

  function handleSubmit(event) {
    event.preventDefault();
    setNotice("Formulário pronto para integração. Nenhuma conta ou sessão foi criada.");
  }

  return (
    <main className="auth-page">
      <section className="auth-visual">
        <Brand />
        <div className="visual-copy">
          <span className="visual-kicker"><i /> LOGÍSTICA EM MOVIMENTO</span>
          <h1>Sua operação,<br /><em>sob controle.</em></h1>
          <p>Organize entregas, acompanhe cada etapa e mantenha toda a sua operação conectada em um só lugar.</p>
          <div className="visual-stats">
            <div><b>01</b><span>Visibilidade<br />em tempo real</span></div>
            <div><b>02</b><span>Operação<br />simplificada</span></div>
            <div><b>03</b><span>Equipe<br />conectada</span></div>
          </div>
        </div>
        <div className="visual-foot">
          <span>© 2026 RedeLog</span>
          <span>Logística que aproxima.</span>
        </div>
      </section>

      <section className="auth-main">
        <div className="auth-mobile-brand">
          <Brand />
        </div>
        <div className="auth-card">
          <span className="auth-eyebrow">{isSignup ? "COMECE A USAR A REDELOG" : "BEM-VINDO DE VOLTA"}</span>
          <h2>{isSignup ? "Crie sua conta" : "Acesse sua conta"}</h2>
          <p className="auth-intro">
            {isSignup ? "Preencha seus dados para começar a organizar sua operação." : "Entre com seus dados para acessar o painel de gestão."}
          </p>

          <form onSubmit={handleSubmit}>
            {isSignup && (
              <label className="auth-field">
                <span>Nome completo</span>
                <input name="nome" type="text" placeholder="Como podemos te chamar?" autoComplete="name" required />
              </label>
            )}

            <label className="auth-field">
              <span>E-mail corporativo</span>
              <input name="email" type="email" placeholder="voce@empresa.com.br" autoComplete="email" required />
            </label>

            <label className="auth-field">
              <span>Senha</span>
              <div className="password-wrap">
                <input
                  name="senha"
                  type={showPassword ? "text" : "password"}
                  placeholder="Mínimo de 8 caracteres"
                  autoComplete={isSignup ? "new-password" : "current-password"}
                  minLength={8}
                  required
                />
                <button type="button" onClick={() => setShowPassword(value => !value)}>
                  {showPassword ? "Ocultar" : "Mostrar"}
                </button>
              </div>
            </label>

            {isSignup && (
              <label className="auth-field">
                <span>Confirmar senha</span>
                <input name="confirmarSenha" type={showPassword ? "text" : "password"} placeholder="Digite a senha novamente" minLength={8} required />
              </label>
            )}

            {!isSignup && (
              <div className="auth-options">
                <label>
                  <input type="checkbox" name="lembrar" /> Manter conectado
                </label>
                <button type="button" onClick={() => setNotice("A recuperação de senha poderá ser conectada quando a autenticação for integrada.")}>
                  Esqueci minha senha
                </button>
              </div>
            )}

            {isSignup && (
              <label className="terms-check">
                <input type="checkbox" required />
                <span>Concordo com os termos de uso e a política de privacidade.</span>
              </label>
            )}

            {notice && <div className="auth-notice" role="status">{notice}</div>}

            <button className="auth-submit" type="submit">
              {isSignup ? "Criar conta" : "Entrar"} <span>→</span>
            </button>
          </form>

          <div className="auth-switch">
            {isSignup ? "Já tem uma conta?" : "Ainda não tem uma conta?"}{" "}
            <button onClick={() => { setNotice(""); navigate(isSignup ? "/login" : "/cadastro"); }}>
              {isSignup ? "Fazer login" : "Criar conta"}
            </button>
          </div>

          <div className="auth-demo-note">
            <i /> Modo de demonstração · autenticação ainda não conectada
          </div>
        </div>

        <div className="auth-main-foot">
          © 2026 RedeLog <span>Privacidade · Termos</span>
        </div>
      </section>
    </main>
  );
}

export default AuthPage;
