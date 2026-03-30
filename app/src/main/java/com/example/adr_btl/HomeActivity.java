package com.example.adr_btl;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.FrameLayout;
import android.widget.Toast;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.GridLayoutManager;

import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    private SessionManager session;
    RecyclerView rvCategory, rvProduct;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        com.google.android.material.tabs.TabLayout tabLayout = findViewById(R.id.tabLayout);

        tabLayout.addTab(tabLayout.newTab().setText("Mời bạn"));
        tabLayout.addTab(tabLayout.newTab().setText("Gần bạn"));
        tabLayout.addTab(tabLayout.newTab().setText("Mới nhất"));
        tabLayout.addTab(tabLayout.newTab().setText("Video"));

        // ===== HIỂN THỊ CATEGORY + PRODUCT =====
        rvCategory = findViewById(R.id.rvCategory);
        rvProduct = findViewById(R.id.rvProduct);

// LayoutManager
        rvCategory.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        );
        rvProduct.setLayoutManager(new GridLayoutManager(this, 3));

// DATA CATEGORY
        List<Category> listC = new ArrayList<>();
        listC.add(new Category(android.R.drawable.ic_menu_gallery, "BĐS"));
        listC.add(new Category(android.R.drawable.ic_menu_gallery, "Xe"));
        listC.add(new Category(android.R.drawable.ic_menu_gallery, "Thời trang"));
        listC.add(new Category(android.R.drawable.ic_menu_gallery, "Điện tử"));
        listC.add(new Category(android.R.drawable.ic_menu_gallery, "Thực phẩm"));

        rvCategory.setAdapter(new CategoryAdapter(listC));

// DATA PRODUCT
        List<Product> listP = new ArrayList<>();
        listP.add(new Product(android.R.drawable.ic_menu_gallery, "iPhone 11", "6tr", "Hà Nội"));
        listP.add(new Product(android.R.drawable.ic_menu_gallery, "Samsung", "5tr", "HCM"));
        listP.add(new Product(android.R.drawable.ic_menu_gallery, "Mực khô", "240.000 đ", "Quảng Ninh"));
        listP.add(new Product(android.R.drawable.ic_menu_gallery, "Laptop", "10tr", "Hà Nội"));
        listP.add(new Product(android.R.drawable.ic_menu_gallery, "Bán nhà", "750tr", "Đà Nẵng"));
        listP.add(new Product(android.R.drawable.ic_menu_gallery, "Căn hộ", "2.5 tỷ", "Thái Bình"));
        listP.add(new Product(android.R.drawable.ic_menu_gallery, "lô đất", "3,15 tỷ", "Hà Nam"));
        listP.add(new Product(android.R.drawable.ic_menu_gallery, "Khẩu trang", "100.000 đ", "Bắc Ninh"));
        listP.add(new Product(android.R.drawable.ic_menu_gallery, "Áo phông", "250.000 đ", "Tuyên Quang"));
        rvProduct.setAdapter(new ProductAdapter(listP));

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
    // ===== MODEL =====
    class Category {
        int img;
        String name;

        Category(int img, String name) {
            this.img = img;
            this.name = name;
        }
    }

    class Product {
        int img;
        String title, price, location;

        Product(int img, String title, String price, String location) {
            this.img = img;
            this.title = title;
            this.price = price;
            this.location = location;
        }
    }

    // ===== CATEGORY ADAPTER =====
    class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

        List<Category> list;

        CategoryAdapter(List<Category> list) {
            this.list = list;
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            ImageView img;
            TextView txt;

            ViewHolder(View v) {
                super(v);
                img = v.findViewById(R.id.imgIcon);
                txt = v.findViewById(R.id.txtName);
            }
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup p, int v) {
            View view = LayoutInflater.from(p.getContext())
                    .inflate(R.layout.item_category, p, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder h, int i) {
            Category c = list.get(i);
            h.img.setImageResource(c.img);
            h.txt.setText(c.name);
        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }

    // ===== PRODUCT ADAPTER =====
    class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

        List<Product> list;

        ProductAdapter(List<Product> list) {
            this.list = list;
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            ImageView img;
            TextView title, price, location;

            ViewHolder(View v) {
                super(v);
                img = v.findViewById(R.id.imgProduct);
                title = v.findViewById(R.id.txtTitle);
                price = v.findViewById(R.id.txtPrice);
                location = v.findViewById(R.id.txtLocation);
            }
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup p, int v) {
            View view = LayoutInflater.from(p.getContext())
                    .inflate(R.layout.item_product, p, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder h, int i) {
            Product p1 = list.get(i);
            h.img.setImageResource(p1.img);
            h.title.setText(p1.title);
            h.price.setText(p1.price);
            h.location.setText(p1.location);
        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }
}