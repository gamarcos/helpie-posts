package gabrielmarcos.com.br.helpieblog.utils

import android.content.Context
import android.widget.ImageView
import com.squareup.picasso.Picasso

/**
 * Created by Gabriel Marcos on 06/10/2018
 */
class PicassoServiceHelper(private val context: Context?) {
    fun loadImage(url: String?, view: ImageView) {
        Picasso.with(context)
                .load(url)
                .fit()
                .centerCrop()
                .into(view)
    }
}