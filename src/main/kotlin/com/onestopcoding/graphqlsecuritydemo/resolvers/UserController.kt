package com.onestopcoding.graphqlsecuritydemo.resolvers

import com.onestopcoding.graphqlsecuritydemo.nodes.User
import com.onestopcoding.graphqlsecuritydemo.services.UserService
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Controller

@Controller
open class UserController(private val userService: UserService) {

    @MutationMapping
    open fun register(@Argument input: User): User {
        return userService.save(input)
    }

    @QueryMapping
    open fun userByUsername(@Argument username: String): User {
        return userService.findByUsername(username)
    }

    @PreAuthorize("hasRole('ADMIN')")
    @QueryMapping
    open fun allUsers():List<User>{
        return userService.findAll()
    }
}