package com.projecteugene.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.projecteugene.dialog.DialogBuilder

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DialogBuilder(this, supportFragmentManager, "SampleDialogTag")
            .setMessage("This is a sample dialog")
            .show()
    }
}
