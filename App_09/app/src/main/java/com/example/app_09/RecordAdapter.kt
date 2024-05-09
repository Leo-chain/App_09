// RecordAdapter.kt
package com.example.app_09

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.app_09.Bprecords
import com.example.app_09.bprecord
import com.example.app_09.databinding.ActivityMainBinding
import com.example.app_09.databinding.ItemRecordBinding

class RecordAdapter(
    private val context: Context,
    private var data: Bprecords
) : RecyclerView.Adapter<RecordAdapter.RecordViewHolder>() {

    class RecordViewHolder(val binding: ItemRecordBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecordViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding = ItemRecordBinding.inflate(inflater, parent, false)
        return RecordViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecordViewHolder, position: Int) {
        val record = data.bprecords[position]
        with(holder.binding) {
            txtDate.text = record.datetime
            txtResult.text = "SYS: ${record.sys}, DIA: ${record.dia}, HR: ${record.hr}"

        }
    }
    override fun getItemCount(): Int {
        return data.bprecords.size
    }

    fun updateData(newData: Bprecords) {
        data = newData
        notifyDataSetChanged()
    }

    fun addRecord(newRecord: bprecord) {
        val mutableList = data.bprecords.toMutableList()
        mutableList.add(newRecord)
        data = Bprecords(mutableList)
        notifyDataSetChanged()
    }
}
