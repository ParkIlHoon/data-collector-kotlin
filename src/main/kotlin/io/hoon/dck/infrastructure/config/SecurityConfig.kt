package io.hoon.dck.infrastructure.config

import io.hoon.dck.infrastructure.filter.KeyAuthenticationFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

/**
 * 보안 설정
 */
@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val keyAuthenticationFilter: KeyAuthenticationFilter
) {

    @Bean
    fun securityFilterChain(http: HttpSecurity) =
        http
            .httpBasic().disable()
            .csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
                .authorizeHttpRequests()
                .anyRequest().authenticated()
            .and()
                .addFilterBefore(keyAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java)
            .build()

}