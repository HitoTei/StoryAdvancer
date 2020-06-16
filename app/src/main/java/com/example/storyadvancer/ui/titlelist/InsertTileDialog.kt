package com.example.storyadvancer.ui.titlelist

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.EditText
import androidx.fragment.app.DialogFragment

class InsertTileDialog(
    private val insert: (String) -> Unit
) : DialogFragment() {
    constructor() : this({})

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        return requireActivity().let {
            val builder = AlertDialog.Builder(it)
            val textEditor = EditText(it)

            builder
                .setTitle("新しく追加")
                .setView(textEditor)
                .setPositiveButton("Ok") { _, _ ->
                    insert(textEditor.text.toString())
                }
                .setNegativeButton("Cancel") { _, _ ->
                    dismiss()
                }
            builder.create()
        }
    }
}