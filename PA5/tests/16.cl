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
        if (value/2 = 1)
            then
            value <- value + 2
            else
            value <- value + 1
            fi;
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