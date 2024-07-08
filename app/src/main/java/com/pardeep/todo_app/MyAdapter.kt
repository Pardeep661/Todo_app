package com.pardeep.todo_app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(
    var itemData: ArrayList<MyData>,
    var activityInterface: ActivityInterface
) : RecyclerView.Adapter<MyAdapter.ViewHolder>(){

    class ViewHolder(var view : View) : RecyclerView.ViewHolder(view){

        var title_text = view.findViewById<TextView>(R.id.Title)
        var des_text = view.findViewById<TextView>(R.id.Description)
        var update_btn = view.findViewById<Button>(R.id.Update)
        var delete_btn = view.findViewById<Button>(R.id.delete)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.custom_design,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title_text.setText(itemData[position].title)
        holder.des_text.setText(itemData[position].description)

        holder.update_btn.setOnClickListener {
            activityInterface.update(position)

        }

        holder.delete_btn.setOnClickListener {
            activityInterface.delete(position)
        }



    }

}
