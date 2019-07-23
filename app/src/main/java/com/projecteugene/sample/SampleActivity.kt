package com.projecteugene.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.projecteugene.dialog.DialogBuilder
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import kotlinx.android.synthetic.main.dialog_pin.*

class SampleActivity : AppCompatActivity() {
    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragment =
            DialogBuilder(this@SampleActivity, supportFragmentManager)
                .setMessage("This is a sample dialog")
                .setTextNegativeButton("Negative")
                .setTextNeutralButton("Neutral")
                .setTextPositiveButton("Positive")
                .show()

        fragment.onPositiveClick.subscribe {dialog ->
            Log.d("onPositiveClick", "triggered")
        }.addTo(compositeDisposable)

        fragment.onNegativeClick.subscribe {dialog ->
            Log.d("onNegativeClick", "triggered")
        }.addTo(compositeDisposable)

        fragment.onNeutralClick.subscribe {dialog ->
            Log.d("onNeutralClick", "triggered")
        }.addTo(compositeDisposable)
    }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }
}
