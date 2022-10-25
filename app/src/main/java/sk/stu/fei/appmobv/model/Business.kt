package sk.stu.fei.appmobv.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Business(
    @SerializedName("id") @Expose val id: Long,
    @SerializedName("lat") @Expose val lat: String? = null,
    @SerializedName("lon") @Expose val long: String? = null,
    @SerializedName("tags") @Expose val tags: Tags
) {
    data class Tags(
        @SerializedName("operator") @Expose var ownerName: String? = "",
        @SerializedName("name") @Expose val businessTitle: String? = "{Bez mena}",
        @SerializedName("phone") @Expose val phoneNumber: String? = "-",
        @SerializedName("website") @Expose val webUrl: String? = "http://www.google.com"
    )
}