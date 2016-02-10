/*
 * CS164: Spring 2004
 * Programming Assignment 2
 *
 * The scanner definition for Cool.
 *
 */

import java_cup.runtime.Symbol;

%%
%line

/* Code enclosed in %{ %} is copied verbatim to the lexer class definition.
 * All extra variables/methods you want to use in the lexer actions go
 * here.  Don't remove anything that was here initially.  */
%{
    // Max size of string constants
    static int MAX_STR_CONST = 1024;

    // For assembling string constants
    StringBuffer string_buf = new StringBuffer();

    // For line numbers
    private int curr_lineno = 1;
    int get_curr_lineno() {
	return curr_lineno;
    }
    void update_curr_lineno() {
    curr_lineno++;
    return;
    }

    //String error Flags for reporting purposes
    private int null_terminator_flag = 0;
    private int length_flag = 0;
    private int newline_flag = 0;
    private int comment_nester_level = 0;

    //Setting the null terminator flag
    void set_null_terminator_flag(int val) {
        null_terminator_flag = val;
        return;
    }

    void comment_nester(String comparator) {
    if(comparator == "(*") {
        if(comment_nester_level == 0 && yystate() == YYINITIAL) {
        
        yybegin(LINE_COMMENT);
        }
        comment_nester_level++;
        return;
    }
    else {
        if(comparator == "*)" && comment_nester_level >= 2) {
        comment_nester_level--;
        return;
        }
        if(comparator == "*)" && comment_nester_level < 2 && yystate() == LINE_COMMENT) {
        comment_nester_level--;
       
        yybegin(YYINITIAL);
        return;
        }
    }
    return;
    }
    private AbstractSymbol filename;

    void set_filename(String fname) {
	filename = AbstractTable.stringtable.addString(fname);
    }

    AbstractSymbol curr_filename() {
	return filename;
    }

    /*
     * Add extra field and methods here.
     */
%}


/*  Code enclosed in %init{ %init} is copied verbatim to the lexer
 *  class constructor, all the extra initialization you want to do should
 *  go here. */
%init{
    // empty for now

%init}

/*  Code enclosed in %eofval{ %eofval} specifies java code that is
 *  executed when end-of-file is reached.  If you use multiple lexical
 *  states and want to do something special if an EOF is encountered in
 *  one of those states, place your code in the switch statement.
 *  Ultimately, you should return the EOF symbol, or your lexer won't
 *  work. */
%eofval{
    switch(yystate()) {
    case YYINITIAL:
	/* nothing special to do in the initial state */
	break;

/* If necessary, add code for other states here, e.g: */
    case LINE_COMMENT:
            yybegin(YYINITIAL);
            return new Symbol(TokenConstants.ERROR, "EOF in comment");
    case STRING_STATE:
        break;
 
    }
    return new Symbol(TokenConstants.EOF);
%eofval}

/* Do not modify the following two jlex directives */
%class CoolLexer
%cup


/* This defines a new start condition for line comments.
 * .
 * Hint: You might need additional start conditions. */
%state LINE_COMMENT
%state STRING_STATE  
/* Start condition for strings */
%state SINGLE_LINE_COMMENT 
/* -- Comment start */


/* Define lexical rules after the %% separator.  There is some code
 * provided for you that you may wish to use, but you may change it
 * if you like.
 * .
 * Some things you must fill-in (not necessarily a complete list):
 *   + Handle (* *) comments.  These comments should be properly nested.
 *   + Some additional multiple-character operators may be needed.  One
 *     (DARROW) is provided for you.
 *   + Handle strings.  String constants adhere to C syntax and may
 *     contain escape sequences: \c is accepted for all characters c
 *     (except for \n \t \b \f) in which case the result is c.
 * .
 * The complete Cool lexical specification is given in the Cool
 * Reference Manual (CoolAid).  Please be sure to look there. */
%%


<YYINITIAL>(\n)+               {
                                    string_buf.setLength(0); 
                                    string_buf.append(yytext());
                                    int count = 0;
                                    while(count < string_buf.length()){
                                    if(string_buf.charAt(count) == '\n') {
                                        update_curr_lineno();
                                        count++;
                                    }
                                    }
                                    string_buf.setLength(0);
                                    }
 <YYINITIAL>(\n)(\s*)               { 
                                    string_buf.setLength(0); 
                                    string_buf.append(yytext());
                                    int count = 0;
                                    while(count < string_buf.length()){
                                    if  (string_buf.charAt(count) == '\n') {
                                        update_curr_lineno();    
                                    }
                                    count++;
                                    }
                                    string_buf.setLength(0);
                                    }
