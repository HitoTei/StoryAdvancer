package com.example.storyadvancer.ui.world_content

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.storyadvancer.R
import com.example.storyadvancer.ui.world_content.storylist.StoryListFragment
import com.example.storyadvancer.ui.world_content.storylist.StoryListViewModel
import com.example.storyadvancer.ui.world_content.termlist.TermListFragment
import com.example.storyadvancer.ui.world_content.termlist.TermListViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class WorldContentActivity : AppCompatActivity() {

    private val storyListViewModel: StoryListViewModel by viewModels()
    private val termListViewModel: TermListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.world_content_activity)

        val worldId = intent.getLongExtra(WORLD_ID, -1)
        val worldTitle = intent.getStringExtra(WORLD_TITLE) ?: ""
        storyListViewModel.worldId = worldId
        termListViewModel.worldId = worldId

        supportActionBar?.title = worldTitle
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        supportActionBar?.setHomeButtonEnabled(true);

        findViewById<BottomNavigationView>(R.id.world_content_bottom_navigation).setOnNavigationItemSelectedListener {
            return@setOnNavigationItemSelectedListener when (it.itemId) {
                R.id.story_item -> setStoryFragment()
                R.id.term_item -> setTermFragment()
                else -> false
            }
        }

        if (savedInstanceState == null) {
            setStoryFragment()
        }
    }

    private fun setStoryFragment(): Boolean {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.contant_list_container,
                StoryListFragment()
            )
            .commitNow()
        findViewById<FloatingActionButton>(R.id.contentActivityFloatingActionButton).setOnClickListener {
            storyListViewModel
                .insertItemDialog()
                .show(supportFragmentManager, "storyInsertItemDialog")
        }
        return true
    }

    private fun setTermFragment(): Boolean {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.contant_list_container,
                TermListFragment()
            )
            .commitNow()
        findViewById<FloatingActionButton>(R.id.contentActivityFloatingActionButton).setOnClickListener {
            termListViewModel
                .insertItemDialog()
                .show(supportFragmentManager, "storyInsertItemDialog")
        }
        return true
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