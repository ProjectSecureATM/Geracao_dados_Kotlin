package Geracao_dados_Kotlin

class Usuario {
    var nome: String = ""
    var senha: Int = 0
    var email: String = ""
    val senhaCorr = 123

    fun VerSenha(): Boolean{
    return senha == senhaCorr
    }
}
