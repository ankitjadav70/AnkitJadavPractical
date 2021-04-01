package com.example.ankitjadavpractical.ui.user

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.ankitjadavpractical.R
import com.example.ankitjadavpractical.data.entity.User
import com.example.ankitjadavpractical.databinding.ItemUserBinding
import com.squareup.picasso.Picasso

class ItemListAdapter(val userList: List<String>) : RecyclerView.Adapter<ItemListAdapter.ViewHolder>() {

    //this method is returning the view for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemListAdapter.ViewHolder {
        val binding : ItemUserBinding =DataBindingUtil.inflate(LayoutInflater.from(parent.context),R.layout.item_user, parent, false)
        return ViewHolder(binding)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: ItemListAdapter.ViewHolder, position: Int) {
        holder.bindItems(userList[position])
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return userList.size
    }

    //the class is hodling the list view
    inner class ViewHolder(val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bindItems(user: String) {
            Picasso.get().load(user).into(binding.ivItem)
        }
    }


}