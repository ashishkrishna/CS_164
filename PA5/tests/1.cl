class Main inherits IO {
  test : String;
  main():Object {
    {
    while not(test = "x") loop {
	out_string(">");
    test <- in_string();
    if test = "d" then out_string("whassup?!\n") else test fi;
    if test = "e" then out_string("whassup2?!\n") else test fi;
    if test = "f" then out_string("whassup3?!\n") else out_string("nil3\n") fi;
} pool;
}
  };
};
