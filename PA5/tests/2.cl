class Main inherits IO {

   foo(x:Int, y:Int) : Object {
      { out_int(y);
        out_int(x); 
        x <- 6; 
        out_int(x); 
       (* x <- x * x; *)
       (*out_int(x);  *)
      }
   };

   main() : Object {
     foo(4, 5)
   };
};
   
