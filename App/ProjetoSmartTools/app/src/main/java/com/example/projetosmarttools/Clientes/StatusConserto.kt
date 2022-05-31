package com.example.projetosmarttools.Clientes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.projetosmarttools.R

class StatusConserto : AppCompatActivity() {

    lateinit var img: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_status_conserto)

        img = findViewById(R.id.img_status)

        val status = intent.getStringExtra("statusConserto")

        if (status == "PENDENTE") {
            img.setBackgroundResource(R.mipmap.pendente)
        } else if (status == "EM_ANDAMENTO") {
            img.setBackgroundResource(R.mipmap.emandamento)
        } else {
            img.setBackgroundResource(R.mipmap.finalizado)
        }

    }
}