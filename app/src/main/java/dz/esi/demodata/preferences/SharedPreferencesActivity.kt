package dz.esi.demodata.preferences

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import dz.esi.demodata.AppTools
import dz.esi.demodata.R
import kotlinx.android.synthetic.main.activity_shared_preferences.*

/**
 * Created by Amine on 14/04/2018.
 */
class SharedPreferencesActivity : AppCompatActivity() {
    val PARAMS_NAME = "PARAMS"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shared_preferences)
        val params = getSharedPreferences(PARAMS_NAME, 0)
        val text = params.getString("monparam", "défaut")
        paramEditText.setText(text)
        AppTools.showToast(this, "Données lues")
        val btn = findViewById<Button>(R.id.btnSauvegarder)
        btn?.setOnClickListener({
            val prms = getSharedPreferences(PARAMS_NAME, 0)
            val editeur = prms.edit()
            editeur.putString("monparam", paramEditText.text.toString())
            editeur.apply()
            AppTools.showToast(this, "Données sauvegardées")
        })

    }
}