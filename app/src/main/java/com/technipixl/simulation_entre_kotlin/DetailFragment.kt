package com.technipixl.simulation_entre_kotlin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.squareup.picasso.Picasso
import com.technipixl.simulation_entre_kotlin.databinding.FragmentDetailBinding
import com.technipixl.simulation_entre_kotlin.web.MmoImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


private lateinit var binding: FragmentDetailBinding
class DetailFragment : Fragment() {
private val conn by lazy { MmoImpl() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args : DetailFragmentArgs by navArgs()
        setupData(args?.id.toInt())

    }
    fun setupData(id: Int){
        CoroutineScope(Dispatchers.IO).launch {
            val response = conn.mmoDetailimpl(id)
            withContext(Dispatchers.Main){
                if (response.isSuccessful){
                    response?.let { data ->
                        data.body()?.let {
                            binding.title.text = it.title
                            binding.developperText.text = it.developer
                            binding.descriptiontext.text = it.short_description
                            Picasso.get()
                                .load(it.thumbnail)
                                .into(binding.imageGame)
                        }
                    }
                }

            }
        }

    }

}