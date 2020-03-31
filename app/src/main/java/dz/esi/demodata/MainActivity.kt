package dz.esi.demodata

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import dz.esi.demodata.data.DatabaseActivity
import dz.esi.demodata.external.ExternalStorageActivity
import dz.esi.demodata.internal.InternalStorageActivity
import dz.esi.demodata.preferences.SharedPreferencesActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setEvent(btnSharedPreferences, SharedPreferencesActivity::class.java)
        setEvent(btnInternalStorage, InternalStorageActivity::class.java)
        setEvent(btnExternalStorage, ExternalStorageActivity::class.java)
        setEvent(btnDatabase, DatabaseActivity::class.java)


    }


    private fun setEvent(btn : Button, cls: Class<*>) {

        btn.setOnClickListener({
            val intent = Intent(this, cls)
            startActivity(intent)
        })

    }
}
