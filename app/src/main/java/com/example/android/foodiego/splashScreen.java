package com.example.android.foodiego;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class splashScreen extends AppCompatActivity {
    private static final long SPLASH_DELAY = 3000; // 3 seconds
    private TextView splashText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(getResources().getColor(R.color.status_bar_color));

        splashText = findViewById(R.id.splash_text_id);
        animateText();

        new Handler().postDelayed(() ->
        {
            // For example, startActivity(new Intent(SplashScreen.this, MainActivity.class));
            Intent i = new Intent(splashScreen.this, user.class);
            startActivity(i);
            finish();
        }, SPLASH_DELAY);
    }
    private void animateText() {
        Animation fadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        splashText.startAnimation(fadeInAnimation);
    }
}