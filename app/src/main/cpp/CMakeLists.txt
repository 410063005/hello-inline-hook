cmake_minimum_required(VERSION 3.4.1)

add_library(hello-inline-hook SHARED
        hello-inline-hook.c inlinehook/inlineHook.c inlinehook/relocate.c inlinehook/hooktest.c)

# Include libraries needed for hello-inline-hook lib
target_link_libraries(hello-inline-hook
                      android
                      log)
