package com

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class Application

@Configuration
class Configuration {

    @Bean(name = arrayOf("dataSource"))
    fun dataSource(): DataSource {

        return EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.HSQL)
                .addScript("schema.sql")
                .build()
    }

    //Create a JdbcTemplate Bean that connects to our database
    @Bean
    fun jdbcTemplate(@Qualifier("dataSource") dataSource: DataSource): JdbcTemplate {
        return JdbcTemplate(dataSource)
    }
}

fun main(args: Array<String>) {
	runApplication<Application>(*args)
}
