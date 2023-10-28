import org.apache.commons.dbcp2.BasicDataSource
import org.springframework.jdbc.core.JdbcTemplate

class Conexao {

    /*var jdbcTemplate: JdbcTemplate? = null
        get() {
            if (field == null) {
                val dataSource = BasicDataSource()
                dataSource.driverClassName = "com.mysql.cj.jdbc.Driver"
                dataSource.url = "jdbc:mysql://localhost:3306/secureatm"
                dataSource.username = "root"
                dataSource.password = "fgandb25"
                val novoJdbcTemplate = JdbcTemplate(dataSource)
                field = novoJdbcTemplate
            }
            return field
        }*/

    fun conectar(): JdbcTemplate {
        val dataSource = BasicDataSource()
        dataSource.driverClassName = "com.mysql.cj.jdbc.Driver"
        dataSource.url = "jdbc:mysql://localhost:3306/secureatm"
        dataSource.username = "root"
        dataSource.password = "fgandb25"
        return JdbcTemplate(dataSource)
    }


}