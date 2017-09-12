__author__ = '14038690'
studentInfo = [['Ilona Dane', 'Computer Science', ['u08007', 'u08008', 'u08010']],['Mada Jaffery', 'Software Engineering', ['u08007', 'u08008', 'u08010']], ['Pierre Blanc', 'Network Computing', ['u08007', 'u08010', 'u08906']]]

for z in range(len(studentInfo)):
    for x in range(len(studentInfo[z])):
        if x<=1:
            print(studentInfo[z][x])
    for y in range(len(studentInfo[z][2])):
        print('\t',studentInfo[z][2][y])
