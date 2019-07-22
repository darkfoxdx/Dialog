package com.projecteugene.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.projecteugene.dialog.DialogBuilder
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import kotlinx.android.synthetic.main.dialog_pin.*

class MainActivity : AppCompatActivity() {
    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DialogBuilder(this@MainActivity, supportFragmentManager)
            .setMessage("This is a sample dialog")
            .show()

        val fragment = DialogBuilder(this, supportFragmentManager)
            .setTitle("Enter your PIN")
            .setTextNegativeButton("Cancel")
            .setTextPositiveButton("Enter")
            .setDismissiblePositiveOnClick(false)
            .setView(R.layout.dialog_pin)
            .show()

        fragment.onDialogShown.subscribe {dialog->
            dialog.til_enter_pin.editText?.setText("12345")
        }.addTo(compositeDisposable)

        fragment.onPositiveClick.subscribe {dialog ->
            val validateEnterPin = dialog.til_enter_pin.editText?.text?.toString() == "123456"

            if (validateEnterPin) {
                dialog.dismiss()
            } else {
                DialogBuilder(this, supportFragmentManager)
                    .setMessage("PIN is not 123456")
                    .show()
            }
            Log.d("onPositiveClick", "triggered")
        }.addTo(compositeDisposable)

    }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }
}
