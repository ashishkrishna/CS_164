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

  foo (value : Int, bar : Int) : Int
  {
    {
      while (value < 6)  loop {
        bar <- 0;
        while(bar < 10) loop {
        bar <- bar + 1;
        out_int(value+bar);
        out_string("\n");
        } pool;
        value <- value + 1;
      } pool;
      out_int(value);
      value;
      }
      
    
  };

  main() : Object
  {
    {
    foo(2, 5)+6;
    }
  };
};