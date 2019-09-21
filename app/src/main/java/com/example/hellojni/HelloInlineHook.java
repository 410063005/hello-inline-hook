package com.example.hellojni;

public class HelloInlineHook {
    static {
        System.loadLibrary("hello-inline-hook");
    }

    public native void hook();
    public native void unhook();
    public native int calc(int a, int b);
    public native int calcOrigin(int a, int b);
}
