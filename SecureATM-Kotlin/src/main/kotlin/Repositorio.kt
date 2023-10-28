import com.github.britooo.looca.api.core.Looca
import com.github.britooo.looca.api.group.processos.Processo
import com.github.britooo.looca.api.group.processos.ProcessoGrupo
import org.springframework.jdbc.core.JdbcTemplate

val looca: Looca = Looca()
class Repositorio {

    lateinit var jdbcTemplate: JdbcTemplate

    fun iniciar() {
        jdbcTemplate = Conexao().conectar()
    }

    fun criarTabela() {
        jdbcTemplate.execute(
            """
        CREATE TABLE IF NOT EXISTS Processos (
        id INT AUTO_INCREMENT,
        PID INT,
        nome varchar(45),
        fkATM INT,
        FOREIGN KEY (fkATM) REFERENCES ATM(idATM),
        CONSTRAINT pkATMAPro PRIMARY KEY (id, fkATM)
        );
        """
        )
    }

    fun cadastrar(grupoProcesso:Processos) {
        jdbcTemplate.update("""
            INSERT INTO processos(PID, nome, fkATM) VALUES (?, ?, 1)
        """,
            grupoProcesso.PID,
            grupoProcesso.nome
        )
    }

    fun verificarUsuario(email: String, senha: String): Boolean {
        val sql = "SELECT COUNT(*) FROM funcionario WHERE email = ? AND senha = ?"
        val count = jdbcTemplate.queryForObject(sql, Int::class.java, email, senha)

        return count > 0
    }

}