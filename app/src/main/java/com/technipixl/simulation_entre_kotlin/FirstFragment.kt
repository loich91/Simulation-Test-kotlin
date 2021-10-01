package com.technipixl.simulation_entre_kotlin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.technipixl.simulation_entre_kotlin.ui.MmoAdapter
import com.technipixl.simulation_entre_kotlin.data.DataMmo
import com.technipixl.simulation_entre_kotlin.databinding.FragmentFirstBinding
import com.technipixl.simulation_entre_kotlin.web.MmoImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class FirstFragment : Fragment() ,MmoAdapter.MmoClickListener{

    private lateinit var binding: FragmentFirstBinding
    private val conn by lazy { MmoImpl() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFirstBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupData()
    }
    fun setupData(){
        CoroutineScope(Dispatchers.IO).launch {
            val response = conn.mmoImpl()
            withContext(Dispatchers.Main){
                if(response.isSuccessful){
                    response.body().let {data ->
                        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
                        binding.recyclerView.adapter = data?.let { MmoAdapter(it,this@FirstFragment ) }
                    }
                }
            }
        }
    }

    override fun mmoClickListener(mmo: DataMmo)  {
        val action = FirstFragmentDirections.actionFirstFragmentToDetailFragment(mmo.id.toLong())
        findNavController().navigate(action)
    }
}