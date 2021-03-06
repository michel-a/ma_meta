import logo from '../../assets/img/logo.svg';

import './styles.css';

function Header() {
    return (
        <header>
            <div className="dsmeta-logo-container">
                <img src={logo} alt="DSMeta" />
                <h1>MAlves_Meta</h1>
                <p>
                    Desenvolvido por <a href="https://github.com/michel-a">michel-a</a>
                </p>
            </div>
        </header>
    );
}

export default Header;