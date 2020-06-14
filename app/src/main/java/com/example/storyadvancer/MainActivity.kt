package com.example.storyadvancer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.storyadvancer.repository.Repository
import com.example.storyadvancer.ui.main.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        Repository.initialize(applicationContext)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.main_container, MainFragment.newInstance())
                    .commitNow()
        }
    }
}