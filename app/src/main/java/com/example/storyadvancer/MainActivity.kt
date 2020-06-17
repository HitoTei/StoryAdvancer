package com.example.storyadvancer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.storyadvancer.repository.Repository
import com.example.storyadvancer.ui.worldlist.WorldsFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        Repository.initialize(applicationContext)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_container, WorldsFragment.newInstance())
                .commitNow()
        }
    }
}