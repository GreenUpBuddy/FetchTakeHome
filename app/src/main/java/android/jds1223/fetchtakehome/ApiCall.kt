package android.jds1223.fetchtakehome
import android.content.Context
import android.provider.ContactsContract.Data
import android.widget.Toast
import com.google.gson.Gson

import retrofit.*

class ApiCall {
    // Process the data and group by listId and sort by name/id
    fun processData(data: List<DataModel>?): List<DataModel> {
        if (data == null) return emptyList()

        val buckets = HashMap<Int, ArrayList<DataModel>>()
        for (item in data) {
            if (!item.name.isNullOrEmpty()) {
                buckets.getOrPut(item.listId, ::ArrayList).add(item)
            }
        }

        val result = mutableListOf<DataModel>()
        for (list in buckets.values) {
            list.sortBy { it.id.toInt() }
            result.addAll(list)
        }

        return result
    }
    fun getData(context: Context, callback: (List<DataModel>) -> Unit) {

        // Create a Retrofit instance with the base URL and
        // a GsonConverterFactory for parsing the response.
        val retrofit: Retrofit = Retrofit.Builder().baseUrl("https://fetch-hiring.s3.amazonaws.com").addConverterFactory(
            GsonConverterFactory.create()).build()

        val service: ApiService = retrofit.create<ApiService>(ApiService::class.java)
        val call = service.getData()

        // make an asynchronous API request.
        call.enqueue(object : Callback<List<DataModel>> {
            override fun onResponse(response: Response<List<DataModel>>?, retrofit: Retrofit?) {
                if (response != null) {
                    if (response.isSuccess) {
                        val data = response.body()
                        val processedData = processData(data)
                        callback(processedData)
                    } else {
                        // Handle API error
                        Toast.makeText(context, "API Error", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            override fun onFailure(t: Throwable?) {
                Toast.makeText(context, "Request Fail", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
