package io.github.anotherjack.kotlintest

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.tbruyelle.rxpermissions2.RxPermissions
import io.reactivex.Observable
import io.reactivex.functions.Function3
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val TAG = "Observable --------> "

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        test.setOnClickListener {
            val intent = Intent(this,Main2Activity::class.java)
            AvoidOnResult(this)
                    .startForResult(intent,23)
                    .subscribe {
                        if(it.resultCode == Activity.RESULT_OK){
                            val text = it.data.getStringExtra("text")
                            Toast.makeText(this,"fetched data -> "+text,Toast.LENGTH_SHORT).show()
                        }else{
                            Toast.makeText(this,"canceled",Toast.LENGTH_SHORT).show()
                        }

                    }
        }

        RxPermissions(this)
                .request(Manifest.permission.INTERNET)
                .subscribe { granted ->
                    if (granted){

                    }else{

                    }
                }
    }

    fun getA(): Observable<String> {
        return Observable.just("A","AA").subscribeOn(Schedulers.newThread())
    }

    fun getB(): Observable<String>? {
        return Observable.just("B","BB")
    }

    fun getC(): Observable<String>? {
        return Observable.just("C","CC").subscribeOn(Schedulers.newThread())
    }

    fun test(){
        Observable.zip(getA(),getB(),getC(),object :Function3<String,String,String,Map<String,String>>{
            override fun apply(t1: String, t2: String, t3: String): Map<String, String> {
                return mutableMapOf("aaa" to t1,
                        "bbb" to t2,
                        "ccc" to t3)
            }

        })

                .subscribe { Log.d(TAG,it.toString()) }
    }
}
