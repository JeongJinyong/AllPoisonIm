package apiteam.allpoisonim.api.data

object BookDetail {
    data class Book(
            val like: Boolean,
            val id: Int,
            val bookName: String,
            val bookBrief: String,
            val bookContents: String,
            val bookAuthor: String,
            val likeCount: Int,
            val replyCount: Int,
            val bookImgUri: String,
            val bookPublisher: String,
            val isLike: Boolean,
            val createdAt: String,
            val category: Category,
            val bookComments: ArrayList<BookComments>,
            val user: Membership.User
    )

    data class Category(
            val id: Int,
            val categoryName: String
    )

    data class BookComments(
            val commentId: Int,
            val comment: String,
            val userId: Int,
            val userName: String,
            val createdAt: String
    )

    data class BookLike(
            val statusCode: Int,
            val message: String,
            val data: BookLikeData
    )

    data class BookLikeData(
            val likeCount: Int,
            val like: Boolean
    )

}