package com.example.ghost.loginandsignup;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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
    String em = "r4ju.riy4nd4@gmail.com";
    String p = "ada123";
    ArrayAdapter<User>adapter=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText)findViewById(R.id.txtEmail);
        pass = (EditText)findViewById(R.id.txtPass);
        login = (Button)findViewById(R.id.btnLogin);
        sukses = (TextView)findViewById(R.id.txtSukses);
        status = (TextView)findViewById(R.id.txtStatus);


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




                          // if (user.getEmail().toString().equals(username.getText().toString()) ){
                                //startActivity(new Intent(MainActivity.this, HalamanUtama.class));
                                //Intent i = new Intent (MainActivity.this, HalamanUtama.class);
                              //  sukses.setText("Sukses");
                               sukses.append(
                                    System.getProperty("line.separator") +
                                    "id = " + String.valueOf(user.getId())+
                                            System.getProperty("line.separator") +

                                             "Email = " + user.getEmail()+

                                              System.getProperty("line.separator") +

                                               "Password = " + user.getPassword()
                               );
                               //status.setText("Berhasil");

                          // if(user.getEmail()=="raju"){
                              // status.setText("Berhasil");

                         // }else{
                             //  status.setText("Gagal");
                          // }


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
