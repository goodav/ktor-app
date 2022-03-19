package com.app

import io.ktor.application.*
import com.app.plugins.configureRouting
import com.fasterxml.jackson.databind.SerializationFeature
import io.ktor.features.ContentNegotiation
import io.ktor.jackson.jackson
import java.lang.Compiler.enable
import java.text.DateFormat

fun main(args: Array<String>): Unit =
  io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {

  configureRouting()

  install(ContentNegotiation) {
    jackson()
  }
}
