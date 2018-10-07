package gabrielmarcos.com.br.helpieblog.views

import android.os.Bundle
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import gabrielmarcos.com.br.helpieblog.R
import gabrielmarcos.com.br.helpieblog.models.PostsModel
import gabrielmarcos.com.br.helpieblog.services.PostsService
import gabrielmarcos.com.br.helpieblog.views.adapters.PostsAdapter
import kotlinx.android.synthetic.main.activity_posts.*
import kotlinx.android.synthetic.main.layout_loading_view.*

/**
 * Created by Gabriel Marcos on 06/10/2018
 */
class PostsActivity: AppCompatActivity() {

    private var toolbar: ActionBar? = null

    companion object {
        const val USER_ID = "id"
    }

    private var postsService: PostsService? = null
    private var userId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_posts)

        postsService = PostsService(this)
        userId = intent.extras.getString(USER_ID)

        setupView()
        requestPosts(userId)
    }

    override fun onRestart() {
        super.onRestart()
        requestPosts(userId)
    }

    override fun onPause() {
        super.onPause()
        postsService?.unsubscribe()
    }

    override fun onDestroy() {
        super.onDestroy()
        postsService?.unsubscribe()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        finish()
        return true
    }

    private fun setupView() {
        toolbar = supportActionBar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        toolbar?.title = getString(R.string.activity_title_posts)
        progressBar.visibility = View.VISIBLE
    }

    private fun requestPosts(id: String?) {
        postsService?.unsubscribe()
        postsService?.subscrible(id!!,{
            setupAdapter(it)
            progressBar.visibility = View.GONE
        },{})
    }

    private fun setupAdapter(posts: ArrayList<PostsModel>) {
        postsRecyclerView.adapter = PostsAdapter(this,  posts)
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        postsRecyclerView.layoutManager = layoutManager
    }
}