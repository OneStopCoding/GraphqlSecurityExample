package com.onestopcoding.graphqlsecuritydemo.resolvers


import com.onestopcoding.graphqlsecuritydemo.security.RSAKeyProperties
import com.onestopcoding.graphqlsecuritydemo.services.UserService
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.stereotype.Controller

@Controller
class LoginController(private val rsaKeys: RSAKeyProperties, private val userService: UserService) {

    @MutationMapping
    fun login(@Argument username: String, @Argument password: String): String {
        return userService.login(username, password, rsaKeys.publicKey.toString())
    }




}

