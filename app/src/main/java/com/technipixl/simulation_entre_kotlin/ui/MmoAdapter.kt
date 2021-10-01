package com.technipixl.simulation_entre_kotlin.ui


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.technipixl.simulation_entre_kotlin.data.DataMmo
import com.technipixl.simulation_entre_kotlin.databinding.MmoCellBinding



class MmoAdapter(private val tabMmo:List<DataMmo>,private val listener: MmoClickListener):RecyclerView.Adapter<MmoAdapter.MmoViewHolder>() {

    interface MmoClickListener {
        fun mmoClickListener(itemMmo :DataMmo)
    }

    class MmoViewHolder(val binding: MmoCellBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(mmo :DataMmo,listener: MmoClickListener ){
            binding.textView.text = mmo.title
            Picasso.get()
                .load(mmo.thumbnail)
                .into(binding.image1)
            itemView.setOnClickListener {
                listener.mmoClickListener(mmo)
            }
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MmoViewHolder {
        val binding = MmoCellBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return  MmoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MmoViewHolder, position: Int) {
        holder.bind(tabMmo[position],listener)
    }

    override fun getItemCount(): Int {
        return tabMmo.count()
    }
}