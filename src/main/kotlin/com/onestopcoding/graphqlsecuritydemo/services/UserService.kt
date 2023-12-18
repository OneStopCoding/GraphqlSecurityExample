package com.onestopcoding.graphqlsecuritydemo.services

import com.onestopcoding.graphqlsecuritydemo.nodes.User
import com.onestopcoding.graphqlsecuritydemo.repositories.UserRepository
import com.onestopcoding.graphqlsecuritydemo.security.JwtUtil
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository, private val passwordEncoder: PasswordEncoder) {

    fun save(user: User): User {
        user.setPassword(passwordEncoder.encode(user.getPassword()))
        return userRepository.save(user)
    }

    fun login(username: String, password: String, secret: String): String {
        val user = userRepository.findByUsername(username) ?: throw UsernameNotFoundException("user couldn't be found")
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw BadCredentialsException("Bad password!")
        }
        return JwtUtil.generateToken(
            username = username,
            signedSecret = secret,
            roles = user.getRoles().split(",")
        )
    }

    fun findByUsername(username: String): User {
        return userRepository.findByUsername(username)
    }

    fun findAll ():List<User>{
        return userRepository.findAll();
    }

}