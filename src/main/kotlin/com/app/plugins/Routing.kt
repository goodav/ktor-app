package com.app.plugins

import com.app.domain.Male
import com.app.domain.Person
import com.app.service.PersonService
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import org.bson.types.ObjectId
import org.koin.ktor.ext.inject

fun Route.apiRoutes() {

    val personService by inject<PersonService>()


    get("/app/persons/{id}") {
        val id = call.parameters["id"]!!.toLong()
        val person = personService.get(id)
        if (person != null) {
            call.respond(person)
        } else {
            call.respond(HttpStatusCode.NotFound, mapOf("msg" to "not found"))
        }
    }

    get("/app/persons") {
        val results = personService.getAllPersons().map { it }.toList()
        call.respond(results)
    }
    /*
     @Get("/persons/all")
    @Produces(MediaType.APPLICATION_JSON)
    suspend fun getPersons(): List<Person> {
        logger.info("controller: key=${provider.get().getValue()} is accessing person=${MongoOperator.id}")
        return withContext(coroutineContext + provider.get()) {
            service.getAllPersons().map { it }.toList()
        }
    }
     */
}

private fun createTheOldMan(): Person {
    return Person(
        _id = ObjectId.get().toString(),
        id = 1,
        firstName = "dave",
        lastName = "goodin",
        age = 51,
        isFunny = true,
        gender = Male()
    )
}
