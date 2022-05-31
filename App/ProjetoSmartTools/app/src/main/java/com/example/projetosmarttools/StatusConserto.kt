package com.example.projetosmarttools

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class StatusConserto : AppCompatActivity() {

    lateinit var img: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_status_conserto)

        img = findViewById(R.id.img_status)

        val status = intent.getStringExtra("statusConserto")

        if (status == "PENDENTE") {
            img.setBackgroundResource(R.mipmap.aranha)
        } else if (status == "EM_ANDAMENTO") {
            img.setBackgroundResource(R.mipmap.aranha)
        } else {
            img.setBackgroundResource(R.mipmap.aranha)
        }

    }
}