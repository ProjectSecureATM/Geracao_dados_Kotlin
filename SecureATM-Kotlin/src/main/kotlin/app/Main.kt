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

            val repositorio = Repositorio()
            repositorio.iniciar()

            val email = JOptionPane.showInputDialog("Olá, digite o seu e-mail:")
            val senha = JOptionPane.showInputDialog("Digite a sua senha:")

            if (email != null && senha != null) {
                if (repositorio.verificarUsuario(email, senha)) {
                    JOptionPane.showMessageDialog(null,
                    "Usuário encontrado no banco de dados.",
                    "Bem-vindo (a) novamente!",
                    JOptionPane.INFORMATION_MESSAGE)

                    repositorio.criarTabela()
                    val novoProcesso = Processos()

                    val looca = Looca()

                    val grupoProcesso = looca.grupoDeProcessos


                    println("Iniciado!")

                    Timer().schedule(object : TimerTask() {
                        override fun run() {
                            grupoProcesso.processos.forEachIndexed { p, processo ->
                                val nomeProcesso = processo.nome
                                val pidProcesso = processo.pid
                                novoProcesso.PID = pidProcesso
                                novoProcesso.nome = nomeProcesso
                                repositorio.cadastrar(novoProcesso)

                    println("""
                    Nome: ${processo.nome}
                    PID: ${processo.pid}
            
                    """.trimIndent())
                            }
                    monitoramento()
                        }
                    }, 2000)
                }

                else {
                    JOptionPane.showMessageDialog(null,
                    "Usuário não encontrado no banco de dados.",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE)
                }
            }
                else {
                    JOptionPane.showMessageDialog(null,
                    "E-mail ou senha inválidos.",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE)
            }
        }
    }
}

fun monitoramento(){

    val looca = Looca()


    val grupoProcesssos = looca.grupoDeProcessos

    grupoProcesssos.processos.forEachIndexed { p, processo ->
        println("""
            Nome: ${processo.nome}
            PID: ${processo.pid}
        """.trimIndent())
    }

    Timer().schedule(object : TimerTask() {
        override fun run() {
                monitoramento() // agendando a própria função
        }
    }, 2000)
}
