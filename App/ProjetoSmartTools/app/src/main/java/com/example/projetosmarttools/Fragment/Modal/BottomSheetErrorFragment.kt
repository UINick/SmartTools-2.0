package com.example.projetosmarttools.Fragment.Modal

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.FragmentManager
import com.example.projetosmarttools.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.bottom_sheet_fragment.*
import java.lang.RuntimeException

class BottomSheetErrorFragment: BottomSheetDialogFragment(), View.OnClickListener {

    private var mListener: ItemBottomSheetClick? = null
    private var titleModal: String = ""
    private var btnTitleModal: String = ""


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bottom_sheet_fragment, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_bottom.setOnClickListener(this)
        btn_bottom.setText(btnTitleModal)
        tv_title.setText(titleModal)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        mListener = if (context is ItemBottomSheetClick) {
            context
        } else {
            throw RuntimeException(
                context.toString() + "Must implement ItemBottomSheetClick"
            )
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    override fun onClick(p0: View?) {
        val btnSelected = p0 as Button
        mListener!!.onItemClick(btnSelected.text.toString())
        dismiss()
    }

    fun setUp(fragment: FragmentManager, title: String, btnTitle: String) {
        show(fragment, ActionBottomSheetBtn.TAG)
        btnTitleModal = btnTitle
        titleModal = title
    }

}