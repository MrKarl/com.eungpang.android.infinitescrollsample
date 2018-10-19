package com.eungpang.android.infinitescrollsample

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.eungpang.android.infinitescrollsample.vo.User

class UserListViewAdapter(private var userList: List<User>?): RecyclerView.Adapter<UserListViewAdapter.ViewHolder>() {

    fun updateUserList(pUserList: List<User>) {
        userList = pUserList

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_userlist, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = userList?.size ?: 0

    override fun onBindViewHolder(vh: ViewHolder, position: Int) {
        val user = userList?.get(position)
        if (user !== null) {
            vh.updateView(user)
        }
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val nameView: TextView
        private val avartarUrlView: TextView
        private val repoUrlView: TextView

        init {
            nameView = view.findViewById<TextView>(R.id.name)
            avartarUrlView = view.findViewById<TextView>(R.id.avatarUrl)
            repoUrlView = view.findViewById<TextView>(R.id.repoUrl)
        }

        fun updateView(user: User) {
            nameView.text = user.login
            avartarUrlView.text = user.avatar_url
            repoUrlView.text = user.repos_url
        }
    }

}