package com.example.projetosmarttools

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.projetosmarttools.Cadastro.Service.Oficina.CadastroOficinaVO
import com.example.projetosmarttools.Fragment.Loading.LoadingScreen
import com.example.projetosmarttools.Fragment.Loading.LoadingScreen.dialog
import kotlinx.android.synthetic.main.fragment_ajustes.*
import kotlinx.android.synthetic.main.loading_screen.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.stream.DoubleStream.builder

class Ajustes : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_ajustes)
    }

}