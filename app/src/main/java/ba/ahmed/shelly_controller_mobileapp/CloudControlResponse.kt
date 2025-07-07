package ba.ahmed.shelly_controller_mobileapp

import com.google.gson.annotations.SerializedName
import org.json.JSONObject

data class CloudControlResponse(
    @SerializedName("isok") val isOk: Boolean,
    @SerializedName("data") val data: JSONObject
)