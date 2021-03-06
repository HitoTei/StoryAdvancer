package com.example.storyadvancer

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.storyadvancer.repository.Repository
import com.example.storyadvancer.shared_preference.DarkMode
import com.example.storyadvancer.ui.worldlist.WorldListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        DarkMode.initTheme(this)

        Repository.initialize(applicationContext)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_container, WorldListFragment())
                .commitNow()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.night_mode, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.night_mode -> {
                DarkMode.changeTheme(this)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }


}