<YYINITIAL>(\s*)(\n)+               {
                                    string_buf.setLength(0); 
                                    string_buf.append(yytext());
                                    int count = 0;
                                    while(count < string_buf.length()){
                                    if(string_buf.charAt(count) == '\n') {
                                        update_curr_lineno();
                                        
                                    }
                                    count++;
                                    }
                                    string_buf.setLength(0);
                                    }
<YYINITIAL>\s+	           { /*Do nothing*/ }
// <YYINITIAL>"0x0B"+                  {update_curr_lineno(); }

<YYINITIAL> "*)"                {return new Symbol(TokenConstants.ERROR, "Unmatched *)");}


<YYINITIAL>"(*"                 { comment_nester("(*");}
<YYINITIAL>"--"                 { yybegin(SINGLE_LINE_COMMENT);  }

/*LINE_COMMENT: The regular comment state denoted by (* *) */

<LINE_COMMENT>"(*"              {   
                                comment_nester("(*"); }
<LINE_COMMENT>"*)"              {   
                                 comment_nester("*)"); }

<LINE_COMMENT>(\n)             {
                                 update_curr_lineno(); }
<LINE_COMMENT>(\s*)(\n)              {   
                                update_curr_lineno(); }
<LINE_COMMENT>(\n)(\s*)           {
                                update_curr_lineno();  }
<LINE_COMMENT>[^"*)"\n]        {  /* System.out.println(yytext()); */  }






<YYINITIAL>\"                  {string_buf.setLength(0);  
                               yybegin(STRING_STATE); }




<YYINITIAL>"=>"		{ return new Symbol(TokenConstants.DARROW); }
<YYINITIAL>"<-"     {return new Symbol(TokenConstants.ASSIGN); } 




<YYINITIAL>[0-9][0-9]*  { /* Integers */
                          return new Symbol(TokenConstants.INT_CONST,
					    AbstractTable.inttable.addString(yytext())); }






<YYINITIAL>[Cc][Aa][Ss][Ee]	{ return new Symbol(TokenConstants.CASE); }
<YYINITIAL>[Cc][Ll][Aa][Ss][Ss] { return new Symbol(TokenConstants.CLASS); }
<YYINITIAL>[Ee][Ll][Ss][Ee]  	{ return new Symbol(TokenConstants.ELSE); }
<YYINITIAL>[Ee][Ss][Aa][Cc]	{ return new Symbol(TokenConstants.ESAC); }
<YYINITIAL>f[Aa][Ll][Ss][Ee]	{ return new Symbol(TokenConstants.BOOL_CONST, Boolean.FALSE); }
<YYINITIAL>[Ff][Ii]             { return new Symbol(TokenConstants.FI); }
<YYINITIAL>[Ii][Ff]  		{ return new Symbol(TokenConstants.IF); }
<YYINITIAL>[Ii][Nn]             { return new Symbol(TokenConstants.IN); }
<YYINITIAL>[Ii][Nn][Hh][Ee][Rr][Ii][Tt][Ss] { return new Symbol(TokenConstants.INHERITS); }
<YYINITIAL>[Ii][Ss][Vv][Oo][Ii][Dd] { return new Symbol(TokenConstants.ISVOID); }
<YYINITIAL>[Ll][Ee][Tt]         { return new Symbol(TokenConstants.LET); }
<YYINITIAL>[Ll][Oo][Oo][Pp]  	{ return new Symbol(TokenConstants.LOOP); }
<YYINITIAL>[Nn][Ee][Ww]		{ return new Symbol(TokenConstants.NEW); }
<YYINITIAL>[Nn][Oo][Tt] 	{ return new Symbol(TokenConstants.NOT); }
<YYINITIAL>[Oo][Ff]		{ return new Symbol(TokenConstants.OF); }
<YYINITIAL>[Pp][Oo][Oo][Ll]  	{ return new Symbol(TokenConstants.POOL); }
<YYINITIAL>[Tt][Hh][Ee][Nn]   	{ return new Symbol(TokenConstants.THEN); }
<YYINITIAL>t[Rr][Uu][Ee]	{ return new Symbol(TokenConstants.BOOL_CONST, Boolean.TRUE); }
<YYINITIAL>[Ww][Hh][Ii][Ll][Ee] { return new Symbol(TokenConstants.WHILE); }
<YYINITIAL>[A-Z][a-z|A-Z|_]* {return new Symbol(TokenConstants.TYPEID, AbstractTable.idtable.addString(yytext())); }
<YYINITIAL>[a-z][a-z|A-Z|_]*  {return new Symbol(TokenConstants.OBJECTID, AbstractTable.idtable.addString(yytext())); }
 
