package gabrielmarcos.com.br.helpieblog.views.adapters

import android.content.Context
import android.os.Handler
import android.support.v7.widget.RecyclerView
import android.view.*
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
        fun onPhotoClicked(photo: PhotosModel, pressed: Boolean)
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

            val handler = Handler()
            val mLongPressed = Runnable {
                listener.onPhotoClicked(photo, true)
            }

            itemView.photoContent.setOnTouchListener { view, motionEvent ->
                if (motionEvent?.action == MotionEvent.ACTION_DOWN) {
                    handler.postDelayed(mLongPressed, 500)
                }
                if ((motionEvent?.action == MotionEvent.ACTION_MOVE)||(motionEvent?.action == MotionEvent.ACTION_UP)) {
                    handler.removeCallbacks(mLongPressed)
                    listener.onPhotoClicked(photo, false)
                }
                true
            }
        }
    }
}