package com

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType
import javax.sql.DataSource

@SpringBootApplication
class Application

@Configuration
class Configuration {
		// @Bean(name = arrayOf("dataSource"))
    // fun dataSource(): DataSource {

    //     //This will create a new embedded database and run the schema.sql script
    //     return EmbeddedDatabaseBuilder()
    //             .setType(EmbeddedDatabaseType.HSQL)
    //             .addScript("schema.sql")
    //             .build()
    // }

    @Bean
    fun jdbcTemplate(@Qualifier("dataSource") dataSource: DataSource): JdbcTemplate {
        return JdbcTemplate(dataSource)
    }
}

fun main(args: Array<String>) {
	runApplication<Application>(*args)
}
