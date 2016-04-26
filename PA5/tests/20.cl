class Foo {
  testee : Int <- 5;
  bar() : Int {
    testee
  };
 
  badz() : Int {
    4
  };

   bars() : Int {
    3
  };
};

class Baz inherits Foo {
  trye : Int <- 6;
 
  bars() : Int {
    testee
  };

   bar() : Int {
    trye
  };
};


class Main inherits IO {

  main() : Int {	-- main() is an atrophied method so we can parse. 
    {
     let x : Foo <- (new Foo) in {
      out_int(x.bars());
      out_int(x.bar());
      6;
     };
    }
  };
};