package ba.ahmed.shelly_controller_mobileapp

import com.google.gson.annotations.SerializedName
import org.json.JSONObject

data class LocalStatusResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("source") val source: String,
    @SerializedName("state") val state: String,
    @SerializedName("apower") val apower: Double,
    @SerializedName("voltage") val voltage: Double,
    @SerializedName("current") val current: Double,
    @SerializedName("pf") val pf: Double,
    @SerializedName("freq") val freq: Double,
    @SerializedName("aenergy") val aenergy: JSONObject,
    @SerializedName("temperature") val temperature: JSONObject,
    @SerializedName("pos_control") val posControl: String,
    @SerializedName("last_direction") val lastDirection: String,
    @SerializedName("current_pos") val currentPos: Int
)