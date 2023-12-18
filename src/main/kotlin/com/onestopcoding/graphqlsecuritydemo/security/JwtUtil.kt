package com.onestopcoding.graphqlsecuritydemo.security

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import java.time.Instant
import java.util.*
import javax.crypto.SecretKey

object JwtUtil {
    fun generateToken(
        username: String,
        signedSecret: String,
        roles: List<String> = listOf()
    ):String{
        val claims = HashMap<String, Any>()
        claims["roles"]= roles
        val currentDate = Date.from(Instant.now())

        val key = getSecret(signedSecret)
        return Jwts.builder()
            .claims(claims)
            .subject(username)
            .issuedAt(currentDate)
            .expiration(Date.from(currentDate.toInstant().plusSeconds(60*60*24)))
            .id(UUID.randomUUID().toString())
            .signWith(key)
            .compact()
    }

    fun validateToken(token: String, signedSecret: String): Claims =  Jwts.parser()
        .verifyWith(getSecret(signedSecret))
        .build()
        .parseSignedClaims(token)
        .payload;
}

private fun getSecret(signedSecret: String): SecretKey =Keys.hmacShaKeyFor(signedSecret.toByteArray())

