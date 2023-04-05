package com.cekepek.adv160420021week4.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.cekepek.adv160420021week4.R
import com.cekepek.adv160420021week4.model.Student
import com.cekepek.adv160420021week4.util.loadImage
import com.squareup.picasso.Picasso

class StudentListAdapter(val studentList:ArrayList<Student>)
    :RecyclerView.Adapter<StudentListAdapter.StudentViewHolder>()
{
    class StudentViewHolder(var view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            StudentViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.student_list_item, parent, false)
        return StudentViewHolder(view)
    }


    override fun onBindViewHolder(holder: StudentViewHolder, position: Int){
        val txtID = holder.view.findViewById<TextView>(R.id.txtID) // karena ada 2 id text view yang txtID maka pakai gini dan class nya ga nyambung ke layout apa-apa
        txtID.text = studentList[position].id
        val txtName = holder.view.findViewById<TextView>(R.id.txtName)
        txtName.text = studentList[position].name
        val image  = holder.view.findViewById<ImageView>(R.id.imageView)
        var progressBar = holder.view.findViewById<ProgressBar>(R.id.progressBar)
        image.loadImage(studentList[position].photoUrl, progressBar)
        val btnDetail = holder.view.findViewById<Button>(R.id.btnDetail)
        btnDetail.setOnClickListener {
            var id = "0"
            studentList[position].id?.let{
                id = it
            }
            val action = StudentListFragmentDirections.actionStudentDetail(id)
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return studentList.size
    }

    fun updateStudentList(newStudentList: ArrayList<Student>) {
        studentList.clear()
        studentList.addAll(newStudentList)
        notifyDataSetChanged()
    }

}