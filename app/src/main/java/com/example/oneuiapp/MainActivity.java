package com.example.oneuiapp;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout; // تم التعديل هنا
import androidx.fragment.app.Fragment;
import com.google.android.material.appbar.CollapsingToolbarLayout; // تم التعديل هنا
import com.google.android.material.appbar.MaterialToolbar; // تم التعديل هنا

public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private LinearLayout drawerScroll, drawerSettings;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // تعيين الواجهة إلى التخطيط الرئيسي الذي يحتوي على DrawerLayout
        setContentView(R.layout.activity_main);

        // الوصول إلى مكونات الواجهة من XML
        drawerLayout = findViewById(R.id.drawer_layout);
        drawerScroll = findViewById(R.id.drawer_scroll);
        drawerSettings = findViewById(R.id.drawer_settings);

        // تعيين عنوان الشاشة في CollapsingToolbarLayout
        CollapsingToolbarLayout collapsingToolbar = findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(getString(R.string.app_name));

        // زر قائمة (يمكنك إضافة أيقونة هنا)
        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // تفعيل شريط القائمة لفتح وإغلاق الـ Drawer
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(drawerScroll); // يفتح القائمة
            }
        });

        // عند الضغط على "شاشة التمرير"
        drawerScroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // عرض الفراجمنت الخاص بالتمرير
                openFragment(new ScrollFragment());
                drawerLayout.closeDrawers();
            }
        });

        // عند الضغط على "شاشة الإعدادات"
        drawerSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFragment(new SettingsFragment());
                drawerLayout.closeDrawers();
            }
        });

        // نبدأ بعرض شاشة التمرير بشكل افتراضي
        if (savedInstanceState == null) {
            openFragment(new ScrollFragment());
        }
    }

    // طريقة مساعدة لعرض الفراجمنت المطلوب في الحاوية
    private void openFragment(Fragment fragment) {
        getSupportFragmentManager()
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit();
    }
}
