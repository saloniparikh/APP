package com.example.a123.listdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String JSON_URL = "http://sales.arkayapps.com/playquiz/getleveliinfo.php";

    //listview object
    ListView listView;

    List<Demo> DemoList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView)findViewById(R.id.list1);

        DemoList = new ArrayList<>();

        levelinfolist();
    }

    private void levelinfolist() {

        StringRequest stringrequest = new StringRequest(Request.Method.GET, JSON_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jarray = new JSONArray(response);
                            for (int i = 0; i < jarray.length(); i++) {
                                JSONObject heroObject = jarray.getJSONObject(i);

                                //creating a hero object and giving them the values from json object
                                Demo demo = new Demo(heroObject.getString("lelveno"), heroObject.getString("levelname"));

                                //adding the hero to herolist
                                DemoList.add(demo);
                            }
                            ListViewAdapter adapter = new ListViewAdapter(DemoList, getApplicationContext());

                            //adding the adapter to listview
                            listView.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    },
                new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
                    RequestQueue requestQueue = Volley.newRequestQueue(this);

        requestQueue.add(stringrequest);
    }
}
