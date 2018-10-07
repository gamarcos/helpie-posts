package gabrielmarcos.com.br.helpieblog.views.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import gabrielmarcos.com.br.helpieblog.R
import gabrielmarcos.com.br.helpieblog.models.PostsModel
import kotlinx.android.synthetic.main.adapter_posts.view.*

/**
 * Created by Gabriel Marcos on 06/10/2018
 */
class PostsAdapter(val context: Context,
                   val posts: ArrayList<PostsModel>): RecyclerView.Adapter<PostsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.adapter_posts, parent, false)
        return PostsAdapter.ViewHolder(view, context)
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        return holder.bindView(posts[position])
    }

    class ViewHolder(itemView: View, val context: Context) : RecyclerView.ViewHolder(itemView) {
        fun bindView(post: PostsModel) {
            itemView.postTitle.text = post.title
            itemView.postBody.text = post.body
        }
    }
}