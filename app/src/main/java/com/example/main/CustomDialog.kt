package com.example.main

import android.app.Dialog
import android.content.Context
import android.view.WindowManager
import kotlinx.android.synthetic.main.activity_delete_dialog.*

class CustomDialog (context: Context) {
    private val dialog = Dialog(context)
    private lateinit var onClickListener: OnDialogClickListener

    fun setOnClickListener(listener: OnDialogClickListener)
    {
        onClickListener = listener
    }

    fun showDialog()
    {
        dialog.setContentView(R.layout.activity_delete_dialog)
        dialog.window!!.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT)
        dialog.setCanceledOnTouchOutside(true)
        dialog.setCancelable(true)
        dialog.show()

        dialog.btn_negative.text = "취소"
        dialog.btn_positive.text = "확인"

        dialog.btn_negative.setOnClickListener {
            dialog.dismiss()
        }

        dialog.btn_positive.setOnClickListener {
            onClickListener.onClicked()
            dialog.dismiss()
        }

    }

    interface OnDialogClickListener
    {
        fun onClicked()
    }

}
