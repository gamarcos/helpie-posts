package gabrielmarcos.com.br.helpieblog.views.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import gabrielmarcos.com.br.helpieblog.R
import gabrielmarcos.com.br.helpieblog.models.PhotosModel
import gabrielmarcos.com.br.helpieblog.utils.PicassoServiceHelper
import kotlinx.android.synthetic.main.adapter_photos.view.*

/**
 * Created by Gabriel Marcos on 06/10/2018
 */
class PhotosAdapter(val context: Context,
                    val photos: ArrayList<PhotosModel>,
                    val listener: PhotosAdapterListener): RecyclerView.Adapter<PhotosAdapter.ViewHolder>() {

    interface PhotosAdapterListener {
        fun onPhotoClicked(photo: PhotosModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.adapter_photos, parent, false)
        return PhotosAdapter.ViewHolder(view, listener, context)
    }

    override fun getItemCount(): Int {
        return photos.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        return holder.bindView(photos[position])
    }

    class ViewHolder(itemView: View, val listener: PhotosAdapterListener, val context: Context) : RecyclerView.ViewHolder(itemView) {
        fun bindView(photo: PhotosModel) {
            val picassoService = PicassoServiceHelper(context)

            picassoService.loadImage(photo.thumbnailUrl, itemView.photoCard)

            itemView.photoContent.setOnClickListener {
                listener.onPhotoClicked(photo)
            }
        }
    }
}