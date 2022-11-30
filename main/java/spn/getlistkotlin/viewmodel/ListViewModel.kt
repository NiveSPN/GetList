package spn.getlistkotlin.viewmodel

import android.util.Log
import androidx.lifecycle.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import spn.getlistkotlin.adapter.ListAdapter
import spn.getlistkotlin.data.Heirarchy
import spn.getlistkotlin.data.getListData
import spn.getlistkotlin.retrofit.RetrofitInstance

class ListViewModel : ViewModel() {

    private var listLiveData = MutableLiveData<List<Heirarchy>>()

    fun getList() {

        RetrofitInstance.api.getList().enqueue(object : Callback<getListData> {
            override fun onResponse(call: Call<getListData>, response: Response<getListData>) {
                if (response.body() != null) {
                    listLiveData.value = response.body()!!.dataObject[0].heirarchyList

                } else
                    return
            }

            override fun onFailure(call: Call<getListData>, t: Throwable) {
                Log.d("TAG", t.message.toString())
            }
        })
    }


    fun filters(text: String): MutableLiveData<List<Heirarchy>?> {

        val allList = listLiveData

        var livedata = MutableLiveData<List<Heirarchy>?>()

        livedata.value = allList.value?.filterByName(text)

        return livedata
    }

    private fun List<Heirarchy>.filterByName(name: String) =
        this.filter { it.contactName.startsWith(name, true)
        }?.run { ifEmpty { this@filterByName } }.orEmpty()

    fun observelistLiveData(): LiveData<List<Heirarchy>> {
        return listLiveData
    }
}