#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct Access {
    int id;
    char name[255];
} Access;

Access getAccessInstance() {
    Access access = {0, "Access"};
    return access;
}