package dz.esi.demodata.data

import android.content.Intent
import android.os.Bundle
import dz.esi.demodata.AppTools
import dz.esi.demodata.R

import kotlinx.android.synthetic.main.activity_database.*
import kotlinx.android.synthetic.main.content_database.*
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout


class DatabaseActivity : AppCompatActivity() {
    private var db: ProduitDB? = null
    private var dao: ProduitDAO? = null
    private var produits: MutableList<Produit>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_database)

        setSupportActionBar(toolbar)
        initDB()
        val activity = this
        fab.setOnClickListener {
            val intent = Intent(activity, EditProduitActivity::class.java)
            intent.putExtra("mode","ajout")
            startActivity(intent)
        }
    }

    fun initDB() {
        var act = this


        object : AsyncTask<Void, Void, Void>() {
            override fun doInBackground(vararg voids: Void): Void? {
                act.db = ProduitDB.getInstance(act)
                act.dao = db?.produitDAO()
                produits = act.dao?.getProduits()


                return null
            }

            override fun onPostExecute(result: Void?) {
                if(produits != null) {
                    val adapter = ProduitListAdapter(act, R.layout.produit_item, produits!!)
                    lvProduits.adapter = adapter
                    AppTools.showToast(act, "Données Chargées")
                }

            }
        }.execute()
    }

}
