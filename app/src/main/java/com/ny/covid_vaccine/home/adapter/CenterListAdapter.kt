package com.ny.covid_vaccine.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.ny.covid_vaccine.R

class CenterListAdapter(val centerList:List<String>, val onItemClick: onItemClick):RecyclerView.Adapter<CenterListAdapter.CenterItemViewHolder>() {

    inner class CenterItemViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val centerNameTV:AppCompatTextView = itemView.findViewById<AppCompatTextView>(R.id.centerName)

        fun bind(centerName:String){
            centerNameTV.text = centerName

            centerNameTV.setOnClickListener {
                onItemClick.onClick(centerList[adapterPosition])
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CenterItemViewHolder {
        return CenterItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.center_item,null,false)
        )
    }

    override fun getItemCount(): Int {
        return centerList.size
    }

    override fun onBindViewHolder(holder: CenterItemViewHolder, position: Int) {
        holder.bind(centerList[position])
    }

}

interface onItemClick{
    fun onClick(city:String)
}