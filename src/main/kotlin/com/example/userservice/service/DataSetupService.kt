package com.example.userservice.service

import jakarta.annotation.Resource
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.CommandLineRunner
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate
import org.springframework.stereotype.Service
import org.springframework.util.StreamUtils

@Service
class DataSetupService(@Value("classpath:h2/init.sql") val initSql: Resource, val entityTemplate: R2dbcEntityTemplate): CommandLineRunner {
    override fun run(vararg args: String?) {
        val query = StreamUtils.copyToString(initSql.javaClass.getResourceAsStream(this.toString()), Charsets.UTF_8)
        entityTemplate.databaseClient.sql(query).then().subscribe()
    }
}