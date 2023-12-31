import com.github.britooo.looca.api.core.Looca
import com.github.britooo.looca.api.group.processos.Processo
import com.github.britooo.looca.api.group.processos.ProcessoGrupo
import org.springframework.jdbc.core.JdbcTemplate
import javax.swing.JOptionPane

// Inicializa uma instância do Looca para monitoramento de processos no sistema
val looca: Looca = Looca()

class Repositorio {

    lateinit var jdbcTemplate: JdbcTemplate // Declara uma variável para manipulação do banco de dados,
    // o lateinit indica que uma variável será inicializada posteriormente, após a sua declaração, e que você está garantindo que ela não será nula quando for usada.

    // Função para iniciar a conexão com o banco de dados
    fun iniciar() {
        jdbcTemplate = conectarSQL() // Cria uma conexão usando a classe Conexao
    }

    // Função para criar uma tabela no banco de dados
    fun criarTabela() {
        jdbcTemplate.execute(
            """
        IF NOT EXISTS (SELECT 1 FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'processos')
BEGIN
    CREATE TABLE processos (
        id INT IDENTITY(1,1) PRIMARY KEY,
        PID INT,
        nome VARCHAR(45),
        data_hora DATETIME,
        fkComp INT,
        fkATM INT,
        FOREIGN KEY (fkATM) REFERENCES ATM(idATM)
    );
END;
        """
        )
    }

    // Função para cadastrar informações sobre um processo no banco de dados
    fun cadastrar(grupoProcesso:Processos, idATM: Int) {
        jdbcTemplate.update("""
            INSERT INTO processos(PID, nome, data_hora, fkATM) VALUES (?, ?, ?, ?)
        """,
            grupoProcesso.PID,
            grupoProcesso.nome,
            grupoProcesso.data_hora,
            idATM
        )
    }

    // Função para verificar a existência de um usuário com base em email e senha no banco de dados
    fun verificarUsuario(email: String, senha: String): Boolean {
        val sql = "SELECT COUNT(*) FROM usuario WHERE email = ? AND senha = ?"
        val count = jdbcTemplate.queryForObject(sql, Int::class.java, email, senha)

        return count > 0
    }

    fun verificarExistenciaATM(idATM: Int): Boolean {
        val sql = "SELECT COUNT(*) FROM ATM WHERE idATM = ?"
        val count = jdbcTemplate.queryForObject(sql, Int::class.java, idATM)
        return count > 0
    }

    // Função para listar os IDs dos ATMs disponíveis para o usuário escolher
    fun listarIDsATMsParaEscolha(): Int? {
        // Lógica para obter a lista de IDs dos ATMs do banco de dados
        val listaDeIDsATMs = obterListaDeIDsATMsDoBancoDeDados()

        // Exibe a lista de IDs dos ATMs para o usuário escolher
        val escolha = JOptionPane.showInputDialog(
            null,
            "Escolha o ID do ATM:",
            "Escolha de ATM",
            JOptionPane.QUESTION_MESSAGE,
            null,
            listaDeIDsATMs.toTypedArray(),
            null
        ) as Int? ?: return null

        // Verifica se o ATM escolhido existe no banco de dados
        if (verificarExistenciaATM(escolha)) {
            return escolha
        } else {
            JOptionPane.showMessageDialog(
                null,
                "ATM escolhido não encontrado no banco de dados. Saindo do programa.",
                "Erro",
                JOptionPane.ERROR_MESSAGE
            )
            return null
        }
    }

    // Função para obter a lista de IDs dos ATMs do banco de dados
    private fun obterListaDeIDsATMsDoBancoDeDados(): List<Int> {
        val sql = "SELECT idATM FROM ATM"
        return jdbcTemplate.queryForList(sql, Int::class.java)
    }
}

