package com.example.recyclev

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class TodoAdapter(val todoList: ArrayList<TodoList>) : RecyclerView.Adapter<TodoAdapter.CustomViewHolder>() {
    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //뷰를 잡아주는 역활
        val content = itemView.findViewById<TextView>(R.id.tv_contents)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoAdapter.CustomViewHolder {
        //액티비티의 oncreat랑 비슷함 (xml 연결)
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        //list_item 액티비티(xml) 연동
        //어댑터와 연결될 액티비티의 컨텍스트 가지고옴
        //context는 액티비티에 있는 모든 정보

        return CustomViewHolder(view)
    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    override fun onBindViewHolder(holder: TodoAdapter.CustomViewHolder, position: Int) {
        //뷰홀더를 바인드, (이 함수가 계속 실행됨)
        holder.content.setText(todoList.get(position).contents)

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.content.context, SubActivity::class.java)
            intent.putExtra("position", position)
            intent.putExtra("mode", "edit")
            intent.putParcelableArrayListExtra("todoList", todoList)

            holder.content.context.startActivity(intent)   //(화면이동)
            (holder.content.context as MainActivity).finish()
        }

    }

}