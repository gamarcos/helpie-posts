package gabrielmarcos.com.br.helpieblog.views.fragments

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import gabrielmarcos.com.br.helpieblog.R
import gabrielmarcos.com.br.helpieblog.models.PhotosModel
import gabrielmarcos.com.br.helpieblog.services.PhotosService
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

    override fun onPhotoClicked(photo: PhotosModel) {
        val intent = Intent(context, PhotosActivity::class.java)
        intent.putExtra(PhotosActivity.URL_IMAGE, photo.url)
        intent.putExtra(PhotosActivity.TITLE_IMAGE, photo.title)
        startActivity(intent)
    }
}