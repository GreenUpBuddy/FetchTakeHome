package android.jds1223.fetchtakehome

import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dataRecyclerView: RecyclerView = findViewById(R.id.dataRecyclerView)
        dataRecyclerView.layoutManager = LinearLayoutManager(this)

        ApiCall().getData(this) { data ->
            val adapter = DataAdapter(data)
            dataRecyclerView.adapter = adapter
        }
    }
}