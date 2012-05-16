f = open("0-machining-queue.txt",'r')
log = f.read().strip().split("\n")

averageQueueLen = 0.0
simulationTime = 0.0
t = []
q = []
print "start"
for i in range (0 , len(log)-1):
    t1 , qLen = log[i].split()
    t1 = float(t1)
    qLen = int(qLen)
    t.append(t1)
    q.append(qLen)
    #print log[i+1]+"rrr" 
    t2 , qLen2 = log[i+1].split()
    t2 = float(t2)
    averageQueueLen += ((t2-t1)*qLen)
    simulationTime = t2
averageQueueLen /= simulationTime
t1 , qLen = log[len(log)-1].split()
t1 = float(t1)
qLen = int(qLen)
t.append(t1)
q.append(qLen)
print "Average Queue Length = %f"%averageQueueLen
f2 = open('t.txt','w')
f3 = open('q.txt','w')
f2.write(str(t))
f3.write(str(q))
f2.close()
f3.close()
