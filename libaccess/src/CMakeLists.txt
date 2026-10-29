cmake_minimum_required(VERSION 3.21)
project(Access VERSION 1.0.0 LANGUAGES C)

add_library(access SHARED
    lib.c
    ui.c
    access.c
)