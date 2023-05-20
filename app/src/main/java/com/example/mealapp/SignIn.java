package com.example.mealapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.identity.SignInCredential;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class SignIn extends AppCompatActivity {
    private static final int REQ_ONE_TAP = 2 ;// Can be any integer unique to the Activity.
    private boolean showOneTapUI = true;
    private static final int RC_SIGN_IN = 1;

    public static final String TAG="here";
    private FirebaseAuth firebaseAuth;
    private Button signInButton;
    private EditText emailEditText, passwordEditText;
    private TextView signupTextView, forgetPasswordTextView;
    private ProgressDialog progressDialog, loadingBar;
    private ImageView googleImageView, facebookImageView;
    private GoogleSignInClient mGoogleSignInClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        firebaseAuth = FirebaseAuth.getInstance();

        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if (currentUser != null) {
            startActivity(new Intent(this, MainActivity.class));
        }
        signInButton = findViewById(R.id.signinButton);
        emailEditText = findViewById(R.id.editTextTextEmailAddress);
        passwordEditText = findViewById(R.id.editTextTextPassword);
        signupTextView = findViewById(R.id.createnewAccout);
        forgetPasswordTextView = findViewById(R.id.forgetpassword);
        progressDialog = new ProgressDialog(this);
        googleImageView = findViewById(R.id.googleAuth);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin(emailEditText.getText().toString().trim(),passwordEditText.getText().toString().trim());

            }
        });
        forgetPasswordTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRecoverPasswordDialog();

            }
        });
        googleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, RC_SIGN_IN);

            }
        });
        signupTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignIn.this, SignUp.class));

            }
        });


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                progressDialog.setMessage("Signing in...");
                progressDialog.show();
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException ignored) { }
        }
    }
    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener(this, task -> {
            if (task.isSuccessful()) {
                FirebaseUser currentUser = firebaseAuth.getCurrentUser();
                startActivity(new Intent(this, MainActivity.class));
                Toast.makeText(this, "You have successfully logged in", Toast.LENGTH_SHORT).show();
            }
        });
    }



    private void userLogin(String email,String password) {

        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!email.matches(emailPattern)) {
            Toast.makeText(this, "Please enter a valid email", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please enter your password", Toast.LENGTH_SHORT).show();
            return;
        }
        progressDialog.setMessage("Signing in...");
        progressDialog.show();
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, task -> {
            if (task.isSuccessful()) {
                startActivity(new Intent(this, MainActivity.class));
                Toast.makeText(SignIn.this, "Logged in successfully", Toast.LENGTH_SHORT).show();
            } else {
//                Toast.makeText(LoginActivity.this, "Wrong email or password, or an error with the Internet connection", Toast.LENGTH_SHORT).show();
                Toast.makeText(SignIn.this, Objects.requireNonNull(task.getException())
                        .getLocalizedMessage(), Toast.LENGTH_LONG).show();
                progressDialog.dismiss();
            }
        });
    }
    private void showRecoverPasswordDialog() {
        LinearLayout linearLayout = new LinearLayout(this);
        final EditText emailEditText = new EditText(this);
        emailEditText.setHint("E-mail");
        emailEditText.setMinEms(10);
        emailEditText.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        linearLayout.addView(emailEditText);
        linearLayout.setPadding(10, 10, 10, 10);
        new MaterialAlertDialogBuilder(this, R.style.CutShapeTheme)
                .setTitle("change Password")
                .setMessage("After confirmation, you will receive an email with a link to change your password")
                .setView(linearLayout)
                .setPositiveButton("Confrimation", (dialogInterface, i) -> {
                    String email = emailEditText.getText().toString().trim();
                    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                    if (TextUtils.isEmpty(email)) {
                        Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (!email.matches(emailPattern)) {
                        Toast.makeText(this, "Please enter a valid email", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    beginRecovery(email);
                })
                .setNeutralButton("cancel", (dialogInterface, i) -> dialogInterface.dismiss())
                .show();
    }
    private void beginRecovery(String email) {
        loadingBar = new ProgressDialog(this);
        loadingBar.setMessage("Confirmation message is being sent");
        loadingBar.setCanceledOnTouchOutside(false);
        loadingBar.show();
        firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener(task -> {
            loadingBar.dismiss();
            if (task.isSuccessful()) {
                Toast.makeText(SignIn.this, "Confirmation message sent successfully, check your email", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(SignIn.this, "Failed to send confirmation message, this email is not registered", Toast.LENGTH_LONG).show();
            }
        }).addOnFailureListener(e -> {
            loadingBar.dismiss();
        });
    }}