package ru.aston.news

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity


class LottieActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splashscreen)

        supportActionBar?.hide()

        Handler().postDelayed({
            val intent = Intent(this@LottieActivity,MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }
}