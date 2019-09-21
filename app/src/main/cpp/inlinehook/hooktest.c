#include <stdio.h>

#include <android/log.h>
#include "include/inlineHook.h"

#define LOGI(...)   __android_log_print((int)ANDROID_LOG_INFO, "BitmapProfiler", __VA_ARGS__)

int (*old_calc)(int, int) = NULL;

int add(int a, int b)
{
    LOGI("add: a = %d, b = %d", a, b);
	return a + b;
}

int minus(int a, int b)
{
	LOGI("minus: a = %d, b = %d", a, b);
	return a - b;
}

int hook()
{
	// hook
	// 目标函数 - add()
	// 新函数 - minus()
	// 原始函数地址 - old_calc
	if (registerInlineHook((uint32_t) add, (uint32_t) minus, (uint32_t **) &old_calc) != ELE7EN_OK){
		return -1;
	}
	if (inlineHook((uint32_t) add) != ELE7EN_OK) {
		return -1;
	}
	return 0;
}

int unHook()
{
	if (inlineUnHook((uint32_t) add) != ELE7EN_OK) {
		return -1;
	}
	return 0;
}

int calcOrigin(int a, int b) {
	if (old_calc != NULL) {
		LOGI("calcOrigin: calling the original function! a = %d, b = %d", a, b);
		// 通过原始函数地址来调用被 hook 前的函数
		return old_calc(a, b);
	}
	return add(a, b);
}