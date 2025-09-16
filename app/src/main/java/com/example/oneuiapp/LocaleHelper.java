package com.example.oneuiapp;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import java.util.Locale;

// مساعد لتغيير لغة التطبيق في الوقت الفعلي دون إعادة تشغيل كامل
public class LocaleHelper {
    public static void setLocale(Context context, String language) {
        Locale myLocale = new Locale(language);
        Locale.setDefault(myLocale);
        Resources res = context.getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.setLocale(myLocale);
        res.updateConfiguration(conf, dm);
    }
}
