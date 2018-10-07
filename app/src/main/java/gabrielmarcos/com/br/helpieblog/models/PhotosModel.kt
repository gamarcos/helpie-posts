package gabrielmarcos.com.br.helpieblog.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by Gabriel Marcos on 06/10/2018
 */

@Parcelize
data class PhotosModel(val albumId: Int,
                  val id: Int,
                  val title: String,
                  val url: String,
                  val thumbnailUrl: String): Parcelable
