package apiteam.allpoisonim.api

import apiteam.allpoisonim.api.data.BookStore
import io.reactivex.Observable
import retrofit2.http.Header
import retrofit2.http.POST

class BookStoreService {

    interface BookStoreApi {
        @POST("book-store")
        fun getStoreList(@Header("Authorization") token: String): Observable<List<BookStore.Data>>
    }

    companion object {
        fun getStoreList(token: String): Observable<List<BookStore.Data>> {
            return ApiFactory.create(BookStoreApi::class.java).getStoreList(token)
        }
    }
}