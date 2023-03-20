package com.cekepek.adv160420021week4.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.cekepek.adv160420021week4.R
import com.cekepek.adv160420021week4.viewmodel.DetailViewModel
import com.cekepek.adv160420021week4.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.fragment_student_detail.*

class StudentDetailFragment : Fragment() {
    private lateinit var viewModel: DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_student_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        if(arguments != null){
            val studentId = StudentDetailFragmentArgs.fromBundle(requireArguments()).studentId
            viewModel.fetch(studentId)
            observeViewModel()
        }

    }

    fun observeViewModel(){
        viewModel.studentLD.observe(viewLifecycleOwner, Observer {
            txtID.setText(it.id)
            txtName.setText(it.name)
            txtBod.setText(it.dob)
            txtPhone.setText(it.phone)
        })
    }
}