package com.example.storyadvancer.ui.titlelist

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.EditText
import androidx.fragment.app.DialogFragment

class InsertTileDialog(
    private val insert: (String) -> Unit,
    private var value: String = ""
) : DialogFragment() {
    constructor() : this({})

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        return requireActivity().let {
            val builder = AlertDialog.Builder(it)
            val editText = EditText(it)
            editText.setText(value)

            builder
                .setTitle("新しく追加")
                .setView(editText)
                .setPositiveButton("Ok") { _, _ ->
                    insert(editText.text.toString())
                }
                .setNegativeButton("Cancel") { _, _ ->
                    dismiss()
                }
            builder.create()
        }
    }
}