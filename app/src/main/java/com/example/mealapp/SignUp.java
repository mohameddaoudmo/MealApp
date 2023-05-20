package com.example.mealapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import java.util.Collections;
import java.util.Objects;

public class SignUp extends AppCompatActivity {
    private EditText nameEditText, emailEditText, passwordEditText;
    private Button registerButton;
    private TextView SignInTextView;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    private ImageView googleImageView, facebookImageView;
    private GoogleSignInClient mGoogleSignInClient;
    private static final int RC_SIGN_IN = 1;
    private LoginButton facebookLoginButton;
    private CallbackManager mCallbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        firebaseAuth = FirebaseAuth.getInstance();

        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if (currentUser != null) {
            startActivity(new Intent(this, MainActivity.class));
        }
        nameEditText = findViewById(R.id.nameTxtfield);
        emailEditText = findViewById(R.id.emailTxtfiled);
        passwordEditText = findViewById(R.id.password_et);
        registerButton = findViewById(R.id.sign_up_btn);
        SignInTextView = findViewById(R.id.sign_in_tv);
        progressDialog = new ProgressDialog(this);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(getString(R.string.default_web_client_id)).requestEmail().build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        mCallbackManager = CallbackManager.Factory.create();
        facebookImageView = findViewById(R.id.facebookAuthsignUp);
        facebookLoginButton = findViewById(R.id.facebook_btn);
        facebookLoginButton.setReadPermissions("email", "public_profile");
        facebookLoginButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                handleFacebookAccessToken(loginResult.getAccessToken());
                progressDialog.setMessage("جاري تسجيل الدخول...");
                progressDialog.show();
                startActivity(new Intent(SignUp.this, MainActivity.class));
                Toast.makeText(SignUp.this, "تم تسجيل الدخول بنجاح", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel() {
            }

            @Override
            public void onError(@NonNull FacebookException error) {
            }
        });

        registerButton = findViewById(R.id.sign_up_btn);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();


            }
        });
        SignInTextView = findViewById(R.id.sign_in_tv);
        SignInTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUp.this, SignIn.class));


            }
        });
        googleImageView = findViewById(R.id.googleAuthSignUp);
        googleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, RC_SIGN_IN);
            }
        });
        facebookImageView = findViewById(R.id.facebookAuthsignUp);
        facebookImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                facebookLoginButton.performClick();
                AccessToken accessToken = AccessToken.getCurrentAccessToken();
                boolean isLoggedIn = accessToken != null && !accessToken.isExpired();
                LoginManager.getInstance().logInWithReadPermissions(SignUp.this, Collections.singletonList("public_profile"));
            }
        });


    }

    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if (currentUser != null) {
            startActivity(new Intent(this, MainActivity.class));
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                progressDialog.setMessage("جاري تسجيل الدخول...");
                progressDialog.show();
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException ignored) {
            }
        }
    }

    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener(this, task -> {
            if (task.isSuccessful()) {
                FirebaseUser currentUser = firebaseAuth.getCurrentUser();
                startActivity(new Intent(this, MainActivity.class));
                Toast.makeText(this, "تم تسجيل الدخول بنجاح", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void handleFacebookAccessToken(AccessToken token) {
        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener(this, task -> {
            if (task.isSuccessful()) {
                FirebaseUser currentUser = firebaseAuth.getCurrentUser();
            }
        });
    }

    private void registerUser() {
        String name = nameEditText.getText().toString().trim();
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "من فضلك أدخل الإسم", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "من فضلك أدخل البريد الإلكتروني", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!email.matches(emailPattern)) {
            Toast.makeText(this, "من فضلك أدخل بريد إلكتروني صحيح", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "من فضلك أدخل كلمة السر", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("جاري إنشاء الحساب...");
        progressDialog.show();
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, task -> {
            if (task.isSuccessful()) {
                Toast.makeText(SignUp.this, "تم إنشاء الحساب بنجاح", Toast.LENGTH_LONG).show();
                startActivity(new Intent(this, SignIn.class));
            } else if (password.length() < 6) {
                Toast.makeText(this, "يجب أن تكون كلمة السر أكبر من 6", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            } else {
//                Toast.makeText(SignUpActivity.this, "هذا الحساب مسجل بالفعل أو أن هناك خطأ في الإتصال بالإنترنت",Toast.LENGTH_LONG).show();
                Toast.makeText(SignUp.this, Objects.requireNonNull(task.getException()).getLocalizedMessage(), Toast.LENGTH_LONG).show();
                progressDialog.dismiss();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        moveTaskToBack(true);
        startActivity(new Intent(this, SignIn.class));
    }

}