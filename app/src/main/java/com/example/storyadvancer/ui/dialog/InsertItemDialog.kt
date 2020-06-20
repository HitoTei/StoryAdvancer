package com.example.storyadvancer.ui.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.EditText
import androidx.fragment.app.DialogFragment

class InsertItemDialog(
    private val insert: (String) -> Unit
) : DialogFragment() {


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        retainInstance = true

        return requireActivity().let {
            val builder = AlertDialog.Builder(it)
            val editText = EditText(it)

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

    override fun onDestroyView() {
        val dialog = dialog
        if (dialog != null && retainInstance) {
            dialog.setDismissMessage(null)
        }
        super.onDestroyView()
    }
}