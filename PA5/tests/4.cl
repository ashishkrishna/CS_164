-- Lets can be nested

class Main inherits IO
{
  print(x:Int) : Object
  { {
     out_int(x);
     out_string("\n");
    }
  };

  main() : Object
  {
    let foo : Int <- 5 in 
       let foo: Int <- ~1 in
                let bar : Int <- 6 in  
                   let yota : Int <- 7 in
                        let gimel : Int <- 8 in
                          let bin : Int <- 15 in 
                            
                             print(foo+yota+gimel+bin) -- prints 1
          
  };
};
