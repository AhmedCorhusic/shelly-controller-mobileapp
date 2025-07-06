package ba.ahmed.shelly_controller_mobileapp

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface Api {

    @GET
    suspend fun controlRollers(
        @Url url: String,
        @Query("go") go: String,
        @Query("roller_pos") rollerPos: Int
    ): Response<ShellyControlResponse>

    @GET
    suspend fun getStatus(
        @Url url: String
    ): Response<ShellyGetStatusResponse>
}