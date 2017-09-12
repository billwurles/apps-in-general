__author__ = '14038690'

studentInfo = [{'name':'Ilona Dane', 'course':'Computer Science', 'module':['u08007', 'u08008', 'u08010']},
               {'name':'Mada Jaffery', 'course':'Software Engineering', 'module':['u08007', 'u08008', 'u08010']},
               {'name':'Pierre Blanc', 'course':'Network Computing', 'module':['u08007', 'u08010', 'u08906']}]

for x in studentInfo:
    print(x["name"])
    print(x['course'])
    for y in range(len(x['module'])):
        print('\t',x['module'][y])
    print('')

ok=True
while ok:
    student = input('What student would you like to modify?')
    for x in studentInfo:
        if student in x['name']:
            student=x
            y=x
    print(student)
    key = input('What should the key name be? ')
    value = input('What is the value? ')
    new={key:value}
    student[key]=[value]
    studentInfo.remove(y)
    studentInfo.append(student)
    print(studentInfo)