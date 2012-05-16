for i in range(0,10):
    f = open("throughput\\"+str(i)+"-hourly-throughput.txt",'r')
    perHour = {}

    output = open("sim.txt","a")
    for line in f:
        time , items = line.split()
        hour = int(float(time)/60.0)
        #print hour , time
        if hour in perHour:
            perHour[hour] = max(perHour[hour] , float(items))
        else:
            perHour[hour] = float(items)

        #if prev in perHour:
         #   perHour[hour] -= perHour[prev] 

    print perHour

    s = str(int(perHour[0]))+"\t"
    for hour in range(1,len(perHour)):
        s += str(int(perHour[hour] - perHour[hour-1])) +"\t"
    s = s[0:len(s)-1]
    output.write(s+"\n")
    output.close()
