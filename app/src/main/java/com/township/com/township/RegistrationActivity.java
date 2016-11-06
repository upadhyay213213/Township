package com.township.com.township;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import database.SqliteHelper;
import model.User;
import util.UserUtil;

public class RegistrationActivity extends AppCompatActivity{
    private EditText edtFullName;
    private EditText edtUserName;
    private EditText edtPassword;
    private EditText edtPhoneNumber;
    private EditText edtEmailId;
    private EditText edtBlock;
    private EditText edtFlatNumber;
    private String email;
    private String password;
    private String fullName;
    private String userName;
    private String phoneNumber;
    private String block;
    private String flatNumber;
    private View mLoginFormView;
    private View mProgressView;
    private userRegistrationTask mUserRegistrationTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        ActionBar actionBar = getSupportActionBar();

        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#466b8c")));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initViews();

    }

    private void initViews() {
        mLoginFormView = findViewById(R.id.registration_form);
        mProgressView = findViewById(R.id.registration_progress);
        edtEmailId= (EditText) findViewById(R.id.email);
        edtUserName= (EditText) findViewById(R.id.edtUserName);
        edtFullName= (EditText) findViewById(R.id.edtfullName);
        edtFlatNumber= (EditText) findViewById(R.id.edtFlatNumber);
        edtBlock= (EditText) findViewById(R.id.edtBlock);
        edtPassword= (EditText) findViewById(R.id.password);
        edtPhoneNumber= (EditText) findViewById(R.id.edtPhoneNumber);

        findViewById(R.id.registration_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptRegistration();
            }
        });
    }

    private boolean isEmailValid(String email) {
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 4;
    }

    private void attemptRegistration() {

        // Reset errors.
        edtEmailId.setError(null);
        edtPassword.setError(null);
        edtUserName.setError(null);
        edtFlatNumber.setError(null);
        edtFullName.setError(null);
        edtBlock.setError(null);
        edtPhoneNumber.setError(null);

        // Store values at the time of the login attempt.
        email = edtEmailId.getText().toString();
        password = edtPassword.getText().toString();
        fullName = edtFullName.getText().toString();
        userName = edtUserName.getText().toString();
        phoneNumber = edtPhoneNumber.getText().toString();
        block = edtBlock.getText().toString();
        flatNumber = edtFlatNumber.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            edtPassword.setError(getString(R.string.error_invalid_password));
            focusView = edtPassword;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            edtEmailId.setError(getString(R.string.error_field_required));
            focusView = edtEmailId;
            cancel = true;
        } else if (!isEmailValid(email)) {
            edtEmailId.setError(getString(R.string.error_invalid_email_1));
            focusView = edtEmailId;
            cancel = true;
        }
        if (TextUtils.isEmpty(userName)) {
            edtUserName.setError(getString(R.string.error_field_required));
            focusView = edtUserName;
            cancel = true;
        } // Check for a valid email address.
        if (TextUtils.isEmpty(fullName)) {
            edtFullName.setError(getString(R.string.error_field_required));
            focusView = edtFullName;
            cancel = true;
        } // Check for a valid email address.
        if (TextUtils.isEmpty(phoneNumber)) {
            edtPhoneNumber.setError(getString(R.string.error_field_required));
            focusView = edtPhoneNumber;
            cancel = true;
        }
        if (TextUtils.isEmpty(block)) {
            edtBlock.setError(getString(R.string.error_field_required));
            focusView = edtBlock;
            cancel = true;
        } // Check for a valid email address.
        if (TextUtils.isEmpty(flatNumber)) {
            edtFlatNumber.setError(getString(R.string.error_field_required));
            focusView = edtFlatNumber;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            User user=new User(userName,fullName,email,password,phoneNumber,block,flatNumber);
            //showProgress(true);
            mUserRegistrationTask = new userRegistrationTask();
            mUserRegistrationTask.execute(user);
        }
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    class userRegistrationTask extends AsyncTask<User, Void, Boolean> {
        ProgressDialog dialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog=new ProgressDialog(RegistrationActivity.this);
            dialog.setMessage("Please Wait");
            dialog.show();
        }

        @Override
        protected Boolean doInBackground(User... params) {
            SqliteHelper.init(RegistrationActivity.this);
            return UserUtil.addStudentSuccess(params[0]);

        }

        @Override
        protected void onPostExecute(Boolean addSuccess) {
            super.onPostExecute(addSuccess);
            if(addSuccess){
                Toast.makeText(RegistrationActivity.this,"User is registered successfully",Toast.LENGTH_SHORT).show();
                RegistrationActivity.this.finish();

            }else{
                dialog.dismiss();
                //showProgress(false);
                Toast.makeText(RegistrationActivity.this,"User is already registerd",Toast.LENGTH_LONG).show();
            }
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; go home
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
