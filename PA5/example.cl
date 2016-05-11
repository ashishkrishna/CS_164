

class Main {
  main():Int { { (new BetterIO).out(new BetterIO); 3;} };
};

class BetterIO inherits IO {
	out(a:Object) : SELF_TYPE {
		case a of 
		i : Int => out_int(i);
		s : String => out_string(s);
		esac
	};
};

