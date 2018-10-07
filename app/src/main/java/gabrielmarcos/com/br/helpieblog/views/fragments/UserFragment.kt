package gabrielmarcos.com.br.helpieblog.views.fragments

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import gabrielmarcos.com.br.helpieblog.R
import gabrielmarcos.com.br.helpieblog.models.UsersModel
import gabrielmarcos.com.br.helpieblog.services.UsersService
import gabrielmarcos.com.br.helpieblog.views.PostsActivity
import gabrielmarcos.com.br.helpieblog.views.adapters.UserAdapter
import kotlinx.android.synthetic.main.fragment_users.*
import kotlinx.android.synthetic.main.layout_loading_view.*

/**
 * Created by Gabriel Marcos on 06/10/2018
 */
class UserFragment: Fragment(), UserAdapter.UserAdapterListener {

    private var usersService: UsersService? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_users, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        usersService = UsersService(context!!)
        requestUsers()
    }

    override fun onResume() {
        super.onResume()
        requestUsers()
    }

    override fun onPause() {
        super.onPause()
        usersService?.unsubscribe()

    }

    private fun selector(user: UsersModel): String = user.name

    private fun orderList(desorderUser: ArrayList<UsersModel>): ArrayList<UsersModel> {
        val orderedUsers = ArrayList<UsersModel>()
        desorderUser.sortBy {selector(it)}
        desorderUser.forEach{
            orderedUsers.add(it)
        }
        return orderedUsers
    }

    private fun requestUsers() {
        usersService?.unsubscribe()
        usersService?.subscrible({
            progressBar.visibility = View.GONE
            setupAdapter(it)
        },{})
    }

    private fun setupAdapter(users: ArrayList<UsersModel>) {
        userRecyclerView.adapter = UserAdapter(context!!,  orderList(users), this)
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        userRecyclerView.layoutManager = layoutManager
    }

    override fun onUsersClicked(id: String) {
        val intent = Intent(context, PostsActivity::class.java)
        intent.putExtra(PostsActivity.USER_ID, id)
        startActivity(intent)
    }
}