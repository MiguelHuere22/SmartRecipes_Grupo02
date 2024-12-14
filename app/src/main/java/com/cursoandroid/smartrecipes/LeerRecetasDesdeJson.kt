import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.InputStreamReader
import com.cursoandroid.smartrecipes.Comida

fun leerRecetasDesdeJson(context: Context): List<Comida> {
    val inputStream = context.assets.open("Recetas.json")
    val reader = InputStreamReader(inputStream)
    val recetasType = object : TypeToken<List<Comida>>() {}.type
    return Gson().fromJson(reader, recetasType)
}
