package com.onestopcoding.graphqlsecuritydemo.security

import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority

class CustomAuthentication(val authentication: Boolean = false, val token: String? = null,
                           private val username: String? = null, private val roles: MutableCollection<String>? = mutableListOf()): Authentication {
    override fun getName(): String {
       return  username.orEmpty()
    }

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return roles?.map {
            SimpleGrantedAuthority(it)
        }?.toMutableList() ?: mutableListOf()
    }

    override fun getCredentials(): String {
        return token.orEmpty()
    }

    override fun getDetails(): String{
        return username.orEmpty()
    }

    override fun getPrincipal(): String {
        return username.orEmpty()
    }

    override fun isAuthenticated(): Boolean {
       return authentication
    }

    override fun setAuthenticated(isAuthenticated: Boolean) {
        throw UnsupportedOperationException("Method not supported")
    }
}

