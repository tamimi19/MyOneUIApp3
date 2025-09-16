package com.example.oneuiapp;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.oneuiproject.sesl.drawerlayout.DrawerLayout;
import com.oneuiproject.sesl.appbar.CollapsingToolbarLayout;
import com.oneuiproject.sesl.appbar.MaterialToolbar;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private LinearLayout drawerContainer, drawerScroll, drawerSettings;

    @Override  
    protected void onCreate(@Nullable Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.activity_main);  

        // الحصول على مكونات الواجهة من XML
        drawerLayout = findViewById(R.id.drawer_layout);  
        drawerContainer = findViewById(R.id.drawer_container); // الحاوية الرئيسية للقائمة
        drawerScroll = findViewById(R.id.drawer_scroll);  
        drawerSettings = findViewById(R.id.drawer_settings);  

        // تعيين عنوان الشاشة في CollapsingToolbarLayout
        CollapsingToolbarLayout collapsingToolbar = findViewById(R.id.collapsing_toolbar);  
        collapsingToolbar.setTitle(getString(R.string.app_name));  

        // إعداد شريط الأدوات (Toolbar)
        MaterialToolbar toolbar = findViewById(R.id.toolbar);  
        setSupportActionBar(toolbar);  
        // إذا رغبت بإضافة أيقونة للقائمة الجانبية:
        // toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);

        // عند الضغط على أيقونة القائمة، نفتح اللوحة الجانبية (الدروار)
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {  
            @Override  
            public void onClick(View view) {  
                drawerLayout.openDrawer(drawerContainer); // فتح الحاوية التي فيها عناصر القائمة
            }  
        });  

        // إعداد استجابة الضغط على عناصر القائمة
        drawerScroll.setOnClickListener(new View.OnClickListener() {  
            @Override  
            public void onClick(View view) {  
                openFragment(new ScrollFragment());  
                drawerLayout.closeDrawers();  
            }  
        });  

        drawerSettings.setOnClickListener(new View.OnClickListener() {  
            @Override  
            public void onClick(View view) {  
                openFragment(new SettingsFragment());  
                drawerLayout.closeDrawers();  
            }  
        });  

        // بدء عرض شاشة التمرير بشكل افتراضي عند فتح التطبيق
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
