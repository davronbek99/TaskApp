package com.example.taskapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.taskapp.R
import com.example.taskapp.adapters.PhoneAdapter
import com.example.taskapp.databinding.FragmentListBinding
import com.example.taskapp.models.GetPhone
import com.example.taskapp.responseApis.ResApis
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment : Fragment() {
    private lateinit var binding: FragmentListBinding
    private lateinit var viewModel: ListViewModel
    private lateinit var phoneAdapter: PhoneAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
        setViewModelListeners()
    }

    private fun init() {
        viewModel = ViewModelProvider(this)[ListViewModel::class.java]

        phoneAdapter = PhoneAdapter()
        binding.rvPhone.adapter = phoneAdapter

        viewModel.phoneList()
    }

    private fun setViewModelListeners() {
        viewModel.smartPhoneList.observe(requireActivity(), observer)
    }

    private val observer = Observer<ResApis<GetPhone>> {
        when (it) {
            is ResApis.Error -> {
                binding.loadingLayout.loadingLayout.visibility = View.GONE
            }
            is ResApis.Success -> {

                it.data.offers.forEach { offer ->
                    phoneAdapter.addFile(offer)
                }
                binding.loadingLayout.loadingLayout.visibility = View.GONE
            }
            else -> {}
        }
        viewModel.smartPhoneList.postValue(ResApis.Loading())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.smartPhoneList.removeObserver(observer)
    }
}