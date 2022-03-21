package com.app.service.mongo


import com.app.domain.Person
import com.app.service.PersonService
import com.mongodb.ReadConcern
import com.mongodb.WriteConcern
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoCollection
import com.mongodb.client.model.Filters.eq
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.litote.kmongo.findOne

class MongoPersonService : PersonService, KoinComponent {

    val client by inject<MongoClient>()

    var collection: MongoCollection<Person> = client.getDatabase("app")
        .withWriteConcern(WriteConcern.ACKNOWLEDGED)
        .withReadConcern(ReadConcern.DEFAULT)
        .getCollection("persons", Person::class.java)

    override
    suspend fun getAllPersons(): Flow<Person> = withContext(Dispatchers.IO) {
        collection.find().asFlow()
    }

    override
    suspend fun get(id: Long): Person? = withContext(Dispatchers.IO) {
        collection.findOne(eq("id", id))
    }

//    override
//    suspend fun getSimulateSlowCall(id: Long): Person? = withContext(Dispatchers.IO) {
//        delay(500)
//        get(id)
//    }

//    override
//    suspend fun create(person: Person): Person = withContext(Dispatchers.IO) {
//        logger.info("service: key=${coroutineContext[ApiKey]?.getValue()} is updating person{${person.id}}}")
//        mongoCollection.insertOne(person).let { result ->
//            throwIfNotSuccess(person, result.wasAcknowledged())
//            get(person.id)!!
//        }
//    }
//
//    override
//    suspend fun update(id: Long, person: Person): Person = withContext(Dispatchers.IO) {
//        logger.info("service: key=${coroutineContext[ApiKey]?.getValue()} is updating person{${person.id}}}")
//        mongoCollection.updateOne(
//            eq("id", person.id), person, UpdateOptions().upsert(true)
//        ).let { result ->
//            throwIfNotSuccess(person, result.wasAcknowledged())
//            get(person.id)!!
//        }
//    }
//
//    private fun throwIfNotSuccess(person: Person, wasAcknowledged: Boolean) =
//        if (!wasAcknowledged) {
//            throw RuntimeException("Failed to save person{${person.id}}")
//        } else {
//        }


}