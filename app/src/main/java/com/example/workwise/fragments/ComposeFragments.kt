package com.example.workwise.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.workwise.R
import org.apache.commons.io.FileUtils
import java.io.File
import java.io.IOException
import java.nio.charset.Charset


class ComposeFragments : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var userAdapter: ContentAdapter
    private val userList = ArrayList<ContentClass>()
    private lateinit var dataFile: File

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_compose, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)





        dataFile = getDataFile()
        loadItems()

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        userAdapter = ContentAdapter(userList)
        recyclerView.adapter = userAdapter

        userAdapter.setOnLongClickListener(object : ContentAdapter.OnLongClickListener {
            override fun onItemLongClicked(position: Int) {
                // Remove the item from the list
                userList.removeAt(position)
                // Notify the adapter that the data has changed
                userAdapter.notifyDataSetChanged()
                // Save the changes to the file
                saveItems()
            }
        })

        val submitButton = view.findViewById<Button>(R.id.post_button)
        submitButton.setOnClickListener {
            val postEditText = view.findViewById<EditText>(R.id.post_title_edittext)

            val contentEditText = view.findViewById<EditText>(R.id.post_content_edittext)

            val post = postEditText.text.toString()
            val content = contentEditText.text.toString()


            val user = ContentClass(post, content)
            userList.add(user)
            saveItems()
            userAdapter.notifyDataSetChanged()
            postEditText.setText("")
            contentEditText.setText("")

        }
        val post1 = "Importance of Software Engineering"
        val content1 = "Software engineering is a critical field that provides numerous benefits to modern society. It enables the creation of new technologies, improves efficiency and productivity, enhances user experience, allows for innovation, ensures quality and reliability, and facilitates collaboration and communication. These benefits are achieved through the development and implementation of well-established processes and methodologies, which are designed to produce high-quality software that meets the needs of users and businesses. Without software engineering, many of the technologies and services we rely on today would not exist, and the benefits they provide would be lost."

        val post2 = "Android app development"
        val content2 = "Kotlin is a modern, statically-typed programming language that has gained popularity as an alternative to Java for Android app development. Kotlin is designed to be more concise and expressive than Java, making it easier to read and write code. Kotlin is also interoperable with Java, meaning that developers can use Java libraries and frameworks in their Kotlin-based Android apps. Kotlin has many features that are particularly useful for Android development, such as null safety, extension functions, and coroutines for asynchronous programming. Kotlin also has excellent tooling support, with a powerful IDE plugin for Android Studio and a command-line compiler for building apps outside of the IDE. As a result, Kotlin has become a popular choice for developers looking to build high-quality Android apps quickly and efficiently."

        val post3 = "How to start learning Android app development with Kotlin."
        val content3 = "To start learning Android app development with Kotlin, there are several steps you can follow. First, familiarize yourself with the Kotlin programming language by studying its syntax, features, and best practices. Then, learn the basics of Android app development by building simple apps and following tutorials. Finally, dive into more advanced topics like app architecture, APIs, and testing. Good luck!"
        userList.add(ContentClass(post1, content1))
        userList.add(ContentClass(post2, content2))
        userList.add(ContentClass(post3, content3))
        saveItems()
        userAdapter.notifyDataSetChanged()
    }

    private fun getDataFile(): File{
        return File(requireContext().filesDir, "data.txt")
    }

    private fun loadItems(){
        try{
            userList.clear()
            val lines = FileUtils.readLines(dataFile, Charset.defaultCharset())
            for(line in lines){
                val parts = line.split(":")
                val post = parts[0]
                val content = parts[1]
                val user = ContentClass(post, content)
                userList.add(user)
            }
        }catch (ioException: IOException){
            ioException.printStackTrace()
        }

    }

    private fun saveItems(){
        val lines = mutableListOf<String>()
        for(user in userList){
            val line = "${user.Post}:${user.Content}"
            lines.add(line)
        }

        try{
            FileUtils.writeLines(dataFile, lines)
        }catch (ioException: IOException){
            ioException.printStackTrace()
        }
    }
}
