__author__ = '14038690'

main=[]
ok=True
go=0
try:
    while go != 'STOP':
        go=input('Would you like to enter a person? Y/N/STOP')

        if go == 'Y' or go == 'y':
            while ok:
                try:
                    name=input('Name: ')
                    age=int(input('Age: '))
                    course=input('Course: ')
                    home=input('Hometown: ')
                    ok=False
                except ValueError:
                    print("You didn't enter a number")

            info=open('Personal Information.txt','a')
            x=name
            x=x+'_'
            x=x+str(age)
            x=x+'_'
            x=x+course
            x=x+'_'
            x=x+home
            x=x+'\n'
            info.write(x)
            info.close()
        if go == 'N' or go == 'n':
            info=open('Personal Information.txt','r')
            for lines in info:
                text=info.readlines()
                #print(text)
                for i in range(len(text)):
                    (words,void)=text[i].split('\n')
                    #print(words)
                    list = words.split('_')
                    #print(list)
                    main.append(list)

            for students in main:
                for items in students:
                    print(items)
                print('\n')
            info.close()



except IOError as err:
    print('There was an error: \n',err)
finally:
    info.close()

info.close()