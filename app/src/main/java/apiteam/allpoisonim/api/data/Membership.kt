package apiteam.allpoisonim.api.data

object Membership {
    data class Sign(
            val statusCode : Int,
            val message : String?,
            val data: User,
            val totalCount: Int
    )

    data class User(
            val userId: Int,
            val nickname: String,
            val email: String,
            val createdAt: String,
            val updatedAt: String
    )
}

