package spn.getlistkotlin.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    var retrofit = Retrofit.Builder()
        .baseUrl("http://demo2143341.mockable.io/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api = retrofit.create(api::class.java)
}