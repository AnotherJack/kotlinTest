package io.github.anotherjack.kotlintest

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main2.*

class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        back.setOnClickListener {
            val intent = Intent()
            intent.putExtra("text",text.text.toString())
            setResult(Activity.RESULT_OK,intent)
            finish()
        }
    }
}
