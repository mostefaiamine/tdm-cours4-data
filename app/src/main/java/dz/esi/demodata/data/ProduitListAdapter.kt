package dz.esi.demodata.data

import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import dz.esi.demodata.AppTools
import dz.esi.demodata.R
import kotlinx.android.synthetic.main.activity_edit_produit.*

/**
 * Created by Amine on 14/04/2018.
 */
class ProduitListAdapter(private val _ctx: Context, rId: Int, private val produits: MutableList<Produit>) : ArrayAdapter<Produit>(_ctx, rId, produits) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = _ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val rowView = inflater.inflate(R.layout.produit_item, parent, false)
        val txtNom = rowView.findViewById<TextView>(R.id.txtNom)
        val txtPrix = rowView.findViewById<TextView>(R.id.txtPrix)

        val txtId = rowView.findViewById<TextView>(R.id.txtId)
        var btnEdit = rowView.findViewById<TextView>(R.id.btnEdit)
        var btnRemove = rowView.findViewById<TextView>(R.id.btnRemove)
        val p = produits[position]
        txtNom.text = p.designation
        txtPrix.text = p.prix.toString()
        txtId.text = p.code
        btnEdit.setOnClickListener({
            val intent = Intent(_ctx, EditProduitActivity::class.java)
            intent.putExtra("mode", "modif")
            intent.putExtra("code", p.code)
            _ctx.startActivity(intent)
        })
        btnRemove.setOnClickListener({
            supprimerProduit(_ctx, p)
        })


        return rowView
    }

    fun supprimerProduit(ctx: Context, p: Produit) {
        var adapter = this
        object : AsyncTask<Void, Void, Void>() {
            override fun doInBackground(vararg voids: Void): Void? {
                val db = ProduitDB.getInstance(ctx)
                val dao = db?.produitDAO()
                dao?.supprimer(p)
                return null
            }


            override fun onPostExecute(result: Void?) {
                AppTools.showToast(ctx, "Produit supprimé")
                adapter.remove(p)

            }
        }.execute()
    }
}