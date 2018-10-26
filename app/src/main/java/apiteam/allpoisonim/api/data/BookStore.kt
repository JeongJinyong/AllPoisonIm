package apiteam.allpoisonim.api.data

object BookStore {
    data class Data(
        val id: Int,
        val storeName: String,
        val storeTheme: String,
        val storeTitle: String,
        val storeMainImage: String,
        val openTime : String,
        val weekendOpenTime : String,
        val storeLocation: String,
        val storePhoneNum: String,
        val storeSns: String,
        val bookStoreImages: List<BookStoreImages>,
        val user: Membership.User,
        val createdAt: String,
        val updatedAt: String,
        val show: Boolean
    )

    data class BookStoreImages(
        val id: Int,
        val book_store_id: String,
        val contents: String,
        val image_url: String
    )
}