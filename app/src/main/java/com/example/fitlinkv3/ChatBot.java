package com.example.fitlinkv3;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.ibm.cloud.sdk.core.security.IamAuthenticator;

public class ChatBot extends AppCompatActivity {

    // generate IAM token using API key
    IamAuthenticator authenticator = new IamAuthenticator.Builder()
            .apikey("8v2X6SGbyc0TvRpdDcwLGRaie9llNTh7rdm2Vv2hTNnu\n")
            .build();

    //Create the service instance
    //ChatBot service = new ChatBot(authenticator);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_bot);


    }
}