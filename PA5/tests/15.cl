class Main inherits IO {
 foo(x : Int) : Int {
 	if isvoid(x)
 	then
 		x
 	else
 		~x
 	fi
 };
  main():Object {
    out_int(
		(if 5 < 7
		then
			if 5 <= 6
			then
				~foo(3)
			else 
				2
			fi
		else
			0
		fi)+2
	 )
  };
};