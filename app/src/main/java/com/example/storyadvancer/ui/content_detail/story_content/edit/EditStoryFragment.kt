package com.example.storyadvancer.ui.content_detail.story_content.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.storyadvancer.databinding.FragmentEditStoryBinding
import com.example.storyadvancer.ui.content_detail.story_content.StoryContentViewModel

class EditStoryFragment : Fragment() {

    private val viewModel: StoryContentViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentEditStoryBinding.inflate(inflater, container, false).apply {
            vm = viewModel
            lifecycleOwner = this@EditStoryFragment
        }
        return binding.root
    }
}