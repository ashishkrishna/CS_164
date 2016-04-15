class Main inherits IO {
  main():Object {
    out_int(
		(if 5 = 5
		then
			if 9 <= 6
			then
				if 8 < 7
				then
					101
				else
					100
				fi
			else 
				201
			fi
		else
			0
		fi)+2
	 )
  };
};