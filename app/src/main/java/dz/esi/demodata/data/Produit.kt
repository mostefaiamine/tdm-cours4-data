package dz.esi.demodata.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Amine on 14/04/2018.
 */
@Entity()
data class Produit (
        @PrimaryKey() var code: String,
        @ColumnInfo(name = "designation") var designation: String,
        @ColumnInfo(name = "prix") var prix: Double
){
    constructor():this("","",0.0){

    }
}