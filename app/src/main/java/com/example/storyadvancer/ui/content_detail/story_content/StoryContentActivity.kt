package com.example.storyadvancer.ui.content_detail.story_content

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.storyadvancer.R
import com.example.storyadvancer.ui.content_detail.story_content.edit.EditStoryFragment
import com.example.storyadvancer.ui.content_detail.story_content.show.ShowStoryFragment

class StoryContentActivity : AppCompatActivity() {

    private val viewModel: StoryContentViewModel by viewModels()
    private var isShowStoryFragment = true
    private var statusItem: MenuItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.story_content_activity)

        viewModel.storyId = intent.getLongExtra(STORY_ID, -1)
        viewModel.storyTitle = intent.getStringExtra(STORY_TITLE) ?: ""

        title = viewModel.storyTitle
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        setFragment()
    }

    private fun setFragment() {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.container,
                if (isShowStoryFragment)
                    ShowStoryFragment()
                else
                    EditStoryFragment()
            )
            .commitNow()

        statusItem?.setIcon(
            if (isShowStoryFragment) R.drawable.ic_baseline_edit_24
            else R.drawable.ic_baseline_book_24
        )

        isShowStoryFragment = !isShowStoryFragment
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.content_menu, menu)
        statusItem = menu?.findItem(R.id.content_change)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.content_save -> {
                viewModel.save()
                Toast.makeText(this, "保存しました", Toast.LENGTH_SHORT).show()
            }
            R.id.content_change -> setFragment()
            android.R.id.home -> finish()
        }
        return true
    }

    companion object {
        const val STORY_ID = "STORY_ID"
        const val STORY_TITLE = "STORY_TITLE"
    }

}