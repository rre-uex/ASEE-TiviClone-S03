package es.unex.giiis.asee.tiviclone.view.home

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController

import es.unex.giiis.asee.tiviclone.R
import es.unex.giiis.asee.tiviclone.databinding.ActivityHomeBinding
import es.unex.giiis.asee.tiviclone.model.User

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    companion object {
        const val USER_INFO = "USER_INFO"
        fun start(
            context: Context,
            user: User,
        ) {
            val intent = Intent(context, HomeActivity::class.java).apply {
                putExtra(USER_INFO, user)
            }
            context.startActivity(intent)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val user = intent.getSerializableExtra(USER_INFO) as User

        setUpUI(user)
        setUpListeners()
    }

    fun setUpUI(user: User) {
        with(binding) {
            val navHostFragment =
                supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
            binding.bottomNavigation.setupWithNavController(navHostFragment.navController)
        }
    }

    fun setUpListeners() {
        //nothing to do
    }

}