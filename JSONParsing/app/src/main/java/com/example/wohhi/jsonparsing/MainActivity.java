package com.example.wohhi.jsonparsing;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private String LOG_TAG = MainActivity.class.getSimpleName();
    private ProgressDialog progressDialog;
    private ListView listView;

    //URL to get Contacts JSOn
    private static String url = "http://api.androidhive.info/contacts/";
    private ArrayList<HashMap<String, String>> contactList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contactList = new ArrayList<>();
        listView = (ListView) findViewById(R.id.list);

        new GetContacts().execute();

    }

    public class GetContacts extends AsyncTask<Void, Void, Void>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //Showing Progress Dialog
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setMessage("Please Wait...");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            HttpHandler httpHandler = new HttpHandler();

            // Making a request to url and getting response
            String jsonStr = httpHandler.makeServiceCall(url);
            Log.e(LOG_TAG, "Response from URL: " + jsonStr);

            if (jsonStr != null){
                try {
                    JSONObject jsonObject = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    JSONArray contacts = jsonObject.getJSONArray("contacts");

                    //Lopping through all contacts
                    for (int i = 0; i < contacts.length(); i++){
                        JSONObject contact = contacts.getJSONObject(i);

                        String id = contact.getString("id");
                        String name = contact.getString("name");
                        String email = contact.getString("email");
                        String address = contact.getString("address");
                        String gender = contact.getString("gender");


                        //Phone Node is Json Object
                        JSONObject phone = contact.getJSONObject("phone");

                        String mobile = phone.getString("mobile");
                        String home = phone.getString("home");
                        String office = phone.getString("office");

                        // tmp hash map for single contact
                        HashMap<String, String> contactinfo = new HashMap<>();

                        // adding each child node to HashMap key => value
                        contactinfo.put("id", id);
                        contactinfo.put("name", name);
                        contactinfo.put("email", email);
                        contactinfo.put("mobile", mobile);

                        // adding contact to contact list
                        contactList.add(contactinfo);
                    }


                } catch (final JSONException e) {
                    e.printStackTrace();

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json Parsing error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }else{

                Log.e(LOG_TAG, "Couldn't json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check logcat.", Toast.LENGTH_SHORT).show();


                    }
                });
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            //Close the progress Dialog
            if (progressDialog.isShowing())
                progressDialog.dismiss();


            ListAdapter adapter = new SimpleAdapter(
                    MainActivity.this, contactList,
                    R.layout.list_item,
                    new String[]{"name", "email", "mobile"},
                    new int[]{R.id.name, R.id.email, R.id.mobile}
            );

            listView.setAdapter(adapter);
        }
    }
}
