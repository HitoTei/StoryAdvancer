package com.example.storyadvancer.ui.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class TitleItemMenuDialog(
    private val delete: () -> Unit,
    private val edit: () -> Unit,
    private val editTag: () -> Unit
) : DialogFragment() {
    private var rolled = false

    constructor() : this({}, {}, {}) {
        rolled = true
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        if (rolled) dismiss()

        return requireActivity().let {
            val builder = AlertDialog.Builder(it)

            builder
                .setTitle("更新")
                .setItems(arrayOf("削除", "編集", "タグの編集")) { _, index ->
                    when (index) {
                        0 -> delete()
                        1 -> edit()
                        2 -> editTag()
                    }
                }
                .setNegativeButton("Cancel") { _, _ ->
                    dismiss()
                }
            builder.create()
        }
    }
}