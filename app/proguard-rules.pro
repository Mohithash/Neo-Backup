// Add project specific ProGuard rules here.
// You can control the set of applied configuration files using the
// proguardFiles setting in build.gradle.

// If your project uses WebView with JS, uncomment the following
// and specify the fully qualified class name to the JavaScript interface
// class:
//-keepclassmembers class fqcn.of.javascript.interface.for.webview {
//   public *;
//}

// Uncomment this to preserve the line number information for
// debugging stack traces.
//-keepattributes SourceFile,LineNumberTable

// If you keep the line number information, uncomment this to
// hide the original source file name.
//-renamesourcefileattribute SourceFile

// This fixes crash in fragments in builds with minify
// Keep all classes that extend androidx.fragment.app.Fragment
// to prevent obfuscation of their members
-keep class * extends androidx.fragment.app.Fragment{}

// Keep `Companion` object fields of serializable classes.
// This avoids serializer lookup through `getDeclaredClasses` as done for named companion objects.
// If a class is annotated with @kotlinx.serialization.Serializable,
// keep its Companion object fields
-if @kotlinx.serialization.Serializable class **
-keepclassmembers class <1> {
    static <1>$Companion Companion;
}

// Keep `serializer()` on companion objects (both default and named) of serializable classes.
// If a class is annotated with @kotlinx.serialization.Serializable,
// keep its companion object's serializer method
-if @kotlinx.serialization.Serializable class ** {
    static **$* *;
}
-keepclassmembers class <2>$<3> {
    kotlinx.serialization.KSerializer serializer(...);
}

// Keep `INSTANCE.serializer()` of serializable objects.
// If a class is annotated with @kotlinx.serialization.Serializable and has a public static INSTANCE,
// keep its serializer method
-if @kotlinx.serialization.Serializable class ** {
    public static ** INSTANCE;
}
-keepclassmembers class <1> {
    public static <1> INSTANCE;
    kotlinx.serialization.KSerializer serializer(...);
}

// @Serializable and @Polymorphic are used at runtime for polymorphic serialization.
// Keep runtime visible annotations and annotation default attributes
-keepattributes RuntimeVisibleAnnotations,AnnotationDefault

// Disable code obfuscation
-dontobfuscate

// Ignore warnings related to specific classes
-dontwarn org.bouncycastle.jsse.BCSSLParameters
-dontwarn org.bouncycastle.jsse.BCSSLSocket
-dontwarn org.bouncycastle.jsse.provider.BouncyCastleJsseProvider
-dontwarn org.conscrypt.Conscrypt$Version
-dontwarn org.conscrypt.Conscrypt
-dontwarn org.conscrypt.ConscryptHostnameVerifier
-dontwarn org.openjsse.javax.net.ssl.SSLParameters
-dontwarn org.openjsse.javax.net.ssl.SSLSocket
-dontwarn org.openjsse.net.ssl.OpenJSSE