<YYINITIAL>"+"			{ return new Symbol(TokenConstants.PLUS); }
<YYINITIAL>"/"			{ return new Symbol(TokenConstants.DIV); }
<YYINITIAL>"-"			{ return new Symbol(TokenConstants.MINUS); }
<YYINITIAL>"*"			{ return new Symbol(TokenConstants.MULT); }
<YYINITIAL>"="			{ return new Symbol(TokenConstants.EQ); }
<YYINITIAL>"<"			{ return new Symbol(TokenConstants.LT); }
<YYINITIAL>"."			{ return new Symbol(TokenConstants.DOT); }
<YYINITIAL>"~"			{ return new Symbol(TokenConstants.NEG); }
<YYINITIAL>","			{ return new Symbol(TokenConstants.COMMA); }
<YYINITIAL>";"			{ return new Symbol(TokenConstants.SEMI); }
<YYINITIAL>":"			{ return new Symbol(TokenConstants.COLON); }
<YYINITIAL>"("			{ return new Symbol(TokenConstants.LPAREN); }
<YYINITIAL>")"			{ return new Symbol(TokenConstants.RPAREN); }
<YYINITIAL>"@"			{ return new Symbol(TokenConstants.AT); }
<YYINITIAL>"}"			{ return new Symbol(TokenConstants.RBRACE); }
<YYINITIAL>"{"			{ return new Symbol(TokenConstants.LBRACE); }
<YYINITIAL>\'           { return new Symbol(TokenConstants.ERROR, "'");}
<YYINITIAL>">"          { return new Symbol(TokenConstants.ERROR, ">"); }
<YYINITIAL>"["          { return new Symbol(TokenConstants.ERROR, "["); }
<YYINITIAL>"]"          { return new Symbol(TokenConstants.ERROR, "]"); }


/*STRING_STATE: The state used to form strings */

<STRING_STATE>\"                        {yybegin(YYINITIAL);

                                        if(null_terminator_flag == 1) {
                                        set_null_terminator_flag(0);
                                        return new Symbol(TokenConstants.ERROR, "Null terminator in string");
                                         }
                                        if(string_buf.length() > MAX_STR_CONST) {
                                        set_null_terminator_flag(0);
                                        return new Symbol(TokenConstants.ERROR, "String constant too long");
                                        }
                                        set_null_terminator_flag(0);
                                        return new Symbol(TokenConstants.STR_CONST, AbstractTable.stringtable.addString(string_buf.toString(), MAX_STR_CONST)); }

<STRING_STATE>[^\n\\\b\f\t\0]           {string_buf.append(yytext()); }
<STRING_STATE>\n+                        {
                                        update_curr_lineno();
                                        yybegin(YYINITIAL);
                                        return new Symbol(TokenConstants.ERROR, "Unterminated string constant");
                                        }
<STRING_STATE>\0|"null_character"       {
                                        set_null_terminator_flag(1);
                                        }

<STRING_STATE>\\n                      { 
                                        string_buf.append('\n'); }
<STRING_STATE>\\b                       {string_buf.append('\b'); }
<STRING_STATE>\\t                       {string_buf.append('\t'); }
<STRING_STATE>\\f                       {string_buf.append('\f'); }

<STRING_STATE>\\[^nbtf]                  { int indexer = 0;
                                            while (yytext().charAt(indexer) == '\\'){
                                            indexer++;
                                            }
                                            if(yytext().charAt(indexer) == '\n'){
                                            update_curr_lineno();
                                            }
                                            string_buf.append(yytext().charAt(indexer));

                                            }
<STRING_STATE>\\                       {/* do nothing do not append */}




/*SINGLE_LINE_COMMENT: This is for single line comments started by a -- */

<SINGLE_LINE_COMMENT>[^\n]              { /*do nothing do not make tokens for this */}
<SINGLE_LINE_COMMENT>\n+                 {yybegin(YYINITIAL);
                                        update_curr_lineno();
                                        }




[^]                { /*
                    *  This should be the very last rule and will match
                    *  everything not matched by other lexical rules.
                    */
                   System.err.println("LEXER BUG - UNMATCHED: " + yytext()); }
