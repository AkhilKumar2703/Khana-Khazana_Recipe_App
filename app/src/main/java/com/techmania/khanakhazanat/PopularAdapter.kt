package com.techmania.khanakhazanat

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.techmania.khanakhazanat.databinding.PopularRvItemBinding

class PopularAdapter(var dataList:ArrayList<Recipe>,var context: Context):RecyclerView.Adapter<PopularAdapter.viewHolder>() {
    inner class viewHolder(var binding: PopularRvItemBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        var binding = PopularRvItemBinding.inflate(LayoutInflater.from(context),parent,false)
        return viewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.binding.popularImg
        Glide.with(context).load(dataList.get(position).img).into(holder.binding.popularImg)

        var time = dataList.get(position).ing.split("\n".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()

        holder.binding.popularTxt.text=dataList.get(position).tittle
        holder.binding.popularTime.text=time.get(0)
        holder.itemView.setOnClickListener{
         var intent   = Intent(context,RecipeActivity::class.java)
            intent.putExtra("img",dataList.get(position).img)
            intent.putExtra("tittle",dataList.get(position).tittle)
            intent.putExtra("des",dataList.get(position).des)
            intent.putExtra("ing",dataList.get(position).ing)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }

    }
}