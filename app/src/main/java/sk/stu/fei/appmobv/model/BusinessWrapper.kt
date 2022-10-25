package sk.stu.fei.appmobv.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class BusinessWrapper(
    @SerializedName("elements") @Expose val businessList: MutableList<Business>
)