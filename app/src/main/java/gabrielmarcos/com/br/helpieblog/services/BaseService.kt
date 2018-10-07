package gabrielmarcos.com.br.helpieblog.services

import android.content.Context
import android.provider.SyncStateContract
import gabrielmarcos.com.br.helpieblog.utils.ConstantsHelper
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import java.util.concurrent.TimeUnit

/**
 * Created by Gabriel Marcos on 06/10/2018
 */
open class BaseService(val context: Context?) {

    var observable: Disposable? = null

    fun unsubscribe() {
        observable?.dispose()
    }

    protected fun initObservable(subscribeFunction:() -> Unit) {
        observable = Observable.interval(ConstantsHelper.RX_TIME_INTERVAL_IN_SECONDS, TimeUnit.SECONDS)
                .startWith(0)
                .subscribe {
                    subscribeFunction()
                }
    }
}