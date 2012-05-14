FOR /L %%i in (2 2 20) DO (
FOR /L %%j IN (1 1 3) DO (
	iperf.exe -c 169.254.124.96 -u -P 1 -i 1 -p 5001 -f m -b %%iM -t 10 -T 1 >> wireless-udp.txt
	:sleep for 1 sec
	ping 127.0.0.1 -n 11 -w 1000
)
)