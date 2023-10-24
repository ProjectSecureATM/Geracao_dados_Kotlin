package app

import com.github.britooo.looca.api.core.Looca
import java.util.*


open class Main {
    companion object {
        @JvmStatic fun main(args: Array<String>) {

            val looca = Looca()

            val redebytesenviados = looca.rede.grupoDeInterfaces.interfaces.get(0).bytesEnviados.toFloat() / (1024 * 1024);
            val redebytesrecebidos = looca.rede.grupoDeInterfaces.interfaces.get(0).bytesRecebidos.toFloat() / (1024 * 1024);

            val grupoProcesssos = looca.grupoDeProcessos

            println("Iniciado!")

            Timer().schedule(object : TimerTask() {
                override fun run() {

                    grupoProcesssos.processos.forEachIndexed { p, processo ->
                        println("""
            Nome: ${processo.nome}
            PID: ${processo.pid}
            
        """.trimIndent())
                    }

                    println("""
             Total de processos: ${grupoProcesssos.totalProcessos}    
             Total de threads: ${grupoProcesssos.totalThreads}    
             """.trimIndent())

                    println("""
               Bytes Enviados: ${redebytesenviados}
               Bytes Recebidos: ${redebytesrecebidos}
           """.trimIndent())

                    monitoramento()
                }
            }, 600000)
        }
    }
}

fun monitoramento(){

    val looca = Looca()

    val redebytesenviados = looca.rede.grupoDeInterfaces.interfaces.get(0).bytesEnviados.toFloat() / (1024 * 1024);
    val redebytesrecebidos = looca.rede.grupoDeInterfaces.interfaces.get(0).bytesRecebidos.toFloat() / (1024 * 1024);


    val grupoProcesssos = looca.grupoDeProcessos

    grupoProcesssos.processos.forEachIndexed { p, processo ->
        println("""
            Nome: ${processo.nome}
            PID: ${processo.pid}
        """.trimIndent())
    }

    println("""
             Total de processos: ${grupoProcesssos.totalProcessos}    
             Total de threads: ${grupoProcesssos.totalThreads}    
             """.trimIndent())

    println("""
               Bytes Enviados: ${redebytesenviados}
               Bytes Recebidos: ${redebytesrecebidos}
           """.trimIndent())
    Timer().schedule(object : TimerTask() {
        override fun run() {
                monitoramento() // agendando a própria função

        }
    }, 600000)
}