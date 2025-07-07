package ba.ahmed.shelly_controller_mobileapp

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ShellyDAO {
    private val TAG = "ShellyDAO"

    suspend fun getLocalCurrentPos(host: Int): Int {
        return withContext(Dispatchers.IO) {
            try {
                val url = "http://192.168.1.${host}/rpc/Cover.GetStatus?id=0"
                val response = ApiAdapter.retrofit.getLocalStatus(url)
                if (response.isSuccessful) {
                    val localStatusResponse: LocalStatusResponse? = response.body()
                    if (localStatusResponse != null) {
                        Log.d(TAG, "Request succeeded. Current position: ${localStatusResponse.currentPos}")
                        localStatusResponse.currentPos
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

    suspend fun controlLocalRollers(host: Int, go: String, rollerPos: Int): String {
        return withContext(Dispatchers.IO) {
            try {
                val url = "http://192.168.1.${host}/roller/0"
                val response = ApiAdapter.retrofit.controlLocalRollers(url, go, rollerPos)
                if (response.isSuccessful) {
                    val localControlResponse: LocalControlResponse? = response.body()
                    if (localControlResponse != null) {
                        Log.d(TAG, "Request succeeded. Current position: ${localControlResponse.currentPos}")
                        "SUCCESS: Current Position: ${localControlResponse.currentPos}"
                    } else {
                        Log.w(TAG, "Request successful but response body is null.")
                        "FAILED: no data"
                    }
                } else {
                    val errorBody = response.errorBody()?.string()
                    Log.e(TAG, "Request not successful. Code: ${response.code()}, Error: $errorBody")
                    "FAILED: ${response.code()} - $errorBody"
                }
            } catch (e: Exception) {
                Log.e(TAG, "Network request failed", e)
                "FAILED: ${e.message}"
            }
        }
    }

    suspend fun getCloudCurrentPos(host: Int): Int {
        return withContext(Dispatchers.IO) {
            try {
                var hostID = BuildConfig.ID_HOST1
                if (host == 3)
                    hostID = BuildConfig.ID_HOST2
                if (host == 4)
                    hostID = BuildConfig.ID_HOST3

                val response = ApiAdapter.retrofit.getCloudStatus(hostID)

                if (response.isSuccessful) {
                    val cloudStatusResponse: CloudStatusResponse? = response.body()
                    val currentPos = cloudStatusResponse?.data?.deviceStatus?.cover0?.currentPos

                    if (cloudStatusResponse != null) {
                        Log.d(TAG, "Request succeeded. Current position: $currentPos")
                        currentPos
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
            }!!
        }
    }

    suspend fun controlCloudRollers(host: Int, go: String, rollerPos: Int): String {
        return withContext(Dispatchers.IO) {
            try {
                var hostID = BuildConfig.ID_HOST1
                if (host == 3)
                    hostID = BuildConfig.ID_HOST2
                if (host == 4)
                    hostID = BuildConfig.ID_HOST3

                val response = ApiAdapter.retrofit.controlCloudRollers(hostID, rollerPos)

                if (response.isSuccessful) {
                    val cloudControlResponse = response.body()

                    if (cloudControlResponse != null) {
                        Log.d(TAG, "Request succeeded")
                        "Success"
                    } else {
                        Log.w(TAG, "Request successful but response body is null.")
                        "Success, but no data"
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