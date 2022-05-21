package com.example.projetosmarttools.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.projetosmarttools.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.fragment_floating_action_button.*
import kotlinx.android.synthetic.main.loading_screen.*

class FragmentFloatingActionButton : Fragment() {

    private var clicked = false


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       val toBottom: Animation by lazy {
            AnimationUtils.loadAnimation(
                view.context,
                R.anim.to_bottom_anim
            )
        }

       val rotateOpen: Animation by lazy {
            AnimationUtils.loadAnimation(
                view.context,
                R.anim.rotate_open_anim
            )
        }
        val rotateClose: Animation by lazy {
            AnimationUtils.loadAnimation(
                view.context,
                R.anim.rotate_close_anim
            )
        }
        val fromBottom: Animation by lazy {
            AnimationUtils.loadAnimation(
                view.context,
                R.anim.from_bottom_anim
            )
        }

        add.setOnClickListener(
            onAddButtonClicked()
        )
        add_money.setOnClickListener(
            Toast.makeText(view.context, "Entrada", Toast.LENGTH_SHORT).show()
        )
        exit_money.setOnClickListener(
            Toast.makeText(view.context, "Saída", Toast.LENGTH_SHORT).show()
        )
        add_car.setOnClickListener(
            Toast.makeText(view.context, "Cadastrar Veículo", Toast.LENGTH_SHORT).show()
        )
        add_user.setOnClickListener(
            Toast.makeText(view.context, "Cadastrar Cliente", Toast.LENGTH_SHORT).show()
        )
    }


    private fun setContentView(fragmentFloatingActionButton: Int) {

    }

    private fun onAddButtonClicked() {
        setVisibility(clicked)
        setAnimation(clicked)
        clicked = !clicked
    }

     fun setAnimation(clicked:Boolean) {
        if (!clicked){
            val fromBottom: Animation by lazy { AnimationUtils.loadAnimation(
                view?.context,
                R.anim.to_bottom_anim
            ) }
            add_money.startAnimation(fromBottom)
            exit_money.startAnimation(fromBottom)
            add_car.startAnimation(fromBottom)
            add_user.startAnimation(fromBottom)
            val rotateOpen = null
            add.startAnimation(rotateOpen)
        }else{
            val toBottom = null
            add_money.startAnimation(toBottom)
            exit_money.startAnimation(toBottom)
            add_car.startAnimation(toBottom)
            add_user.startAnimation(toBottom)
            val rotateClose = null
            add.startAnimation(rotateClose)
        }
    }

    private fun setVisibility(clicked:Boolean) {
        if(!clicked){
            add_money.visibility = View.VISIBLE
            exit_money.visibility = View.VISIBLE
            add_car.visibility = View.VISIBLE
            add_user.visibility = View.VISIBLE
        } else {
            add_money.visibility = View.INVISIBLE
            exit_money.visibility = View.INVISIBLE
            add_car.visibility = View.INVISIBLE
            add_user.visibility = View.INVISIBLE
        }
    }
}

private fun FloatingActionButton.setOnClickListener(show: Unit) {
    TODO("Not yet implemented")
}
