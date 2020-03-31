package dz.esi.demodata.external

import android.os.Bundle
import android.os.Environment
import android.os.Environment.getExternalStorageDirectory
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import dz.esi.demodata.AppTools
import dz.esi.demodata.R
import kotlinx.android.synthetic.main.activity_external_storage.*
import java.io.*

class ExternalStorageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_external_storage)
        readCheckBox.isChecked = isReadable()
        val writeCheckBox = findViewById<CheckBox>(R.id.readWriteCheckbox)
        writeCheckBox?.isChecked = isWritable()
        lireDonnees()
        val button = findViewById<Button>(R.id.btnSaveExternal)
        button?.setOnClickListener({

            val txt = findViewById<EditText>(R.id.txtExternal)
            Sauver(txt.text.toString())
        })
    }

    private fun lireDonnees() {
        val file = File(getExternalFilesDir(null), "monfichier.txt")
        try {
            val stream = FileInputStream(file)
            val text = AppTools.lire(stream)
            val txt = findViewById<EditText>(R.id.txtExternal) as EditText
            txt.setText(text)
            AppTools.showToast(this, "Données lues")

        } catch (e: FileNotFoundException) {
            AppTools.alertbox(this, "fichier non trouvé")
        } catch (e: IOException) {
            AppTools.alertbox(this, "erreur de lecture")
        }


    }

    private fun isWritable(): Boolean {
        val state = Environment.getExternalStorageState()
        return if (Environment.MEDIA_MOUNTED.equals(state)) {
            true
        } else false
    }

    private fun isReadable(): Boolean {
        val state = Environment.getExternalStorageState()
        return if (Environment.MEDIA_MOUNTED.equals(state) || Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            true
        } else false
    }

    private fun Sauver(text: String) {
        val file = File(getExternalFilesDir(null), "monfichier.txt")
        try {
            val stream = FileOutputStream(file)
            stream.write(text.toByteArray())
            stream.close()
            AppTools.showToast(this, "Données sauvegardées")
        } catch (e: FileNotFoundException) {
            AppTools.alertbox(this, "fichier non trouvé")
        } catch (e: IOException) {
            // TODO Auto-generated catch block
            AppTools.alertbox(this, "erreur d'écriture")
        }

    }
}
