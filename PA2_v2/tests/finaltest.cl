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
"\n \b	\0"
5
----------
(**------"---""\\""*)
\
\\
\\\
\\\\\
\\\\\\
\\t\t\t\t\v\f
"This is a \
string"
"\a"
"\f"
"\1"
"\@"
"\f"
--
(*
