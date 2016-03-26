# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in C:\Users\Administrator\AppData\Local\Android\sdk/tools/proguard/proguard-android.txt
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
-ignorewarnings                     # 忽略警告，避免打包时某些警告出现
-optimizationpasses 5               # 指定代码的压缩级别
-dontusemixedcaseclassnames         # 是否使用大小写混合
-dontskipnonpubliclibraryclasses    # 是否混淆第三方jar
-dontpreverify                      # 混淆时是否做预校验
-verbose                            # 混淆时是否记录日志
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*        # 混淆时所采用的算法

#-dontwarn android.support.v4.**     #缺省proguard 会检查每一个引用是否正确，但是第三方库里面往往有些不会用到的类，没有正确引用。如果不配置的话，系统就会报错。
#-dontwarn android.os.**
#
-keep interface android.support.v4.app.** { *; }
-keep public class * extends android.support.v4.**
-keep public class * extends android.app.Fragment

-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.support.v4.widget
#-keepclasseswithmembernames class * {       # 保持 native 方法不被混淆
#    native <methods>;
#}
#
-keepclasseswithmembers class * {            # 保持自定义控件类不被混淆
    public <init>(android.content.Context, android.util.AttributeSet);
}

-keepclasseswithmembers class * {            # 保持自定义控件类不被混淆
    public <init>(android.content.Context, android.util.AttributeSet, int);
}


-keepclassmembers enum * {                  # 保持枚举 enum 类不被混淆
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
#
#
#保持 Serializable 不被混淆
-keepnames class * implements java.io.Serializable


#不混淆泛型
-keepattributes Signature

# For using GSON @Expose annotation
-keepattributes *Annotation*

# Gson specific classes
-keep class sun.misc.Unsafe { *; }
#-keep class com.google.gson.stream.** { *; }

# Application classes that will be serialized/deserialized over Gson
-keep class com.google.gson.examples.android.model.** { *; }
-keep class com.google.gson.** { *;}
-keep class cn.zsmy.akm.entity.** { *; }
-keep class cn.zsmy.akm.chat.bean.**{*;}
-keep class cn.zsmy.akm.http.FileEntity
-keep class cn.zsmy.akm.salon.bean.**{*;}
-keep class cn.zsmy.akm.salon.activity.choosePhoto.**{*;}
-keep class cn.zsmy.akm.side.bean.**{*;}

-dontwarn com.baidu.**

-keep class android.support.v4.** { *; }        # 保持哪些类不被混淆
-keep class com.baidu.** { *; }
-keep class com.tencent.mm.** { *; }
-keep class vi.com.gdi.bgl.android.**{ *; }
-keep class android.os.**{ *; }
-keep class com.google.gson.**{ *; }
-keep class com.bigkoo.convenientbanner.**{ *; }
-keep class de.hdodenhof.circleimageview.**{ *; }
-keep class android.support.multidex.**{ *; }
-keep class com.bigkoo.pickerview.**{ *; }
-keep class com.nostra13.universalimageloader.**{ *; }
-keep class sun.misc.Unsafe { *; }
-keep class cn.zsmy.akm.chat.**{ *; }
-keep class cn.zsmy.akm.widget.**{ *; }
-keep class cn.zsmy.akm.interrogation.InterrogationChatActivity
-keep class cn.zsmy.akm.home.MyApplication
-keep class cn.zsmy.akm.http.** { *; }
-keep class cn.zsmy.akm.home.HomeActivity
-keep class cn.zsmy.akm.utils.**{ *; }
-keep class cn.zsmy.akm.db.**{ *; }


# OrmLite uses reflection
-keep class com.j256.ormlite.** { *; }
-keep class com.j256.ormlite.android.** { *; }
-keep class com.j256.ormlite.field.** { *; }
-keep class com.j256.ormlite.stmt.** { *; }
-keepclassmembers class * {
@com.j256.ormlite.field.DatabaseField *;
}

