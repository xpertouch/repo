package corp.burenz.expertouch.activities;import android.content.Intent;import android.content.SharedPreferences;import android.graphics.Color;import android.media.MediaPlayer;import android.net.Uri;import android.os.AsyncTask;import android.os.Bundle;import android.os.Handler;import android.support.design.widget.Snackbar;import android.support.v7.app.AppCompatActivity;import android.util.Log;import android.view.View;import android.view.animation.AnimationUtils;import android.view.inputmethod.InputMethodManager;import android.widget.CompoundButton;import android.widget.EditText;import android.widget.ImageButton;import android.widget.LinearLayout;import android.widget.Spinner;import android.widget.Switch;import android.widget.TextView;import android.widget.Toast;import android.widget.ViewFlipper;import org.apache.http.HttpEntity;import org.apache.http.HttpResponse;import org.apache.http.NameValuePair;import org.apache.http.client.ClientProtocolException;import org.apache.http.client.HttpClient;import org.apache.http.client.entity.UrlEncodedFormEntity;import org.apache.http.client.methods.HttpPost;import org.apache.http.impl.client.DefaultHttpClient;import org.apache.http.message.BasicNameValuePair;import java.io.BufferedReader;import java.io.IOException;import java.io.InputStreamReader;import java.io.UnsupportedEncodingException;import java.util.ArrayList;import java.util.List;import corp.burenz.expertouch.R;public class SettingsActivity extends AppCompatActivity implements View.OnClickListener{    SharedPreferences updateInfo;    SharedPreferences.Editor updateEditor;    InputMethodManager im;    ImageButton switch3,imageButton3;    SharedPreferences narrate;    String NARRATION = "narrate";    ArrayList<String> statesArray = new ArrayList<String>();    String UPDATE_INFO = "updateInfo";    String updateUsername = "/settings/update_username.php";    String updatePassword = "/settings/update_password.php";    String updateState = "/settings/update_state.php";    String hit;    Boolean SUBMIT_FLAG = false;    ViewFlipper settingsFlipepr;    String updateFeild;    String LOCAL_APP_DATA = "userInformation";    SharedPreferences userData;    SharedPreferences.Editor editor;    String userName,userPassword,currentState,userEmail;    ImageButton editUserName,changePassword,changeEmail,editCurrentState;    Switch updateStatus,switchNarrator;    TextView  cUserNameV,cEmailV,cStateV;    TextView cancelChangesV,saveChangesV,myDiaTitle,myDiaSubTitle;    EditText myDiaEditText,newPasswordET;    Spinner modifyStateSpinner;    ViewFlipper passwordFlipper;    LinearLayout dialogLayout,dialogView,settingsLayout;    ViewFlipper flipTheState,dialogFlipper;    LinearLayout restRoom;    ViewFlipper settingsProgressFlipper;    @Override    protected void onCreate(Bundle savedInstanceState) {        super.onCreate(savedInstanceState);        setContentView(R.layout.activity_settings);        userData = getSharedPreferences(LOCAL_APP_DATA,0);        editor = userData.edit();        im = (InputMethodManager)SettingsActivity.this.getSystemService(INPUT_METHOD_SERVICE);        switch3 = (ImageButton) findViewById(R.id.switch3);        imageButton3 = (ImageButton) findViewById(R.id.imageButton3);        switch3.setOnClickListener(new View.OnClickListener() {            @Override            public void onClick(View v) {                String url = "https://www.facebook.com/1cliclaway/";                Intent i = new Intent(Intent.ACTION_VIEW);                i.setData(Uri.parse(url));                try{                    startActivity(i);                }catch (Exception e){                    Toast.makeText(SettingsActivity.this, "Please Connect To networks and try again", Toast.LENGTH_SHORT).show();                }            }        });        imageButton3.setOnClickListener(new View.OnClickListener() {            @Override            public void onClick(View v) {                String url = "https://twitter.com/1clickaway_";                Intent i = new Intent(Intent.ACTION_VIEW);                i.setData(Uri.parse(url));                try{                    startActivity(i);                }catch (Exception e){                    Toast.makeText(SettingsActivity.this, "Please Connect To networks and try again", Toast.LENGTH_SHORT).show();                }            }        });        statesArray.add("Select Your State");        statesArray.add("Andhra Pradesh");        statesArray.add("Arunachal Pradesh");        statesArray.add("Assam");        statesArray.add("Bihar");        statesArray.add("Chandigarh");        statesArray.add("Chhattisgarh");        statesArray.add("Dadra and Nagar Haveli");        statesArray.add("Daman and Diu");        statesArray.add("Delhi");        statesArray.add("Goa");        statesArray.add("Gujarat");        statesArray.add("Haryana");        statesArray.add("Himachal Pradesh");        statesArray.add("Jammu and Kashmir");        statesArray.add("Jharkhand");        statesArray.add("Karnataka");        statesArray.add("Kerala");        statesArray.add("Lakshadweep");        statesArray.add("Madhya Pradesh");        statesArray.add("Maharashtra");        statesArray.add("Manipur");        statesArray.add("Meghalaya");        statesArray.add("Mizoram");        statesArray.add("Nagaland");        statesArray.add("Odisha");        statesArray.add("Puducherry");        statesArray.add("Punjab");        statesArray.add("Rajasthan");        statesArray.add("Sikkim");        statesArray.add("Tamil Nadu");        statesArray.add("Telengana");        statesArray.add("Tripura");        statesArray.add("Uttar Pradesh");        statesArray.add("Uttarakhand");        statesArray.add("West Bengal");        setViews();        updateInfo = getSharedPreferences(UPDATE_INFO,0);        updateEditor = updateInfo.edit();        recall();        if (!updateInfo.getBoolean("check",false)){            updateStatus.setChecked(false);        }else{            updateStatus.setChecked(true);        }        updateStatus.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {            @Override            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {                if (isChecked){                    // set update  = true                    updateEditor.putBoolean("check",true);                    updateEditor.apply();                }else {                    // set update  = false                    updateEditor.putBoolean("check",false);                    updateEditor.apply();                }            }        });        switchNarrator.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {            @Override            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {                narrate = getSharedPreferences(NARRATION,0);                SharedPreferences.Editor editor;                editor = narrate.edit();                if (isChecked){                    // set update  = true                    editor.putBoolean("narrate",true);                    editor.apply();                    MediaPlayer ourSplasSound =  MediaPlayer.create(SettingsActivity.this,R.raw.enabled);                    ourSplasSound.start();                }else {                    // set update  = false                    editor.putBoolean("narrate",false);                    editor.apply();                }            }        });    }    void recall(){    userData = getSharedPreferences(LOCAL_APP_DATA,0);        userName = userData.getString("userName","This is your Screen Name");        userEmail = userData.getString("userEmail","example@email.com");        currentState = userData.getString("userState","Your State appears here");        userPassword = userData.getString("userPassword","Your account is Password Protected");        cUserNameV.setText(userName);        cEmailV.setText(userEmail);        cStateV.setText(currentState);    }    public void setViews(){        userData = getSharedPreferences(LOCAL_APP_DATA,0);        editUserName = (ImageButton)findViewById(R.id.editUsernameIB);        changePassword = (ImageButton)findViewById(R.id.changePasswordIB);        changeEmail  = (ImageButton) findViewById(R.id.changeEmailIB);        settingsFlipepr = (ViewFlipper) findViewById(R.id.settingsFlipper);        updateStatus = (Switch) findViewById(R.id.updateStatus);        switchNarrator = (Switch) findViewById(R.id.switchNarrator);        cUserNameV = (TextView) findViewById(R.id.cUsername);        cEmailV = (TextView) findViewById(R.id.cEmail);        cStateV = (TextView)findViewById(R.id.cStateV);        cancelChangesV = (TextView) findViewById(R.id.cancelChanges);        saveChangesV = (TextView) findViewById(R.id.saveChanges);        myDiaTitle = (TextView) findViewById(R.id.myDiaTitle);        myDiaSubTitle = (TextView)findViewById(R.id.myDiaSubtitle);        modifyStateSpinner = (Spinner) findViewById(R.id.modifyStateSpinner);        myDiaEditText = (EditText)findViewById(R.id.myDiaEditText);        newPasswordET = (EditText) findViewById(R.id.newPasswordET);        passwordFlipper = (ViewFlipper) findViewById(R.id.passwordFlipper);        dialogLayout = (LinearLayout) findViewById(R.id.dialogLayout);        dialogView = (LinearLayout) findViewById(R.id.dialogView);        settingsProgressFlipper = (ViewFlipper)  findViewById(R.id.settingsProgressFlipper);        restRoom = (LinearLayout) findViewById(R.id.restRoom);        settingsLayout = (LinearLayout) findViewById(R.id.settingsLayout);        editCurrentState = (ImageButton) findViewById(R.id.changeStateIB);        flipTheState = (ViewFlipper) findViewById(R.id.flipTheState);        dialogFlipper = (ViewFlipper) findViewById(R.id.dialogFlipper);        narrate = getSharedPreferences(NARRATION,0);        if (narrate.getBoolean("narrate",true)){            switchNarrator.setChecked(true);        }else {            switchNarrator.setChecked(false);        }        editUserName.setOnClickListener(this);        changePassword.setOnClickListener(this);        changeEmail.setOnClickListener(this);        editCurrentState.setOnClickListener(this);        saveChangesV.setOnClickListener(this);        cancelChangesV.setOnClickListener(this);    }    @Override    public void onClick(View v) {        switch (v.getId()){            case R.id.editUsernameIB:                if (!userData.getBoolean("VERIFIED",true)){                    Snackbar.make(v,"Please Verify Your Account First",Snackbar.LENGTH_LONG).show();                    break;                }                updateFeild = "userName";                hit = updateUsername;                dialogLayout.setVisibility(View.VISIBLE);                dialogView.setVisibility(View.VISIBLE);                saveChangesV.setText("SAVE CHANGES");                dialogFlipper.showNext();                dialogLayout.setBackgroundColor(Color.argb(128,0,0,0));                myDiaTitle.setText("Enter Your Username");                myDiaSubTitle.setText("Your Current Username");                myDiaEditText.setText(userName);                myDiaEditText.setHint("Enter New Username");                editUserName.setEnabled(false);                changeEmail.setEnabled(false);                changePassword.setEnabled(false);                break;            case R.id.changePasswordIB:                userData = getSharedPreferences(LOCAL_APP_DATA,0);                if (!userData.getBoolean("VERIFIED",true)){                    Snackbar.make(v,"Please Verify Your Account First",Snackbar.LENGTH_LONG).show();                    break;                }                updateFeild = "userPassword";                hit = updatePassword;                saveChangesV.setText("NEXT");                dialogLayout.setVisibility(View.VISIBLE);                dialogView.setVisibility(View.VISIBLE);                dialogFlipper.showNext();                dialogLayout.setBackgroundColor(Color.argb(128,0,0,0));                myDiaTitle.setText("Modify Password");                myDiaEditText.setText("");                myDiaEditText.setHint("Enter Old Password");                myDiaSubTitle.setText("Password length must not be less than  8 characters");                editUserName.setEnabled(false);                changeEmail.setEnabled(false);                changePassword.setEnabled(false);                break;            case R.id.changeEmailIB:                userData = getSharedPreferences(LOCAL_APP_DATA,0);                if (userData.getBoolean("VERIFIED",true)){                    Snackbar.make(v,"Phone Number can only be modified before activation Only",Snackbar.LENGTH_LONG).show();                    break;                }                saveChangesV.setText("SAVE CHANGES");                dialogLayout.setVisibility(View.VISIBLE);                dialogView.setVisibility(View.VISIBLE);                dialogFlipper.showNext();                dialogLayout.setBackgroundColor(Color.argb(128,0,0,0));                myDiaTitle.setText("Enter New Phone Number");                myDiaSubTitle.setText("This Phone Number is used for Verification Purposes");                myDiaEditText.setText(userEmail);                editUserName.setEnabled(false);                changeEmail.setEnabled(false);                changePassword.setEnabled(false);                break;            case R.id.changeStateIB:                if (!userData.getBoolean("VERIFIED",true)){                    Snackbar.make(v,"Please Verify Your Account First",Snackbar.LENGTH_LONG).show();                    break;                }                updateFeild = "userState";                hit = updateState;                editCurrentState.setEnabled(false);                modifyStateSpinner.setVisibility(View.VISIBLE);                modifyStateSpinner.setSelection(statesArray.indexOf(currentState));                passwordFlipper.setVisibility(View.GONE);                saveChangesV.setText("UPDATE STATE");                dialogLayout.setVisibility(View.VISIBLE);                dialogView.setVisibility(View.VISIBLE);                dialogFlipper.showNext();                dialogLayout.setBackgroundColor(Color.argb(128,0,0,0));                myDiaTitle.setText("Select State");                myDiaSubTitle.setText("Feeds are filtered by your Current Location");                editUserName.setEnabled(false);                changeEmail.setEnabled(false);                changePassword.setEnabled(false);                break;            case R.id.saveChanges:                if (saveChangesV.getText().toString().equals("UPDATE STATE")) {                    String updateUsername = "/settings/update_username.php";                    String updatePassword = "/settings/update_password.php";                    String updateState = "/settings/update_state.php";                    if(modifyStateSpinner.getSelectedItem().toString().equals("Select Your State")){                        Toast.makeText(SettingsActivity.this, "Please Select a valid State", Toast.LENGTH_SHORT).show();                    }else {                        new UpdateLocals().execute("userState",modifyStateSpinner.getSelectedItem().toString(),"/settings/update_state.php");                        im.hideSoftInputFromWindow(myDiaEditText.getWindowToken(),0);                    }                    break;                }                if (saveChangesV.getText().toString().equals("SUBMIT PASSWORD")){                    if (newPasswordET.getText().toString().length() > 7){                        new UpdateLocals().execute("newPassword",newPasswordET.getText().toString(),"/settings/update_password.php");                        im.hideSoftInputFromWindow(myDiaEditText.getWindowToken(),0);                    }else {                        Toast.makeText(SettingsActivity.this, "Password length must be at least 8 Characters", Toast.LENGTH_SHORT).show();                    }                    break;                }                else if (myDiaTitle.getText().toString().equals("Enter Your Username")){                    if (myDiaEditText.getText().toString().length() < 4){                        Toast.makeText(SettingsActivity.this, "Username must be at least 4 Characters Long", Toast.LENGTH_SHORT).show();                    }else {                        new UpdateLocals().execute("userName",myDiaEditText.getText().toString(),"/settings/update_username.php");                        im.hideSoftInputFromWindow(myDiaEditText.getWindowToken(),0);                    }                }else if(myDiaTitle.getText().toString().equals("Modify Password")){                    if(myDiaEditText.getText().toString().equals(userData.getString("userPassword","9789").toString())){                        saveChangesV.setText("SUBMIT PASSWORD");                        passwordFlipper.showNext();                    }else{                        Snackbar.make(v,"Password didn't Match",Snackbar.LENGTH_LONG).show();                    }                }else if(myDiaTitle.getText().toString().equals("Enter New Phone Number")){                    if (myDiaEditText.getText().toString().length() == 10){                        if (myDiaEditText.getText().toString().contains(" ")){                            Toast.makeText(SettingsActivity.this, "Phone Number must not contain any spaces", Toast.LENGTH_SHORT).show();                        }else {                            new CheckAvailability().execute();                        }                    }else {                        Toast.makeText(SettingsActivity.this, "Please enter a valid Phone Number address", Toast.LENGTH_SHORT).show();                    }                }else if(myDiaTitle.getText().toString().equals("Select State")){                    modifyStateSpinner.setVisibility(View.GONE);                    passwordFlipper.setVisibility(View.VISIBLE);                    dialogLayout.setBackgroundColor(Color.TRANSPARENT);                    restRoom.setBackgroundColor(Color.TRANSPARENT);                    dialogFlipper.showPrevious();                    editUserName.setEnabled(true);                    changeEmail.setEnabled(true);                    changePassword.setEnabled(true);                    im.hideSoftInputFromWindow(myDiaEditText.getWindowToken(),0);                }                recall();                break;            case R.id.cancelChanges:                if (myDiaTitle.getText().toString().equals("Select State")){                    modifyStateSpinner.setVisibility(View.GONE);                    passwordFlipper.setVisibility(View.VISIBLE);                }else if (myDiaTitle.getText().toString().equals("Modify Password")){                    if (saveChangesV.getText().toString().equals("SUBMIT PASSWORD")){                        passwordFlipper.showPrevious();                    }                }                dialogLayout.setBackgroundColor(Color.TRANSPARENT);                restRoom.setBackgroundColor(Color.TRANSPARENT);                dialogFlipper.showPrevious();                editCurrentState.setEnabled(true);                editUserName.setEnabled(true);                changeEmail.setEnabled(true);                changePassword.setEnabled(true);                im.hideSoftInputFromWindow(myDiaEditText.getWindowToken(),0);                break;        }    }    class UpdateLocals extends AsyncTask<String,String,String>{        StringBuilder builder = new StringBuilder();        List<NameValuePair> nameValuePairs  = new ArrayList<NameValuePair>();        BufferedReader bufferedReader;        @Override        protected void onPreExecute() {            super.onPreExecute();            settingsProgressFlipper.showNext();        }        @Override        protected String doInBackground(String... params) {            userData = getSharedPreferences(LOCAL_APP_DATA,0);            nameValuePairs.add(new BasicNameValuePair("userPhone",userEmail));            nameValuePairs.add(new BasicNameValuePair(params[0],params[1]));            Log.e("ALLIWANT","userEmail : "+userEmail);            Log.e("ALLIWANT","feild : "+params[0]);            Log.e("ALLIWANT","value : "+params[1]);            Log.e("ALLIWANT","hit : "+getString(R.string.host)+""+params[2]);            try{                try{                    HttpClient httpClient = new DefaultHttpClient();                    HttpPost httpPost = new HttpPost(getString(R.string.host)+""+params[2]);                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));                    HttpResponse httpResponse = (HttpResponse) httpClient.execute(httpPost);                    HttpEntity httpEntity = (HttpEntity)httpResponse.getEntity();                    bufferedReader = new BufferedReader(new InputStreamReader(httpEntity.getContent()));                    String str = "";                    while( (str = bufferedReader.readLine()) != null ){                        builder.append(str);                    }                } catch (UnsupportedEncodingException e) {                    e.printStackTrace();                } catch (ClientProtocolException e) {                    e.printStackTrace();                } catch (IOException e) {                    e.printStackTrace();                } catch (Exception e){                    e.printStackTrace();                }                return builder.toString();            } catch (Exception e){                e.printStackTrace();    //            Toast.makeText(SettingsActivity.this, "Exception here", Toast.LENGTH_SHORT).show();            }            return builder.toString();        }        @Override        protected void onPostExecute(String s) {            super.onPostExecute(s);        //    Toast.makeText(SettingsActivity.this, ""+s, Toast.LENGTH_SHORT).show();            editor = userData.edit();            if (s.contains("1")){                dialogLayout.setBackgroundColor(Color.TRANSPARENT);                restRoom.setBackgroundColor(Color.TRANSPARENT);                dialogFlipper.showPrevious();                editUserName.setEnabled(true);                changeEmail.setEnabled(true);                changePassword.setEnabled(true);                if (saveChangesV.getText().toString().equals("SUBMIT PASSWORD")){                    editor.putString(updateFeild,newPasswordET.getText().toString());                    passwordFlipper.showNext();                }else if (saveChangesV.getText().toString().equals("UPDATE STATE")){                    editor.putString(updateFeild,modifyStateSpinner.getSelectedItem().toString());                    modifyStateSpinner.setVisibility(View.GONE);                    editCurrentState.setEnabled(true);                    passwordFlipper.setVisibility(View.VISIBLE);                }else{                    editor.putString(updateFeild,myDiaEditText.getText().toString());                }                editor.commit();            }else {                new Handler().postDelayed(new Runnable() {                    @Override                    public void run() {                        Toast.makeText(SettingsActivity.this, "Network Issues, Please try again Later ", Toast.LENGTH_SHORT).show();                    }                },100);            }            settingsProgressFlipper.showNext();            recall();        }    }    class CheckAvailability extends AsyncTask< String, String, String>{        StringBuilder builder = new StringBuilder();        BufferedReader bufferedReader;        String USER_EMAIL = myDiaEditText.getText().toString();        List<NameValuePair> nameValuePairs = new ArrayList<>();        @Override        protected String doInBackground(String... params) {            nameValuePairs.add(new BasicNameValuePair("userPhone",USER_EMAIL));            try {                HttpClient httpClient = new DefaultHttpClient();                HttpPost httpPost =  new HttpPost(getString(R.string.host)+"/registrations/generate_random.php");                httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));                HttpResponse httpResponse = (HttpResponse) httpClient.execute(httpPost);                HttpEntity httpEntity = httpResponse.getEntity();                bufferedReader = new BufferedReader(new InputStreamReader(httpEntity.getContent()));                String str = "";                while ( (str = bufferedReader.readLine()) != null ){                    builder.append(str);                }            } catch (ClientProtocolException e) {                e.printStackTrace();            } catch (IOException e) {                e.printStackTrace();            } catch (Exception e){            }            return builder.toString();        }        @Override        protected void onPreExecute() {            super.onPreExecute();            // flip the thing here for progress            settingsProgressFlipper.showNext();        }        @Override        protected void onPostExecute(String s) {            super.onPostExecute(s);            if (s.equals("1")){                Toast.makeText(SettingsActivity.this, "This Phone Number is already Registered With Us", Toast.LENGTH_SHORT).show();            }else if (s.length() == 4){                Toast.makeText(SettingsActivity.this, "New Verification Code was sent To your New Number", Toast.LENGTH_SHORT).show();                editor = userData.edit();                editor.putString("OTP",s);                editor.apply();                dialogLayout.setBackgroundColor(Color.TRANSPARENT);                restRoom.setBackgroundColor(Color.TRANSPARENT);                dialogFlipper.showPrevious();                editUserName.setEnabled(true);                changeEmail.setEnabled(true);                changePassword.setEnabled(true);                userData = getSharedPreferences(LOCAL_APP_DATA,0);                editor = userData.edit();                editor.putString("userEmail",myDiaEditText.getText().toString());                editor.apply();                im.hideSoftInputFromWindow(myDiaEditText.getWindowToken(),0);                new Handler().postDelayed(new Runnable() {                    @Override                    public void run() {                        finish();                    }                },1000);                cEmailV.setText(USER_EMAIL);            }            else if (s.equals("die")) {                Toast.makeText(SettingsActivity.this, "Couldn't Connect to Our Server", Toast.LENGTH_SHORT).show();            }else {                Toast.makeText(SettingsActivity.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();            }            settingsProgressFlipper.showNext();        }    }    public void finishThis(View v){        finish();    }}