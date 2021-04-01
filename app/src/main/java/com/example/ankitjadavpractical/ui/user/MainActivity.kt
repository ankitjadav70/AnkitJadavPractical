package com.example.ankitjadavpractical.ui.user

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ankitjadavpractical.R
import com.example.ankitjadavpractical.data.entity.Example
import com.example.ankitjadavpractical.data.entity.User
import com.example.ankitjadavpractical.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    lateinit var mainActivityViewModel: MainActivityViewModel
    lateinit var binding: ActivityMainBinding

    var offset : Int=0
    var limit : Int=10
    var loading :Boolean=true
     var userList=LinkedHashSet<User>()
     var adapter : UserListAdapter?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_main)
        mainActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

       loadData()

    }

    fun loadData() {
            mainActivityViewModel.getUser(offset,limit)?.observe(this@MainActivity, Observer {
            if(it.data!=null) {
                it.data!!.users?.let { it1 -> userList.addAll(it1) }
                if(adapter==null) {
                    setAdapter(userList)
                }
                else{
                    adapter?.notifyDataSetChanged()
                }
                loading=true
            }
        })

    }

    fun setAdapter(userList: LinkedHashSet<User>) {
        rcy_user.layoutManager=LinearLayoutManager(this)
        adapter=UserListAdapter(this,userList)
        rcy_user.adapter=adapter
        rcy_user.scrollToPosition(userList.size)
        var pastVisiblesItems: Int
        var visibleItemCount: Int
        var totalItemCount: Int

        rcy_user.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0) { //check for scroll down
                    visibleItemCount = (rcy_user.layoutManager as LinearLayoutManager).getChildCount()
                    totalItemCount = (rcy_user.layoutManager as LinearLayoutManager).getItemCount()
                    pastVisiblesItems = (rcy_user.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                    if (loading) {
                        if (visibleItemCount + pastVisiblesItems >= totalItemCount) {
                            loading = false
                            offset+=10
                            loadData()
                        }
                    }
                }
            }
        })

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}