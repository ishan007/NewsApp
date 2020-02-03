package com.example.bulletinapp.view.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.example.bulletinapp.R
import com.example.bulletinapp.di.application.BulletinApplication


/**
 *  Main launching activity used as splash screen
 */
class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (application as BulletinApplication).appComponent.newsViewComponent().create().inject(this)

        startDeliveryActivity()
    }

    // starting news activity with delay of 1 second to give feel of splash screen
    private fun startDeliveryActivity(){
        Handler().postDelayed({
            val intent = Intent(this, NewsActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
        }, 1000)
    }
}
