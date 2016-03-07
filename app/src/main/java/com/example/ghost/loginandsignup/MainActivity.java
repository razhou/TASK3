package com.example.ghost.loginandsignup;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends Activity {
    EditText username,pass;
    TextView sukses,status;
    Button login;
    String l = "login";
    SharedPreferences SP;
    String IS_USER_LOGIN="IsUserLogin";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText)findViewById(R.id.txtEmail);
        pass = (EditText)findViewById(R.id.txtPass);
        login = (Button)findViewById(R.id.btnLogin);


            SP = getSharedPreferences(l, Context.MODE_PRIVATE);



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create();

                Retrofit retrofit = new Retrofit.Builder().baseUrl("http://private-69e82-andro.apiary-mock.com").addConverterFactory(GsonConverterFactory.create(gson)).build();

                LoginAPI user_api = retrofit.create(LoginAPI.class);

                Call<Users> call = user_api.getUsers();
                call.enqueue(new Callback<Users>() {
                    @Override
                    public void onResponse(Response<Users> response, Retrofit retrofit) {
                        //username.getText().toString();
                        //pass.getText().toString();

                          // if (response.isSuccess()) {
                                // request successful (status code 200, 201)
                              // User result = response.body();

                              // Log.e("Connect", String.valueOf(response.body().getId().toString().equals("raju")));
                              // if(response.body().getId().toString().equals("raju")) {

                                 //  sukses.setText("Sukses");

                              // }else{
                                  // sukses.setText("Gagal Login");
                              // }



                              // status.setText("Berhasil");
                            //} else {
                                //request not successful (like 400,401,403 etc)
                                //Handle errors
                             // status.setText("Gagal");
                           //}


                       for (Users.UserItem user : response.body().getUsers()) {

                           // String id = String.valueOf(user.getId()).toString();




                          if ((user.getEmail().toString().equals(username.getText().toString())) && (user.getPassword().toString().equals(pass.getText().toString())) ){
                                //startActivity(new Intent(MainActivity.this, HalamanUtama.class));
                              SharedPreferences.Editor sp_editor= SP.edit();
                              sp_editor.putBoolean(IS_USER_LOGIN,true);
                              sp_editor.putString("username", username.getText().toString());
                              sp_editor.putString("password", pass.getText().toString());
                              sp_editor.commit();

                                Intent i = new Intent (MainActivity.this, HalamanUtama.class);
                              i.putExtra("user",username.getText().toString());
                              i.putExtra("pass",pass.getText().toString());
                              i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                              i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(i);
                              //  sukses.setText("Sukses");
                              // sukses.append(
                                   // System.getProperty("line.separator") +
                                    //"id = " + String.valueOf(user.getId())+
                                            //System.getProperty("line.separator") +

                                             //"Email = " + user.getEmail()+

                                              //System.getProperty("line.separator") +

                                               //"Password = " + user.getPassword()
                              // );
                               //status.setText("Berhasil");

                          // if(user.getEmail()=="raju"){
                              // status.setText("Berhasil");

                          }else{
                              Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                           }


                        // }else{
                             //  status.setText("Gagal");
                               //sukses.append(
                                       //System.getProperty("line.separator") +
                                              // "id = " + String.valueOf(user.getId())+

                                       //System.getProperty("line.separator") +

                                              // "Email = " + user.getEmail() +

                                               //System.getProperty("line.separator") +

                                               //"Password = " + user.getPassword()
                              // );
                         // }



                       }


                    }



                    @Override
                    public void onFailure(Throwable t) {
                        //tv_respond.setText(String.valueOf(t));

                    }
                });








            }
        });


    }



}
