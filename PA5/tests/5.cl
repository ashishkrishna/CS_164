-- Case branches are tested from most specific to most generic.


class Main inherits IO
{
  main() : Object
  {
    let thing : Bool <- (new Bool) in
      case thing of
	i : String => out_string( "string\n" );
	b : Int => out_string( "int\n" );
      esac
  };
};
