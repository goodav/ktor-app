package com.app.plugins

import com.app.domain.Male
import com.app.domain.Person
import io.ktor.application.*
import org.bson.types.ObjectId

fun Application.configureRouting() {

    //val personService by inject<MongoPersonService>()

    // Starting point for a Ktor app:
//    routing {
//        route("/app/persons/{id}", HttpMethod.Get) {
//            handle {
//                //val person = createTheOldMan()
//                val id = call.parameters["id"]!!.toLong()
//                val person = personService.get(id)
//                if (person != null) {
//                    call.respond(person)
//                } else {
//                    call.respond(HttpStatusCode.NotFound, mapOf("msg" to "not found"))
//                }
//            }
//        }
//    }
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
