package com.app.service

import com.app.domain.Person
import kotlinx.coroutines.flow.Flow

interface PersonService {
    suspend fun get(id: Long): Person?

    //suspend fun getSimulateSlowCall(id: Long): Person?
    suspend fun getAllPersons(): Flow<Person>
    //suspend fun create(person: Person): Person
    //suspend fun update(id: Long, person: Person): Person
}