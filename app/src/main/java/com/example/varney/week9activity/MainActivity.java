package com.example.varney.week9activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private EditText email;
    private EditText password;
    private JSONObject body = new JSONObject();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void trylogin(View view){
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

        try {
            body.put("email", email.getText().toString());
            body.put("password", password.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        final RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

        StringRequest request = new StringRequest(Request.Method.POST, "http://jiaxiaw1@andromeda-60.ics.uci.edu:6477/api/idm/login",
                resposne ->{
                    if(resposne != null && !resposne.equals("")){
                        Intent intent = new Intent(this, AfterLoginActivity.class);
                        startActivity(intent);
                        finish();
                    }else{
                        Toast.makeText(getApplicationContext(), "Login Fail", Toast.LENGTH_SHORT).show();
                    }
                }, error ->{
                    Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();

        }){
            @Override
            public byte[] getBody() throws AuthFailureError{
                return body.toString().getBytes();
            }

            @Override
            public String getBodyContentType() {
                return "application/json";
            }
        };

        queue.add(request);


//        Intent intent = new Intent(this, AfterLoginActivity.class);
//        startActivity(intent);
//        finish();

    }
}
