package gabrielmarcos.com.br.helpieblog.services.interfaces

import gabrielmarcos.com.br.helpieblog.BuildConfig
import gabrielmarcos.com.br.helpieblog.models.PostsModel
import gabrielmarcos.com.br.helpieblog.models.UsersModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Gabriel Marcos on 06/10/2018
 */
interface UsersServiceInterface {
    @GET(BuildConfig.USER_URL)
    fun getUsers(): Observable<ArrayList<UsersModel>>

    @GET(BuildConfig.USER_POSTS)
    fun getPostsByUser(@Query("userId") userId: String): Observable<ArrayList<PostsModel>>
}