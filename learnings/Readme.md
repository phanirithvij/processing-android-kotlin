# Learnings

## References

- [Muzei](https://github.com/phanirithvij/muzei/) a live wallpaper application
    Cloned it just incase

## Issues
+ SettingsActivity crashes the wallpaper service
    - **Issue**:

        Live wallpaper settings onclick crashes the app

        ```java
        java.lang.IllegalStateException: Could not execute method for android:onClick
        at android.view.View$DeclaredOnClickListener.onClick(View.java:4707)
        at android.view.View.performClick(View.java:5619)
        at android.view.View$PerformClick.run(View.java:22298)
        at android.os.Handler.handleCallback(Handler.java:754)
        at android.os.Handler.dispatchMessage(Handler.java:95)
        at android.os.Looper.loop(Looper.java:165)
        at android.app.ActivityThread.main(ActivityThread.java:6375)
        at java.lang.reflect.Method.invoke(Native Method)
        at com.android.internal.os.ZygoteInit$MethodAndArgsCaller.run(ZygoteInit.java:912)
        at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:802)
        Caused by: java.lang.reflect.InvocationTargetException
        at java.lang.reflect.Method.invoke(Native Method)
        at android.view.View$DeclaredOnClickListener.onClick(View.java:4702)
        ... 9 more
        Caused by: java.lang.SecurityException: Permission Denial: starting Intent { cmp=processing.test.test_android_sketch/.SettingsActivity (has extras) } from ProcessRecord{7e62d4 4075:com.android.wallpaper.livepicker/u0a64} (pid=4075, uid=10064) not exported from uid 11033
        at android.os.Parcel.readException(Parcel.java:1715)
        at android.os.Parcel.readException(Parcel.java:1668)
        at android.app.ActivityManagerProxy.startActivity(ActivityManagerNative.java:3153)
        at android.app.Instrumentation.execStartActivity(Instrumentation.java:1520)
        at android.app.Activity.startActivityForResult(Activity.java:4396)
        at android.app.Activity.startActivityForResult(Activity.java:4355)
        at android.app.Activity.startActivity(Activity.java:4679)
        at android.app.Activity.startActivity(Activity.java:4647)
        at com.android.wallpaper.livepicker.LiveWallpaperPreview.configureLiveWallpaper(LiveWallpaperPreview.java:122)
        ... 11 more
        ```
    - **Solution**:

        An option `android:exported="true"` must be enabled to export activities to external services in `AndroidManifest.xml`.

        ```xml
        <activity
            android:name=".SettingsActivity"
            android:theme="@style/Theme.AppCompat.DayNight"
            android:exported="true"
            android:parentActivityName=".MainActivity"
            android:label="@string/title_activity_settings">
        </activity>
        ```

    - **References**:
        - Muzei source [code](https://github.com/romannurik/muzei/blob/master/main/src/main/AndroidManifest.xml#L68-L73).
+ Adding Settings to wallpaper
    - **Problem**:

        How to add a settings option to the live wallpaper
    - **Solution**:

        [Search](https://www.google.com/search?q=settings+for+live+wallpaper+android) revealed [this](https://stackoverflow.com/a/8878948/8608146)
    - **Resources**:
        - [Muzei source code](https://github.com/romannurik/muzei/blob/master/main/src/main/res/xml/wallpaper.xml#L23)
        - [This so post](https://stackoverflow.com/a/8878948/8608146)
