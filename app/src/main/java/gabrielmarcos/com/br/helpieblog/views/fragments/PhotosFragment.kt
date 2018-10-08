package gabrielmarcos.com.br.helpieblog.views.fragments

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import gabrielmarcos.com.br.helpieblog.R
import gabrielmarcos.com.br.helpieblog.models.PhotosModel
import gabrielmarcos.com.br.helpieblog.services.PhotosService
import gabrielmarcos.com.br.helpieblog.utils.PicassoServiceHelper
import gabrielmarcos.com.br.helpieblog.views.PhotosActivity
import gabrielmarcos.com.br.helpieblog.views.adapters.PhotosAdapter
import kotlinx.android.synthetic.main.fragment_photos.*
import kotlinx.android.synthetic.main.layout_loading_view.*

/**
 * Created by Gabriel Marcos on 06/10/2018
 */
class PhotosFragment: Fragment(), PhotosAdapter.PhotosAdapterListener {

    private var photosService: PhotosService? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_photos, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        photosService = PhotosService(context!!)
        progressBar.visibility = View.VISIBLE
        requestPhotos()
    }

    override fun onResume() {
        super.onResume()
        requestPhotos()
    }

    override fun onPause() {
        super.onPause()
        photosService?.unsubscribe()
    }

    private fun requestPhotos() {
        photosService?.subscrible({
            setupAdapter(it)
            progressBar.visibility = View.GONE
        },{})
    }

    private fun setupAdapter(photos: ArrayList<PhotosModel>) {
        photosRecyclerView.adapter = PhotosAdapter(context!!, photos, this)
        val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        photosRecyclerView.layoutManager = layoutManager
    }

    override fun onPhotoClicked(photo: PhotosModel, pressed: Boolean) {
        val picassoService = PicassoServiceHelper(context)
        picassoService.loadImage(photo.url, imageModal)

        if (pressed) {
            imageCard.visibility = View.VISIBLE
            val scaleIn = AnimationUtils.loadAnimation(context, R.anim.scale_fade_in)
            imageCard.startAnimation(scaleIn)
        } else if (!pressed && imageCard.visibility == View.VISIBLE) {
            val scaleOut = AnimationUtils.loadAnimation(context, R.anim.scale_fade_out)
            imageCard.startAnimation(scaleOut)
            imageCard.visibility = View.GONE
        }
    }
}