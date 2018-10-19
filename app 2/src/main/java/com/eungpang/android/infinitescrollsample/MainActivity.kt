package com.eungpang.android.infinitescrollsample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.TextView
import com.eungpang.android.infinitescrollsample.api.APIClient
import com.eungpang.android.infinitescrollsample.api.GitHubService
import com.eungpang.android.infinitescrollsample.vo.User
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private var since = 1
    private val limit = 100

    private var count = 0

    private val allUserList = mutableListOf<User>()

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: UserListViewAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewManager = GridLayoutManager(this, 1)
        viewAdapter = UserListViewAdapter(allUserList)
        recyclerView = findViewById(R.id.recyclerView)

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = viewManager
        recyclerView.adapter = viewAdapter

        recyclerView.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if (!recyclerView.canScrollVertically(1)) {
                    loadMoreUsers()
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {

            }
        })

        loadMoreUsers()
    }

    private fun updateActivity() {
        val countView = findViewById<TextView>(R.id.count)
        countView.text = "${count} 개"
    }

    private fun loadMoreUsers() {
        val service = APIClient.getClient().create(GitHubService::class.java)
        val call = service.users(since)

        call.enqueue(object: retrofit2.Callback<List<User>> {
            override fun onFailure(call: Call<List<User>>?, t: Throwable?) {
                Log.e("MainActivity", t.toString())
            }

            override fun onResponse(call: Call<List<User>>?, response: Response<List<User>>?) {
                val userList = response?.body()
                Log.e("MainActivity", "size: ${userList?.size ?: 0}")

                since += limit


                allUserList.addAll(userList?.asIterable() ?: listOf())
                viewAdapter.updateUserList(allUserList)

                count = allUserList.size
                updateActivity()
            }
        })
    }
}
