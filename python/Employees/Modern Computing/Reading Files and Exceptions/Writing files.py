__author__ = '14038690'

file=open('test.txt',"w+")
file2=open('testfile.txt','r')
try:
    x=file2.readline()
    file.write(x)

    y=file2.readline()
    file.write(y)

    r=file.readlines()
    print(r)

except IOError as err:
    print('An unexpected error has occured:\n',err)
finally:
    if file in locals():
        file.close()
    if file2 in locals():
        file2.close()

file.close()
file2.close()