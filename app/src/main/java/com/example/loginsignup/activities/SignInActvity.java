package com.example.loginsignup.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.loginsignup.R;
import com.example.loginsignup.databinding.ActivitySignInActvityBinding;
import com.example.loginsignup.utilities.Constants;
import com.example.loginsignup.utilities.PreferenceManager;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;


public class SignInActvity extends AppCompatActivity {

    private ActivitySignInActvityBinding binding;
    private PreferenceManager preferenceManager;
    /**
     * Called when the activity is starting. Initializes the activity, sets up the UI,
     * and restores the saved state if available.
     *
     * @param savedInstanceState a Bundle containing the activity's previously saved state, or null if there is none
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //EdgeToEdge.enable(this);
        //setContentView(R.layout.activity_sign_in_actvity);
        //ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            //Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            //v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            //return insets;
        //});

        binding = ActivitySignInActvityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        preferenceManager = new PreferenceManager(getApplicationContext());
        setListeners();
    }
    /**
     * Sets up event listeners for UI components.
     */
    private void setListeners(){
        binding.textCreateNewAccount.setOnClickListener(view ->
                startActivity(new Intent(getApplicationContext(), SignUpActivity.class )));

        binding.buttonSignIn.setOnClickListener(view -> {
            if(isValidateSignInDetails()){
                SignIn();
            }
        });

    }
    /**
     * Displays a toast message on the screen.
     *
     * @param message the message to display in the toast
     */
    private void showToast(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
    /**
     * Initiates the user sign-in process.
     */
    private void SignIn() {
        loading(true);
        FirebaseFirestore database = FirebaseFirestore.getInstance();

        database.collection(Constants.KEY_COLLECTION_USERS)
                .whereEqualTo(Constants.KEY_EMAIL, binding.inputEmail.getText().toString())
                .whereEqualTo(Constants.KEY_PASSWORD, binding.inputPassword.getText().toString())
                .get()
                .addOnCompleteListener(task ->{
                    if(task.isSuccessful() && task.getResult().getDocuments().size()>0){
                        DocumentSnapshot documentSnapshot = task.getResult().getDocuments().get(0);

                        preferenceManager.putBoolean(Constants.KEY_IS_SIGNED_IN, true);
                        preferenceManager.putString(Constants.KEY_USER_ID, documentSnapshot.getId());
                        preferenceManager.putString(Constants.KEY_NAME, documentSnapshot.getString(Constants.KEY_NAME));
                        preferenceManager.putString(Constants.KEY_NAME, documentSnapshot.getString(Constants.KEY_NAME));

                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        showToast("signed in");

                    }
                    else{
                        loading(false);
                        showToast("unable to sign in");
                    }
                });
    }
    /**
     * Shows or hides a button and progressbar based on the specified flag.
     *
     * @param isLoading true to show progressBar & hide sign in button, false to show sign in button and hides progressbar
     */
    private void loading(Boolean isLoading){
        if(isLoading){
            binding.buttonSignIn.setVisibility(View.INVISIBLE);
            binding.progressBar.setVisibility(View.VISIBLE);
        }else{
            binding.progressBar.setVisibility(View.INVISIBLE);
            binding.buttonSignIn.setVisibility(View.VISIBLE);
        }
    }

    /**
     * Validates the user's sign-in details.
     *
     * @return true if the sign-in details are valid; false otherwise
     */
    private boolean isValidateSignInDetails() {
        if (binding.inputEmail.getText().toString().trim().isEmpty()) {
            showToast("Please Enter your Email");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(binding.inputEmail.getText().toString()).matches()) {
            showToast("Please vaild Email");
            return false;
        } else if (binding.inputPassword.getText().toString().trim().isEmpty()) {
            showToast("Please Enter your Password");
            return false;

        }else{
            return true;
        }
    }

}