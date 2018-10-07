package gabrielmarcos.com.br.helpieblog.views

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.ActionBar
import gabrielmarcos.com.br.helpieblog.R
import gabrielmarcos.com.br.helpieblog.views.fragments.PhotosFragment
import gabrielmarcos.com.br.helpieblog.views.fragments.UserFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var toolbar: ActionBar? = null
    private var fragmentList = arrayListOf(UserFragment(), PhotosFragment())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupView()
        navigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigationUser -> {
                    toolbar?.title = getString(R.string.fragment_title_user)
                    loadFragment(fragmentList[0])
                }
                R.id.navigationPhotos -> {
                    toolbar?.title = getString(R.string.fragment_title_photos)
                    loadFragment(fragmentList[1])
                }
                else -> {
                    false
                }
            }
        }
    }

    private fun setupView() {
        toolbar = supportActionBar
        toolbar?.title = getString(R.string.fragment_title_user)
        loadFragment(fragmentList[0])
    }

    private fun loadFragment(fragment: Fragment): Boolean {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.mainFrameLayout, fragment)
        transaction.addToBackStack(null)
        transaction.commit()

        return true
    }
}
