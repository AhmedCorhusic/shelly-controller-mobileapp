package ba.ahmed.shelly_controller_mobileapp

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Url
import retrofit2.http.Query
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Field

interface Api {

    @GET
    suspend fun getLocalStatus(
        @Url url: String
    ): Response<LocalStatusResponse>

    @GET
    suspend fun controlLocalRollers(
        @Url url: String,
        @Query("go") go: String,
        @Query("roller_pos") rollerPos: Int
    ): Response<LocalControlResponse>

    @GET("device/status")
    suspend fun getCloudStatus(
        @Query("id") id: String,
        @Query("auth_key") authKey: String = BuildConfig.CLOUD_API_KEY
    ): Response<CloudStatusResponse>

    @FormUrlEncoded
    @POST("/device/relay/roller/control")
    suspend fun controlCloudRollers(
        @Field("id") id: String,
        @Field("pos") pos: Int,
        @Field("auth_key") authKey: String = BuildConfig.CLOUD_API_KEY
    ): Response<CloudControlResponse>
}