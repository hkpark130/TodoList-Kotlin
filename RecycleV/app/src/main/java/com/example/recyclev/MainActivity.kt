package com.example.recyclev

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_main.*
import com.example.recyclev.TodoList as TodoList

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var todoList: ArrayList<TodoList> = loadData()

        rv_todolist.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        //VERTICAL 세로방향으로 만듬
        rv_todolist.setHasFixedSize(true)

        rv_todolist.adapter = TodoAdapter(todoList)

        btn_add.setOnClickListener {
            var position = todoList.size
            val intent = Intent(this, SubActivity::class.java)
            intent.putExtra("position", position)
            intent.putExtra("mode", "add")
            intent.putParcelableArrayListExtra("todoList", todoList)
            startActivity(intent)
            finish()
        }
    }

    private fun loadData(): ArrayList<TodoList> {
        val pref = getSharedPreferences("pref", 0)
        val gson = Gson()
        val itemType = object : TypeToken<ArrayList<TodoList>>() {}.type
        var todoList= gson.fromJson<ArrayList<TodoList>>(pref.getString("todoList", ""), itemType)

        if (todoList == null) {
            todoList = arrayListOf<TodoList>(
                TodoList("there is no data")
            )
        }
        return todoList
    }

    override fun onBackPressed() {
        finish()
    }

}
