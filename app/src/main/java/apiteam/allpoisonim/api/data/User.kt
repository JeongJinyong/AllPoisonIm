package apiteam.allpoisonim.api.data

object User {
    data class Sign(
            val statusCode : Int,
            val message : String?,
            val userId : Int,
            val nickname : String,
            val email : String
    )
}

