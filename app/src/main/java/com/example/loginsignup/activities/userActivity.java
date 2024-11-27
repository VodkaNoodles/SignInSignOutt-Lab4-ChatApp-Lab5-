package com.example.loginsignup.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.loginsignup.R;
import com.example.loginsignup.databinding.ActivityUserBinding;
import com.example.loginsignup.utilities.PreferenceManager;

public class userActivity extends AppCompatActivity {

        private ActivityUserBinding binding;

        private PreferenceManager preferenceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setListeners();
        //getUsers();
    }
    private void setListeners(){
        binding.imageBack.setOnClickListener(view -> onBackPressed());
    }
    private void getUsers(){

    }

    private void showToast(String message){
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
    }
    private void loading (Boolean isLoading){
        if(isLoading){
            binding.progressBar.setVisibility(View.VISIBLE);
        }else{
            binding.progressBar.setVisibility(View.INVISIBLE);
        }

    }

}