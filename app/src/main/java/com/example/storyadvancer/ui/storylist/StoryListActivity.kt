package com.example.storyadvancer.ui.storylist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.storyadvancer.R
import com.example.storyadvancer.ui.storylist.ui.main.StoryListFragment

class StoryListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.story_list_activity)

        val worldId = intent.getLongExtra(STORY_WORLD_ID, -1)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.story_list_container, StoryListFragment.newInstance(worldId))
                .commitNow()
        }
    }

    companion object {
        const val STORY_WORLD_ID = "STORY_WORlD_ID"
    }

}