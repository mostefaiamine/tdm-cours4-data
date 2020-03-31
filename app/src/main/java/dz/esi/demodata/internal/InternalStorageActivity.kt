package dz.esi.demodata.internal

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import dz.esi.demodata.AppTools
import dz.esi.demodata.R
import java.io.FileNotFoundException
import java.io.IOException

class InternalStorageActivity : AppCompatActivity() {

    private val fichier = "monfichier.dat"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_internal_storage)
        lireDonnees()
        val button = findViewById<Button>(R.id.btnSauveFichier)

        button?.setOnClickListener({

            val txt = findViewById<View>(R.id.txtFichier) as EditText
            sauvegarder(txt.text.toString())
        })
    }

    private fun sauvegarder(string: String) {

        try {
            val stream = openFileOutput(fichier, Context.MODE_PRIVATE)
            stream.write(string.toByteArray())
            stream.close()
            AppTools.showToast(this, "Données sauvegardées")
        } catch (e: FileNotFoundException) {
            AppTools.alertbox(this, "fichier non trouvé")
        } catch (e: IOException) {
            AppTools.alertbox(this, "erreur d'écriture")
        }

    }

    private fun lireDonnees() {
        try {
            val stream = openFileInput(fichier)
            val text = AppTools.lire(stream)
            val txt = findViewById<EditText>(R.id.txtFichier)
            txt?.setText(text)
            stream.close()
            AppTools.showToast(this, "Données lues")
        } catch (ex: FileNotFoundException) {
            AppTools.alertbox(this, "fichier non trouvé")
        } catch (ex: IOException) {
            AppTools.alertbox(this, "erreur de lecture")

        }

    }
}
