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

    private var logsFragment = arrayListOf("User Fragment", "Photos Fragment")
    private var currentPage = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupView()
        navigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigationUser -> {
                    toolbar?.title = getString(R.string.fragment_title_user)
                    loadFragment(0)
                }
                R.id.navigationPhotos -> {
                    toolbar?.title = getString(R.string.fragment_title_photos)
                    loadFragment(1)
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
        loadFragment(0)
    }

    private fun loadFragment(index: Int): Boolean {
        val fragment = fragmentList[index]
        val tag = logsFragment[index]

        val fragmentTransaction = supportFragmentManager.beginTransaction()

        if (supportFragmentManager.findFragmentByTag(tag) == null) {
            fragmentTransaction.add(R.id.mainFrameLayout, fragment, tag)
        }

        fragmentTransaction.hide(fragmentList[currentPage])
        fragmentTransaction.show(fragment)
        fragmentTransaction.commit()

        currentPage = index

        return true
    }
}
