package com.example.adr_btl;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        session = new SessionManager(this);

        // Các nút BottomNavigation
        LinearLayout btnManage = findViewById(R.id.btnManage);
        LinearLayout btnAccount = findViewById(R.id.btnAccount);
        LinearLayout btnContact = findViewById(R.id.btnContact);
        FrameLayout btnPost = findViewById(R.id.btnPost);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!session.isLoggedIn()) {
                    // Chưa đăng nhập → chuyển sang LoginActivity
                    startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                } else {
                    // TODO: Thêm hành vi khi đã đăng nhập
                    Toast.makeText(HomeActivity.this, "Đã đăng nhập, xử lý chức năng tương ứng", Toast.LENGTH_SHORT).show();
                }
            }
        };

        btnManage.setOnClickListener(listener);
        btnAccount.setOnClickListener(listener);
        btnContact.setOnClickListener(listener);
        btnPost.setOnClickListener(listener);
    }
}