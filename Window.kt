import java.io.File
fun main() {



    val nome = "CapturaDadosPy.py"

    var arq = File(nome)

    // create a new file
    arq.writeText("import psutil\n" +
            "import time \n" +
            "import platform\n" +
            "import mysql.connector\n" +
            "\n" +
            "'''\n" +
            "def mysql_connection(host, user, passwd, database=None):\n" +
            "    \n" +
            "    return connection\n" +
            "\n" +
            "#connection = mysql_connection(\"localhost\",\"root\",\"5505\", \"python_dados\")\n" +
            "'''\n" +
            "\n" +
            "i = 0\n" +
            "while i<50:\n" +
            "    CPU1 = psutil.cpu_percent()\n" +
            "    CPU2 = 1.1 * CPU1\n" +
            "    CPU3 = CPU2 / 0.95\n" +
            "    \n" +
            "    MEMO1 = psutil.virtual_memory().percent\n" +
            "    MEMO2 = 1.15 * MEMO1\n" +
            "    MEMO3 = MEMO2 / 1.05 \n" +
            "    \n" +
            "    meu_so = platform.system()\n" +
            "    if(meu_so == \"Linux\"):\n" +
            "        DISCO1 = psutil.disk_usage('/').percent\n" +
            "    elif(meu_so == \"Windows\"):\n" +
            "        DISCO1 = psutil.disk_usage('C:\\\\').percent   \n" +
            "        \n" +
            "    DISCO2 = 0.95 * DISCO1\n" +
            "    DISCO3 = 3 * DISCO2\n" +
            "\n" +
            "    print(\"SO que eu uso : \",meu_so)\n" +
            "    \n" +
            "    print(\"\\nCPU1:\",CPU1)\n" +
            "    print(\"\\nCPU2:\",CPU2)\n" +
            "    print(\"\\nCPU2:\",CPU3)\n" +
            "\n" +
            "    mydb = mysql.connector.connect(host = 'localhost',user = 'root',passwd = '5505',database = 'python_dados')\n" +
            "    cursor = mydb.cursor()\n" +
            "\n" +
            "    query = 'INSERT INTO CPUS(CPU1, CPU2, CPU3) VALUES(%s, %s,%s)'\n" +
            "    param = [CPU1, CPU2, CPU3]\n" +
            "    cursor.execute(query, param)\n" +
            "    mydb.commit()\n" +
            "\n" +
            "    print(\"\\nMEMO1:\",MEMO1)\n" +
            "    print(\"\\nMEMO2:\",MEMO2)\n" +
            "    print(\"\\nMEMO3:\",MEMO3)\n" +
            "\n" +
            "    query = 'INSERT INTO MEMO(MEMO1, MEMO2, MEMO3) VALUES(%s, %s,%s)'\n" +
            "    param2 = [MEMO1, MEMO2, MEMO3]\n" +
            "    cursor.execute(query, param2)\n" +
            "    mydb.commit()\n" +
            "\n" +
            "    print(\"\\nDISCO1:\",DISCO1)\n" +
            "    print(\"\\nDISCO2:\",DISCO2)\n" +
            "    print(\"\\nDISCO3:\",DISCO3)\n" +
            "\n" +
            "    query = 'INSERT INTO DISCO(DISCO1, DISCO2, DISCO3) VALUES(%s, %s,%s)'\n" +
            "    param3 = [DISCO1, DISCO2, DISCO3]\n" +
            "    cursor.execute(query, param2)\n" +
            "    mydb.commit()\n" +
            "    print(\"---------------------------------\")\n" +
            "    i += 1\n" +
            "    time.sleep(5)")

    val nomeBash = "Install.bat"

    var arqBash = File(nomeBash)

    // create a new file
    arqBash.writeText("@\"%SystemRoot%\\System32\\WindowsPowerShell\\v1.0\\powershell.exe\" -NoProfile -InputFormat None -ExecutionPolicy Bypass -Command \"iex ((New-Object System.Net.WebClient).DownloadString('https://chocolatey.org/install.ps1'))\" && SET \"PATH=%PATH%;%ALLUSERSPROFILE%\\chocolatey\\bin\"\n\n" +
            "choco install python311 --params \"/C:\\Users\\Public\"" +
            "cd "
    )

}
