package com.example.a3todolist;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInStatusCodes;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class LoginActivity extends AppCompatActivity {
    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button buttonLogin;
    private Button buttonGoogleSignIn; // Кнопка для Google Sign-In
    private TextView registerLink;
    private DatabaseHelper databaseHelper;

    private static final int RC_SIGN_IN = 9001;
    private GoogleSignInClient googleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextEmail = findViewById(R.id.editTextTextEmailAddress);
        editTextPassword = findViewById(R.id.editTextTextPassword);
        buttonLogin = findViewById(R.id.loginButton);
        registerLink = findViewById(R.id.registerLink);
        buttonGoogleSignIn = findViewById(R.id.buttonGoogleSignIn); // Инициализация кнопки

        databaseHelper = new DatabaseHelper(this);

        // Настройка Google Sign-In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id)) // Убедись, что у тебя есть этот ID
                .requestEmail()
                .build();

        googleSignInClient = GoogleSignIn.getClient(this, gso);

        buttonLogin.setOnClickListener(v -> loginUser());
        registerLink.setOnClickListener(v -> openRegisterActivity());
        buttonGoogleSignIn.setOnClickListener(v -> signInWithGoogle()); // Обработчик для Google Sign-In
    }

    private void loginUser() {
        String username = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        if (databaseHelper.checkUser(username, password)) {
            // Успешный вход
            Intent intent = new Intent(LoginActivity.this, TodoListActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "Invalid credentials", Toast.LENGTH_SHORT).show();
        }
    }

    private void openRegisterActivity() {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }

    private void signInWithGoogle() {
        Intent signInIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            // Успешный вход через Google
            Intent intent = new Intent(LoginActivity.this, TodoListActivity.class);
            startActivity(intent);
            finish();
        } catch (ApiException e) {
            int statusCode = e.getStatusCode(); // Получаем код ошибки
            String errorMessage = getGoogleSignInStatusCodeString(statusCode); // Получаем сообщение по коду ошибки
            Toast.makeText(this, "Sign in failed: " + errorMessage, Toast.LENGTH_LONG).show();
            e.printStackTrace(); // Выводим полную информацию об ошибке в лог
        }
    }

    /*private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            // Успешный вход через Google
            String email = account.getEmail();
            String displayName = account.getDisplayName();
            // Здесь вы можете сохранить пользователя в своей базе данных

            Toast.makeText(this, "Welcome " + displayName, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginActivity.this, TodoListActivity.class);
            startActivity(intent);
            finish();
        } catch (ApiException e) {
            Toast.makeText(this, "Google sign-in failed", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }*/

    private String getGoogleSignInStatusCodeString(int statusCode) {
        switch (statusCode) {
            case GoogleSignInStatusCodes.SIGN_IN_CANCELLED:
                return "Sign in was cancelled by the user.";
            case GoogleSignInStatusCodes.SIGN_IN_FAILED:
                return "Sign in failed due to an issue with the network or API.";
            case GoogleSignInStatusCodes.NETWORK_ERROR:
                return "A network error occurred.";
            case GoogleSignInStatusCodes.INTERNAL_ERROR:
                return "An internal error occurred.";
            case GoogleSignInStatusCodes.DEVELOPER_ERROR:
                return "Application configuration issue (e.g., invalid OAuth client ID).";
            case GoogleSignInStatusCodes.SIGN_IN_REQUIRED:
                return "Sign in is required.";
            case GoogleSignInStatusCodes.INVALID_ACCOUNT:
                return "Invalid account. Please check your Google account.";
            default:
                return "Unknown error occurred with status code: " + statusCode;
        }
    }

}

