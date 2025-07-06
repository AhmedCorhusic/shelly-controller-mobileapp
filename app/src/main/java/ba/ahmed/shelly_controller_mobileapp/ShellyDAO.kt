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
                    val shellyControlResponse: ShellyControlResponse? = response.body()
                    if (shellyControlResponse != null) {
                        Log.d(TAG, "Request succeeded. Current position: ${shellyControlResponse.currentPos}")
                        "Success. Current Position: ${shellyControlResponse.currentPos}"
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

    suspend fun getCurrentPos(host: Int): Int {
        return withContext(Dispatchers.IO) {
            try {
                val url = "http://192.168.1.${host}/rpc/Cover.GetStatus?id=0"
                val response = ApiAdapter.retrofit.getStatus(url)
                if (response.isSuccessful) {
                    val shellyGetStatusResponse: ShellyGetStatusResponse? = response.body()
                    if (shellyGetStatusResponse != null) {
                        Log.d(TAG, "Request succeeded. Current position: ${shellyGetStatusResponse.currentPos}")
                        shellyGetStatusResponse.currentPos
                    } else {
                        Log.w(TAG, "Request successful but response body is null.")
                        -1
                    }
                } else {
                    val errorBody = response.errorBody()?.string()
                    Log.e(TAG, "Request not successful. Code: ${response.code()}, Error: $errorBody")
                    -1
                }
            } catch (e: Exception) {
                Log.e(TAG, "Network request failed", e)
                -1
            }
        }
    }

}