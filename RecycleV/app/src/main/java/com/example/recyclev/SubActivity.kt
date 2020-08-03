package com.example.recyclev

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_sub.*
import java.util.ArrayList

class SubActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub)
        val position = intent.getIntExtra("position",0)
        val mode = intent.getStringExtra("mode")
        var todoList = intent.getParcelableArrayListExtra<TodoList>("todoList")


        if (intent.hasExtra("todoList")) {
            if (todoList != null && mode == "edit") {
                edit_text.setText(todoList.get(position).contents.toString())
                tv_detail.setText(todoList.get(position).contents.toString())
            }
        }

        val textWatcher = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (todoList != null) {
                    tv_detail.setText(edit_text.text)
                }
            }
        }

        edit_text.addTextChangedListener(textWatcher)

        btn_save.setOnClickListener {
            val todo_contents = edit_text.text.toString()

            if (todoList != null) {
                if (mode == "add"){
                    todoList.add(TodoList(todo_contents))
                }else if(mode == "edit"){
                    todoList.get(position).contents = todo_contents
                }
                saveData(todoList)
            }
        }

        btn_delete.setOnClickListener {
            if (todoList != null && mode == "edit") {
                todoList.removeAt(position)
                saveData(todoList)
            }
        }

    }

    override fun onBackPressed() {
        go_to_mainActivity()
    }

    private fun saveData(todoList: ArrayList<TodoList>?) {
        val pref = getSharedPreferences("pref", 0)
        //앱 내부의 프리퍼런스? 앱 내부 pref의 폴더를 만듬?
        val edit = pref.edit() //수정모드
        val gson = Gson()
        val json = gson.toJson(todoList)
        edit.putString("todoList", json)
        edit.apply() //값 저장
        go_to_mainActivity()
    }

    private fun go_to_mainActivity(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}