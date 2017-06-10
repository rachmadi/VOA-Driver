package id.ac.mdp.voadriver.activities;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import id.ac.mdp.voadriver.R;
import id.ac.mdp.voadriver.utils.AppController;
import id.ac.mdp.voadriver.utils.CustomRequest;
import id.ac.mdp.voadriver.utils.Utilities;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "ActivityLogin";
    private static final int REQUEST_SIGNUP = 0;

    private Button btnLogin,btnViewPassword;
    private EditText etUsername,etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = (Button)findViewById(R.id.btnLogin);
        etUsername = (EditText)findViewById(R.id.et_username);
        etPassword = (EditText)findViewById(R.id.et_password);
        btnViewPassword = (Button)findViewById(R.id.btn_view_password);
        btnViewPassword.setVisibility(View.GONE);

        btnViewPassword.setOnTouchListener((new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction()== MotionEvent.ACTION_UP){
                    // etpassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }else if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){
                    // etpassword.setInputType(InputType.TYPE_CLASS_TEXT);
                    etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                return true;
            }
        }));

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utilities.hideKeyboard(LoginActivity.this);
                //String username = etUsername.getText().toString();
                //String password = etPassword.getText().toString();
                //login(username,password);
                final Utilities util=new Utilities();
                util.setUser(LoginActivity.this
                        ,"driver@gmail.com"
                        ,"dom1"
                        ,"Agus Muliawan"
                        ,"Driver"
                        ,"string 1"
                        ,"08127345921"
                        ,"1234"
                        ,"0"
                        ,"0");
                startActivity(new Intent(LoginActivity.this, InputKilometerActivity.class));
                finish();

                /*AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(LoginActivity.this);
                alertDialogBuilder.setMessage("Ingin memasukkan jarak tempuh hari ini?");
                alertDialogBuilder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(LoginActivity.this, InputKilometerActivity.class));
                        finish();
                    }
                });

                alertDialogBuilder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final Utilities util=new Utilities();
                        util.setUser(LoginActivity.this
                                ,"driver@gmail.com"
                                ,"dom1"
                                ,"Agus Muliawan"
                                ,"Driver"
                                ,"string 1"
                                ,"08127345921"
                                ,"1234"
                                ,"0"
                                ,"0");
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finish();
                    }
                });
                alertDialogBuilder.show();*/


                /*startActivity(new Intent(LoginActivity.this,MainActivity.class));
                finish();*/
            }
        });

        etPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(start>0 || count>0){
                    btnViewPassword.setVisibility(View.VISIBLE);
                }else{
                    btnViewPassword.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void login(final String userId, final String password) {
        Log.d(TAG, "Login");

        if (!validate()) {
            onLoginFailed("False Credentials");
            return;
        }

        btnLogin.setEnabled(false);

        ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.Theme_AppCompat_DayNight_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        // GET User Data
        final Utilities util=new Utilities();
        //String url=util.getConnectionUrl("viewUserByPosition");
        String url=util.getConnectionUrl("viewUserWithPassword");

        HashMap<String,String> param=new HashMap<>();
        param.put("user_id",userId);
        param.put("password",password);
        //param.put("position","Driver");
        CustomRequest customRequest=new CustomRequest(Request.Method.POST, url, param, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                btnLogin.setEnabled(true);
                System.out.print(response);
                int success;
                try {
                    success=response.getInt("success");
                    if(success==1){
                        JSONArray jsonUser = response.getJSONArray("data");
                        try{
                            for(int i=0;i<jsonUser.length();i++){
                                JSONObject jsonobject= (JSONObject) jsonUser.get(i);
                                if(jsonobject.optString("position").equalsIgnoreCase("driver")){
                                    util.setUser(LoginActivity.this
                                            ,jsonobject.optString("user_id")
                                            ,jsonobject.optString("domain")
                                            ,jsonobject.optString("name")
                                            ,jsonobject.optString("position")
                                            ,jsonobject.optString("photo")
                                            ,jsonobject.optString("phone_number")
                                            ,jsonobject.optString("pin")
                                            ,jsonobject.optString("current_quota")
                                            ,jsonobject.optString("maximum_quota"));
                                }else{
                                    Utilities.showAsToast(LoginActivity.this, "Please Login as Driver", Toast.LENGTH_SHORT);
                                    onLoginSuccess();
                                }
                            }

                        }catch(JSONException e){
                            Log.e("log_tag", "Error parsing data "+e.toString());
                        }
                        if(util.getUser(LoginActivity.this)!=null){
                            Utilities.showAsToast(LoginActivity.this, "Connect Success", Toast.LENGTH_SHORT);
                            onLoginSuccess();
                        }
                    }else{
                        onLoginFailed("Connection Failure");
                    }
                } catch (JSONException e) {
                    onLoginFailed("Json error: "+e);
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                btnLogin.setEnabled(true);
                onLoginFailed("volley error: "+error.getMessage());
            }
        });
        AppController.getInstance().addToRequestQueue(customRequest);
        progressDialog.dismiss();
    }

    public void onLoginSuccess() {
        startActivity(new Intent(LoginActivity.this,MainActivity.class));
        finish();
    }

    public void onLoginFailed(String message) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(LoginActivity.this);
        alertDialog.setTitle("Notice")
                .setMessage("Sign in failed.\n"+message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                })
                .setCancelable(false)
                .show();
    }

    public boolean validate() {
        boolean valid = true;

        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();

        //if (username.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(username).matches()) {
        if (username.isEmpty()) {
            etUsername.setError("enter a valid user id");
            valid = false;
        } else {
            etUsername.setError(null);
        }

        if (password.isEmpty()) {
            etPassword.setError("enter password");
            valid = false;
        } else {
            etPassword.setError(null);
        }

        return valid;
    }
}
