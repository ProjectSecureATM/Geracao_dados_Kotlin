import com.github.britooo.looca.api.core.Looca
import com.github.britooo.looca.api.group.processos.Processo
import com.github.britooo.looca.api.group.processos.ProcessoGrupo
import org.springframework.jdbc.core.JdbcTemplate

// Inicializa uma instância do Looca para monitoramento de processos no sistema
val looca: Looca = Looca()

class Repositorio {

    lateinit var jdbcTemplate: JdbcTemplate // Declara uma variável para manipulação do banco de dados, o lateinit indica que uma variável será inicializada posteriormente, após a sua declaração, e que você está garantindo que ela não será nula quando for usada.

    // Função para iniciar a conexão com o banco de dados
    fun iniciar() {
        jdbcTemplate = Conexao().conectar() // Cria uma conexão usando a classe Conexao
    }

    // Função para criar uma tabela no banco de dados
    fun criarTabela() {
        jdbcTemplate.execute(
            """
        CREATE TABLE IF NOT EXISTS Processos (
        id INT AUTO_INCREMENT,
        PID INT,
        nome varchar(45),
        hora DATETIME,
        fkATM INT,
        FOREIGN KEY (fkATM) REFERENCES ATM(idATM),
        CONSTRAINT pkATMAPro PRIMARY KEY (id, fkATM)
        );
        """
        )
    }

    // Função para cadastrar informações sobre um processo no banco de dados
    fun cadastrar(grupoProcesso:Processos) {
        jdbcTemplate.update("""
            INSERT INTO processos(PID, nome, hora, fkATM) VALUES (?, ?, ?, 1)
        """,
            grupoProcesso.PID,
            grupoProcesso.nome,
            grupoProcesso.hora
        )
    }

    // Função para verificar a existência de um usuário com base em email e senha no banco de dados
    fun verificarUsuario(email: String, senha: String): Boolean {
        val sql = "SELECT COUNT(*) FROM usuario WHERE email = ? AND senha = ?"
        val count = jdbcTemplate.queryForObject(sql, Int::class.java, email, senha)

        return count > 0
    }

}