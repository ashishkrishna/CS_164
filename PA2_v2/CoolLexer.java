/* The following code was generated by JFlex 1.6.1 */

/*
 * CS164: Spring 2004
 * Programming Assignment 2
 *
 * The scanner definition for Cool.
 *
 */

import java_cup.runtime.Symbol;


/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.6.1
 * from the specification file <tt>cool.lex</tt>
 */
class CoolLexer implements java_cup.runtime.Scanner {

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int YYINITIAL = 0;
  public static final int LINE_COMMENT = 2;
  public static final int STRING_STATE = 4;
  public static final int SINGLE_LINE_COMMENT = 6;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = { 
     0,  0,  1,  1,  2,  2,  3, 3
  };

  /** 
   * Translates characters to character classes
   */
  private static final String ZZ_CMAP_PACKED = 
    "\1\101\7\0\1\77\1\103\1\6\1\1\1\100\1\1\22\0\1\1"+
    "\1\0\1\7\4\0\1\73\1\4\1\3\1\2\1\61\1\65\1\5"+
    "\1\63\1\62\12\13\1\67\1\66\1\12\1\10\1\11\1\0\1\70"+
    "\1\41\1\37\1\40\1\53\1\43\1\22\1\37\1\47\1\45\2\37"+
    "\1\44\1\37\1\46\1\52\1\54\1\37\1\50\1\42\1\27\1\56"+
    "\1\51\1\55\3\37\1\74\1\76\1\75\1\0\1\57\1\0\1\15"+
    "\1\102\1\14\1\32\1\17\1\21\1\60\1\25\1\23\2\60\1\20"+
    "\1\60\1\24\1\31\1\33\1\60\1\26\1\16\1\35\1\36\1\30"+
    "\1\34\3\60\1\72\1\57\1\71\1\64\6\0\1\1\32\0\1\1"+
    "\u15df\0\1\1\u097f\0\13\1\35\0\2\1\5\0\1\1\57\0\1\1"+
    "\u0fa0\0\1\1\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\ud00f\0";

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\4\0\1\1\1\2\1\3\1\4\1\5\1\6\1\7"+
    "\1\10\1\11\1\12\1\13\5\14\1\15\2\14\1\15"+
    "\4\14\11\15\1\16\1\17\1\20\1\21\1\22\1\23"+
    "\1\24\1\25\1\26\1\27\1\30\1\31\1\32\1\33"+
    "\3\34\1\35\1\36\1\37\1\40\1\1\1\41\1\42"+
    "\1\43\1\44\1\45\1\46\1\47\1\50\1\51\1\52"+
    "\7\14\2\53\1\14\1\54\1\55\2\14\1\15\1\56"+
    "\4\14\7\15\1\54\1\55\2\15\1\56\2\15\1\57"+
    "\1\60\1\61\1\62\1\63\4\14\1\64\4\14\1\65"+
    "\1\66\1\15\4\14\4\15\1\64\3\15\1\65\1\66"+
    "\2\15\1\67\1\14\1\70\1\71\1\72\3\14\1\73"+
    "\1\74\1\14\1\73\1\75\1\67\1\15\1\70\1\71"+
    "\1\72\2\15\1\74\1\15\1\76\1\77\2\14\1\100"+
    "\1\76\2\15\1\100\1\101\1\14\1\101\1\15\1\14"+
    "\1\15\2\102";

