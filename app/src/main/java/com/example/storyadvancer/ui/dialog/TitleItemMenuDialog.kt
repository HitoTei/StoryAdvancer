package com.example.storyadvancer.ui.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class TitleItemMenuDialog(
    private val delete: () -> Unit,
    private val edit: () -> Unit
) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        return requireActivity().let {
            val builder = AlertDialog.Builder(it)

            builder
                .setTitle("更新")
                .setItems(arrayOf("削除", "編集")) { _, index ->
                    when (index) {
                        0 -> delete()
                        1 -> edit()
                    }
                }
                .setNegativeButton("Cancel") { _, _ ->
                    dismiss()
                }
            builder.create()
        }
    }
}