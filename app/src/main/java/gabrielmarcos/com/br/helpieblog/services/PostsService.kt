package gabrielmarcos.com.br.helpieblog.services

import android.content.Context
import gabrielmarcos.com.br.helpieblog.models.PostsModel
import gabrielmarcos.com.br.helpieblog.services.interfaces.UsersServiceInterface
import gabrielmarcos.com.br.helpieblog.utils.RetrofitHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Gabriel Marcos on 06/10/2018
 */
class PostsService(context: Context): BaseService(context) {
    private val photosService by lazy {
        RetrofitHelper.getRetrofit().create(UsersServiceInterface::class.java)
    }

    fun subscrible(id: String,
                   successCallback: (response: ArrayList<PostsModel>) -> Unit,
                   errorCallback: () -> Unit) {
        initObservable {
            photosService.getPostsByUser(id)
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