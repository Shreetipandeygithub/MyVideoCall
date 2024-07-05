package com.example.myvideocall

import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.zegocloud.uikit.prebuilt.call.ZegoUIKitPrebuiltCallService
import com.zegocloud.uikit.prebuilt.call.invite.ZegoUIKitPrebuiltCallInvitationConfig

class LoginActivity : AppCompatActivity() {

    private lateinit var myUsername:EditText
    private lateinit var loginButton: AppCompatButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        myUsername=findViewById(R.id.personName)
        loginButton=findViewById(R.id.loginButton)


        loginButton.setOnClickListener {
            val myUserId=myUsername.text.toString()
            if(myUserId.isNotEmpty()){
                val intent=Intent(this@LoginActivity,MainActivity::class.java)
                intent.putExtra("userID",myUserId)
                startActivity(intent)


                setupZegoUIKit(myUserId)
            }
        }
    }

    private fun setupZegoUIKit(userId:String){
        val application:Application=application
        val appID:Long=844302906
        val appSign="9596d8b624a1ecf468593badc7f23937e168190aa159193c6a249d0c9c7ed122"
        val userName:String=userId
        val callInvitationConfig=ZegoUIKitPrebuiltCallInvitationConfig()
        ZegoUIKitPrebuiltCallService.init(application,appID,appSign,userId,userName,callInvitationConfig)
    }

    override fun onDestroy() {
        super.onDestroy()
        ZegoUIKitPrebuiltCallService.unInit()
    }
}