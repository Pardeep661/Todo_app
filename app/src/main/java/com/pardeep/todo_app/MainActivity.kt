package com.pardeep.todo_app

import android.app.Dialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.pardeep.todo_app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), ActivityInterface {
    var binding : ActivityMainBinding? = null
    var item_data = arrayListOf<MyData>()
    var recyclerAdapter = MyAdapter(item_data,this)
    lateinit var linearLayoutManager: LinearLayoutManager
    //dataBase
    lateinit var dataBase : DataBase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        dataBase = DataBase.getInstance(this)

        binding?.fab?.setOnClickListener {
            Dialog(this).apply {
                setContentView(R.layout.custom_dialog)

                //variable
                val tittle = findViewById<EditText>(R.id.title)
                val description = findViewById<EditText>(R.id.description)
                val all_rb = findViewById<RadioButton>(R.id.all)
                val low_rb = findViewById<RadioButton>(R.id.low)
                val medium_rb = findViewById<RadioButton>(R.id.medium)
                val high_rb = findViewById<RadioButton>(R.id.high)
                val add_button = findViewById<Button>(R.id.Add)


                add_button.setOnClickListener {
                    if (tittle.text.trim().isNullOrEmpty()){
                        tittle.error = "Enter the tittle"
                    }else if (description.text.trim().isNullOrEmpty()){
                    description.error = "Enter the tittle"
                    }else if (all_rb.isChecked == false && low_rb.isChecked == false && medium_rb.isChecked == false && high_rb.isChecked == false){
                        all_rb.error = "select priority"
                    }else{
                        var tittle_data = tittle.text.toString()
                        var description_data = description.text.toString()
                        dataBase.datadao().insertData(MyData(title = tittle_data, description = description_data, priority ="low"))
                        getData()
                    }
                }

            }.show()
        }


        linearLayoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        binding?.rv?.layoutManager = linearLayoutManager
        binding?.rv?.adapter = recyclerAdapter


    }

    private fun getData() {
        item_data.addAll(dataBase.datadao().getAllData())
    }

    override fun update(position: Int) {
        Dialog(this).apply {
            setContentView(R.layout.custom_dialog)

            //variable
            val tittle = findViewById<EditText>(R.id.title)
            val description = findViewById<EditText>(R.id.description)
            val all_rb = findViewById<RadioButton>(R.id.all)
            val low_rb = findViewById<RadioButton>(R.id.low)
            val medium_rb = findViewById<RadioButton>(R.id.medium)
            val high_rb = findViewById<RadioButton>(R.id.high)
            val add_button = findViewById<Button>(R.id.Add)


            add_button.setOnClickListener {
                if (tittle.text.trim().isNullOrEmpty()){
                    tittle.error = "Enter the tittle"
                }else if (description.text.trim().isNullOrEmpty()){
                    description.error = "Enter the tittle"
                }else if (all_rb.isChecked == false && low_rb.isChecked == false && medium_rb.isChecked == false && high_rb.isChecked == false){
                    all_rb.error = "select priority"
                }else{
                    var tittle_data = tittle.text.toString()
                    var description_data = description.text.toString()
                    dataBase.datadao().updateDate(MyData(title = tittle_data, description = description_data, priority ="low"))
                    getData()
                }
            }

        }.show()

    }

    override fun delete(position: Int) {
        dataBase.datadao().deleteData(item_data[position])
        recyclerAdapter.notifyDataSetChanged()

    }
}