  private static int [] zzUnpackAction() {
    int [] result = new int[175];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** 
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\104\0\210\0\314\0\u0110\0\u0154\0\u0198\0\u0110"+
    "\0\u01dc\0\u0220\0\u0110\0\u0264\0\u0110\0\u02a8\0\u02ec\0\u0330"+
    "\0\u0374\0\u03b8\0\u03fc\0\u0440\0\u0484\0\u04c8\0\u050c\0\u0550"+
    "\0\u0594\0\u05d8\0\u061c\0\u0660\0\u06a4\0\u06e8\0\u072c\0\u0770"+
    "\0\u07b4\0\u07f8\0\u083c\0\u0880\0\u08c4\0\u0110\0\u0110\0\u0110"+
    "\0\u0110\0\u0110\0\u0110\0\u0110\0\u0110\0\u0110\0\u0110\0\u0110"+
    "\0\u0110\0\u0110\0\u0110\0\u0110\0\u0908\0\u01dc\0\u0110\0\u0110"+
    "\0\u0110\0\u0110\0\u094c\0\u0110\0\u0110\0\u0110\0\u0110\0\u0110"+
    "\0\u0110\0\u0110\0\u0110\0\u0110\0\u0110\0\u0990\0\u09d4\0\u0a18"+
    "\0\u0a5c\0\u0aa0\0\u0ae4\0\u0b28\0\u0374\0\u06a4\0\u0b6c\0\u0374"+
    "\0\u0bb0\0\u0bf4\0\u0c38\0\u0c7c\0\u0374\0\u0cc0\0\u0d04\0\u0d48"+
    "\0\u0d8c\0\u0dd0\0\u0e14\0\u0e58\0\u0e9c\0\u0ee0\0\u0f24\0\u0f68"+
    "\0\u06a4\0\u0fac\0\u0ff0\0\u1034\0\u06a4\0\u1078\0\u10bc\0\u0110"+
    "\0\u0110\0\u0110\0\u0110\0\u0110\0\u1100\0\u1144\0\u1188\0\u11cc"+
    "\0\u0374\0\u1210\0\u1254\0\u1298\0\u12dc\0\u0374\0\u0374\0\u1320"+
    "\0\u1364\0\u13a8\0\u13ec\0\u1430\0\u1474\0\u14b8\0\u14fc\0\u1540"+
    "\0\u06a4\0\u1584\0\u15c8\0\u160c\0\u06a4\0\u06a4\0\u1650\0\u1694"+
    "\0\u0374\0\u16d8\0\u0374\0\u0374\0\u0374\0\u171c\0\u1760\0\u17a4"+
    "\0\u06a4\0\u0374\0\u17e8\0\u0374\0\u0374\0\u06a4\0\u182c\0\u06a4"+
    "\0\u06a4\0\u06a4\0\u1870\0\u18b4\0\u06a4\0\u18f8\0\u0374\0\u0374"+
    "\0\u193c\0\u1980\0\u0374\0\u06a4\0\u19c4\0\u1a08\0\u06a4\0\u0374"+
    "\0\u1a4c\0\u06a4\0\u1a90\0\u1ad4\0\u1b18\0\u0374\0\u06a4";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[175];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /** 
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\5\1\6\1\7\1\10\1\11\1\12\1\6\1\13"+
    "\1\14\1\15\1\16\1\17\1\20\2\21\1\22\1\23"+
    "\1\24\1\25\1\26\1\27\2\21\1\30\1\21\1\31"+
    "\1\21\1\32\1\33\1\34\1\21\1\35\1\36\2\35"+
    "\1\37\1\40\1\41\1\42\3\35\1\43\1\35\1\44"+
    "\1\45\1\35\1\5\1\21\1\46\1\47\1\50\1\51"+
    "\1\52\1\53\1\54\1\55\1\56\1\57\1\60\1\61"+
    "\1\62\1\63\1\5\1\6\1\5\1\21\1\6\2\64"+
    "\1\65\1\64\1\66\1\64\1\67\75\64\6\70\1\71"+
    "\1\72\66\70\1\73\2\5\1\74\1\70\1\75\6\76"+
    "\1\77\75\76\105\0\1\6\4\0\1\6\71\0\1\6"+
    "\2\0\1\6\3\0\1\100\102\0\1\101\106\0\1\102"+
    "\107\0\1\103\77\0\1\104\2\0\1\105\106\0\1\17"+
    "\103\0\2\21\1\106\2\21\1\107\20\21\1\106\2\21"+
    "\1\107\14\21\21\0\1\21\14\0\46\21\21\0\1\21"+
    "\14\0\3\21\1\110\1\21\1\111\21\21\1\110\1\21"+
    "\1\111\14\21\21\0\1\21\14\0\4\21\1\112\11\21"+
    "\1\113\11\21\1\112\6\21\1\113\6\21\21\0\1\21"+
    "\14\0\2\21\1\114\5\21\1\115\15\21\1\114\3\21"+
    "\1\115\13\21\21\0\1\21\14\0\10\35\1\116\21\35"+
    "\1\116\13\35\21\0\1\35\14\0\3\21\1\117\2\21"+
    "\2\120\1\21\1\121\15\21\1\117\3\21\1\121\12\21"+
    "\21\0\1\21\14\0\4\21\1\122\11\21\1\123\11\21"+
    "\1\122\6\21\1\123\6\21\21\0\1\21\14\0\12\35"+
    "\1\124\21\35\1\124\11\35\21\0\1\35\14\0\6\21"+
    "\2\125\36\21\21\0\1\21\14\0\16\21\1\126\20\21"+
    "\1\126\6\21\21\0\1\21\14\0\12\21\1\127\21\21"+
    "\1\127\11\21\21\0\1\21\14\0\12\21\1\130\1\131"+
    "\20\21\1\130\1\131\10\21\21\0\1\21\14\0\46\35"+
    "\21\0\1\35\14\0\2\35\1\132\2\35\1\133\20\35"+
    "\1\132\2\35\1\133\14\35\21\0\1\35\14\0\3\35"+
    "\1\134\1\35\1\135\21\35\1\134\1\35\1\135\14\35"+
    "\21\0\1\35\14\0\4\35\1\136\11\35\1\137\11\35"+
    "\1\136\6\35\1\137\6\35\21\0\1\35\14\0\3\35"+
    "\1\140\2\35\2\141\1\35\1\142\15\35\1\140\3\35"+
    "\1\142\12\35\21\0\1\35\14\0\4\35\1\143\11\35"+
    "\1\144\11\35\1\143\6\35\1\144\6\35\21\0\1\35"+
    "\14\0\6\35\2\145\36\35\21\0\1\35\14\0\16\35"+
    "\1\146\20\35\1\146\6\35\21\0\1\35\14\0\12\35"+
    "\1\147\21\35\1\147\11\35\21\0\1\35\4\0\1\150"+
    "\100\0\21\151\1\152\2\151\1\153\10\151\1\75\44\151"+
    "\1\154\1\151\13\0\3\21\1\155\23\21\1\155\16\21"+
    "\21\0\1\21\14\0\2\21\1\156\23\21\1\156\17\21"+
    "\21\0\1\21\14\0\2\21\1\157\23\21\1\157\17\21"+
    "\21\0\1\21\14\0\3\21\1\160\23\21\1\160\16\21"+
    "\21\0\1\21\14\0\14\21\1\161\5\21\1\161\23\21"+
    "\21\0\1\21\14\0\16\21\1\162\20\21\1\162\6\21"+
    "\21\0\1\21\14\0\5\21\1\163\23\21\1\163\14\21"+
    "\21\0\1\21\14\0\15\21\1\164\20\21\1\164\7\21"+
    "\21\0\1\21\14\0\12\21\1\165\21\21\1\165\11\21"+
    "\21\0\1\21\14\0\21\21\1\166\20\21\1\166\3\21"+
    "\21\0\1\21\14\0\14\21\1\167\5\21\1\167\23\21"+
    "\21\0\1\21\14\0\4\35\1\170\23\35\1\170\15\35"+
    "\21\0\1\35\14\0\16\21\1\171\20\21\1\171\6\21"+
    "\21\0\1\21\14\0\10\21\1\172\21\21\1\172\13\21"+
    "\21\0\1\21\14\0\4\21\1\173\23\21\1\173\15\21"+
    "\21\0\1\21\14\0\23\21\1\174\17\21\1\174\2\21"+
    "\21\0\1\21\14\0\3\35\1\175\23\35\1\175\16\35"+
    "\21\0\1\35\14\0\2\35\1\176\23\35\1\176\17\35"+
    "\21\0\1\35\14\0\2\35\1\177\23\35\1\177\17\35"+
    "\21\0\1\35\14\0\3\35\1\200\23\35\1\200\16\35"+
    "\21\0\1\35\14\0\14\35\1\201\5\35\1\201\23\35"+
    "\21\0\1\35\14\0\16\35\1\202\20\35\1\202\6\35"+
    "\21\0\1\35\14\0\15\35\1\203\20\35\1\203\7\35"+
    "\21\0\1\35\14\0\12\35\1\204\21\35\1\204\11\35"+
    "\21\0\1\35\14\0\21\35\1\205\20\35\1\205\3\35"+
    "\21\0\1\35\14\0\14\35\1\206\5\35\1\206\23\35"+
    "\21\0\1\35\14\0\16\35\1\207\20\35\1\207\6\35"+
    "\21\0\1\35\14\0\10\35\1\210\21\35\1\210\13\35"+
    "\21\0\1\35\14\0\4\21\1\211\23\21\1\211\15\21"+
    "\21\0\1\21\14\0\3\21\1\212\23\21\1\212\16\21"+
    "\21\0\1\21\14\0\1\21\1\213\23\21\1\213\20\21"+
    "\21\0\1\21\14\0\4\21\1\214\23\21\1\214\15\21"+
    "\21\0\1\21\14\0\20\21\1\215\20\21\1\215\4\21"+
    "\21\0\1\21\14\0\3\21\1\216\23\21\1\216\16\21"+
    "\21\0\1\21\14\0\16\21\1\217\20\21\1\217\6\21"+
    "\21\0\1\21\14\0\4\21\1\220\23\21\1\220\15\21"+
    "\21\0\1\21\14\0\11\35\1\221\21\35\1\221\12\35"+
    "\21\0\1\35\14\0\5\21\1\222\23\21\1\222\14\21"+
    "\21\0\1\21\14\0\5\21\1\223\23\21\1\223\14\21"+
    "\21\0\1\21\14\0\11\21\1\224\21\21\1\224\12\21"+
    "\21\0\1\21\14\0\4\21\1\225\23\21\1\225\15\21"+
    "\21\0\1\21\14\0\4\35\1\226\23\35\1\226\15\35"+
    "\21\0\1\35\14\0\3\35\1\227\23\35\1\227\16\35"+
    "\21\0\1\35\14\0\1\35\1\230\23\35\1\230\20\35"+
    "\21\0\1\35\14\0\4\35\1\231\23\35\1\231\15\35"+
    "\21\0\1\35\14\0\20\35\1\232\20\35\1\232\4\35"+
    "\21\0\1\35\14\0\16\35\1\233\20\35\1\233\6\35"+
    "\21\0\1\35\14\0\4\35\1\234\23\35\1\234\15\35"+
    "\21\0\1\35\14\0\5\35\1\235\23\35\1\235\14\35"+
    "\21\0\1\35\14\0\5\35\1\236\23\35\1\236\14\35"+
    "\21\0\1\35\14\0\3\21\1\237\23\21\1\237\16\21"+
    "\21\0\1\21\14\0\4\21\1\240\23\21\1\240\15\21"+
    "\21\0\1\21\14\0\10\21\1\241\21\21\1\241\13\21"+
    "\21\0\1\21\14\0\13\21\1\242\21\21\1\242\10\21"+
    "\21\0\1\21\14\0\4\21\1\243\23\21\1\243\15\21"+
    "\21\0\1\21\14\0\3\35\1\244\23\35\1\244\16\35"+
    "\21\0\1\35\14\0\10\35\1\245\21\35\1\245\13\35"+
    "\21\0\1\35\14\0\13\35\1\246\21\35\1\246\10\35"+
    "\21\0\1\35\14\0\4\35\1\247\23\35\1\247\15\35"+
    "\21\0\1\35\14\0\17\21\1\250\20\21\1\250\5\21"+
    "\21\0\1\21\14\0\10\21\1\251\21\21\1\251\13\21"+
    "\21\0\1\21\14\0\17\35\1\252\20\35\1\252\5\35"+
    "\21\0\1\35\14\0\10\35\1\253\21\35\1\253\13\35"+
    "\21\0\1\35\14\0\14\21\1\254\5\21\1\254\23\21"+
    "\21\0\1\21\14\0\14\35\1\255\5\35\1\255\23\35"+
    "\21\0\1\35\14\0\3\21\1\256\23\21\1\256\16\21"+
    "\21\0\1\21\14\0\3\35\1\257\23\35\1\257\16\35"+
    "\21\0\1\35\1\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[7004];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /* error messages for the codes above */
  private static final String ZZ_ERROR_MSG[] = {
    "Unknown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\4\0\1\11\2\1\1\11\2\1\1\11\1\1\1\11"+
    "\30\1\17\11\2\1\4\11\1\1\12\11\42\1\5\11"+
    "\103\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[175];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** the input device */
  private java.io.Reader zzReader;

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private char zzBuffer[] = new char[ZZ_BUFFERSIZE];

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /** number of newlines encountered up to the start of the matched text */
  private int yyline;

  /** the number of characters up to the start of the matched text */
  private int yychar;

  /**
   * the number of characters from the last newline up to the start of the 
   * matched text
   */
  private int yycolumn;

  /** 
   * zzAtBOL == true <=> the scanner is currently at the beginning of a line
   */
  private boolean zzAtBOL = true;

  /** zzAtEOF == true <=> the scanner is at the EOF */
  private boolean zzAtEOF;

  /** denotes if the user-EOF-code has already been executed */
  private boolean zzEOFDone;
  
  /** 
   * The number of occupied positions in zzBuffer beyond zzEndRead.
   * When a lead/high surrogate has been read from the input stream
   * into the final zzBuffer position, this will have a value of 1;
   * otherwise, it will have a value of 0.
   */
  private int zzFinalHighSurrogate = 0;

  /* user code: */
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


  /**
   * Creates a new scanner
   *
   * @param   in  the java.io.Reader to read input from.
   */
  CoolLexer(java.io.Reader in) {
      // empty for now

    this.zzReader = in;
  }


  /** 
   * Unpacks the compressed character translation table.
   *
   * @param packed   the packed character translation table
   * @return         the unpacked character translation table
   */
  private static char [] zzUnpackCMap(String packed) {
    char [] map = new char[0x110000];
    int i = 0;  /* index in packed string  */
    int j = 0;  /* index in unpacked array */
    while (i < 238) {
      int  count = packed.charAt(i++);
      char value = packed.charAt(i++);
      do map[j++] = value; while (--count > 0);
    }
    return map;
  }


  /**
   * Refills the input buffer.
   *
   * @return      <code>false</code>, iff there was new input.
   * 
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {

    /* first: make room (if you can) */
    if (zzStartRead > 0) {
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
      System.arraycopy(zzBuffer, zzStartRead,
                       zzBuffer, 0,
                       zzEndRead-zzStartRead);

      /* translate stored positions */
      zzEndRead-= zzStartRead;
      zzCurrentPos-= zzStartRead;
      zzMarkedPos-= zzStartRead;
      zzStartRead = 0;
    }

    /* is the buffer big enough? */
    if (zzCurrentPos >= zzBuffer.length - zzFinalHighSurrogate) {
      /* if not: blow it up */
      char newBuffer[] = new char[zzBuffer.length*2];
      System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
      zzBuffer = newBuffer;
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
    }

    /* fill the buffer with new input */
    int requested = zzBuffer.length - zzEndRead;
    int numRead = zzReader.read(zzBuffer, zzEndRead, requested);

    /* not supposed to occur according to specification of java.io.Reader */
    if (numRead == 0) {
      throw new java.io.IOException("Reader returned 0 characters. See JFlex examples for workaround.");
    }
    if (numRead > 0) {
      zzEndRead += numRead;
      /* If numRead == requested, we might have requested to few chars to
         encode a full Unicode character. We assume that a Reader would
         otherwise never return half characters. */
      if (numRead == requested) {
        if (Character.isHighSurrogate(zzBuffer[zzEndRead - 1])) {
          --zzEndRead;
          zzFinalHighSurrogate = 1;
        }
      }
      /* potentially more input available */
      return false;
    }

    /* numRead < 0 ==> end of stream */
    return true;
  }

    
  /**
   * Closes the input stream.
   */
  public final void yyclose() throws java.io.IOException {
    zzAtEOF = true;            /* indicate end of file */
    zzEndRead = zzStartRead;  /* invalidate buffer    */

    if (zzReader != null)
      zzReader.close();
  }


  /**
   * Resets the scanner to read from a new input stream.
   * Does not close the old reader.
   *
   * All internal variables are reset, the old input stream 
   * <b>cannot</b> be reused (internal buffer is discarded and lost).
   * Lexical state is set to <tt>ZZ_INITIAL</tt>.
   *
   * Internal scan buffer is resized down to its initial length, if it has grown.
   *
   * @param reader   the new input stream 
   */
  public final void yyreset(java.io.Reader reader) {
    zzReader = reader;
    zzAtBOL  = true;
    zzAtEOF  = false;
    zzEOFDone = false;
    zzEndRead = zzStartRead = 0;
    zzCurrentPos = zzMarkedPos = 0;
    zzFinalHighSurrogate = 0;
    yyline = yychar = yycolumn = 0;
    zzLexicalState = YYINITIAL;
    if (zzBuffer.length > ZZ_BUFFERSIZE)
      zzBuffer = new char[ZZ_BUFFERSIZE];
  }


  /**
   * Returns the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  public final String yytext() {
    return new String( zzBuffer, zzStartRead, zzMarkedPos-zzStartRead );
  }


  /**
   * Returns the character at position <tt>pos</tt> from the 
   * matched text. 
   * 
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch. 
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  public final char yycharat(int pos) {
    return zzBuffer[zzStartRead+pos];
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occured while scanning.
   *
   * In a wellformed scanner (no or only correct usage of 
   * yypushback(int) and a match-all fallback rule) this method 
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  } 


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Contains user EOF-code, which will be executed exactly once,
   * when the end of file is reached
   */
  private void zzDoEOF() throws java.io.IOException {
    if (!zzEOFDone) {
      zzEOFDone = true;
      yyclose();
    }
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public java_cup.runtime.Symbol next_token() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    char [] zzBufferL = zzBuffer;
    char [] zzCMapL = ZZ_CMAP;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      boolean zzR = false;
      int zzCh;
      int zzCharCount;
      for (zzCurrentPosL = zzStartRead  ;
           zzCurrentPosL < zzMarkedPosL ;
           zzCurrentPosL += zzCharCount ) {
        zzCh = Character.codePointAt(zzBufferL, zzCurrentPosL, zzMarkedPosL);
        zzCharCount = Character.charCount(zzCh);
        switch (zzCh) {
        case '\u000B':
        case '\u000C':
        case '\u0085':
        case '\u2028':
        case '\u2029':
          yyline++;
          zzR = false;
          break;
        case '\r':
          yyline++;
          zzR = true;
          break;
        case '\n':
          if (zzR)
            zzR = false;
          else {
            yyline++;
          }
          break;
        default:
          zzR = false;
        }
      }

      if (zzR) {
        // peek one character ahead if it is \n (if we have counted one line too much)
        boolean zzPeek;
        if (zzMarkedPosL < zzEndReadL)
          zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        else if (zzAtEOF)
          zzPeek = false;
        else {
          boolean eof = zzRefill();
          zzEndReadL = zzEndRead;
          zzMarkedPosL = zzMarkedPos;
          zzBufferL = zzBuffer;
          if (eof) 
            zzPeek = false;
          else 
            zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        }
        if (zzPeek) yyline--;
      }
      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;
  
      zzState = ZZ_LEXSTATE[zzLexicalState];

      // set up zzAction for empty match case:
      int zzAttributes = zzAttrL[zzState];
      if ( (zzAttributes & 1) == 1 ) {
        zzAction = zzState;
      }


      zzForAction: {
        while (true) {
    
          if (zzCurrentPosL < zzEndReadL) {
            zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
            zzCurrentPosL += Character.charCount(zzInput);
          }
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
              zzCurrentPosL += Character.charCount(zzInput);
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMapL[zzInput] ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
        zzAtEOF = true;
            zzDoEOF();
          {     switch(yystate()) {
    case YYINITIAL:
	/* nothing special to do in the initial state */
	break;

/* If necessary, add code for other states here, e.g: */
    case LINE_COMMENT:
            yybegin(YYINITIAL);
            return new Symbol(TokenConstants.ERROR, "EOF in comment");
    case STRING_STATE:
            yybegin(YYINITIAL);
            return new Symbol(TokenConstants.ERROR, "Unterminated string constant");
 
    }
    return new Symbol(TokenConstants.EOF);
 }
      }
      else {
        switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
          case 1: 
            { /*
                    *  This should be the very last rule and will match
                    *  everything not matched by other lexical rules.
                    */
                   System.err.println("LEXER BUG - UNMATCHED: " + yytext());
            }
          case 67: break;
          case 2: 
            { string_buf.setLength(0); 
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
          case 68: break;
          case 3: 
            { return new Symbol(TokenConstants.MULT);
            }
          case 69: break;
          case 4: 
            { return new Symbol(TokenConstants.RPAREN);
            }
          case 70: break;
          case 5: 
            { return new Symbol(TokenConstants.LPAREN);
            }
          case 71: break;
          case 6: 
            { return new Symbol(TokenConstants.MINUS);
            }
          case 72: break;
          case 7: 
            { string_buf.setLength(0);  
                               yybegin(STRING_STATE);
            }
          case 73: break;
          case 8: 
            { return new Symbol(TokenConstants.EQ);
            }
          case 74: break;
          case 9: 
            { return new Symbol(TokenConstants.ERROR, ">");
            }
          case 75: break;
          case 10: 
            { return new Symbol(TokenConstants.LT);
            }
          case 76: break;
          case 11: 
            { /* Integers */
                          return new Symbol(TokenConstants.INT_CONST,
					    AbstractTable.inttable.addString(yytext()));
            }
          case 77: break;
          case 12: 
            { return new Symbol(TokenConstants.OBJECTID, AbstractTable.idtable.addString(yytext()));
            }
          case 78: break;
          case 13: 
            { return new Symbol(TokenConstants.TYPEID, AbstractTable.idtable.addString(yytext()));
            }
          case 79: break;
          case 14: 
            { return new Symbol(TokenConstants.PLUS);
            }
          case 80: break;
          case 15: 
            { return new Symbol(TokenConstants.DIV);
            }
          case 81: break;
          case 16: 
            { return new Symbol(TokenConstants.DOT);
            }
          case 82: break;
          case 17: 
            { return new Symbol(TokenConstants.NEG);
            }
          case 83: break;
          case 18: 
            { return new Symbol(TokenConstants.COMMA);
            }
          case 84: break;
          case 19: 
            { return new Symbol(TokenConstants.SEMI);
            }
          case 85: break;
          case 20: 
            { return new Symbol(TokenConstants.COLON);
            }
          case 86: break;
          case 21: 
            { return new Symbol(TokenConstants.AT);
            }
          case 87: break;
          case 22: 
            { return new Symbol(TokenConstants.RBRACE);
            }
          case 88: break;
          case 23: 
            { return new Symbol(TokenConstants.LBRACE);
            }
          case 89: break;
          case 24: 
            { return new Symbol(TokenConstants.ERROR, "'");
            }
          case 90: break;
          case 25: 
            { return new Symbol(TokenConstants.ERROR, "[");
            }
          case 91: break;
          case 26: 
            { return new Symbol(TokenConstants.ERROR, "]");
            }
          case 92: break;
          case 27: 
            { return new Symbol(TokenConstants.ERROR, "\\");
            }
          case 93: break;
          case 28: 
            { 
            }
          case 94: break;
          case 29: 
            { update_curr_lineno();
            }
          case 95: break;
          case 30: 
            { string_buf.append(yytext());
            }
          case 96: break;
          case 31: 
            { update_curr_lineno();
                                        yybegin(YYINITIAL);
                                        return new Symbol(TokenConstants.ERROR, "Unterminated string constant");
            }
          case 97: break;
          case 32: 
            { yybegin(YYINITIAL);

                                        if(null_terminator_flag == 1) {
                                        set_null_terminator_flag(0);
                                        return new Symbol(TokenConstants.ERROR, "Null terminator in string");
                                         }
                                        if(string_buf.length() > MAX_STR_CONST) {
                                        set_null_terminator_flag(0);
                                        return new Symbol(TokenConstants.ERROR, "String constant too long");
                                        }
                                        set_null_terminator_flag(0);
                                        return new Symbol(TokenConstants.STR_CONST, AbstractTable.stringtable.addString(string_buf.toString(), MAX_STR_CONST));
            }
          case 98: break;
          case 33: 
            { set_null_terminator_flag(1);
            }
          case 99: break;
          case 34: 
            { string_buf.append('\t');
            }
          case 100: break;
          case 35: 
            { /*do nothing do not make tokens for this */
            }
          case 101: break;
          case 36: 
            { yybegin(YYINITIAL);
                                        update_curr_lineno();
            }
          case 102: break;
          case 37: 
            { return new Symbol(TokenConstants.ERROR, "Unmatched *)");
            }
          case 103: break;
          case 38: 
            { comment_nester("(*");
            }
          case 104: break;
          case 39: 
            { yybegin(SINGLE_LINE_COMMENT);
            }
          case 105: break;
          case 40: 
            { return new Symbol(TokenConstants.DARROW);
            }
          case 106: break;
          case 41: 
            { return new Symbol(TokenConstants.ASSIGN);
            }
          case 107: break;
          case 42: 
            { return new Symbol(TokenConstants.LE);
            }
          case 108: break;
          case 43: 
            { return new Symbol(TokenConstants.FI);
            }
          case 109: break;
          case 44: 
            { return new Symbol(TokenConstants.IF);
            }
          case 110: break;
          case 45: 
            { return new Symbol(TokenConstants.IN);
            }
          case 111: break;
          case 46: 
            { return new Symbol(TokenConstants.OF);
            }
          case 112: break;
          case 47: 
            { comment_nester("*)");
            }
          case 113: break;
          case 48: 
            { //int indexer = 0;
                                            //while (yytext().charAt(indexer) == '\\'){
                                            //indexer++;
                                            //}
                                            if(yytext().charAt(1) == '\n'){
                                            update_curr_lineno();
                                            }
                                            string_buf.append(yytext().charAt(1));
            }
          case 114: break;
          case 49: 
            { string_buf.append('\f');
            }
          case 115: break;
          case 50: 
            { string_buf.append('\n');
            }
          case 116: break;
          case 51: 
            { string_buf.append('\b');
            }
          case 117: break;
          case 52: 
            { return new Symbol(TokenConstants.LET);
            }
          case 118: break;
          case 53: 
            { return new Symbol(TokenConstants.NEW);
            }
          case 119: break;
          case 54: 
            { return new Symbol(TokenConstants.NOT);
            }
          case 120: break;
          case 55: 
            { return new Symbol(TokenConstants.CASE);
            }
          case 121: break;
          case 56: 
            { return new Symbol(TokenConstants.ESAC);
            }
          case 122: break;
          case 57: 
            { return new Symbol(TokenConstants.ELSE);
            }
          case 123: break;
          case 58: 
            { return new Symbol(TokenConstants.LOOP);
            }
          case 124: break;
          case 59: 
            { return new Symbol(TokenConstants.THEN);
            }
          case 125: break;
          case 60: 
            { return new Symbol(TokenConstants.POOL);
            }
          case 126: break;
          case 61: 
            { return new Symbol(TokenConstants.BOOL_CONST, Boolean.TRUE);
            }
          case 127: break;
          case 62: 
            { return new Symbol(TokenConstants.CLASS);
            }
          case 128: break;
          case 63: 
            { return new Symbol(TokenConstants.BOOL_CONST, Boolean.FALSE);
            }
          case 129: break;
          case 64: 
            { return new Symbol(TokenConstants.WHILE);
            }
          case 130: break;
          case 65: 
            { return new Symbol(TokenConstants.ISVOID);
            }
          case 131: break;
          case 66: 
            { return new Symbol(TokenConstants.INHERITS);
            }
          case 132: break;
          default:
            zzScanError(ZZ_NO_MATCH);
        }
      }
    }
  }


}
