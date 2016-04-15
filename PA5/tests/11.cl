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

  foo (value : Int, bar : Int, gimel : Int) : Int
  {
    {
      while (value < 6)  loop {
        bar <- 0;
        while(bar <= 10) loop {
         gimel <- 0;
        while(gimel <= 7) loop {
        out_int(value+bar+gimel);
        out_string("\n");
        gimel <- gimel + 1;
        } pool;
        bar <- bar + 1;
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
    foo(2, 3, 4)+6;
    }
  };
};