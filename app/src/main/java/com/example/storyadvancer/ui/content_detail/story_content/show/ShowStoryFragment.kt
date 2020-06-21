package com.example.storyadvancer.ui.content_detail.story_content.show

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.storyadvancer.databinding.ShowStoryFragmentBinding
import com.example.storyadvancer.ui.content_detail.story_content.StoryContentViewModel

class ShowStoryFragment : Fragment() {

    companion object {
        fun newInstance() =
            ShowStoryFragment()
    }

    private val viewModel: StoryContentViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = ShowStoryFragmentBinding.inflate(inflater, container, false).apply {
            vm = viewModel
            lifecycleOwner = this@ShowStoryFragment
        }
        return binding.root
    }
}