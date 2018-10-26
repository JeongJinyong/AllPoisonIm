package apiteam.allpoisonim.api.data

object Book {
    data class Data(
            val id: Int,
            val createdAt: String,
            val updatedAt: String,
            val contents: String,
            val user: Membership.User,
            val category: Category,
            val book: Book
    )

    data class Category(
            val id: Int,
            val categoryName: String
    )

    data class Book(
            val like: Boolean,
            val id: Int,
            val bookName: String,
            val bookBrief: String,
            val bookAuthor: String,
            val likeCount: Int,
            val replyCount: Int,
            val bookImgUri: String,
            val bookPublisher: String,
            val isLike: Boolean,
            val publishAt: String,
            val createdAt: String,
            val updatedAt: String
    )

    data class LikeResponse(
            val statusCode: Int,
            val message: String,
            val data: String?,
            val totalCount: Int
    )
}