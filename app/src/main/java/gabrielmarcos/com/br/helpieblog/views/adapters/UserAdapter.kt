package gabrielmarcos.com.br.helpieblog.views.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import gabrielmarcos.com.br.helpieblog.R
import gabrielmarcos.com.br.helpieblog.models.UsersModel
import kotlinx.android.synthetic.main.adapter_user.view.*

/**
 * Created by Gabriel Marcos on 06/10/2018
 */
class UserAdapter(val context: Context,
                  val users: ArrayList<UsersModel>,
                  val listener: UserAdapterListener): RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    interface UserAdapterListener {
        fun onUsersClicked(id: String)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.adapter_user, parent, false)
        return UserAdapter.ViewHolder(view, listener, context)
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        return holder.bindView(users[position])
    }

    class ViewHolder(itemView: View, val listener: UserAdapterListener, val context: Context): RecyclerView.ViewHolder(itemView){
        fun bindView(user: UsersModel) {
            itemView.userId.text = user.id.toString()
            itemView.userName.text = user.name
            itemView.userCity.text = user.address.city
            itemView.userEmail.text = user.email
            itemView.userCompanyName.text = user.company.name

            itemView.userAdapterContent.setOnClickListener {
                listener.onUsersClicked(user.id.toString())
            }

        }
    }
}