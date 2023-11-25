package app

import Processos
import Repositorio
import com.github.britooo.looca.api.core.Looca
import com.github.britooo.looca.api.group.processos.Processo
import java.util.*
import javax.swing.JOptionPane


open class Main {
    companion object {
        @JvmStatic fun main(args: Array<String>) {

            // Cria uma instância do Repositorio para interagir com o banco de dados
            val repositorio = Repositorio()
            repositorio.iniciar()


            // Solicita ao usuário que digite seu e-mail e senha
            val email = JOptionPane.showInputDialog("Olá, digite o seu e-mail:")
            val senha = JOptionPane.showInputDialog("Digite a sua senha:")

            if (email != null && senha != null) {
                // Verifica se o usuário com o e-mail e senha fornecidos existe no banco de dados
                if (repositorio.verificarUsuario(email, senha)) {

                    // Mostra uma mensagem de boas-vindas se o usuário existe
                    JOptionPane.showMessageDialog(null,
                    "Usuário encontrado no banco de dados.",
                    "Bem-vindo (a) novamente!",
                    JOptionPane.INFORMATION_MESSAGE)

                    val idATMEscolhido = repositorio.listarIDsATMsParaEscolha()

                    if (idATMEscolhido != null) {
                        // Restante do código...

                        // Agora, você pode usar 'idATMEscolhido' como a fkATM nas operações do banco de dados
                        println("ID do ATM Escolhido: $idATMEscolhido")

                    }

                    // Cria uma tabela no banco de dados
                    repositorio.criarTabela()
                    val novoProcesso = Processos()

                    // Cria uma instância do Looca para monitorar processos
                    val looca = Looca()
                    val grupoProcesso = looca.grupoDeProcessos


                    println("Iniciado!")

                    // Agendar uma tarefa para rodar periodicamente a cada 5 segundos
                    Timer().schedule(object : TimerTask() {
                        override fun run() {
                            // Percorre a lista de processos do sistema
                            grupoProcesso.processos.forEachIndexed { p, processo ->
                                val nomeProcesso = processo.nome
                                val pidProcesso = processo.pid
                                novoProcesso.PID = pidProcesso
                                novoProcesso.nome = nomeProcesso
                                // Armazena informações do processo no banco de dados
                                if (idATMEscolhido != null) {
                                    repositorio.cadastrar(novoProcesso, idATMEscolhido)
                                }

                    println("""
                    Nome: ${processo.nome}
                    PID: ${processo.pid}
            
                    """.trimIndent())
                            }
                    // Chama a função de monitoramento após coletar informações
                    monitoramento()
                        }
                    }, 30000)
                }

                else {
                    // Mostra uma mensagem de erro se o usuário não existe no banco de dados
                    JOptionPane.showMessageDialog(null,
                    "Usuário não encontrado no banco de dados.",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE)
                }
            }
        }
    }
}

// Função de monitoramento para listar processos em intervalos regulares
fun monitoramento(){

    val looca = Looca()
    val grupoProcesssos = looca.grupoDeProcessos

    grupoProcesssos.processos.forEachIndexed { p, processo ->
        println("""
            Nome: ${processo.nome}
            PID: ${processo.pid}
        """.trimIndent())
    }

    // Agendar a função de monitoramento para ser executada a cada 5 segundos
    Timer().schedule(object : TimerTask() {
        override fun run() {
                monitoramento() // agendando a própria função para execução periódica
        }
    }, 30000)
}
