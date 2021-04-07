package com.example.todolist

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item.view.*
import org.w3c.dom.Text

class TodoAdapter(private val todoItems: MutableList<Todo>) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>(){

    class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false))
    }

    fun addTodo(todo: Todo){
        todoItems.add(todo)
        notifyItemInserted(todoItems.size - 1)
    }

    fun deleteDone(){
        todoItems.removeAll {todo ->
            todo.isDone
        }
        notifyDataSetChanged()
    }

    private fun toggleDone(txt_item: TextView, txt_item_date:TextView, isDone: Boolean){
        if(isDone){
            txt_item.paintFlags = txt_item.paintFlags or STRIKE_THRU_TEXT_FLAG
            txt_item_date.paintFlags = txt_item_date.paintFlags or STRIKE_THRU_TEXT_FLAG
        }else{
            txt_item.paintFlags = txt_item.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
            txt_item_date.paintFlags = txt_item_date.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val currentTodo = todoItems[position]
        holder.itemView.apply {
            txt_item.text = currentTodo.title
            txt_item_date.text = currentTodo.date
            txt_item_category.text = currentTodo.category

            when (currentTodo.category) {
                "Finance" -> img_category.setImageResource(R.drawable.ic_category_finance)
                "Health" -> img_category.setImageResource(R.drawable.ic_category_health)
                "Home" -> img_category.setImageResource(R.drawable.ic_category_home)
                "Other" -> img_category.setImageResource(R.drawable.ic_category_other)
                "School" -> img_category.setImageResource(R.drawable.ic_category_school)
                "Shopping" -> img_category.setImageResource(R.drawable.ic_category_shopping)
                "Work" -> img_category.setImageResource(R.drawable.ic_category_work)
                else -> {
                    img_category.setImageResource(R.drawable.ic_category_other)
                }
            }
            cb_item.isChecked = currentTodo.isDone
            toggleDone(txt_item, txt_item_date,currentTodo.isDone)
            cb_item.setOnCheckedChangeListener { _, isChecked ->
                toggleDone(txt_item, txt_item_date, isChecked)
                currentTodo.isDone = !currentTodo.isDone
            }
        }
    }

    override fun getItemCount(): Int {
        return todoItems.size
    }
}