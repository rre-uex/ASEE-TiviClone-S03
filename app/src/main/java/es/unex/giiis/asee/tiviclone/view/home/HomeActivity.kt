package es.unex.giiis.asee.tiviclone.view.home

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment

import es.unex.giiis.asee.tiviclone.R
import es.unex.giiis.asee.tiviclone.databinding.ActivityHomeBinding
import es.unex.giiis.asee.tiviclone.model.User

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    private lateinit var discoverFragment: DiscoverFragment
    private lateinit var libraryFragment: LibraryFragment
    private lateinit var userFragment: UserFragment

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
        discoverFragment = DiscoverFragment()
        libraryFragment = LibraryFragment()
        userFragment = UserFragment()

        setCurrentFragment(discoverFragment)
    }

    fun setUpListeners() {
        //nothing to do
        with(binding){
            bottomNavigation.setOnItemSelectedListener {
                when(it.itemId){
                    R.id.action_discover -> setCurrentFragment(discoverFragment)
                    R.id.action_library -> setCurrentFragment(libraryFragment)
                    R.id.action_user -> setCurrentFragment(userFragment)
                    else -> setCurrentFragment(discoverFragment)
                }
                true
            }
        }
    }

    private fun setCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, fragment)
            commit()
        }

}