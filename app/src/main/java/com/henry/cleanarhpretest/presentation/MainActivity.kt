package com.henry.cleanarhpretest.presentation

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.henry.cleanarhpretest.R
import com.henry.cleanarhpretest.domain.model.Todo
import com.henry.cleanarhpretest.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    val viewModel: MainActivityViewModel by viewModels()

    private lateinit var toolbar: Toolbar
    private lateinit var tvTitle: TextView
    private lateinit var tvStatus: TextView
    private lateinit var tvUserId: TextView
    private lateinit var tvTodoId: TextView
    private lateinit var btnRandomize: Button
    private lateinit var pbLoading: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.toolbar)
        tvTitle = findViewById(R.id.tv_title)
        tvStatus = findViewById(R.id.tv_status)
        tvUserId = findViewById(R.id.tv_user_id)
        tvTodoId = findViewById(R.id.tv_todo_id)
        btnRandomize = findViewById(R.id.btn_randomize)
        pbLoading = findViewById(R.id.pb_loading)

        toolbar.title = "Todo Randomizer"

        observeViewModel()

        btnRandomize.setOnClickListener {
            viewModel.getTodo((1..200).random())
        }
    }

    private fun observeViewModel() {
        viewModel.todoResponse.observe(this) { response ->
            when (response) {
                is Resource.Error -> {
                    isLoading(false)
                    response.message?.let {
                        Log.e(TAG, "onCreate: $it")
                        showToastMessage("An error occurred $it")
                    }
                }
                is Resource.Loading -> {
                    isLoading(true)
                }
                is Resource.Success -> {
                    isLoading(false)
                    loadDetail(response.data)
                }
            }
        }
    }

    private fun loadDetail(data: Todo?) {
        if (data != null) {
            tvTitle.text = data.title
            tvStatus.text = if (data.completed) "Completed" else "Incomplete"
            tvUserId.text = data.userId.toString()
            tvTodoId.text = data.id.toString()
        }
    }

    private fun showToastMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun isLoading(isLoading: Boolean) {
        if (isLoading) {
            pbLoading.visibility = View.VISIBLE
            btnRandomize.visibility = View.INVISIBLE
            btnRandomize.isEnabled = false
        } else {
            pbLoading.visibility = View.GONE
            btnRandomize.visibility = View.VISIBLE
            btnRandomize.isEnabled = true
        }
    }
}