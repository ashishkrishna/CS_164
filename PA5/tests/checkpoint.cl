class Main inherits IO {
  main():Object {
    out_int(
		(if 2 < 8
		then
			if 5 < 6
			then 
			  5
			 else
			   3
			 fi
		else
			0
		fi)+2
	 )
  };
};
