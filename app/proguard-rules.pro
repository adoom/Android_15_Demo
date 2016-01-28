# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Users/dupengfei/Documents/android-sdk-macosx/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}


#okhttputils
-keep public class com.zhy.http.okhttp.**
-keep public class okio.**
-keep public class okhttp3.**
-dontwarn com.squareup.okhttp.**
-keep class com.squareup.okhttp.** { *;}
-dontwarn okio.**


#友盟
-keep class com.umeng.analytics.**{*;}

#activeandroid
-keep public class com.activeandroid.**
-keep public class * extends com.activeandroid.ActiveRecordBase
-keepattributes Column
-keepattributes Table
-keepattributes Annotation

#bean
-keep class com.qianfeng.android_15_demo.bean.**{*;}
-keepclassmembers class com.qianfeng.android_15_demo.bean.**{*;}

#Gson
# removes such information by default, so configure it to keep all of it.
-keepattributes Signature
# Gson specific classes
-keep class sun.misc.Unsafe { *; }
#-keep class com.google.gson.stream.** { *; }

-keepnames class * implements java.io.Serializable
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    !static !transient <fields>;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

#系统组件，并且有可能声明在配置文件中
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgent
-keep public class * extends android.preference.Preference
-keep public class * extends android.support.v4.app.Fragment
-keep public class * extends android.app.Fragment

#百度地图
-keep class com.baidu.** { *; }
-keep class vi.com.gdi.bgl.android.**{*;}
-keep class com.sinovoice.**{*;}

#支付宝
-keep public class * extends com.alipay.android.app.**
-keep class com.alipay.android.app.** { *; }

