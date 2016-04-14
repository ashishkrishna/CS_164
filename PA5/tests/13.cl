class Main inherits IO {
  main():Object {
    out_int(
		(if not false
		then
			if 5 <= 6
			then
				4
			else 
				2
			fi
		else
			0
		fi)+2
	 )
  };
};