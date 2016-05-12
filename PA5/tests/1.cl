class Main inherits IO {
  test : String;
  main():Object {
    {
   
	out_string(">");
    test <- in_string();
    if test = "death" then let a : Int <- 34 in let b : String <- "Hello" in {out_string(test.substr(2,3)); out_string(b.substr(2,3)); } else test fi;
   
}
  };
};
