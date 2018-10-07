package gabrielmarcos.com.br.helpieblog.services.interfaces

import gabrielmarcos.com.br.helpieblog.BuildConfig
import gabrielmarcos.com.br.helpieblog.models.PhotosModel
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * Created by Gabriel Marcos on 06/10/2018
 */
interface PhothosServiceInterface {
    @GET(BuildConfig.USER_PHOTOS)
    fun getPhotos(): Observable<ArrayList<PhotosModel>>
}