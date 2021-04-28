package com.config

import com.repositories.UserRepository
import com.security.JwtTokenFilter
import com.security.SecurityUser
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig(val jwtTokenFilter: JwtTokenFilter, val userRepository: UserRepository): WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {

        http.cors().and().csrf().disable()

        http
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()


        http
            .exceptionHandling()
            .authenticationEntryPoint { request: HttpServletRequest?, response: HttpServletResponse, ex: AuthenticationException ->
                response.sendError(
                    HttpServletResponse.SC_UNAUTHORIZED,
                    ex.message
                )
            }
            .and()


        http.authorizeRequests() // Our public endpoints
            .antMatchers("/").permitAll()
            .antMatchers(HttpMethod.POST,"/session/**").permitAll()
            .anyRequest().authenticated()


        http.addFilterBefore(
            jwtTokenFilter,
            UsernamePasswordAuthenticationFilter::class.java
        )
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(UserDetailsService { email: String ->
            SecurityUser.fromUser(userRepository
                .findByEmail(email)
                ?: throw UsernameNotFoundException(
                    "User: $email, not found"
                ))
        })
    }

    @Bean
    override fun authenticationManagerBean(): AuthenticationManager {
        return super.authenticationManagerBean()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder(12)
    }

//    @Bean
//    fun authenticationEntryPoint(): AuthenticationEntryPoint? {
//        return CustomAuthenticationEntryPoint()
//    }
//
//    @Bean
//    fun accessDeniedHandler(): AccessDeniedHandler? {
//        return CustomAccessDeniedHandler()
//    }

}