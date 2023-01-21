package io.hoon.dck.infrastructure.util

import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes
import java.util.Locale

object HttpRequestUtil {

    val REMOTE_IP_HEADERS = arrayOf("X-Forwarded-For" , "Proxy-Client-IP", "WL-Proxy-Client-IP", "HTTP_CLIENT_IP", "HTTP_X_FORWARDED_FOR", "HTTP_X_FORWARDED",
        "HTTP_X_CLUSTER_CLIENT_IP", "HTTP_FORWARDED_FOR", "HTTP_FORWARDED", "HTTP_VIA", "REMOTE_ADDR")

    private val DEFAULT_IP = "0.0.0.0"
    fun getRemoteIp(): String =
        when (val requestAttributes = RequestContextHolder.getRequestAttributes()) {
            is ServletRequestAttributes -> getRemoteIpFromHeader(requestAttributes)
            else -> DEFAULT_IP
        }
    fun getLocale(): Locale =
        when (val requestAttributes = RequestContextHolder.getRequestAttributes()) {
            is ServletRequestAttributes -> requestAttributes.request.locale
            else -> Locale.getDefault()
        }

    private fun getRemoteIpFromHeader(requestAttributes: ServletRequestAttributes): String {
        for (headerName in REMOTE_IP_HEADERS) {
            val ipInHeader = requestAttributes.request.getHeader(headerName)?.split(",")?.get(0)
            if (!ipInHeader.isNullOrBlank()) return ipInHeader
        }
        return requestAttributes.request.remoteAddr
    }
}