package gabrielmarcos.com.br.helpieblog.utils

import gabrielmarcos.com.br.helpieblog.BuildConfig
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Gabriel Marcos on 06/10/2018
 */
class RetrofitHelper {
    companion object {
        fun getRetrofit(): Retrofit {
            return Retrofit.Builder()
                    .addCallAdapterFactory(
                            RxJava2CallAdapterFactory.create())
                    .addConverterFactory(
                            GsonConverterFactory.create())
                    .baseUrl(BuildConfig.BASE_URL)
                    .build()
        }
    }
}