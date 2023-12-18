package com.onestopcoding.graphqlsecuritydemo.security

import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component

@Component
class CustomAuthenticationProvider (private val rsaKeys: RSAKeyProperties):AuthenticationProvider{
    override fun authenticate(authentication: Authentication?): Authentication {
        return  runCatching {
            val customAuth = authentication as CustomAuthentication
            val claims = JwtUtil.validateToken(customAuth.credentials, rsaKeys.publicKey.toString())

            return@runCatching CustomAuthentication(
                authentication = true,
                username = claims.subject,
                roles = claims["roles"] as MutableCollection<String>
            )
        }.getOrThrow()
    }

    override fun supports(authentication: Class<*>?): Boolean {
       return CustomAuthentication::class.java == authentication
    }

}