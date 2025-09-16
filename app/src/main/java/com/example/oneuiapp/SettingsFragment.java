package com.example.oneuiapp;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton; // تم إضافة هذا الاستيراد
import android.widget.RadioButton;
import android.widget.RadioGroup;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import com.samsung.android.widget.SeslSwitch; // تم تصحيح مسار الاستيراد
import androidx.core.app.NotificationCompat;

// فراجمنت شاشة الإعدادات
public class SettingsFragment extends Fragment {
    private RadioGroup langGroup;
    private RadioButton langEnglish, langArabic;
    private SeslSwitch themeSwitch;
    private Button notificationButton;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // تحميل واجهة شاشة الإعدادات
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        langGroup = view.findViewById(R.id.radio_lang);
        langEnglish = view.findViewById(R.id.radio_en);
        langArabic = view.findViewById(R.id.radio_ar);
        themeSwitch = view.findViewById(R.id.switch_theme);
        notificationButton = view.findViewById(R.id.button_notify);

        // تغيير اللغة عند اختيار اللغة الإنجليزية
        langEnglish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // تعيين اللغة إلى الإنجليزية ثم إعادة إنشاء النشاط لتطبيق التغيير
                LocaleHelper.setLocale(getContext(), "en");
                getActivity().recreate();
            }
        });

        // تغيير اللغة عند اختيار اللغة العربية
        langArabic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LocaleHelper.setLocale(getContext(), "ar");
                getActivity().recreate();
            }
        });

        // تغيير الثيم بدون إعادة تشغيل التطبيق (تغيير الوضع الليلي)
        // تم استخدام المستمع الصحيح هنا
        themeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // الوضع الليلي
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                } else {
                    // الوضع النهاري
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
            }
        });

        // زر تجربة الإشعار: إنشاء قنوات وإرسال إشعار بسيط
        notificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendTestNotification();
            }
        });

        return view;
    }

    // دالة لإرسال إشعار تجريبي
    private void sendTestNotification() {
        Context ctx = getContext();
        if (ctx == null) return;
        NotificationManager nm = (NotificationManager) ctx.getSystemService(Context.NOTIFICATION_SERVICE);
        String channelId = "test_channel";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId, "Test Channel", NotificationManager.IMPORTANCE_DEFAULT);
            nm.createNotificationChannel(channel);
        }
        NotificationCompat.Builder builder = new NotificationCompat.Builder(ctx, channelId)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle(getString(R.string.notification_title))
                .setContentText(getString(R.string.notification_content))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        nm.notify(123, builder.build());
    }
}
