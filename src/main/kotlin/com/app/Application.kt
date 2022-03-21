package com.app

import com.app.service.mongo.MongoPersonService
import com.fasterxml.jackson.databind.SerializationFeature
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.jackson.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.koin.core.context.startKoin
import org.koin.ktor.ext.inject
import org.slf4j.event.Level

fun main(args: Array<String>) {

    startKoin {
        modules(
            com.app.modules.mongoModule,
            com.app.modules.appModule
        )
    }

    io.ktor.server.netty.EngineMain.main(args)
}


@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {


    install(org.koin.ktor.ext.Koin) {
        modules(
            com.app.modules.mongoModule,
            com.app.modules.appModule
        )
    }

    install(ContentNegotiation) {
        jackson {
            enable(SerializationFeature.INDENT_OUTPUT)
        }
    }

    install(CallLogging) {
        level = Level.INFO
        filter { call -> call.request.path().startsWith("/") }
    }

    install(CORS) {
        method(HttpMethod.Options)
        method(HttpMethod.Put)
        method(HttpMethod.Delete)
        method(HttpMethod.Patch)
        header(HttpHeaders.Authorization)
        header("MyCustomHeader")
        allowCredentials = true
        anyHost() // @TODO: Don't do this in production if possible. Try to limit it.
    }

    install(DataConversion)

    install(DefaultHeaders) {
        header("X-Engine", "Ktor")
        header("X-Environment", "Dev")
    }

//    configureRouting(personService)

    val personService: MongoPersonService by inject()

    println("personService: $personService")

    routing {
        route("/app/persons/{id}", HttpMethod.Get) {
            handle {
                //val person = createTheOldMan()
                val id = call.parameters["id"]!!.toLong()
                val person = personService.get(id)
                if (person != null) {
                    call.respond(person)
                } else {
                    call.respond(HttpStatusCode.NotFound, mapOf("msg" to "not found"))
                }
            }
        }
    }
}

