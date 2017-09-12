def binToInt(binary):
    sofar = 0
    for digit in binary:
        sofar *= 2
        sofar = sofar + int(digit)
    return sofar

def binToFrac(binary):
    sofar = 0.0
    for digit in binary[::-1]:
        sofar = sofar + int(digit)
        sofar /= 2
    return sofar



go = True

while go:
    print("Please type the binary number in our floating point format without spaces.")
    print("Or type 'stop' if you want to stop.")

    ok = False

    while not ok:
        fpnum = input()
        if fpnum.lower() == "stop":
            ok = True
            go = False
        elif len(fpnum) != 16:
            print("The number should be 16 digits long, try again.")
        elif any(((x != "0") and (x != "1")) for x in fpnum):
            print("A binary number should have only 1s and 0s, try again.")
        else:
            ok = True

    if not go:
        break

    sign = (fpnum[0] == "0")
    print("The sign bit is",fpnum[0])
    exponentbits = fpnum[1:8]
    mantissabits = fpnum[8:]
    exponent = binToInt(exponentbits) - 64
    print("The exponent bits are",exponentbits,"so the exponent is",exponent)
    if (exponent < -50):
        print("(Did you forgot to add the excess 64 to the exponent?)")
    mantissa = binToFrac(mantissabits) + 1.0
    print("The mantissa bits are",mantissabits)
    print("With the fixed 1 that is 1.",mantissabits,"b so the mantissa is ",mantissa,"d",sep="")
    result = mantissa * (2**exponent)
    if not sign:
        result = -result
    print("The whole number is",result,"in decimal")
