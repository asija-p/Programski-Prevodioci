main
int a;
int b;
a := 10;
b := a;
for temp in [a, b] apply temp:=temp+1
exit