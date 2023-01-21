package io.hoon.dck.infrastructure.filter

import io.hoon.dck.infrastructure.properties.KeyStoreProperties
import io.hoon.dck.infrastructure.util.SecurityUtil
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

/**
 * 키 인증 필터
 */
@Component
class KeyAuthenticationFilter(
    private val keyStore: KeyStoreProperties
): OncePerRequestFilter() {

    private val headerName = "X-Secret-Key"
    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        if (keyHeaderExists(request)) {
            val key = request.getHeader(headerName)
            if (keyStore.keyExists(key)) {
                SecurityUtil.setAuthentication(key)
            } else {
                response.status = HttpStatus.BAD_REQUEST.value()
                return
            }
        } else {
            response.status = HttpStatus.BAD_REQUEST.value()
            return
        }

        filterChain.doFilter(request, response)
    }

    private fun keyHeaderExists(request: HttpServletRequest) = request.getHeader(headerName)?.isNotBlank() ?: false
}