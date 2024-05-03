package com.example.apiintegration;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.button);
        tv = findViewById(R.id.text);
        btn.setOnClickListener(this);
    }

    public void getData(){
        String url = "https://catfact.ninja/fact";

        RequestQueue reqQue = Volley.newRequestQueue(this);
        StringRequest strReq = new StringRequest(Request.Method.GET, url,
        res->{

            tv.setText(res.toString());
        },
        err->            Toast.makeText(this, "Error", Toast.LENGTH_LONG).show());
        reqQue.add(strReq);

    }

    @Override
    public void onClick(View view) {
        getData();
    }
}