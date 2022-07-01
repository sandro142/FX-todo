package com.example.fx

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_todo.view.*

class todo_adapter (var todos: List<Todo>) : RecyclerView.Adapter<todo_adapter.todoViewHolder>(){
    inner class todoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): todoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_todo, parent,false)
        return todoViewHolder(view)
    }

    override fun onBindViewHolder(holder: todoViewHolder, position: Int) {
        holder.itemView.apply {
            title_id.text = todos[position].title
            checkbox_id.isChecked = todos[position].isChecked
        }


    }

    override fun getItemCount(): Int {
        return todos.size
    }
}