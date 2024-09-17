package android.jds1223.fetchtakehome

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DataAdapter(private val dataList: List<DataModel>) :
    RecyclerView.Adapter<DataAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemIdTextView: TextView = itemView.findViewById(R.id.itemIdTextView)
        val itemNameTextView: TextView = itemView.findViewById(R.id.itemNameTextView)
        val listIdTextView: TextView = itemView.findViewById(R.id.listIdTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_data, parent, false)
        return DataViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val currentItem = dataList[position]
        holder.itemIdTextView.text = "ID: ${currentItem.id}"
        holder.itemNameTextView.text = "Name: ${currentItem.name}"
        holder.listIdTextView.text = "List ID: ${currentItem.listId}"
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}