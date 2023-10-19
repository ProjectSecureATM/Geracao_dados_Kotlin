import com.github.britooo.looca.api.core.Looca



fun main() {
    val looca = Looca()

    val sistema = looca.sistema
    val rede = looca.rede.parametros
    val rede2 = looca.rede.grupoDeInterfaces

    sistema.getPermissao();
    sistema.getFabricante();
    sistema.getArquitetura();
    sistema.getInicializado();
    sistema.getSistemaOperacional();
    rede.getHostName();
    rede.getNomeDeDominio();
    rede.getServidoresDns();
    rede2.getInterfaces();

    System.out.println(sistema);
    System.out.println(rede);
    System.out.println(rede2);




}