package com.cekepek.adv160420021week4.model

import com.google.gson.annotations.SerializedName

data class Student (
    val id:String?,
    @SerializedName("student_name") //karena variabel name disini berbeda dengan yang di JSON yaitu student_name
    val name:String?,
    @SerializedName("birth_of_date")
    val bod:String?,
    val phone:String?,
    @SerializedName("photo_url")
    val photoUrl:String?
    )
