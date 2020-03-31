package dz.esi.demodata.data

import androidx.room.*


/**
 * Created by Amine on 14/04/2018.
 */
@Dao
interface ProduitDAO {
    @Query("SELECT * FROM produit")
    fun getProduits(): MutableList<Produit>

    @Query("SELECT * FROM produit WHERE code = :code")
    fun getProduit(code: String): List<Produit>

    @Insert
    fun ajouter(produit : Produit)

    @Update
    fun modifier(produit : Produit)

    @Delete
    fun supprimer(produit: Produit)

}