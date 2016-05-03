class Main inherits IO {
  test : String;
  main():Object {
    {
   
	out_string(">");
    test <- in_string();
    if test = "d" then let a : Int <- 34 in let b : Int <- 56 in {out_int(a); out_int(b); } else test fi;
   
}
  };
};
