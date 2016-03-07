package com.example.ghost.loginandsignup;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by ghost on 26/02/2016.
 */
public class HalamanUtama extends Activity {
    Button logout;
    TextView username,password;
    String lg="login";
    String IS_USER_LOGIN="IsUserLogin";

    SharedPreferences get_SP;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wlcome);
        Button logout = (Button) findViewById(R.id.btnLogOut);
        username=(TextView)findViewById(R.id.txtUsr);
        password = (TextView)findViewById(R.id.txtPss);

        get_SP =getSharedPreferences(lg, Context.MODE_PRIVATE);
        Toast.makeText(getApplicationContext(),
                "User Login Status: " + isUserLoggedIn(),
                Toast.LENGTH_LONG).show();


        String uName = getIntent().getExtras().getString("user");
        String uPass = getIntent().getExtras().getString("pass");

        username.setText(get_SP.getString("username",""));
        password.setText(get_SP.getString("password",""));



        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences.Editor SP_Editor = get_SP.edit();
                SP_Editor.commit();
                SP_Editor.clear();



                Intent i = new Intent(HalamanUtama.this,MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        });
    }

    public boolean isUserLoggedIn(){
        return get_SP.getBoolean(IS_USER_LOGIN, false);
    }
}