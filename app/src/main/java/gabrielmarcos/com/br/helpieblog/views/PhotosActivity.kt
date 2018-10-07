package gabrielmarcos.com.br.helpieblog.views

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import gabrielmarcos.com.br.helpieblog.R
import gabrielmarcos.com.br.helpieblog.utils.PicassoServiceHelper
import kotlinx.android.synthetic.main.activity_photo.*
import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.view.ViewAnimationUtils
import android.opengl.ETC1.getHeight
import android.opengl.ETC1.getWidth



/**
 * Created by Gabriel Marcos on 06/10/2018
 */
class PhotosActivity: AppCompatActivity() {

    companion object {
        const val URL_IMAGE = "image"
        const val TITLE_IMAGE = "title"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo)

        photoDetailToolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        val urlImage = intent.extras.getString(URL_IMAGE)
        val titleImage = intent.extras.getString(TITLE_IMAGE)

        setupView(urlImage, titleImage)
    }

    private fun setupView(url: String, title: String) {
        val picassoService = PicassoServiceHelper(this)

        picassoService.loadImage(url, photoBackground)
        photoTitle.text = title
        photoDetailToolbar.title = getString(R.string.activity_title_photos)
    }
}