class Foo23 {
  testee : Int <- 5;
  bar() : Int {
    testee
  };
 
  badz() : Int {
    4
  };

   bar8s() : Int {
    3
  };
};

class Baz inherits Foo23 {
  trye : Int <- 6;
 
  bar8s() : Int {
    testee
  };

   bar() : Int { 
    let x : SELF_TYPE <- (new SELF_TYPE) in {
      x.bar8s();
      6;
  }
};
};


class Main inherits IO {

  main() : Int {	-- main() is an atrophied method so we can parse. 
    {
     let x : Baz <- (new Baz) in {
      out_int(x.bar8s());
      out_int(x.bar8s());
      6;
     };
     let y : Foo23 <- (new Foo23) in {
      out_int(y.bar8s());
      out_int(y.bar());
      6;
     };
     let z : Baz <- (new Baz) in {
      out_int(z.bar8s());
      out_int(z.bar());
      6;
     };
    }
  };
};