package ba.ahmed.shelly_controller_mobileapp

import com.google.gson.annotations.SerializedName

data class LocalControlResponse(
    @SerializedName("state") val state: String,
    @SerializedName("source") val source: String,
    @SerializedName("power") val power: Double,
    @SerializedName("is_valid") val isValid: Boolean,
    @SerializedName("safety_switch") val safetySwitch: Boolean,
    @SerializedName("overtemperature") val overtemperature: Boolean,
    @SerializedName("stop_reason") val stopReason: String,
    @SerializedName("last_direction") val lastDirection: String,
    @SerializedName("current_pos") val currentPos: Int,
    @SerializedName("calibrating") val calibrating: Boolean,
    @SerializedName("positioning") val positioning: Boolean
)