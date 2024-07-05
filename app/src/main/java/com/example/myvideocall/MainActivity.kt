package com.example.myvideocall

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.zegocloud.uikit.prebuilt.call.invite.widget.ZegoSendCallInvitationButton
import com.zegocloud.uikit.service.defines.ZegoUIKitUser

class MainActivity : AppCompatActivity() {

    private lateinit var myUserTxt:TextView
    private lateinit var targetUserId:EditText
    private lateinit var videoCallButton:ZegoSendCallInvitationButton
    private lateinit var voiceCallButton:ZegoSendCallInvitationButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        targetUserId=findViewById(R.id.personName)
        myUserTxt=findViewById(R.id.txt2)
        videoCallButton=findViewById(R.id.videoCallButton)
        voiceCallButton=findViewById(R.id.voiceCallButton)


        val myUserID=intent.getStringExtra("userID")

        myUserTxt.text= "Hey $myUserID,\n Whom do you want to call?"



        targetUserId.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //Do nothing or add necessary implementation
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val targetId=targetUserId.text.toString()
                if (targetId.isNotEmpty()){
                    startVideoCall(targetId)
                    startVoiceCall(targetId)
                }
            }

            override fun afterTextChanged(p0: Editable?) {
                //Implementation is not required if you don't need to do anything after text changes.
            }

        })

    }

    private fun startVideoCall(targetUserID:String){
        val targetPerson :String = targetUserID

        videoCallButton.setIsVideoCall(true)
        videoCallButton.resourceID="zego_uikit_call"
        videoCallButton.setInvitees(listOf(ZegoUIKitUser(targetUserID,targetPerson)))
    }

    private fun startVoiceCall(targetUserID:String){
        val targetPerson :String = targetUserID

        voiceCallButton.setIsVideoCall(false)
        voiceCallButton.resourceID="zego_uikit_call"
        voiceCallButton.setInvitees(listOf(ZegoUIKitUser(targetUserID,targetPerson)))
    }
}