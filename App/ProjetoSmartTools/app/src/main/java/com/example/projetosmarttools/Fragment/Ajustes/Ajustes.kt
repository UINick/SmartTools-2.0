package com.example.projetosmarttools.Fragment.Ajustes

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.projetosmarttools.Fragment.Loading.LoadingScreen.dialog
import com.example.projetosmarttools.R
import kotlinx.android.synthetic.main.fragment_ajustes.*
import kotlinx.android.synthetic.main.loading_screen.*
import java.util.stream.DoubleStream.builder

class Ajustes : AppCompatActivity() {

    private val REQUEST_IMAGE_GALLERY = 132
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_ajustes)

        displaypicture.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Selecione a imagem")
            builder.setMessage("Escolha sua opção")
            builder.setPositiveButton("Galeria") { dialog : DialogInterface, which : Int ->
                dialog.dismiss()

                val intent = Intent(Intent.ACTION_PICK)
                intent.type = "image/*"
                startActivityForResult(intent,REQUEST_IMAGE_GALLERY)
            }
            builder.setNegativeButton("Camera") { dialog : DialogInterface, which : Int ->
                dialog.dismiss()
            }
            val dialog :AlertDialog = builder.create()
            dialog.show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_IMAGE_GALLERY && resultCode == RESULT_OK && data != null){
            displaypicture.setImageURI(data.data)
        }else{
            Toast.makeText( this, "Algo deu errado!", Toast.LENGTH_SHORT).show()
        }
    }
}