package com.cekepek.adv160420021week4.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cekepek.adv160420021week4.model.Student

class DetailViewModel:ViewModel() {
    val studentLD = MutableLiveData<Student>()

    fun fetch(id: String) {

        val student1 = Student("16055","Nonie","1998/03/28","5718444778",
            "http://dummyimage.com/75x100.jpg/cc0000/ffffff")
        val student2 =
            Student("13312","Rich","1994/12/14","3925444073","http://dummyimage.com/75x100.jpg/5fa2dd/ffffff")

        val student3 =
            Student("11204","Dinny","1994/10/07","6827808747","http://dummyimage.com/75x100.jpg/5fa2dd/ffffff1")
        val studentList:ArrayList<Student> = arrayListOf<Student>(student1, student2, student3)
        var studentDetail = student1
        for (student in studentList){
            if(student.id == id){
                studentDetail = student
            }
        }
        studentLD.value = studentDetail
    }

}