package spn.getlistkotlin.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import spn.getlistkotlin.data.getListData

interface api {

    @GET("getMyList")
    fun getList() : Call<getListData>

}