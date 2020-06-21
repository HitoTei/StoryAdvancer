package com.example.storyadvancer.ui.content_detail.term_content

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.storyadvancer.R
import com.example.storyadvancer.ui.content_detail.term_content.edit.EditTermFragment
import com.example.storyadvancer.ui.content_detail.term_content.show.ShowTermFragment

class TermContentActivity : AppCompatActivity() {

    private val viewModel: TermContentViewModel by viewModels()
    private var isShowTermFragment = true
    private var statusItem: MenuItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.term_content_activity)

        viewModel.termId = intent.getLongExtra(TERM_ID, -1)
        viewModel.termTitle = intent.getStringExtra(TERM_TITLE) ?: ""

        title = viewModel.termTitle
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        setFragment()
    }

    private fun setFragment() {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.container,
                if (isShowTermFragment)
                    ShowTermFragment()
                else
                    EditTermFragment()
            )
            .commitNow()

        statusItem?.setIcon(
            if (isShowTermFragment) R.drawable.ic_baseline_edit_24
            else R.drawable.ic_baseline_book_24
        )

        isShowTermFragment = !isShowTermFragment
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
        const val TERM_ID = "TERM_ID"
        const val TERM_TITLE = "TERM_TITLE"
    }

}