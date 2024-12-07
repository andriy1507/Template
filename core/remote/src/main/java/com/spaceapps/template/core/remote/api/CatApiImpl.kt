package com.spaceapps.template.core.remote.api

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import javax.inject.Inject

class CatApiImpl
@Inject
constructor(private val client: HttpClient) : CatApi {
    override suspend fun getCat() {
        client.get("https://cataas.com/cat")
    }
}
