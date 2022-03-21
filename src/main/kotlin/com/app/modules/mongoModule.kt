package com.app.modules


import com.mongodb.client.MongoClient
import org.koin.dsl.module
import org.litote.kmongo.KMongo

//fun Application.kmongoModule() {
//    module {
//        single<MongoClient> { KMongo.createClient("mongodb://127.0.0.1:27017") }
//    }
//}

val mongoModule = module {
    module {
        println("initializing mongoModule...")
        single<MongoClient> { KMongo.createClient("mongodb://127.0.0.1:27017") }
    }
}

