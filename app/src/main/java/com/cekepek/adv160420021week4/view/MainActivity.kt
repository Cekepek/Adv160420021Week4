package com.cekepek.adv160420021week4.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.cekepek.adv160420021week4.R
import com.cekepek.adv160420021week4.util.createNotificationChannel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    init {
        instance = this
    }

    companion object {
        private var instance: MainActivity? = null

        fun showNotification(title: String, content: String, icon: Int) {
            val channelId = "${instance?.packageName}-${instance?.getString(R.string.app_name)}"
            val notificationBuilder =
                NotificationCompat.Builder(instance!!.applicationContext, channelId).apply {
                    setSmallIcon(icon)
                    setContentTitle(title)
                    setContentText(content)
                    setStyle(NotificationCompat.BigTextStyle())
                    priority = NotificationCompat.PRIORITY_DEFAULT
                    setAutoCancel(true)

                }
            val notificationManager =
                NotificationManagerCompat.from(instance!!.applicationContext.applicationContext!!)
            notificationManager.notify(1001, notificationBuilder.build())

        }
    }

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)

            val observable = Observable.just("a stream of data", "hello", "world")

            val observer = object : Observer<String> {
                override fun onSubscribe(d: Disposable?) {
                    Log.d("Messages", "begin subscribe")
                }

                override fun onNext(t: String?) {
                    Log.d("Messages", "data: $t")
                }

                override fun onError(e: Throwable?) {
                    Log.e("Messages", "error: ${e!!.message.toString()}")
                }

                override fun onComplete() {
                    Log.d("Messages", "complete")
                }
            }
            observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer)

            createNotificationChannel(this,
                NotificationManagerCompat.IMPORTANCE_DEFAULT, false,
                getString(R.string.app_name), "App notification channel.")

            Observable.timer(5, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe{
                    Log.d("Messages", "five seconds")
                    showNotification("Ini Nama Student",
                    "A new notification created",
                    R.drawable.ic_baseline_error_24)
                }
        }
    }