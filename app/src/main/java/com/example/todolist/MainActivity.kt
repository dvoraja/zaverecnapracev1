package com.example.todolist

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item.*
import java.util.*

class MainActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener/*, TimePickerDialog.OnTimeSetListener*/{

    private lateinit var todoAdapter: TodoAdapter

    var day = 0
    var month = 0
    var year = 0
   // var hour = 0
   // var minute = 0

    var savedDay = 0
    var savedMonth = 0
    var savedYear = 0
   // var savedHour = 0
   // var savedMinute = 0

    var date = ""

    var category = "Other"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        todoAdapter = TodoAdapter(mutableListOf())

        rv_items.adapter = todoAdapter
        rv_items.layoutManager = LinearLayoutManager(this)



        btn_add_item.setOnClickListener {
            val newItem = et_newitem.text.toString()
            if(newItem.isNotEmpty()){
                val todo = Todo(newItem, date, category)
                todoAdapter.addTodo(todo)
                et_newitem.text.clear()
                date = ""
                category = "Other"
            }
        }
        btn_delete_finished.setOnClickListener {
            todoAdapter.deleteDone()
        }
        btn_category.setOnClickListener {
            showCategory()
        }
        btn_date.setOnClickListener {
            pickDate()
        }


    }
    fun showCategory(){
        val listcategory = arrayOf("Finance", "Health", "Home", "Other", "School","Shopping", "Work")
        val cbuilder = AlertDialog.Builder(this@MainActivity)
        cbuilder.setTitle("Choose category")
        cbuilder.setSingleChoiceItems(listcategory, -1) { dialogInterface, i ->
            category = listcategory[i]
            dialogInterface.dismiss()
        }
        cbuilder.setNeutralButton("Cancel") { dialog, which ->
            dialog.cancel()
        }

        val mDialog = cbuilder.create()
        mDialog.show()
    }

    private fun getDateTimeCalendar() {
        val cal = Calendar.getInstance()
        day = cal.get(Calendar.DAY_OF_MONTH)
        month = cal.get(Calendar.MONTH)
        year = cal.get(Calendar.YEAR)
     //   hour = cal.get(Calendar.HOUR)
     //   minute = cal.get(Calendar.MINUTE)
    }

    private fun pickDate(){
        btn_date.setOnClickListener {
            getDateTimeCalendar()

            DatePickerDialog(this, this, year, month, day).show()
        }
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        savedDay = dayOfMonth
        savedMonth = month + 1
        savedYear = year

        getDateTimeCalendar()

       // TimePickerDialog(this, this, hour, minute, true).show()
        date = "$savedDay.$savedMonth.$savedYear"//\n$savedHour:$savedMinute"

    }

 /*   override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        savedHour = hourOfDay
        savedMinute = minute


    }*/
}