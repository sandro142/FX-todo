package com.example.fx.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fx.R
import com.example.fx.Todo
import com.example.fx.todo_adapter
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_home_.*


class Home_Fragment : Fragment() {

    private lateinit var add_todo_button_id: Button
    private lateinit var database: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home_, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        add_todo_button_id = view.findViewById(R.id.add_todo_button_id)

        var todoList = mutableListOf<Todo>()

        val adapter = todo_adapter(todoList)
        todo_recyclerview_id.adapter = adapter
        todo_recyclerview_id.layoutManager = LinearLayoutManager(context)

        add_todo_button_id.setOnClickListener {
            val title = todo_edittext_id.text.toString()
            val todo = Todo(title,false)
            todoList.add(todo)
            todo_edittext_id.setText("")

            database = FirebaseDatabase.getInstance().getReference("Todos")
            val Todo = Todo(title,false)
            database.child(title).setValue(Todo)
        }

    }


}