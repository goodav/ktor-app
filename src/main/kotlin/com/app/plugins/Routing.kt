package com.app.plugins

import com.app.domain.Male
import com.app.domain.Person
import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.request.*
import org.bson.types.ObjectId

fun Application.configureRouting() {

  // Starting point for a Ktor app:
  routing {
    route("/app/persons/{id}", HttpMethod.Get) {
      handle {
        val person = createTheOldMan()
        call.respond(person)
      }
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
