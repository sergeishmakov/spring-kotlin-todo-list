package com

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.core.JdbcTemplate
import javax.sql.DataSource
import org.springframework.beans.factory.annotation.*
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.factory.PasswordEncoderFactories

import org.springframework.security.crypto.password.PasswordEncoder





@SpringBootApplication
class Application

@Configuration
@EnableWebSecurity
public class WebSecurityConfig: WebSecurityConfigurerAdapter() {
     override fun configure(http: HttpSecurity) {
        http.csrf().disable()
            .authorizeRequests()
            .antMatchers("/", "/session/sign_up")
            .permitAll()
            .antMatchers("/users")
            .permitAll()
//            .hasRole("ADMIN")
    }
}

@Configuration
class Configuration {
    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder()
    }
    @Bean
    fun jdbcTemplate(@Qualifier("dataSource") dataSource: DataSource): JdbcTemplate {
        return JdbcTemplate(dataSource)
    }
}

fun main(args: Array<String>) {
	runApplication<Application>(*args)
}
