package ba.ahmed.shelly_controller_mobileapp

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ShellyDAO {
    private val TAG = "ShellyDAO"

    suspend fun controlRollers(host: Int, go: String, rollerPos: Int): String {
        return withContext(Dispatchers.IO) {
            try {
                val url = "http://192.168.1.${host}/roller/0"
                val response = ApiAdapter.retrofit.controlRollers(url, go, rollerPos)
                if (response.isSuccessful) {
                    val shellyResponse: ShellyResponse? = response.body()
                    if (shellyResponse != null) {
                        Log.d(TAG, "Request succeeded. Current position: ${shellyResponse.currentPos}")
                        "Success. Current Position: ${shellyResponse.currentPos}"
                    } else {
                        Log.w(TAG, "Request successful but response body is null.")
                        "Success, but no data."
                    }
                } else {
                    val errorBody = response.errorBody()?.string()
                    Log.e(TAG, "Request not successful. Code: ${response.code()}, Error: $errorBody")
                    "Not successful: ${response.code()} - $errorBody"
                }
            } catch (e: Exception) {
                Log.e(TAG, "Network request failed", e)
                "Failed: ${e.message}"
            }
        }
    }
}