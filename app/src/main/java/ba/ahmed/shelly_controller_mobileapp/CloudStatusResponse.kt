package ba.ahmed.shelly_controller_mobileapp

import com.google.gson.annotations.SerializedName
import org.json.JSONObject

data class CloudStatusResponse(
    @SerializedName("isok") val isOk: Boolean,
    @SerializedName("data") val data: Data
)

data class Data(
    @SerializedName("online") val online: Boolean,
    @SerializedName("device_status") val deviceStatus: DeviceStatus
)

data class DeviceStatus(
    @SerializedName("sys") val sys: JSONObject,
    @SerializedName("input:1") val input1: JSONObject,
    @SerializedName("v_eve:1") val vEve1: JSONObject,
    @SerializedName("mqtt") val mqtt: JSONObject,
    @SerializedName("serial") val serial: String,
    @SerializedName("input:0") val input0: JSONObject,
    @SerializedName("_updated") val updated: String,
    @SerializedName("v_eve:0") val vEve0: JSONObject,
    @SerializedName("ts") val ts: Double,
    @SerializedName("ws") val ws: JSONObject,
    @SerializedName("cover:0") val cover0: Cover0,
    @SerializedName("id") val id: String,
    @SerializedName("ble") val ble: JSONObject,
    @SerializedName("code") val code: String,
    @SerializedName("cloud") val cloud: JSONObject,
    @SerializedName("wifi") val wifi: JSONObject,
)

data class Cover0(
    @SerializedName("id") val id: Int,
    @SerializedName("aenergy") val aenergy: JSONObject,
    @SerializedName("source") val source: String,
    @SerializedName("state") val state: String,
    @SerializedName("apower") val apower: Double,
    @SerializedName("voltage") val voltage: Double,
    @SerializedName("current") val current: Double,
    @SerializedName("pf") val pf: Double,
    @SerializedName("freq") val freq: Double,
    @SerializedName("temperature") val temperature: JSONObject,
    @SerializedName("pos_control") val posControl: String,
    @SerializedName("last_direction") val lastDirection: String,
    @SerializedName("current_pos") val currentPos: Int
)