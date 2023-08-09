package com.example.products.presentation.view.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.products.R

class DialogMessage(private val onPositiveButton: DialogInterface.OnClickListener) :
    DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setMessage(getString(R.string.text_network_message))
                .setPositiveButton("Retry", onPositiveButton)
            builder.create()
        } ?: throw IllegalStateException("Activity not found")
    }

}