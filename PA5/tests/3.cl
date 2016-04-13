-- Arithmetic operators evaluate their arguments left to right.


class Main inherits IO
{
  recite( value : Int ) : Int
  {
    {
      out_int( value*2 );
      out_string( "\n" );
      value;
    }
  };

  foo (value : Int) : Int
  {
    {
      while (value < 6)  loop {
        
        out_int(value);
        value <- value + 1;
      } pool;
      out_int(value);
      value;
      }
      
    
  };

  main() : Object
  {
    {
    foo(2)+6;
    }
  };
};