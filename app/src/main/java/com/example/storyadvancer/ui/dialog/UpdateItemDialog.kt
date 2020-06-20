package com.example.storyadvancer.ui.dialog


import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.EditText
import androidx.fragment.app.DialogFragment

class UpdateItemDialog(
    private val initialValue: String,
    private val update: (String) -> Unit
) : DialogFragment() {
    constructor() : this("", {})

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        return requireActivity().let {
            val builder = AlertDialog.Builder(it)
            val editText = EditText(it)
            editText.setText(initialValue)

            builder
                .setTitle("更新")
                .setView(editText)
                .setPositiveButton("Ok") { _, _ ->
                    update(editText.text.toString())
                }
                .setNegativeButton("Cancel") { _, _ ->
                    dismiss()
                }
            builder.create()
        }
    }
}