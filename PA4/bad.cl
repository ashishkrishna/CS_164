class C {
	a : Int;
	b : Bool;
	init(x : Int, y : Bool) : C {
           {
		a <- x;
		b <- y;
		self;
           }
	};
};

Class Main {
	main():C {
	 {
	  (new C).init(1,1);
	  (new C).init(1,true,3);
	  (new C).iinit(1,true);
	  (new C);
	 }
	};
};

class Main inherits IO{
 main(): Object {{ 
                    let x:Int <- "This should fail" in x; 
                }};
};

class Main inherits IO{
 main(): Object {{ 
                    let x:Bool <- "This should fail" in x; 
                }};
};

class A {
	some_A() : A {new A};
};

class Main inherits IO{
	main(): Object {(new B).some_A().id_b()};
};

class B inherits A {
	id_b : Int <- {007};
};

class A {
	some_A() : A {new A};
};

class Main inherits IO{
	main(): Object {(new B).some_A().id_b()};
};

class B inherits A {
	id_b : Int <- {007;};
};

class A {
	some_A() : A {new A};
};

class Main inherits IO{
	main(): Object {(new B).some_A().id_b()};
};

class B inherits A {
	id_b : Int <- {007;};
};

class Parent inherits Child {
  next() : Parent { self };
};

class Child inherits Parent {
};

class Main {
  scan(y : Child) : Object {{
    let x : Parent <- y in
      while not (isvoid x) loop
        x <- x.next ()
      pool;
  }};

  main() : Object {
    scan(new Child)
  };
};
