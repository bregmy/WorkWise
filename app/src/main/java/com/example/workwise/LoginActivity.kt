package com.example.workwise

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.parse.ParseUser

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


//        val loginButton: Button = findViewById(R.id.login)
//
//        loginButton.setOnClickListener {
//            val intent = Intent(this, MainActivity::class.java)
//            startActivity(intent)
//            finish() // optional, closes LoginActivity after launching MainActivity
//        }
//    }
//}

        findViewById<Button>(R.id.login).setOnClickListener {
            val username = findViewById<EditText>(R.id.username).text.toString()
            val password = findViewById<EditText>(R.id.password).text.toString()
            loginUser(username, password)
        }


    }



    private fun loginUser(username: String, password: String){
        ParseUser.logInInBackground(username, password, ({ user, e->
            if (user != null){

                goToExistingUserActivity()
            }else{
                e.printStackTrace()
                Toast.makeText(this ,"Error Logging in",Toast.LENGTH_SHORT).show()
            }
        }))





    }

    private fun goToExistingUserActivity(){
        val intent=Intent(this@LoginActivity, MainActivity::class.java)
        startActivity(intent)
    }





    companion object{
        const val TAG="MainActivity"

    }

}