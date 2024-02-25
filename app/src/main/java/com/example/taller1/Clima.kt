import org.json.JSONArray
import org.json.JSONObject

data class Clima(
    val time: List<String>,
    val temperature: List<Double>,
    val windspeed: List<Double>,
    val evapotranspiration: List<Double>,
    val wetbulbtemperature: List<Double>
) {
    companion object {
        // Función estática para parsear el JSON y crear una instancia de Clima
        fun fromJson(jsonString: String): Clima {
            val jsonObject = JSONObject(jsonString)
            val timeArray = jsonObject.getJSONArray("time")
            val temperatureArray = jsonObject.getJSONArray("temperature")
            val windspeedArray = jsonObject.getJSONArray("windspeed")
            val evapotranspirationArray = jsonObject.getJSONArray("evapotranspiration")
            val wetbulbtemperatureArray = jsonObject.getJSONArray("wetbulbtemperature")

            val timeList = jsonArrayToListOfString(timeArray)
            val temperatureList = jsonArrayToListOfDouble(temperatureArray)
            val windspeedList = jsonArrayToListOfDouble(windspeedArray)
            val evapotranspirationList = jsonArrayToListOfDouble(evapotranspirationArray)
            val wetbulbtemperatureList = jsonArrayToListOfDouble(wetbulbtemperatureArray)

            return Clima(
                timeList,
                temperatureList,
                windspeedList,
                evapotranspirationList,
                wetbulbtemperatureList
            )
        }

        // Función auxiliar para convertir un JSONArray a una lista de String
        private fun jsonArrayToListOfString(jsonArray: JSONArray): List<String> {
            val list = mutableListOf<String>()
            for (i in 0 until jsonArray.length()) {
                list.add(jsonArray.getString(i))
            }
            return list
        }

        // Función auxiliar para convertir un JSONArray a una lista de Double
        private fun jsonArrayToListOfDouble(jsonArray: JSONArray): List<Double> {
            val list = mutableListOf<Double>()
            for (i in 0 until jsonArray.length()) {
                list.add(jsonArray.getDouble(i))
            }
            return list
        }
    }
}
