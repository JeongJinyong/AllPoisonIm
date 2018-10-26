package apiteam.allpoisonim.api

import apiteam.allpoisonim.api.data.Book
import io.reactivex.Observable
import retrofit2.http.FieldMap
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

class BookService {
    interface BookApi {
        @GET("book/getAll")
        fun getAllBooks(@Header("Authorization") token: String): Observable<List<Book.Data>>

        @POST("bookLike/save")
        fun likes(@Header("Authorization") token: String,
                  @FieldMap map: Map<String, Int>): Observable<Book.LikeResponse>

    }

    companion object {
        fun getAllBooks(token: String): Observable<List<Book.Data>> {
            return ApiFactory.create(BookApi::class.java).getAllBooks(token)
        }

        fun likes(token: String, data: Map<String, Int>): Observable<Book.LikeResponse> {
            return ApiFactory.create(BookApi::class.java).likes(token, data)
        }
    }
}