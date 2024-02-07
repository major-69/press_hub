package dev.major.pressHub.ui.Activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import dev.major.pressHub.R
import dev.major.pressHub.adapter.NewsAdapter
import dev.major.pressHub.databinding.ActivityMainBinding
import dev.major.pressHub.db.ArticleDatabase
import dev.major.pressHub.repository.NewsRepository
import dev.major.pressHub.ui.NewsViewModal
import dev.major.pressHub.ui.NewsViewModalProviderFactory

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    lateinit var viewModal: NewsViewModal


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = NewsRepository(ArticleDatabase(this))

        val viewModalProviderFactory  = NewsViewModalProviderFactory(repository)
        viewModal = ViewModelProvider(this,viewModalProviderFactory).get(NewsViewModal::class.java)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.my_nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomNavigationView.setupWithNavController(navController)


    }



}