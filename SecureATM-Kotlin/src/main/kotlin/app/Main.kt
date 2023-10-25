package app

import com.github.britooo.looca.api.core.Looca
import java.sql.*
import java.util.*


open class Main {
    companion object {
        @JvmStatic fun main(args: Array<String>) {

            val looca = Looca()


            val grupoProcesssos = looca.grupoDeProcessos




            println("Iniciado!")

            Timer().schedule(object : TimerTask() {
                override fun run() {



                    grupoProcesssos.processos.forEachIndexed { p, processo ->
                        val nomeProcesso = processo.nome
                        val pidProcesso = processo.pid

                        insertData(pidProcesso, nomeProcesso)

                        println("""
            Nome: ${processo.nome}
            PID: ${processo.pid}
            
        """.trimIndent())
                    }

                    monitoramento()

                }
            }, 2000)
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

fun insertData(pidProcesso: Int, nomeProcesso: String) {
    val jdbcUrl = "jdbc:mysql://localhost:3306/secureatm"
    val user = "root"
    val password = "fgandb25"

    try {

        val connection: Connection = DriverManager.getConnection(jdbcUrl, user, password)

        val sql = "INSERT INTO processos(PID, nome, fkATM) VALUES (?, ?, 1)"

        val preparedStatement: PreparedStatement = connection.prepareStatement(sql)
        preparedStatement.setInt(1, pidProcesso)
        preparedStatement.setString( 2, nomeProcesso)

        preparedStatement.executeUpdate()
        connection.close()
    } catch (e: SQLException) {
        e.printStackTrace()
        println("Erro ao inserir os dados no banco de dados.")
    }
}