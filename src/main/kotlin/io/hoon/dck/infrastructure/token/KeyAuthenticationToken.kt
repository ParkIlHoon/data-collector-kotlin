package io.hoon.dck.infrastructure.token

import org.springframework.security.authentication.AbstractAuthenticationToken
import org.springframework.security.core.GrantedAuthority

/**
 * 키 인증 토큰
 */
class KeyAuthenticationToken : AbstractAuthenticationToken {
    private var principal: Any
    private var credentials: Any?

    constructor(principal: Any, credentials: Any?) : super(null) {
        this.principal = principal
        this.credentials = credentials
        isAuthenticated = false
    }

    constructor(principal: Any, credentials: Any?, authorities: Collection<GrantedAuthority?>?) : super(authorities) {
        this.principal = principal
        this.credentials = credentials
        isAuthenticated = true
    }

    override fun getCredentials() = credentials

    override fun getPrincipal() = principal
}