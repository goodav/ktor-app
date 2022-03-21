package com.app.modules

import com.app.service.mongo.MongoPersonService
import org.koin.dsl.module

val appModule = module {
    module {
        println("initializing appModule...")
        single<MongoPersonService> { MongoPersonService() }
    }
}

