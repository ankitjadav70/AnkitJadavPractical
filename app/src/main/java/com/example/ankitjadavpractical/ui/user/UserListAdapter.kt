package com.example.ankitjadavpractical.ui.user

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ankitjadavpractical.R
import com.example.ankitjadavpractical.data.entity.User
import com.example.ankitjadavpractical.databinding.RowUserBinding
import com.squareup.picasso.Picasso

class UserListAdapter(val context : Context,val userList: List<User>) : RecyclerView.Adapter<UserListAdapter.ViewHolder>() {

    //this method is returning the view for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListAdapter.ViewHolder {
       val binding : RowUserBinding=DataBindingUtil.inflate(LayoutInflater.from(parent.context),R.layout.row_user, parent, false)
        return ViewHolder(binding)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: UserListAdapter.ViewHolder, position: Int) {
        holder.bindItems(userList[position])
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return userList.size
    }

    //the class is hodling the list view
  inner  class ViewHolder(val binding: RowUserBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindItems(user: User) {
            binding.user=user
            // load the image with Picasso
           Picasso.get().load(user.image).into(binding.ivUser)

            val adapter=ItemListAdapter(user.items)
          val layoutManager=  GridLayoutManager(context, 2)
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL)
            layoutManager.setSpanSizeLookup(object : SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    if(user.items.size%2==0)
                    {
                      return  1
                    }
                    else if(position==user.items.lastIndex){
                      return  2
                    }
                    return  1

                }
            })

            binding.rcyItems.layoutManager=layoutManager
            binding.rcyItems.adapter=adapter
        }
    }
}