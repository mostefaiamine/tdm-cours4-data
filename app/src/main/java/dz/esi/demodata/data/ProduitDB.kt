package dz.esi.demodata.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Created by Amine on 14/04/2018.
 */
@Database(entities = arrayOf(Produit::class), version = 1)
abstract class ProduitDB() : RoomDatabase() {
    abstract fun produitDAO(): ProduitDAO

    companion object {
        private var instance: ProduitDB? = null

        fun getInstance(context: Context): ProduitDB? {
            if (instance == null) {

                instance = Room.databaseBuilder(context.getApplicationContext(),
                            ProduitDB::class.java, "produit.db")
                            .build()

            }
            return instance
        }

        fun destroyInstance() {
            instance = null
        }
    }
}