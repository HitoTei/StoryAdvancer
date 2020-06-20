package com.example.storyadvancer.ui.world_content

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.storyadvancer.R
import com.example.storyadvancer.ui.world_content.storylist.StoryListFragment
import com.example.storyadvancer.ui.world_content.storylist.StoryListViewModel

class WorldContentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.story_list_activity)

        val worldId = intent.getLongExtra(WORLD_ID, -1)
        val worldTitle = intent.getStringExtra(WORLD_TITLE) ?: ""

        viewModels<StoryListViewModel>().value.worldId = worldId

        supportActionBar?.title = worldTitle
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        supportActionBar?.setHomeButtonEnabled(true);

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(
                    R.id.story_list_container,
                    StoryListFragment()
                )
                .commitNow()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            // 戻るボタンが押されたら戻る
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val WORLD_ID = "WORlD_ID"
        const val WORLD_TITLE = "WORLD_TITLE"
    }

}