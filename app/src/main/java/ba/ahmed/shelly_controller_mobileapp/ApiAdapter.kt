package ba.ahmed.shelly_controller_mobileapp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiAdapter {
    val retrofit: Api = Retrofit.Builder()
        .baseUrl("https://shelly-123-eu.shelly.cloud/") // It will get overridden by @Url in Api.kt
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(Api::class.java)
}