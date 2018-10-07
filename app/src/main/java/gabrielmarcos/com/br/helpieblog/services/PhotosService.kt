package gabrielmarcos.com.br.helpieblog.services

import android.content.Context
import gabrielmarcos.com.br.helpieblog.models.PhotosModel
import gabrielmarcos.com.br.helpieblog.services.interfaces.PhothosServiceInterface
import gabrielmarcos.com.br.helpieblog.utils.RetrofitHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Gabriel Marcos on 06/10/2018
 */
class PhotosService(context: Context): BaseService(context){
    private val photosService by lazy {
        RetrofitHelper.getRetrofit().create(PhothosServiceInterface::class.java)
    }

    fun subscrible(successCallback: (response: ArrayList<PhotosModel>) -> Unit,
                   errorCallback: () -> Unit) {
        initObservable {
            photosService.getPhotos()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            { response ->
                                successCallback(response) },
                            {
                                errorCallback()
                            }
                    )
        }
    }
}