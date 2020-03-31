package dz.esi.demodata.data

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dz.esi.demodata.AppTools
import dz.esi.demodata.R
import kotlinx.android.synthetic.main.activity_edit_produit.*
import kotlinx.android.synthetic.main.content_database.*

class EditProduitActivity : AppCompatActivity() {

    var mode: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(
                R.layout.activity_edit_produit)
        mode = intent.getStringExtra("mode")
        if(mode == "modif"){
            val code = intent.getStringExtra("code")
            chargerProduit(code)
        }

        btnSauvegarder.setOnClickListener({
            sauvegarderProduit()
        })
    }

    fun chargerProduit(code: String) {
        var act = this

        object : AsyncTask<Void, Void, Void>() {
            override fun doInBackground(vararg voids: Void): Void? {
                val db = ProduitDB.getInstance(act)
                val dao = db?.produitDAO()
                val produit = dao?.getProduit(code)
                if(produit != null){
                    txtCode.setText(produit[0].code)
                    txtNom.setText(produit[0].designation)
                    txtPrix.setText(produit[0].prix.toString())
                }

                return null
            }


            override fun onPostExecute(result: Void?) {


            }
        }.execute()
    }

    fun sauvegarderProduit() {
        var act = this
        val code = txtCode.text.toString()
        val nom = txtNom.text.toString()
        val prix = txtPrix.text.toString().toDouble()
        object : AsyncTask<Void, Void, Void>() {
            override fun doInBackground(vararg voids: Void): Void? {
                val db = ProduitDB.getInstance(act)
                val dao = db?.produitDAO()
                val produit = Produit(code,nom,prix)
                if(mode == "modif")
                    dao?.modifier(produit)
                else
                    dao?.ajouter(produit)

                return null
            }


            override fun onPostExecute(result: Void?) {
                AppTools.showToast(act, "Produit Ajouté")
                returnToList()

            }
        }.execute()
    }

    fun returnToList(){
        var intent = Intent(this, DatabaseActivity::class.java)
        startActivity(intent)
    }
}
