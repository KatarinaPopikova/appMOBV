package sk.stu.fei.appmobv.data

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import sk.stu.fei.appmobv.model.Business
import sk.stu.fei.appmobv.model.BusinessWrapper

class BusinessDatasource : Datasource() {
    fun loadBusinessList(context: Context): MutableList<Business> {
        val listBusinessWrapperType = object : TypeToken<BusinessWrapper>() {}.type
        val businessWrapper: BusinessWrapper =
            Gson().fromJson(loadJson(context, "business/pubs.json"), listBusinessWrapperType)
        return businessWrapper.businessList
    }

}