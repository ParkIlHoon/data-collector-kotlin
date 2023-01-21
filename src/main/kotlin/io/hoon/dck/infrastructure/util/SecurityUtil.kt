package io.hoon.dck.infrastructure.util

import io.hoon.dck.infrastructure.token.KeyAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder

object SecurityUtil {

    fun getSecretKey(): String =
        SecurityContextHolder.getContext().authentication.principal.toString()

    fun setAuthentication(key: String): KeyAuthenticationToken =
        KeyAuthenticationToken(key, null, listOf(SimpleGrantedAuthority("ROLE_USER")))
            .also { SecurityContextHolder.getContext().authentication = it }
}