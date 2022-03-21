package com.app.plugins

import com.app.domain.Male
import com.app.domain.Person
import com.app.service.PersonService
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import org.bson.types.ObjectId
import org.koin.ktor.ext.inject

fun Route.apiRoutes() {

    val personService by inject<PersonService>()


    get("/app/persons/{id}") {
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
