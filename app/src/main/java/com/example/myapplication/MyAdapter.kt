package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.FirebaseDatabase

class MyAdapter (private val fuelList : ArrayList<User>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.fuel_item,
        parent, false)
        return MyViewHolder(itemView)

    }
    var database = FirebaseDatabase.getInstance().reference
    override fun onBindViewHolder(holder: MyAdapter.MyViewHolder, position: Int) {

        val currentitem = fuelList[position]


        holder.ownerN.text = currentitem.oName
        holder.vRegNo.text = currentitem.vNo
        holder.nIcNo.text = currentitem.nicNo
        holder.licNo.text = currentitem.lNo
        holder.addrs.text = currentitem.address
    }

    override fun getItemCount(): Int {

        return fuelList.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){


        val ownerN : TextView = itemView.findViewById(R.id.oName)
        val vRegNo : TextView = itemView.findViewById(R.id.vNo)
        val nIcNo : TextView = itemView.findViewById(R.id.nicNo)
        val licNo : TextView = itemView.findViewById(R.id.lNo)
        val addrs : TextView = itemView.findViewById(R.id.address)
    }


}