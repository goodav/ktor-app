package com.app

import com.app.plugins.apiRoutes
import com.app.service.PersonService
import com.app.service.mongo.MongoPersonService
import com.fasterxml.jackson.databind.SerializationFeature
import com.mongodb.client.MongoClient
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.jackson.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.koin.dsl.module
import org.koin.ktor.ext.Koin
import org.koin.logger.SLF4JLogger
import org.litote.kmongo.KMongo

fun main(args: Array<String>) {
    embeddedServer(Netty, commandLineEnvironment(args)).start()
}


@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {

    install(ContentNegotiation) {
        jackson {
            enable(SerializationFeature.INDENT_OUTPUT)
        }
    }

    install(Koin) {
        SLF4JLogger()
        modules(
            module {
                single<MongoClient> {
                    KMongo.createClient(
                        environment.config.propertyOrNull("app.mongoUri")?.getString()!!
                    )
                }
                single<PersonService> { MongoPersonService() }
            }
        )
    }

    routing {
        apiRoutes()
    }

}

