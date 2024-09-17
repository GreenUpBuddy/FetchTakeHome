package android.jds1223.fetchtakehome

import retrofit.Call
import retrofit.http.GET


interface ApiService {
    @GET("hiring.json")
    fun getData(): Call<List<DataModel>>

}