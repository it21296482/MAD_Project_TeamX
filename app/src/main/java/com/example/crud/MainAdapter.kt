package com.example.crud

import MainModel
import android.app.AlertDialog
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.FirebaseDatabase
import com.orhanobut.dialogplus.DialogPlus
import com.orhanobut.dialogplus.ViewHolder
import de.hdodenhof.circleimageview.CircleImageView

class MainAdapter(options: FirebaseRecyclerOptions<MainModel>) :
    FirebaseRecyclerAdapter<MainModel, MainAdapter.MyViewHolder>(options) {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var img: CircleImageView = itemView.findViewById(R.id.img1)
        var name: TextView = itemView.findViewById(R.id.name)
        var license: TextView = itemView.findViewById(R.id.licenseNo)
        var phone: TextView = itemView.findViewById(R.id.phone)
        var btnUpdate: Button = itemView.findViewById(R.id.btnUpdate)
        var btnDelete: Button = itemView.findViewById(R.id.btnDelete)
    }

    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.main_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int, model: MainModel) {
        holder.name.text = "Name: ${model.name}"
        holder.license.text = "License No: ${model.licenseNo}"
        holder.phone.text = "Phone: ${model.phone}"

        val requestOptions = RequestOptions().transform(CircleCrop())
            .placeholder(com.firebase.ui.auth.R.drawable.common_google_signin_btn_icon_dark)
            .error(com.firebase.ui.auth.R.drawable.common_google_signin_btn_icon_dark_normal)

        Glide.with(holder.itemView.context)
            .load(model.hdUrl)
            .apply(requestOptions)
            .into(holder.img)

        // Update button click listener
        holder.btnUpdate.setOnClickListener {
            // Display update pop-up window
            val dialogPlus = DialogPlus.newDialog(holder.itemView.context)
                .setContentHolder(ViewHolder(R.layout.update_popup))
                .setGravity(Gravity.CENTER)
                .setMargin(50, 0, 50, 0)
                .create()

            val updateView = dialogPlus.holderView
            val txtName = updateView.findViewById<EditText>(R.id.txtName)
            val txtLicenseNo = updateView.findViewById<EditText>(R.id.txtLicenseNo)
            val txtPhone = updateView.findViewById<EditText>(R.id.txtPhone)
            val txtimgUrl = updateView.findViewById<EditText>(R.id.txtimgUrl)
            val btnUpdate = updateView.findViewById<Button>(R.id.btnUpdate)

            // Set the existing data in the relevant fields
            txtName.setText(model.name)
            txtLicenseNo.setText(model.licenseNo)
            txtPhone.setText(model.phone)
            txtimgUrl.setText(model.hdUrl)

            // Update button click listener in the pop-up window
            btnUpdate.setOnClickListener {
                val updatedName = txtName.text.toString().trim()
                val updatedLicenseNo = txtLicenseNo.text.toString().trim()
                val updatedPhone = txtPhone.text.toString().trim()
                val updatedImageUrl = txtimgUrl.text.toString().trim()

                // Update the data in the database
                val database = FirebaseDatabase.getInstance().reference
                val updatedModel =
                    MainModel(updatedName, updatedLicenseNo, updatedPhone, updatedImageUrl)
                database.child("Driver").child(getRef(position).key.toString()).setValue(updatedModel)
                    .addOnSuccessListener {
                        Toast.makeText(
                            holder.itemView.context,
                            "Data updated successfully!",
                            Toast.LENGTH_SHORT
                        ).show()
                        dialogPlus.dismiss()
                    }
                    .addOnFailureListener {
                        Toast.makeText(
                            holder.itemView.context,
                            "Failed to update data!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
            }

            dialogPlus.show()
        }

        // Delete button click listener
        holder.btnDelete.setOnClickListener {
            // Display confirmation dialog
            val alertDialogBuilder = AlertDialog.Builder(holder.itemView.context)
            alertDialogBuilder.setTitle("Confirmation")
            alertDialogBuilder.setMessage("Are you sure you want to delete this entry?")
            alertDialogBuilder.setPositiveButton("Yes") { _, _ ->
                // Delete the data from the database
                val database = FirebaseDatabase.getInstance().reference
                database.child("Driver").child(getRef(position).key.toString()).removeValue()
                    .addOnSuccessListener {
                        Toast.makeText(
                            holder.itemView.context,
                            "Data deleted successfully!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    .addOnFailureListener {
                        Toast.makeText(
                            holder.itemView.context,
                            "Failed to delete data!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
            }
            alertDialogBuilder.setNegativeButton("No") { _, _ -> }
            val alertDialog = alertDialogBuilder.create()
            alertDialog.show()
        }
    }
}

