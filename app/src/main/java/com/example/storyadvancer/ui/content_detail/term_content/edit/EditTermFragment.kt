package com.example.storyadvancer.ui.content_detail.term_content.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.storyadvancer.databinding.FragmentEditTermBinding
import com.example.storyadvancer.ui.content_detail.term_content.TermContentViewModel

class EditTermFragment : Fragment() {

    private val viewModel: TermContentViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentEditTermBinding.inflate(inflater, container, false)
        with(binding) {
            vm = viewModel
            lifecycleOwner = this@EditTermFragment
        }
        return binding.root
    }

}