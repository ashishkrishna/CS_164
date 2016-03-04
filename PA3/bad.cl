
(*
 *  execute "coolc bad.cl" to see the error messages that the coolc parser
 *  generates
 *
 *  execute "./myparser bad.cl" to see the error messages that your parser
 *  generates
 *)

(* no error *)

class A {
};
(*(* This is a weird case*)
class A {
   set_var(num : Int) : SELF_TYPE {
      {
         var,num,self,foo
      }
   };
};

class A {
   set_var(num : Int) : SELF_TYPE {
      {
     let a:int in 1;
     let a:int, b:Int in 1;
      }
   };
};

class A {
   set_var(num : Int) : SELF_TYPE {
      {

      }
   };
}; *)

class A {
   set_var(num : Int) : SELF_TYPE {
      {
         let a:Int <- ,"banana" in 1;
      }
   };
};
(* error:  b is not a type identifier *)
Class b inherits A {
};

(* error:  a is not a type identifier *)
Class C inherits a {
};

(* error:  keyword inherits is misspelled *)
Class D inherts A {
};

(* error:  closing brace is missing *)
Class E inherits A {
;

class A {
    bar():String {
        {
            5 + 6
        };
        {
            5 + 8
        }
    };

    baz():String { let x:Int <- 5, y:Int <- 6, k:Int in x};
};
