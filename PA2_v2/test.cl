1 This should probably work
2 The lexer just ignores syntax, so putting in things like { shouldn't matter- they just need to be matched by the lexer as tokens.

4 (* *) is okay, but *) is not
5 It should continue lexing
6 "This is a string"
7 "(* *) is still a string"
8 "*) \n \t \b \r"
9 'One of these shouldn't work'
000230
10 " 
Here is goes ""
"
"\n
"\n"
--(**)
(*--*)
(*(**)--*)
"null_character test"
(*
"\\"


            *)
''(***)
"\n \b  \0"
5
----------
(**------"---""\\""*)
\
\\
\\\
\\\\\
\\\\\\
\\t\t\t\t\v\f


(*
 *  A contribution from Anne Sheets (sheets@cory)
 *
 *  Tests the arithmetic operations and various other things
 *)

class A {

   var : Int <- 0;

   value() : Int { var };

   set_var(num : Int) : SELF_TYPE {
      {
         var <- num;
         self;
      }
   };

   method1(num : Int) : SELF_TYPE {  -- same
      self
   };

   method2(num1 : Int, num2 : Int) : B {  -- plus
      (let x : Int in
     {
            x <- num1 + num2;
        (new B).set_var(x);
     }
      )
   };

   method3(num : Int) : C {  -- negate
      (let x : Int in
     {
            x <- ~num;
        (new C).set_var(x);
     }
      )
   };

   method4(num1 : Int, num2 : Int) : D {  -- diff
            if num2 < num1 then
               (let x : Int in
          {
                     x <- num1 - num2;
                 (new D).set_var(x);
              }
               )
            else
               (let x : Int in
          {
                 x <- num2 - num1;
                 (new D).set_var(x);
          }
               )
            fi
   };

   method5(num : Int) : E {  -- factorial
      (let x : Int <- 1 in
     {
        (let y : Int <- 1 in
           while y <= num loop
              {
                     x <- x * y;
                 y <- y + 1;
              }
           pool
        );
        (new E).set_var(x);
     }
      )
   };

};

class B inherits A {  -- B is a number squared

   method5(num : Int) : E { -- square
      (let x : Int in
     {
            x <- num * num;
        (new E).set_var(x);
     }
      )
   };

};

class C inherits B {

   method6(num : Int) : A { -- negate
      (let x : Int in
         {
            x <- ~num;
        (new A).set_var(x);
         }
      )
   };

   method5(num : Int) : E {  -- cube
      (let x : Int in
     {
            x <- num * num * num;
        (new E).set_var(x);
     }
      )
   };

};

class D inherits B {  
        
   method7(num : Int) : Bool {  -- divisible by 3
      (let x : Int <- num in
            if x < 0 then method7(~x) else
            if 0 = x then true else
            if 1 = x then false else
        if 2 = x then false else
           method7(x - 3)
        fi fi fi fi
      )
   };

};

class E inherits D {

   method6(num : Int) : A {  -- division
      (let x : Int in
         {
            x <- num / 8;
        (new A).set_var(x);
         }
      )
   };

};

(* The following code is from atoi.cl in ~cs164/examples *)

(*
   The class A2I provides integer-to-string and string-to-integer
conversion routines.  To use these routines, either inherit them
in the class where needed, have a dummy variable bound to
something of type A2I, or simpl write (new A2I).method(argument).
*)


(*
   c2i   Converts a 1-character string to an integer.  Aborts
         if the string is not "0" through "9"
*)
class A2I {

     c2i(char : String) : Int {
    if char = "0" then 0 else
    if char = "1" then 1 else
    if char = "2" then 2 else
        if char = "3" then 3 else
        if char = "4" then 4 else
        if char = "5" then 5 else
        if char = "6" then 6 else
        if char = "7" then 7 else
        if char = "8" then 8 else
        if char = "9" then 9 else
        { abort(); 0; }  (* the 0 is needed to satisfy the
                  typchecker *)
        fi fi fi fi fi fi fi fi fi fi
     };

(*
   i2c is the inverse of c2i.
*)
     i2c(i : Int) : String {
    if i = 0 then "0" else
    if i = 1 then "1" else
    if i = 2 then "2" else
    if i = 3 then "3" else
    if i = 4 then "4" else
    if i = 5 then "5" else
    if i = 6 then "6" else
    if i = 7 then "7" else
    if i = 8 then "8" else
    if i = 9 then "9" else
    { abort(); ""; }  -- the "" is needed to satisfy the typchecker
        fi fi fi fi fi fi fi fi fi fi
     };

(*
   a2i converts an ASCII string into an integer.  The empty string
is converted to 0.  Signed and unsigned strings are handled.  The
method aborts if the string does not represent an integer.  Very
long strings of digits produce strange answers because of arithmetic 
overflow.

*)
     a2i(s : String) : Int {
        if s.length() = 0 then 0 else
    if s.substr(0,1) = "-" then ~a2i_aux(s.substr(1,s.length()-1)) else
        if s.substr(0,1) = "+" then a2i_aux(s.substr(1,s.length()-1)) else
           a2i_aux(s)
        fi fi fi
     };

(* a2i_aux converts the usigned portion of the string.  As a
   programming example, this method is written iteratively.  *)


     a2i_aux(s : String) : Int {
    (let int : Int <- 0 in  
           {    
               (let j : Int <- s.length() in
              (let i : Int <- 0 in
            while i < j loop
            {
                int <- int * 10 + c2i(s.substr(i,1));
                i <- i + 1;
            }
            pool
          )
           );
              int;
        }
        )
     };
"This is a \
string"
"\a"
"\f"
"\1"
"\@"
"\f"
--
(*