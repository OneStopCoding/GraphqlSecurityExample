package com.onestopcoding.graphqlsecuritydemo.nodes
import org.springframework.data.neo4j.core.schema.Id
import org.springframework.data.neo4j.core.schema.Node

@Node
class User(
    @Id
    private val email: String,
    private val username: String,
    private var password: String,
    private val roles: String,
    private val isAccountNonExpired: Boolean = true,
    private val isAccountNonLocked: Boolean = true,
    private val isCredentialsNonExpired: Boolean = true,
    private val isEnabled: Boolean = true
) {
    fun getUsername(): String{return username}
    fun getPassword(): String{return password}
    fun getRoles():String{return roles}
    fun setPassword(password: String){
        this.password = password
    }
}