package com.example.datastorage.Controladores

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ListView
import android.widget.Toast
import com.example.datastorage.Adapters.UsersListAdapter
import com.example.datastorage.Modelos.User
import com.example.datastorage.R
import com.example.datastorage.Servicios.UserDBServices


class UsersListActivity : AppCompatActivity()
{
    private lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users_list)

        var listImg: MutableList<Int> = mutableListOf<Int>(R.drawable.user, R.drawable.user1, R.drawable.user2, R.drawable.usern)
        val listPosts: List<User>? = UserDBServices(this).consultUsers()
        listView = findViewById<ListView>(R.id.listUsers) as ListView
        val adapter = UsersListAdapter(this, listPosts, listImg)
        listView.adapter = adapter

        val intent = Intent(this, ProfileActivity::class.java)

        listView.setClickable(true)
        listView.setOnItemClickListener { adapterView, view, i, l ->
            Toast.makeText(this, "Item Clicked " + adapter.getName(i),Toast.LENGTH_SHORT).show()

            intent.putExtra(ProfileActivity.IMAGE_PROFILE, adapter.getItemImg(i))
            intent.putExtra(ProfileActivity.NAME_PROFILE, adapter.getName(i))
            intent.putExtra(ProfileActivity.AGE_PROFILE, adapter.getAge(i)!!.toInt())
            intent.putExtra(ProfileActivity.EMAIL_PROFILE, adapter.getEmail(i))
            intent.putExtra(ProfileActivity.PASSW_PROFILE, adapter.getPassword(i))

            startActivity(intent)
        }
    }

    fun goMainActivity(view: View)
    {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
