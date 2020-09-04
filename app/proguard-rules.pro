#
#-------------------------------------------�������ö�����----------------------------------------------
#
#
# -----------------------------���� -----------------------------
#

# ָ�������ѹ������ 0 - 7(ָ��������е����Ż��Ĵ�������Android����Ĭ����5������ָ��Ҳֻ���ڿ����Ż�ʱ�����á�)
-optimizationpasses 5
# ����ʱ�����������ɫɫ������(����ʱ��ʹ�ô�Сд�������)
-dontusemixedcaseclassnames
# ָ����ȥ���Էǹ����Ŀ���(������library�еķ�public����)
-dontskipnonpubliclibraryclasses
# ָ����ȥ���԰��ɼ��Ŀ���ĳ�Ա
-dontskipnonpubliclibraryclassmembers
#�������Ż�������ʹ�ô�ѡ�
-dontoptimize
 # ������ԤУ��,Android����Ҫ,�ɼӿ�����ٶȡ�
-dontpreverify
# ���ξ���
-ignorewarnings
# ָ�������ǲ��õ��㷨������Ĳ�����һ��������
# ����������ǹȸ��Ƽ����㷨��һ�㲻������
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*
# ���������е�Annotation��������
-keepattributes *Annotation*
# �����������, ����JSONʵ��ӳ��ʱ�ǳ���Ҫ
-keepattributes Signature
# �׳��쳣ʱ���������к�
-keepattributes SourceFile,LineNumberTable
 #�Ż�ʱ������ʲ��޸������η��������ĳ�Ա�����������Ż�����Ľ����
# ���磬������һ��������getter����ʱ����Ҳ������Ҫ��ع������ʡ�
# ��Ȼjava�����ƹ淶����Ҫ�����Ҫ��Ȼ�е������������Щ����������⡣�����Ż���ʹ��-repackageclassesʱ�����á�
#ָʾ����������ָ�����еĴ��룬��Ϊ�е�������Աû����Ƴ�public ,����api�п��ܱ��public
-allowaccessmodification
#�����Ż���ʹ��-repackageclassesʱ�����á�
-repackageclasses ''
 # ����ʱ��¼��־(��ӡ��������ϸ��Ϣ)
 # ��仰�ܹ�ʹ���ǵ���Ŀ���������ӳ���ļ�
 # ����������->������������ӳ���ϵ
-verbose

#
# ----------------------------- Ĭ�ϱ��� -----------------------------
#
#----------------------------------------------------
# ������Щ�಻������
#�̳�activity,application,service,broadcastReceiver,contentprovider....�����л���
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
#-keep public class * extends android.support.multidex.MultiDexApplication
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class * extends android.view.View
#-keep class android.support.** {*;}## ����support�µ������༰���ڲ���

#-keep public class com.google.vending.licensing.ILicensingService
#-keep public class com.android.vending.licensing.ILicensingService
#��ʾ�����������������࣬��������������ǻ���Ҳ�ò��ϣ��ǽ���Googleԭ����һЩ����ʱʹ�õġ�
#----------------------------------------------------

# �����̳е�


#��ʾ�������κΰ���native��������������Լ�native����������������Ǹղ���֤�Ľ����һ��
-keepclasseswithmembernames class * {
    native <methods>;
}


#�����Ҫ����layout ��д��onclick����android:onclick="onClick"�������л���
#��ʾ������Activity�в�����View�ķ�������Ϊ������һ���÷�����XML������android:onClick=��buttonClick�����ԣ�
#���û�����ð�ťʱ�ͻ����Activity�е�buttonClick(View view)�����������������������Ļ����Ҳ�����
-keepclassmembers class * extends android.app.Activity{
    public void *(android.view.View);
}

#��ʾ������ö���е�values()��valueOf()������ö�����õķǳ��٣�����Ͳ�������
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

#��ʾ�������κ�һ��View�е�setXxx()��getXxx()������
#��Ϊ���Զ�����Ҫ����Ӧ��setter��getter�ķ���ʵ�֣������˾��޷������ˡ�
-keep public class * extends android.view.View{
    *** get*();
    void set*(***);
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
}
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
}

#��ʾ������Parcelableʵ�����е�CREATOR�ֶΣ�
#�������ʣ�CREATOR�ֶ��Ǿ��Բ��ܸı�ģ�������Сд�����ܱ䣬��Ȼ����Parcelable�������ƶ���ʧ�ܡ�
-keep class * implements android.os.Parcelable {
  #public static final android.os.Parcelable$Creator *;
}
# ��ָ���˼̳�Serizalizable��������³�Ա�����Ƴ�����
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}
# ����R�������Դ
#-keep class **.R$* {
# *;
#}
#��������Դ����static��
-keepclassmembers class **.R$* {
    public static <fields>;
}

# ���ڴ��лص�������onXXEvent��**On*Listener�ģ����ܱ�����
-keepclassmembers class * {
    void *(**On*Event);
    void *(**On*Listener);
}

# ���������Զ���ؼ����̳���View����������
-keep public class * extends android.view.View{
    *** get*();
    void set*(***);
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
}

#
#----------------------------- WebView(��Ŀ��û�п��Ժ���) -----------------------------
#
#webView��Ҫ�������⴦��

-keepclassmembers class * extends android.webkit.WebViewClient {
    public void *(android.webkit.WebView, java.lang.String, android.graphics.Bitmap);
    public boolean *(android.webkit.WebView, java.lang.String);
}
-keepclassmembers class * extends android.webkit.WebViewClient {
    #public void *(android.webkit.WebView, jav.lang.String);
}
#��app����HTML5��JavaScript�Ľ����������⴦��
#������Ҫȷ����ЩjsҪ���õ�ԭ���������ܹ�������������������Ҫ�����´���


#
#---------------------------------ʵ����---------------------------------
#--------(ʵ��Model���ܻ����������Ҳ�����Ӧ�����Ի�ȡ����ֵ)-----
#
-dontwarn com.suchengkeji.android.confusiondemo.md.**
#�Ժ��з�����Ĵ���
#-keep class com.suchengkeji.android.confusiondemo.md.** { *; }
#
# ----------------------------- ������ -----------------------------
#
# ɾ��������Log��صĴ���
-assumenosideeffects class android.util.Log {
    public static boolean isLoggable(java.lang.String, int);
    public static int v(...);
    public static int i(...);
    public static int w(...);
    public static int d(...);
    public static int e(...);
}

# ���ֲ�����صĴ���
-dontnote junit.framework.**
-dontnote junit.runner.**
-dontwarn android.test.**
-dontwarn android.support.test.**
-dontwarn org.junit.**


#
# ----------------------------- ������ -----------------------------
#
-dontwarn com.orhanobut.logger.**
#-keep class com.orhanobut.logger.**{*;}
#-keep interface com.orhanobut.logger.**{*;}

-dontwarn com.google.gson.**
#-keep class com.google.gson.**{*;}
#-keep interface com.google.gson.**{*;}
#        ������������