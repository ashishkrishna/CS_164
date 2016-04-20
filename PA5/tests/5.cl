-- Case branches are tested from most specific to most generic.


class Main inherits IO
{
  main() : Object
  {
    let thing : Int <- (new Int) in
      case thing of
	i : Object => out_string( "obj\n" );
	b : Int => out_string( "int\n" );
      esac
  };
};
