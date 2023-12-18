package com.onestopcoding.graphqlsecuritydemo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity

@SpringBootApplication
@EnableMethodSecurity(securedEnabled = true)
@EnableWebSecurity
@ConfigurationPropertiesScan
class GraphqlSecurityDemoApplication

fun main(args: Array<String>) {
    runApplication<GraphqlSecurityDemoApplication>(*args)
}
