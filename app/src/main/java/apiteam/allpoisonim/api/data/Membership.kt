package apiteam.allpoisonim.api.data

import com.google.gson.annotations.SerializedName

object Membership {
    data class Sign(
            val statusCode : Int,
            val message : String?,
            @SerializedName("data")
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

