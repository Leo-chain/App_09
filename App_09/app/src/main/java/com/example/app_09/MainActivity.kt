package com.example.app_09

import android.app.Activity
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.inputmethod.InputBinding
import android.widget.AdapterView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app_09.databinding.ActivityMainBinding
import com.example.app_09.RecordAdapter
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mAdapter: RecordAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbarMain.inflateMenu(R.menu.menu)
        mAdapter = RecordAdapter(this, Bprecords(emptyList()))
        binding.recordRecycler.adapter=mAdapter
        binding.recordRecycler.layoutManager = LinearLayoutManager(this)

        val records = Bprecords(mutableListOf(
            bprecord("2024-05-09 10:00", 120, 80, 72),
            bprecord("2024-05-09 14:00", 125, 82, 75),
            bprecord("2024-05-10 08:00", 118, 78, 70)
        ))

        mAdapter.updateData(records)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        return when(item.itemId){
            R.id.menu_add -> {
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == REQUEST_CODE_ADD_RECORD && resultCode == Activity.RESULT_OK){
            val sys = data?.getIntExtra("sys", 0) ?: 0
            val dia = data?.getIntExtra("dia", 0) ?: 0
            val hr = data?.getIntExtra("hr", 0) ?: 0
            val newRecord = bprecord(getCurrentDateTime(), sys, dia, hr)

            /*if(records.bprecords == null){
                records = Bprecords(mutableListOf())
            }

            val mutableList = records.bprecords.toMutableList()
            mutableList.add(newRecord)
            records = Bprecords(mutableList)
            val userJson = gson.toJson(records)

            with(sharedPreferenced.edit()){
                puString("user_data", userJson)
                apply()
            }*/

            mAdapter.addRecord(newRecord)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getCurrentDateTime(): String{
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        return current.format(formatter)
    }

    companion object {
        const val REQUEST_CODE_ADD_RECORD = 1
    }
}