package com.example.oneuiapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

// فراجمنت شاشة التمرير التي تعرض قائمة مكوّنة من 200 عنصر
public class ScrollFragment extends Fragment {
    private RecyclerView recyclerView;
    private MyAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // تحميل واجهة الشاشة
        View view = inflater.inflate(R.layout.fragment_scroll, container, false);
        recyclerView = view.findViewById(R.id.recycler_view);

        // إعداد RecyclerView مع مدير تخطيط خطي (عمودي)
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        // إنشاء وتعيين المساعد (أدابتور) الذي يعرض 200 عنصر نصي
        adapter = new MyAdapter();
        recyclerView.setAdapter(adapter);
        return view;
    }
}
