package com.app.config

import com.typesafe.config.ConfigFactory
import io.ktor.config.*

object Config {

    private val config = HoconApplicationConfig(ConfigFactory.load())

    fun getProperty(key: String): String? = config.propertyOrNull(key)?.getString()

    fun requireProperty(key: String): String = getProperty(key)
        ?: throw IllegalStateException("Missing property $key")
}