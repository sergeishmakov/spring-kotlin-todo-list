package com

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class Application

// @Configuration
// @EnableJdbcRepositories (1)
// public class CustomerConfig extends JdbcConfiguration { (2)

//     @Bean
//     NamedParameterJdbcOperations operations() { (3)
//         return new NamedParameterJdbcTemplate(dataSource());
//     }

//     @Bean
//     PlatformTransactionManager transactionManager() { (4)
//         return new DataSourceTransactionManager(dataSource());
//     }

//     @Bean
//     DataSource dataSource(){ (5)
//         return new EmbeddedDatabaseBuilder()
//                 .generateUniqueName(true)
//                 .setType(EmbeddedDatabaseType.HSQL)
//                 .addScript("create-customer-schema.sql")
//                 .build();
//     }
// }

fun main(args: Array<String>) {
	runApplication<Application>(*args)
}
