// -*- mode: java -*- 
//
// file: cool-tree.m4
//
// This file defines the AST
//
//////////////////////////////////////////////////////////



import java.util.Enumeration;
import java.io.PrintStream;
import java.util.Vector;
import java.util.Stack;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.regex.Matcher;



/** Defines simple phylum Program */

abstract class Program extends TreeNode {
    protected Program(int lineNumber) {
        super(lineNumber);
    }
    public abstract void dump_with_types(PrintStream out, int n);
    public abstract void semant();
    public abstract void cgen(PrintStream s);

}


/** Defines simple phylum Class_ */
abstract class Class_ extends TreeNode {
    protected Class_(int lineNumber) {
        super(lineNumber);
    }
    public abstract void dump_with_types(PrintStream out, int n);
    public abstract AbstractSymbol getName();
    public abstract AbstractSymbol getParent();
    public abstract AbstractSymbol getFilename();
    public abstract Features getFeatures();

}


/** Defines list phylum Classes
    <p>
    See <a href="ListNode.html">ListNode</a> for full documentation. */
class Classes extends ListNode {
    public final static Class elementClass = Class_.class;
    /** Returns class of this lists's elements */
    public Class getElementClass() {
        return elementClass;
    }
    protected Classes(int lineNumber, Vector elements) {
        super(lineNumber, elements);
    }
    /** Creates an empty "Classes" list */
    public Classes(int lineNumber) {
        super(lineNumber);
    }
    /** Appends "Class_" element to this list */
    public Classes appendElement(TreeNode elem) {
        addElement(elem);
        return this;
    }
    public TreeNode copy() {
        return new Classes(lineNumber, copyElements());
    }
}


/** Defines simple phylum Feature */
abstract class Feature extends TreeNode {
    protected Feature(int lineNumber) {
        super(lineNumber);
    }
    public abstract void dump_with_types(PrintStream out, int n);

}


/** Defines list phylum Features
    <p>
    See <a href="ListNode.html">ListNode</a> for full documentation. */
class Features extends ListNode {
    public final static Class elementClass = Feature.class;
    /** Returns class of this lists's elements */
    public Class getElementClass() {
        return elementClass;
    }
    protected Features(int lineNumber, Vector elements) {
        super(lineNumber, elements);
    }
    /** Creates an empty "Features" list */
    public Features(int lineNumber) {
        super(lineNumber);
    }
    /** Appends "Feature" element to this list */
    public Features appendElement(TreeNode elem) {
        addElement(elem);
        return this;
    }
    public TreeNode copy() {
        return new Features(lineNumber, copyElements());
    }
}


/** Defines simple phylum Formal */
abstract class Formal extends TreeNode {
    protected Formal(int lineNumber) {
        super(lineNumber);
    }
    public abstract void dump_with_types(PrintStream out, int n);

}


/** Defines list phylum Formals
    <p>
    See <a href="ListNode.html">ListNode</a> for full documentation. */
class Formals extends ListNode {
    public final static Class elementClass = Formal.class;
    /** Returns class of this lists's elements */
    public Class getElementClass() {
        return elementClass;
    }
    protected Formals(int lineNumber, Vector elements) {
        super(lineNumber, elements);
    }
    /** Creates an empty "Formals" list */
    public Formals(int lineNumber) {
        super(lineNumber);
    }
    /** Appends "Formal" element to this list */
    public Formals appendElement(TreeNode elem) {
        addElement(elem);
        return this;
    }
    public TreeNode copy() {
        return new Formals(lineNumber, copyElements());
    }
}


/** Defines simple phylum Expression */
abstract class Expression extends TreeNode {
    protected Expression(int lineNumber) {
        super(lineNumber);
    }
    private AbstractSymbol type = null; 
    public static CgenNode root = null;
    public static String this_class = null;                                
    public AbstractSymbol get_type() { return type; }           
    public Expression set_type(AbstractSymbol s) { type = s; return this; } 
    public abstract void dump_with_types(PrintStream out, int n);
    public void dump_type(PrintStream out, int n) {
        if (type != null)
            { out.println(Utilities.pad(n) + ": " + type.getString()); }
        else
            { out.println(Utilities.pad(n) + ": _no_type"); }
    }
    public int code(PrintStream s, int index, SymbolTable sym) { return index; }
    public void set_root(CgenNode this_root) { Expression.root = this_root; }
    public CgenNode get_root() { return Expression.root; }

}


/** Defines list phylum Expressions
    <p>
    See <a href="ListNode.html">ListNode</a> for full documentation. */
class Expressions extends ListNode {
    public final static Class elementClass = Expression.class;
    /** Returns class of this lists's elements */
    public Class getElementClass() {
        return elementClass;
    }
    protected Expressions(int lineNumber, Vector elements) {
        super(lineNumber, elements);
    }
    /** Creates an empty "Expressions" list */
    public Expressions(int lineNumber) {
        super(lineNumber);
    }
    /** Appends "Expression" element to this list */
    public Expressions appendElement(TreeNode elem) {
        addElement(elem);
        return this;
    }
    public TreeNode copy() {
        return new Expressions(lineNumber, copyElements());
    }
}


/** Defines simple phylum Case */
abstract class Case extends TreeNode {
    protected Case(int lineNumber) {
        super(lineNumber);
    }
    public abstract void dump_with_types(PrintStream out, int n);

}


/** Defines list phylum Cases
    <p>
    See <a href="ListNode.html">ListNode</a> for full documentation. */
class Cases extends ListNode {
    public final static Class elementClass = Case.class;
    /** Returns class of this lists's elements */
    public Class getElementClass() {
        return elementClass;
    }
    protected Cases(int lineNumber, Vector elements) {
        super(lineNumber, elements);
    }
    /** Creates an empty "Cases" list */
    public Cases(int lineNumber) {
        super(lineNumber);
    }
    /** Appends "Case" element to this list */
    public Cases appendElement(TreeNode elem) {
        addElement(elem);
        return this;
    }
    public TreeNode copy() {
        return new Cases(lineNumber, copyElements());
    }
}


/** Defines AST constructor 'programc'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class programc extends Program {
    protected Classes classes;
    /** Creates "programc" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for classes
      */
    public programc(int lineNumber, Classes a1) {
        super(lineNumber);
        classes = a1;
    }
    public TreeNode copy() {
        return new programc(lineNumber, (Classes)classes.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "programc\n");
        classes.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_program");
        for (Enumeration e = classes.getElements(); e.hasMoreElements(); ) {
	    ((Class_)e.nextElement()).dump_with_types(out, n + 2);
        }
    }
    /** This method is the entry point to the semantic checker.  You will
        need to complete it in programming assignment 4.
	<p>
        Your checker should do the following two things:
	<ol>
	<li>Check that the program is semantically correct
	<li>Decorate the abstract syntax tree with type information
        by setting the type field in each Expression node.
        (see tree.h)
	</ol>
	<p>
	You are free to first do (1) and make sure you catch all semantic
    	errors. Part (2) can be done in a second stage when you want
	to test the complete compiler.
    */
    public void semant() {
	/* ClassTable constructor may do some semantic analysis */
	ClassTable classTable = new ClassTable(classes);
	
	/* some semantic analysis code may go here */

	if (classTable.errors()) {
	    System.err.println("Compilation halted due to static semantic errors.");
	    System.exit(1);
	}
    }
    /** This method is the entry point to the code generator.  All of the work
      * of the code generator takes place within CgenClassTable constructor.
      * @param s the output stream 
      * @see CgenClassTable
      * */
    public void cgen(PrintStream s) {
	CgenClassTable codegen_classtable = new CgenClassTable(classes, s);

    }

}


/** Defines AST constructor 'class_c'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class class_c extends Class_ {
    protected AbstractSymbol name;
    protected AbstractSymbol parent;
    protected Features features;
    protected AbstractSymbol filename;
    /** Creates "class_c" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for name
      * @param a1 initial value for parent
      * @param a2 initial value for features
      * @param a3 initial value for filename
      */
    public class_c(int lineNumber, AbstractSymbol a1, AbstractSymbol a2, Features a3, AbstractSymbol a4) {
        super(lineNumber);
        name = a1;
        parent = a2;
        features = a3;
        filename = a4;
    }
    public TreeNode copy() {
        return new class_c(lineNumber, copy_AbstractSymbol(name), copy_AbstractSymbol(parent), (Features)features.copy(), copy_AbstractSymbol(filename));
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "class_c\n");
        dump_AbstractSymbol(out, n+2, name);
        dump_AbstractSymbol(out, n+2, parent);
        features.dump(out, n+2);
        dump_AbstractSymbol(out, n+2, filename);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_class");
        dump_AbstractSymbol(out, n + 2, name);
        dump_AbstractSymbol(out, n + 2, parent);
        out.print(Utilities.pad(n + 2) + "\"");
        Utilities.printEscapedString(out, filename.getString());
        out.println("\"\n" + Utilities.pad(n + 2) + "(");
        for (Enumeration e = features.getElements(); e.hasMoreElements();) {
	    ((Feature)e.nextElement()).dump_with_types(out, n + 2);
        }
        out.println(Utilities.pad(n + 2) + ")");
    }
    public AbstractSymbol getName()     { return name; }
    public AbstractSymbol getParent()   { return parent; }
    public AbstractSymbol getFilename() { return filename; }
    public Features getFeatures()       { return features; }

}


/** Defines AST constructor 'method'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class method extends Feature {
    protected AbstractSymbol name;
    protected Formals formals;
    protected AbstractSymbol return_type;
    protected Expression expr;
    /** Creates "method" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for name
      * @param a1 initial value for formals
      * @param a2 initial value for return_type
      * @param a3 initial value for expr
      */
    public method(int lineNumber, AbstractSymbol a1, Formals a2, AbstractSymbol a3, Expression a4) {
        super(lineNumber);
        name = a1;
        formals = a2;
        return_type = a3;
        expr = a4;
    }
    public TreeNode copy() {
        return new method(lineNumber, copy_AbstractSymbol(name), (Formals)formals.copy(), copy_AbstractSymbol(return_type), (Expression)expr.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "method\n");
        dump_AbstractSymbol(out, n+2, name);
        formals.dump(out, n+2);
        dump_AbstractSymbol(out, n+2, return_type);
        expr.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_method");
        dump_AbstractSymbol(out, n + 2, name);
        for (Enumeration e = formals.getElements(); e.hasMoreElements();) {
	    ((Formal)e.nextElement()).dump_with_types(out, n + 2);
        }
        dump_AbstractSymbol(out, n + 2, return_type);
	expr.dump_with_types(out, n + 2);
    }

}


/** Defines AST constructor 'attr'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class attr extends Feature {
    protected AbstractSymbol name;
    protected AbstractSymbol type_decl;
    protected Expression init;
    protected String this_class;
    /** Creates "attr" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for name
      * @param a1 initial value for type_decl
      * @param a2 initial value for init
      */
    public attr(int lineNumber, AbstractSymbol a1, AbstractSymbol a2, Expression a3) {
        super(lineNumber);
        name = a1;
        type_decl = a2;
        init = a3;
    }
    public TreeNode copy() {
        return new attr(lineNumber, copy_AbstractSymbol(name), copy_AbstractSymbol(type_decl), (Expression)init.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "attr\n");
        dump_AbstractSymbol(out, n+2, name);
        dump_AbstractSymbol(out, n+2, type_decl);
        init.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_attr");
        dump_AbstractSymbol(out, n + 2, name);
        dump_AbstractSymbol(out, n + 2, type_decl);
	init.dump_with_types(out, n + 2);
    }

}


/** Defines AST constructor 'formalc'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class formalc extends Formal {
    protected AbstractSymbol name;
    protected AbstractSymbol type_decl;
    /** Creates "formalc" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for name
      * @param a1 initial value for type_decl
      */
    public formalc(int lineNumber, AbstractSymbol a1, AbstractSymbol a2) {
        super(lineNumber);
        name = a1;
        type_decl = a2;
    }
    public TreeNode copy() {
        return new formalc(lineNumber, copy_AbstractSymbol(name), copy_AbstractSymbol(type_decl));
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "formalc\n");
        dump_AbstractSymbol(out, n+2, name);
        dump_AbstractSymbol(out, n+2, type_decl);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_formal");
        dump_AbstractSymbol(out, n + 2, name);
        dump_AbstractSymbol(out, n + 2, type_decl);
    }

}


/** Defines AST constructor 'branch'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class branch extends Case {
    protected AbstractSymbol name;
    protected AbstractSymbol type_decl;
    protected Expression expr;
    /** Creates "branch" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for name
      * @param a1 initial value for type_decl
      * @param a2 initial value for expr
      */
    public branch(int lineNumber, AbstractSymbol a1, AbstractSymbol a2, Expression a3) {
        super(lineNumber);
        name = a1;
        type_decl = a2;
        expr = a3;
    }
    public TreeNode copy() {
        return new branch(lineNumber, copy_AbstractSymbol(name), copy_AbstractSymbol(type_decl), (Expression)expr.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "branch\n");
        dump_AbstractSymbol(out, n+2, name);
        dump_AbstractSymbol(out, n+2, type_decl);
        expr.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_branch");
        dump_AbstractSymbol(out, n + 2, name);
        dump_AbstractSymbol(out, n + 2, type_decl);
	expr.dump_with_types(out, n + 2);
    }

}


/** Defines AST constructor 'assign'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class assign extends Expression {
    protected AbstractSymbol name;
    protected Expression expr;
    /** Creates "assign" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for name
      * @param a1 initial value for expr
      */
    public assign(int lineNumber, AbstractSymbol a1, Expression a2) {
        super(lineNumber);
        name = a1;
        expr = a2;
    }
    public TreeNode copy() {
        return new assign(lineNumber, copy_AbstractSymbol(name), (Expression)expr.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "assign\n");
        dump_AbstractSymbol(out, n+2, name);
        expr.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_assign");
        dump_AbstractSymbol(out, n + 2, name);
	expr.dump_with_types(out, n + 2);
	dump_type(out, n);
    }
    /** Generates code for this expression.  This method is to be completed 
      * in programming assignment 5.  (You may add or remove parameters as
      * you wish.)
      * @param s the output stream 
      * */
    public int code(PrintStream s, int index, SymbolTable sym) {   
        expr.getClass().cast(expr);
        index = expr.code(s, index, sym);
        try {
        /*Access for assigning to local var/formal. Store at given Frame Pointer offset in lookup */
        StringSymbol aleph = (StringSymbol) AbstractTable.stringtable.lookup(name.toString());   
        Integer f = (Integer) (sym.lookup(aleph));
        CgenSupport.emitStore(CgenSupport.ACC, f, CgenSupport.FP, s);
    }
        catch(java.lang.NullPointerException r) {
            /*Check whether this is an attribute, if it is, then store at the attribute in the proto-Obj table*/
            StringSymbol aleph_1 = (StringSymbol) AbstractTable.stringtable.lookup(name.toString());
            Integer h = (Integer) CgenClassTable.attr_defs.lookup(aleph_1);
            CgenSupport.emitStore(CgenSupport.ACC, h, CgenSupport.SELF, s);
            if(Flags.cgen_Memmgr == Flags.GC_GENGC) {   //GenGC flag 
            CgenSupport.emitAddiu(CgenSupport.A1, CgenSupport.SELF, h*4, s); //Notify GenGC
            CgenSupport.emitJal("_GenGC_Assign", s);
        }
         
        }
        return index;
    }


}


/** Defines AST constructor 'static_dispatch'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class static_dispatch extends Expression {
    protected Expression expr;
    protected AbstractSymbol type_name;
    protected AbstractSymbol name;
    protected Expressions actual;
    /** Creates "static_dispatch" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for expr
      * @param a1 initial value for type_name
      * @param a2 initial value for name
      * @param a3 initial value for actual
      */
    public static_dispatch(int lineNumber, Expression a1, AbstractSymbol a2, AbstractSymbol a3, Expressions a4) {
        super(lineNumber);
        expr = a1;
        type_name = a2;
        name = a3;
        actual = a4;
    }
    public TreeNode copy() {
        return new static_dispatch(lineNumber, (Expression)expr.copy(), copy_AbstractSymbol(type_name), copy_AbstractSymbol(name), (Expressions)actual.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "static_dispatch\n");
        expr.dump(out, n+2);
        dump_AbstractSymbol(out, n+2, type_name);
        dump_AbstractSymbol(out, n+2, name);
        actual.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_static_dispatch");
	expr.dump_with_types(out, n + 2);
        dump_AbstractSymbol(out, n + 2, type_name);
        dump_AbstractSymbol(out, n + 2, name);
        out.println(Utilities.pad(n + 2) + "(");
        for (Enumeration e = actual.getElements(); e.hasMoreElements();) {
	    ((Expression)e.nextElement()).dump_with_types(out, n + 2);
        }
        out.println(Utilities.pad(n + 2) + ")");
	dump_type(out, n);
    }
    /** Generates code for this expression.  This method is to be completed 
      * in programming assignment 5.  (You may add or remove parameters as
      * you wish.)
      * @param s the output stream 
      * */
    public int code(PrintStream s, int index, SymbolTable sym) {
        int old_offset = CgenClassTable.frame_to_top_offset;
        for(Enumeration f = actual.getElements(); f.hasMoreElements();) {
            /*Load all the arguments*/
            Expression nxt = (Expression) f.nextElement();
            nxt.getClass().cast(nxt);
            index = nxt.code(s, index, sym);
            CgenSupport.emitStore(CgenSupport.ACC, 0, CgenSupport.SP, s);
            CgenSupport.emitAddiu (CgenSupport.SP, CgenSupport.SP, -4, s);
            CgenClassTable.frame_to_top_offset = CgenClassTable.frame_to_top_offset  - 4;
        }

            expr.getClass().cast(expr);
            if(!expr.get_type().toString().equals("SELF_TYPE")) {
                /*Evaluate the object calling the method */
                index = expr.code(s, index, sym);
             }
            if(expr.get_type().toString().equals("SELF_TYPE")) 
                CgenSupport.emitMove(CgenSupport.ACC, CgenSupport.SELF, s);
            CgenSupport.emitBne(CgenSupport.ACC, CgenSupport.ZERO, index, s);
            CgenSupport.emitLoadString(CgenSupport.ACC, (StringSymbol)AbstractTable.stringtable.lookup(0), s);
            CgenSupport.emitLoadImm(CgenSupport.T1, 4, s); 
            CgenSupport.emitJal("_dispatch_abort", s);
            StringSymbol string_ind = null;
            s.print(CgenSupport.LABEL_PREFIX+String.valueOf(index)+ CgenSupport.LABEL);
            //Get the method from the Type 
            CgenSupport.emitLoadAddress(CgenSupport.T2, type_name.getString()+CgenSupport.PROTOBJ_SUFFIX, s);
            CgenSupport.emitLoad(CgenSupport.T1, 2, CgenSupport.T2, s);
            Vector<String> methods_for_this_class = null;
            methods_for_this_class = CgenClassTable.virtual_disptbl.get(type_name.toString());
            Enumeration m = methods_for_this_class.elements();
            int ind = 0;
            while(m.hasMoreElements()){
                String next_elem = (String) m.nextElement();
                String pattern = "(([aA-zZ]([aA-zZ]|[\\d])*))\\.(([aA-zZ]([aA-zZ]|[\\d])*))"; //Grab the pattern [Class].[method]
                Pattern r = Pattern.compile(pattern);   
                Matcher q = r.matcher(next_elem);
                if(q.find() && name.toString().equals(q.group(4))) {
                    break;
                }
                ind++;
            }
            index++;
            CgenSupport.emitLoad(CgenSupport.T1, ind, CgenSupport.T1, s);
            CgenSupport.emitJalr(CgenSupport.T1, s);
            CgenClassTable.frame_to_top_offset = old_offset;
            return index;
    }


}


/** Defines AST constructor 'dispatch'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
 
class dispatch extends Expression {
    protected Expression expr;
    protected AbstractSymbol name;
    protected Expressions actual;
 
   


    /** Creates "dispatch" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for expr
      * @param a1 initial value for name
      * @param a2 initial value for actual
      */
    public dispatch(int lineNumber, Expression a1, AbstractSymbol a2, Expressions a3) {
        super(lineNumber);
        expr = a1;
        name = a2;
        actual = a3;
    }
    public TreeNode copy() {
        return new dispatch(lineNumber, (Expression)expr.copy(), copy_AbstractSymbol(name), (Expressions)actual.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "dispatch\n");
        expr.dump(out, n+2);
        dump_AbstractSymbol(out, n+2, name);
        actual.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_dispatch");
	expr.dump_with_types(out, n + 2);
        dump_AbstractSymbol(out, n + 2, name);
        out.println(Utilities.pad(n + 2) + "(");
        for (Enumeration e = actual.getElements(); e.hasMoreElements();) {
	    ((Expression)e.nextElement()).dump_with_types(out, n + 2);
        }
        out.println(Utilities.pad(n + 2) + ")");
	dump_type(out, n);
    }
    /** Generates code for this expression.  This method is to be completed 
      * in programming assignment 5.  (You may add or remove parameters as
      * you wish.)
      * @param s the output stream 
      * */
    public int code(PrintStream s, int index, SymbolTable sym) {
        int old_offset = CgenClassTable.frame_to_top_offset;
        for(Enumeration f = actual.getElements(); f.hasMoreElements();) {
             /*Load all the arguments*/
            Expression nxt = (Expression) f.nextElement();
            nxt.getClass().cast(nxt);
            index = nxt.code(s, index, sym);
            CgenSupport.emitStore(CgenSupport.ACC, 0, CgenSupport.SP, s);
            CgenSupport.emitAddiu (CgenSupport.SP, CgenSupport.SP, -4, s);
            CgenClassTable.frame_to_top_offset = CgenClassTable.frame_to_top_offset  - 4;
        }

            expr.getClass().cast(expr);
            if(!expr.get_type().toString().equals("SELF_TYPE")) {
                //Evaluate the object calling method
                index = expr.code(s, index, sym);
             }
            if(expr.get_type().toString().equals("SELF_TYPE")) 
                CgenSupport.emitMove(CgenSupport.ACC, CgenSupport.SELF, s);
            CgenSupport.emitBne(CgenSupport.ACC, CgenSupport.ZERO, index, s);
            CgenSupport.emitLoadString(CgenSupport.ACC, (StringSymbol)AbstractTable.stringtable.lookup(0), s);
            CgenSupport.emitLoadImm(CgenSupport.T1, 4, s); 
            CgenSupport.emitJal("_dispatch_abort", s);
            StringSymbol string_ind = null;
            s.print(CgenSupport.LABEL_PREFIX+String.valueOf(index)+ CgenSupport.LABEL);
            CgenSupport.emitLoad(CgenSupport.T1, 2, CgenSupport.ACC, s);
            Vector<String> methods_for_this_class = null;
            //Find the method to be called
            if(expr.get_type().toString().equals("SELF_TYPE")) 
                methods_for_this_class = CgenClassTable.virtual_disptbl.get(this.this_class);
            else 
                methods_for_this_class = CgenClassTable.virtual_disptbl.get(expr.get_type().toString());
            Enumeration m = methods_for_this_class.elements();
            int ind = 0;
            while(m.hasMoreElements()){
                String next_elem = (String) m.nextElement();
                String pattern = "(([aA-zZ]([aA-zZ]|[\\d])*))\\.(([aA-zZ]([aA-zZ]|[\\d])*))"; //Grab the pattern [Class].[method]
                Pattern r = Pattern.compile(pattern);   
                Matcher q = r.matcher(next_elem);
                if(q.find() && name.toString().equals(q.group(4))) {
                    break;
                }
                ind++;
            }
            index++;
            CgenSupport.emitLoad(CgenSupport.T1, ind, CgenSupport.T1, s);
            CgenSupport.emitJalr(CgenSupport.T1, s);
            CgenClassTable.frame_to_top_offset = old_offset;
            return index;
    }
}


/** Defines AST constructor 'cond'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class cond extends Expression {
    protected Expression pred;
    protected Expression then_exp;
    protected Expression else_exp;
    /** Creates "cond" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for pred
      * @param a1 initial value for then_exp
      * @param a2 initial value for else_exp
      */
    public cond(int lineNumber, Expression a1, Expression a2, Expression a3) {
        super(lineNumber);
        pred = a1;
        then_exp = a2;
        else_exp = a3;
    }
    public TreeNode copy() {
        return new cond(lineNumber, (Expression)pred.copy(), (Expression)then_exp.copy(), (Expression)else_exp.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "cond\n");
        pred.dump(out, n+2);
        then_exp.dump(out, n+2);
        else_exp.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_cond");
	pred.dump_with_types(out, n + 2);
	then_exp.dump_with_types(out, n + 2);
	else_exp.dump_with_types(out, n + 2);
	dump_type(out, n);
    }
    /** Generates code for this expression.  This method is to be completed 
      * in programming assignment 5.  (You may add or remove parameters as
      * you wish.)/
      * @param s the output stream 
      * */
    public int code(PrintStream s, int index, SymbolTable sym) {
        //Evaluate the boolean
        index = pred.code(s, index, sym);
        CgenSupport.emitLoad(CgenSupport.ACC, 3, CgenSupport.ACC, s);
        int saved_index = index;
        CgenSupport.emitBeqz(CgenSupport.ACC, saved_index, s);
        //Save labels for then and else
        index = index+2;
        index = then_exp.code(s, index, sym);
        CgenSupport.emitBranch(saved_index+1, s);
        s.print(CgenSupport.LABEL_PREFIX+String.valueOf(saved_index)+ CgenSupport.LABEL);
        index = else_exp.code(s, index, sym);
        s.print(CgenSupport.LABEL_PREFIX+String.valueOf(saved_index+1)+ CgenSupport.LABEL);
        return index;
    }


}


/** Defines AST constructor 'loop'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class loop extends Expression {
    protected Expression pred;
    protected Expression body;
    /** Creates "loop" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for pred
      * @param a1 initial value for body
      */
    public loop(int lineNumber, Expression a1, Expression a2) {
        super(lineNumber);
        pred = a1;
        body = a2;
    }
    public TreeNode copy() {
        return new loop(lineNumber, (Expression)pred.copy(), (Expression)body.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "loop\n");
        pred.dump(out, n+2);
        body.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_loop");
	pred.dump_with_types(out, n + 2);
	body.dump_with_types(out, n + 2);
	dump_type(out, n);
    }
    /** Generates code for this expression.  This method is to be completed 
      * in programming assignment 5.  (You may add or remove parameters as
      * you wish.)
      * @param s the output stream 
      * */
    public int code(PrintStream s, int index, SymbolTable sym) {
        int saved_index = index;
        s.print(CgenSupport.LABEL_PREFIX+String.valueOf(saved_index)+ CgenSupport.LABEL);
        index++;
        index = pred.code(s, index, sym);
        //Save label for exiting from loop
        int saved_index_2 = index;
        CgenSupport.emitLoad(CgenSupport.ACC, 3, CgenSupport.ACC, s);
        CgenSupport.emitBeqz(CgenSupport.ACC, saved_index_2, s);
        index++;
        index = body.code(s, index, sym);
        CgenSupport.emitBranch(saved_index, s);
        s.print(CgenSupport.LABEL_PREFIX+String.valueOf(saved_index_2)+ CgenSupport.LABEL);
        index++;
        return index;
    }


}


/** Defines AST constructor 'typcase'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class typcase extends Expression {
    protected Expression expr;
    protected Cases cases;
    /** Creates "typcase" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for expr
      * @param a1 initial value for cases
      */
    public typcase(int lineNumber, Expression a1, Cases a2) {
        super(lineNumber);
        expr = a1;
        cases = a2;
    }
    public TreeNode copy() {
        return new typcase(lineNumber, (Expression)expr.copy(), (Cases)cases.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "typcase\n");
        expr.dump(out, n+2);
        cases.dump(out, n+2);
        
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_typcase");
	expr.dump_with_types(out, n + 2);
        for (Enumeration e = cases.getElements(); e.hasMoreElements();) {
	    ((Case)e.nextElement()).dump_with_types(out, n + 2);
        }
	dump_type(out, n);
    }
    /** Generates code for this expression.  This method is to be completed 
      * in programming assignment 5.  (You may add or remove parameters as
      * you wish.)
      * @param s the output stream 
      * */
    // public int code(PrintStream s, int index, SymbolTable sym) {

    //     return index;
    // }

    /*Sorts branches by number of descendants */
    public branch[] branch_sort(branch[] sort) {
         CgenNode start = super.get_root();
        branch[] return_brn = new branch[sort.length];
         branch smallest_num_children = null;
         int ch_fg = 0;
        int outer_cnt = 0;
        while(outer_cnt < sort.length) {
            int prel_ct = 0;
            while(prel_ct < sort.length) {
                if(sort[prel_ct] != null) {
                    smallest_num_children = sort[prel_ct];
                    break;
                }
                prel_ct++;
            }
            int saved_ind = 0;
            for(int i = 0; i < sort.length; i++) {
                 ch_fg = 0;
                if(sort[i]!= null) {
                    if(start.getNode(sort[i].type_decl.toString(), 0).num_children < start.getNode(smallest_num_children.type_decl.toString(), 0).num_children) {
                        smallest_num_children = sort[i];
                        ch_fg = 1;
                        saved_ind = i;
                }
            }
        }
        for(int k = 0; k < return_brn.length; k++) {
            if(return_brn[k] != null) {
                if(return_brn[k].type_decl == smallest_num_children.type_decl) 
                    ch_fg = 1;
            }
        }
        sort[saved_ind]= null;
        if(ch_fg != 1) {
            return_brn[outer_cnt] = smallest_num_children;
         }
        ch_fg = 0;
        outer_cnt++;
    }
    return return_brn;
}

    public int code(PrintStream s, int index, SymbolTable sym) {
            index = expr.code(s, index, sym);
            CgenSupport.emitStore(CgenSupport.ACC, 0, CgenSupport.SP, s);
            CgenSupport.emitAddiu(CgenSupport.SP, CgenSupport.SP, -4, s);
            CgenClassTable.frame_to_top_offset = CgenClassTable.frame_to_top_offset - 4;
            CgenSupport.emitBne(CgenSupport.ACC, CgenSupport.ZERO, index+1, s);
            CgenSupport.emitLoadString(CgenSupport.ACC, (StringSymbol)AbstractTable.stringtable.lookup(0), s);
            CgenSupport.emitLoadImm(CgenSupport.T1, 4, s); 
            CgenSupport.emitJal("_case_abort2", s);
            CgenNode start = super.get_root();
            int max_node = start.last_descendant_node(start).class_tag+1;
            branch[] branches_to_sort = new branch[max_node];
            branch [] sorted_branches = new branch[max_node];
            CgenSupport.emitLoad(CgenSupport.T2, 0, CgenSupport.ACC, s);
             for(Enumeration g = cases.getElements(); g.hasMoreElements();) {
                branch bet_1 = (branch) g.nextElement();
                //Populate the branches array and pass it to branches-to-sort
                CgenNode branch_type = start.getNode(bet_1.type_decl.toString(), 0);               
                if(branches_to_sort[branch_type.class_tag] == null) { //first come-first serve
                    branches_to_sort[branch_type.class_tag] = bet_1;
                    }
                }

                    int outer_ind = index;
                    index++;
                    int saved_ind = index;
                    sorted_branches = branch_sort(branches_to_sort);
                    for(int  k = 0; k < sorted_branches.length; k++) {
                        if(sorted_branches[k] == null)
                            continue;
                        s.print(CgenSupport.LABEL_PREFIX+String.valueOf(saved_ind)+ CgenSupport.LABEL);
                        CgenSupport.emitLoad(CgenSupport.T2, 0, CgenSupport.ACC, s);
                        saved_ind = index+1;
                        index = index+2;
                        int enter_ind = 0;
                        int bound = sorted_branches.length-1;
                        if(k == bound) 
                            enter_ind = outer_ind;
                        else
                            enter_ind = saved_ind;
                        /*Blti and Bgti for each branch in sorted order from branch_sort */
                        CgenSupport.emitBlti(CgenSupport.T2, start.getNode(sorted_branches[k].type_decl.toString(), 0).class_tag, enter_ind, s);
                        CgenNode interm = start.getNode(sorted_branches[k].type_decl.toString(), 0);
                        CgenSupport.emitBgti(CgenSupport.T2, start.last_descendant_node(interm).class_tag, enter_ind, s);
                        sym.enterScope();
                        StringSymbol aleph = (StringSymbol) AbstractTable.stringtable.addString(sorted_branches[k].name.toString());
                        sym.addId(aleph, CgenClassTable.frame_to_top_offset/4);
                        int old_offset = CgenClassTable.frame_to_top_offset;
                        index = sorted_branches[k].expr.code(s, index, sym);
                        CgenClassTable.frame_to_top_offset = old_offset;
                        sym.exitScope();
                        CgenSupport.emitBranch(outer_ind, s);
                    }
                    s.print(CgenSupport.LABEL_PREFIX+String.valueOf(saved_ind)+ CgenSupport.LABEL); //For consistency
                    CgenSupport.emitJal("_case_abort", s);
                    s.print(CgenSupport.LABEL_PREFIX+String.valueOf(outer_ind)+ CgenSupport.LABEL); // Exiting let
                    CgenSupport.emitAddiu(CgenSupport.SP, CgenSupport.SP, 4, s);
                    CgenClassTable.frame_to_top_offset = CgenClassTable.frame_to_top_offset + 4;
                    return index;
                }
                }

   




/** Defines AST constructor 'block'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class block extends Expression {
    protected Expressions body;
    /** Creates "block" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for body
      */
    public block(int lineNumber, Expressions a1) {
        super(lineNumber);
        body = a1;
    }
    public TreeNode copy() {
        return new block(lineNumber, (Expressions)body.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "block\n");
        body.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_block");
        for (Enumeration e = body.getElements(); e.hasMoreElements();) {
	    ((Expression)e.nextElement()).dump_with_types(out, n + 2);
        }
	dump_type(out, n);
    }
    /** Generates code for this expression.  This method is to be completed 
      * in programming assignment 5.  (You may add or remove parameters as
      * you wish.)
      * @param s the output stream 
      * */
    public int code(PrintStream s, int index, SymbolTable sym) {
        /*Code each statement*/
        for (Enumeration e = body.getElements(); e.hasMoreElements();) {
            Expression f = (Expression) e.nextElement();
            int trail = 0;
            index = f.code(s, index, sym);
        }
        return index;
    }


}


/** Defines AST constructor 'let'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class let extends Expression {
    protected AbstractSymbol identifier;
    protected AbstractSymbol type_decl;
    protected Expression init;
    protected Expression body;
    /** Creates "let" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for identifier
      * @param a1 initial value for type_decl
      * @param a2 initial value for init
      * @param a3 initial value for body
      */
    public let(int lineNumber, AbstractSymbol a1, AbstractSymbol a2, Expression a3, Expression a4) {
        super(lineNumber);
        identifier = a1;
        type_decl = a2;
        init = a3;
        body = a4;
    }
    public TreeNode copy() {
        return new let(lineNumber, copy_AbstractSymbol(identifier), copy_AbstractSymbol(type_decl), (Expression)init.copy(), (Expression)body.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "let\n");
        dump_AbstractSymbol(out, n+2, identifier);
        dump_AbstractSymbol(out, n+2, type_decl);
        init.dump(out, n+2);
        body.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_let");
	dump_AbstractSymbol(out, n + 2, identifier);
	dump_AbstractSymbol(out, n + 2, type_decl);
	init.dump_with_types(out, n + 2);
	body.dump_with_types(out, n + 2);
	dump_type(out, n);
    }
    /** Generates code for this expression.  This method is to be completed 
      * in programming assignment 5.  (You may add or remove parameters as
      * you wish.)
      * @param s the output stream 
      * */
    public int code(PrintStream s, int index, SymbolTable sym) {
        index = init.code(s, index, sym);
        int old_offset = CgenClassTable.frame_to_top_offset;
        sym.enterScope();
        AbstractSymbol aleph = AbstractTable.stringtable.addString(identifier.str);
        sym.addId(aleph, CgenClassTable.frame_to_top_offset/4);
        CgenSupport.emitStore(CgenSupport.ACC, 0, CgenSupport.SP, s);
        CgenSupport.emitAddiu(CgenSupport.SP, CgenSupport.SP, -4, s);
        CgenClassTable.frame_to_top_offset = CgenClassTable.frame_to_top_offset - 4;
        index = body.code(s, index, sym);
        CgenClassTable.frame_to_top_offset = old_offset;
        int diff = Math.abs(CgenClassTable.frame_to_top_offset - old_offset);
        CgenSupport.emitAddiu(CgenSupport.SP, CgenSupport.SP, 4*diff+4, s);
        sym.exitScope();
        return index;

    }


}


/** Defines AST constructor 'plus'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class plus extends Expression {
    protected Expression e1;
    protected Expression e2;
    /** Creates "plus" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for e1
      * @param a1 initial value for e2
      */
    public plus(int lineNumber, Expression a1, Expression a2) {
        super(lineNumber);
        e1 = a1;
        e2 = a2;
    }
    public TreeNode copy() {
        return new plus(lineNumber, (Expression)e1.copy(), (Expression)e2.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "plus\n");
        e1.dump(out, n+2);
        e2.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_plus");
	e1.dump_with_types(out, n + 2);
	e2.dump_with_types(out, n + 2);
	dump_type(out, n);
    }
    /** Generates code for this expression.  This method is to be completed 
      * in programming assignment 5.  (You may add or remove parameters as
      * you wish.)
      * @param s the output stream 
      * */
    public int code(PrintStream s, int index, SymbolTable sym) {
        /* Store arg1 on the stack, get arg2 from acc and add */
      index = e1.code(s, index, sym);
        CgenSupport.emitStore(CgenSupport.ACC, 0, CgenSupport.SP, s);
        CgenSupport.emitAddiu(CgenSupport.SP, CgenSupport.SP, -4, s);
        CgenClassTable.frame_to_top_offset = CgenClassTable.frame_to_top_offset - 4;
        index = e2.code(s, index, sym);       
        CgenSupport.emitJal("Object.copy", s);
        CgenSupport.emitLoad(CgenSupport.T3, 1, CgenSupport.SP, s);
        CgenSupport.emitLoad(CgenSupport.T2, 3, CgenSupport.ACC, s);
        CgenSupport.emitLoad(CgenSupport.T1, 3, CgenSupport.T3, s);
        CgenSupport.emitAdd(CgenSupport.T1, CgenSupport.T1, CgenSupport.T2, s);
        CgenSupport.emitStore(CgenSupport.T1, 3, CgenSupport.ACC, s);
        CgenSupport.emitAddiu(CgenSupport.SP, CgenSupport.SP, 4, s);
        CgenClassTable.frame_to_top_offset = CgenClassTable.frame_to_top_offset + 4;
        return index;
    }


}


/** Defines AST constructor 'sub'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class sub extends Expression {
    protected Expression e1;
    protected Expression e2;
    /** Creates "sub" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for e1
      * @param a1 initial value for e2
      */
    public sub(int lineNumber, Expression a1, Expression a2) {
        super(lineNumber);
        e1 = a1;
        e2 = a2;
    }
    public TreeNode copy() {
        return new sub(lineNumber, (Expression)e1.copy(), (Expression)e2.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "sub\n");
        e1.dump(out, n+2);
        e2.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_sub");
	e1.dump_with_types(out, n + 2);
	e2.dump_with_types(out, n + 2);
	dump_type(out, n);
    }
    /** Generates code for this expression.  This method is to be completed 
      * in programming assignment 5.  (You may add or remove parameters as
      * you wish.)
      * @param s the output stream 
      * */
    public int code(PrintStream s, int index, SymbolTable sym) {
         /* Store arg1 on the stack, get arg2 from acc and sub */
        index = e1.code(s, index, sym);
        CgenSupport.emitStore(CgenSupport.ACC, 0, CgenSupport.SP, s);
        CgenSupport.emitAddiu(CgenSupport.SP, CgenSupport.SP, -4, s);
        CgenClassTable.frame_to_top_offset = CgenClassTable.frame_to_top_offset - 4;
        index = e2.code(s, index, sym);
        CgenSupport.emitJal("Object.copy", s);
        CgenSupport.emitLoad(CgenSupport.T3, 1, CgenSupport.SP, s);
        CgenSupport.emitLoad(CgenSupport.T2, 3, CgenSupport.ACC, s);
        CgenSupport.emitLoad(CgenSupport.T1, 3, CgenSupport.T3, s);
        CgenSupport.emitSub(CgenSupport.T1, CgenSupport.T1, CgenSupport.T2, s);
        CgenSupport.emitStore(CgenSupport.T1, 3, CgenSupport.ACC, s);
        CgenSupport.emitAddiu(CgenSupport.SP, CgenSupport.SP, 4, s);
        CgenClassTable.frame_to_top_offset = CgenClassTable.frame_to_top_offset + 4;
        return index;
    }


}


/** Defines AST constructor 'mul'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class mul extends Expression {
    protected Expression e1;
    protected Expression e2;
    /** Creates "mul" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for e1
      * @param a1 initial value for e2
      */
    public mul(int lineNumber, Expression a1, Expression a2) {
        super(lineNumber);
        e1 = a1;
        e2 = a2;
    }
    public TreeNode copy() {
        return new mul(lineNumber, (Expression)e1.copy(), (Expression)e2.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "mul\n");
        e1.dump(out, n+2);
        e2.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_mul");
	e1.dump_with_types(out, n + 2);
	e2.dump_with_types(out, n + 2);
	dump_type(out, n);
    }
    /** Generates code for this expression.  This method is to be completed 
      * in programming assignment 5.  (You may add or remove parameters as
      * you wish.)
      * @param s the output stream 
      * */
    public int code(PrintStream s, int index, SymbolTable sym) {
         /* Store arg1 on the stack, get arg2 from acc and mult */
        index = e1.code(s, index, sym);
       CgenSupport.emitStore(CgenSupport.ACC, 0, CgenSupport.SP, s);
       CgenSupport.emitAddiu(CgenSupport.SP, CgenSupport.SP, -4, s);
       CgenClassTable.frame_to_top_offset = CgenClassTable.frame_to_top_offset - 4;
        index = e2.code(s, index, sym);
        CgenSupport.emitJal("Object.copy", s);
        CgenSupport.emitLoad(CgenSupport.T3, 1, CgenSupport.SP, s);
         CgenSupport.emitLoad(CgenSupport.T2, 3, CgenSupport.ACC, s);
        CgenSupport.emitLoad(CgenSupport.T1, 3, CgenSupport.T3, s);
        CgenSupport.emitMul(CgenSupport.T1, CgenSupport.T1, CgenSupport.T2, s);
        CgenSupport.emitStore(CgenSupport.T1, 3, CgenSupport.ACC, s);
        CgenSupport.emitAddiu(CgenSupport.SP, CgenSupport.SP, 4, s);
        CgenClassTable.frame_to_top_offset = CgenClassTable.frame_to_top_offset + 4;

        return index;
    }


}


/** Defines AST constructor 'divide'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class divide extends Expression {
    protected Expression e1;
    protected Expression e2;
    /** Creates "divide" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for e1
      * @param a1 initial value for e2
      */
    public divide(int lineNumber, Expression a1, Expression a2) {
        super(lineNumber);
        e1 = a1;
        e2 = a2;
    }
    public TreeNode copy() {
        return new divide(lineNumber, (Expression)e1.copy(), (Expression)e2.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "divide\n");
        e1.dump(out, n+2);
        e2.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_divide");
	e1.dump_with_types(out, n + 2);
	e2.dump_with_types(out, n + 2);
	dump_type(out, n);
    }
    /** Generates code for this expression.  This method is to be completed 
      * in programming assignment 5.  (You may add or remove parameters as
      * you wish.)
      * @param s the output stream 
      * */
    public int code(PrintStream s, int index, SymbolTable sym) {
         /* Store arg1 on the stack, get arg2 from acc and div */
        index = e1.code(s, index, sym);
        CgenSupport.emitStore(CgenSupport.ACC, 0, CgenSupport.SP, s);
        CgenSupport.emitAddiu(CgenSupport.SP, CgenSupport.SP, -4, s);
        CgenClassTable.frame_to_top_offset = CgenClassTable.frame_to_top_offset - 4;
        index = e2.code(s, index, sym);
        CgenSupport.emitJal("Object.copy", s);
        CgenSupport.emitLoad(CgenSupport.T3, 1, CgenSupport.SP, s);
        CgenSupport.emitLoad(CgenSupport.T2, 3, CgenSupport.ACC, s);
        CgenSupport.emitLoad(CgenSupport.T1, 3, CgenSupport.T3, s);
        CgenSupport.emitDiv(CgenSupport.T1, CgenSupport.T1, CgenSupport.T2, s);
        CgenSupport.emitStore(CgenSupport.T1, 3, CgenSupport.ACC, s);
        CgenSupport.emitAddiu(CgenSupport.SP, CgenSupport.SP, 4, s);
        CgenClassTable.frame_to_top_offset = CgenClassTable.frame_to_top_offset + 4;
        return index;
    }


}


/** Defines AST constructor 'neg'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class neg extends Expression {
    protected Expression e1;
    /** Creates "neg" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for e1
      */
    public neg(int lineNumber, Expression a1) {
        super(lineNumber);
        e1 = a1;
    }
    public TreeNode copy() {
        return new neg(lineNumber, (Expression)e1.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "neg\n");
        e1.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_neg");
	e1.dump_with_types(out, n + 2);
	dump_type(out, n);
    }
    /** Generates code for this expression.  This method is to be completed 
      * in programming assignment 5.  (You may add or remove parameters as
      * you wish.)
      * @param s the output stream 
      * */
    public int code(PrintStream s, int index, SymbolTable sym) {
         /* Create new negated obj (integer obj) */
        index = e1.code(s, index, sym);
        CgenSupport.emitJal("Object.copy", s);
        CgenSupport.emitLoad(CgenSupport.T1, 3, CgenSupport.ACC, s);
        CgenSupport.emitLoadImm(CgenSupport.T2, -1, s);
        CgenSupport.emitMul(CgenSupport.T1, CgenSupport.T1, CgenSupport.T2, s);
        CgenSupport.emitStore(CgenSupport.T1, 3, CgenSupport.ACC, s);
        return index;
    }


}


/** Defines AST constructor 'lt'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class lt extends Expression {
    protected Expression e1;
    protected Expression e2;
    /** Creates "lt" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for e1
      * @param a1 initial value for e2
      */
    public lt(int lineNumber, Expression a1, Expression a2) {
        super(lineNumber);
        e1 = a1;
        e2 = a2;
    }
    public TreeNode copy() {
        return new lt(lineNumber, (Expression)e1.copy(), (Expression)e2.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "lt\n");
        e1.dump(out, n+2);
        e2.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_lt");
	e1.dump_with_types(out, n + 2);
	e2.dump_with_types(out, n + 2);
	dump_type(out, n);
    }
    /** Generates code for this expression.  This method is to be completed 
      * in programming assignment 5.  (You may add or remove parameters as
      * you wish.)
      * @param s the output stream 
      * */
    public int code(PrintStream s, int index, SymbolTable sym) {
        /*Does the comparison and stores the result in a new Bool */
        index = e1.code(s, index, sym);
        CgenSupport.emitStore(CgenSupport.ACC, 0, CgenSupport.SP, s);
        CgenSupport.emitAddiu(CgenSupport.SP, CgenSupport.SP, -4, s);
        CgenClassTable.frame_to_top_offset = CgenClassTable.frame_to_top_offset - 4;
        index = e2.code(s, index, sym);
        CgenSupport.emitMove(CgenSupport.T2, CgenSupport.ACC, s);
        CgenSupport.emitLoad(CgenSupport.T2, 3, CgenSupport.T2, s);
        CgenSupport.emitLoad(CgenSupport.T1, 1, CgenSupport.SP, s);
        CgenSupport.emitLoad(CgenSupport.T1, 3, CgenSupport.T1, s);
        CgenSupport.emitAddiu(CgenSupport.SP, CgenSupport.SP, 4, s);
        CgenClassTable.frame_to_top_offset = CgenClassTable.frame_to_top_offset + 4;
        CgenSupport.emitLoadBool(CgenSupport.ACC, new BoolConst(true), s);
        CgenSupport.emitBlt(CgenSupport.T1, CgenSupport.T2, index, s);
        CgenSupport.emitLoadBool(CgenSupport.ACC, new BoolConst(false), s);
        s.print(CgenSupport.LABEL_PREFIX+String.valueOf(index)+ CgenSupport.LABEL);
        index++;
        return index;
        
        
    }


}


/** Defines AST constructor 'eq'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class eq extends Expression {
    protected Expression e1;
    protected Expression e2;
    /** Creates "eq" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for e1
      * @param a1 initial value for e2
      */
    public eq(int lineNumber, Expression a1, Expression a2) {
        super(lineNumber);
        e1 = a1;
        e2 = a2;
    }
    public TreeNode copy() {
        return new eq(lineNumber, (Expression)e1.copy(), (Expression)e2.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "eq\n");
        e1.dump(out, n+2);
        e2.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_eq");
	e1.dump_with_types(out, n + 2);
	e2.dump_with_types(out, n + 2);
	dump_type(out, n);
    }
    /** Generates code for this expression.  This method is to be completed 
      * in programming assignment 5.  (You may add or remove parameters as
      * you wish.)
      * @param s the output stream 
      * */
    public int code(PrintStream s, int index, SymbolTable sym) {
        /* Does the eq comparison and stores the result in a new Bool Cosnt */
        index = e1.code(s, index, sym);
        CgenSupport.emitStore(CgenSupport.ACC, 0, CgenSupport.SP, s);
        CgenSupport.emitAddiu(CgenSupport.SP, CgenSupport.SP, -4, s);
        CgenClassTable.frame_to_top_offset = CgenClassTable.frame_to_top_offset - 4;
        index = e2.code(s, index, sym);
        index++;
        CgenSupport.emitMove(CgenSupport.T2, CgenSupport.ACC, s);
        CgenSupport.emitLoad(CgenSupport.T1, 1, CgenSupport.SP, s);
        CgenSupport.emitAddiu(CgenSupport.SP, CgenSupport.SP, 4, s);
        CgenClassTable.frame_to_top_offset = CgenClassTable.frame_to_top_offset + 4;
        CgenSupport.emitLoadBool(CgenSupport.ACC, new BoolConst(true), s);
        CgenSupport.emitBeq(CgenSupport.T1, CgenSupport.T2, index, s);
        CgenSupport.emitLoadBool(CgenSupport.A1, new BoolConst(false), s);
          CgenSupport.emitJal("equality_test", s);
        s.print(CgenSupport.LABEL_PREFIX+String.valueOf(index)+ CgenSupport.LABEL);
        index++;
        return index;
    }


}


/** Defines AST constructor 'leq'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class leq extends Expression {
    protected Expression e1;
    protected Expression e2;
    /** Creates "leq" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for e1
      * @param a1 initial value for e2
      */
    public leq(int lineNumber, Expression a1, Expression a2) {
        super(lineNumber);
        e1 = a1;
        e2 = a2;
    }
    public TreeNode copy() {
        return new leq(lineNumber, (Expression)e1.copy(), (Expression)e2.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "leq\n");
        e1.dump(out, n+2);
        e2.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_leq");
	e1.dump_with_types(out, n + 2);
	e2.dump_with_types(out, n + 2);
	dump_type(out, n);
    }
    /** Generates code for this expression.  This method is to be completed 
      * in programming assignment 5.  (You may add or remove parameters as
      * you wish.)
      * @param s the output stream /
      * */
    public int code(PrintStream s, int index, SymbolTable sym) {
        /*Does the leq comparison and stores the result in a new BoolConst*/
        index = e1.code(s, index, sym);
        CgenSupport.emitStore(CgenSupport.ACC, 0, CgenSupport.SP, s);
        CgenSupport.emitAddiu(CgenSupport.SP, CgenSupport.SP, -4, s);
        CgenClassTable.frame_to_top_offset = CgenClassTable.frame_to_top_offset - 4;
        index = e2.code(s, index, sym);
        CgenSupport.emitMove(CgenSupport.T2, CgenSupport.ACC, s);
        CgenSupport.emitLoad(CgenSupport.T2, 3, CgenSupport.T2, s);
        CgenSupport.emitLoad(CgenSupport.T1, 1, CgenSupport.SP, s);
        CgenSupport.emitLoad(CgenSupport.T1, 3, CgenSupport.T1, s);
        CgenSupport.emitAddiu(CgenSupport.SP, CgenSupport.SP, 4, s);
        CgenClassTable.frame_to_top_offset = CgenClassTable.frame_to_top_offset + 4;
        CgenSupport.emitLoadBool(CgenSupport.ACC, new BoolConst(true), s);
        CgenSupport.emitBleq(CgenSupport.T1, CgenSupport.T2, index, s);
        CgenSupport.emitLoadBool(CgenSupport.ACC, new BoolConst(false), s);
        s.print(CgenSupport.LABEL_PREFIX+String.valueOf(index)+ CgenSupport.LABEL);
        index++;
        
        return index;
    }


}


/** Defines AST constructor 'comp'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class comp extends Expression {
    protected Expression e1;
    /** Creates "comp" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for e1
      */
    public comp(int lineNumber, Expression a1) {
        super(lineNumber);
        e1 = a1;
    }
    public TreeNode copy() {
        return new comp(lineNumber, (Expression)e1.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "comp\n");
        e1.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_comp");
	e1.dump_with_types(out, n + 2);
	dump_type(out, n);
    }
    /** Generates code for this expression.  This method is to be completed 
      * in programming assignment 5.  (You may add or remove parameters as
      * you wish.)
      * @param s the output stream 
      * */
    public int code(PrintStream s, int index, SymbolTable sym) {
        /*Find the compliment and store result in new BoolConst */
        index = e1.code(s, index, sym);
        CgenSupport.emitLoad(CgenSupport.ACC, 3, CgenSupport.ACC, s);
        CgenSupport.emitMove(CgenSupport.T1, CgenSupport.ACC, s);
        CgenSupport.emitLoadImm(CgenSupport.T2, 0, s);
        CgenSupport.emitLoadBool(CgenSupport.ACC, new BoolConst(true), s);
        CgenSupport.emitBeq(CgenSupport.T1, CgenSupport.T2, index, s);
        CgenSupport.emitLoadBool(CgenSupport.ACC, new BoolConst(false), s);
        s.print(CgenSupport.LABEL_PREFIX+String.valueOf(index)+ CgenSupport.LABEL);
        index++;
        return index;
    }


}


/** Defines AST constructor 'int_const'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class int_const extends Expression {
    protected AbstractSymbol token;
    /** Creates "int_const" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for token
      */
    public int_const(int lineNumber, AbstractSymbol a1) {
        super(lineNumber);
        token = a1;
    }
    public TreeNode copy() {
        return new int_const(lineNumber, copy_AbstractSymbol(token));
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "int_const\n");
        dump_AbstractSymbol(out, n+2, token);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_int");
	dump_AbstractSymbol(out, n + 2, token);
	dump_type(out, n);
    }
    /** Generates code for this expression.  This method method is provided
      * to you as an example of code generation.
      * @param s the output stream 
      * */
    public int code(PrintStream s, int index, SymbolTable sym) {
	CgenSupport.emitLoadInt(CgenSupport.ACC,
                                (IntSymbol)AbstractTable.inttable.lookup(token.getString()), s);
    return index;
    }

}


/** Defines AST constructor 'bool_const'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class bool_const extends Expression {
    protected Boolean val;
    /** Creates "bool_const" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for val
      */
    public bool_const(int lineNumber, Boolean a1) {
        super(lineNumber);
        val = a1;
    }
    public TreeNode copy() {
        return new bool_const(lineNumber, copy_Boolean(val));
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "bool_const\n");
        dump_Boolean(out, n+2, val);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_bool");
	dump_Boolean(out, n + 2, val);
	dump_type(out, n);
    }
    /** Generates code for this expression.  This method method is provided
      * to you as an example of code generation.
      * @param s the output stream 
      * */
    public int code(PrintStream s, int index, SymbolTable sym) {
	CgenSupport.emitLoadBool(CgenSupport.ACC, new BoolConst(val), s);
    return index;
    }

}


/** Defines AST constructor 'string_const'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class string_const extends Expression {
    protected AbstractSymbol token;
    /** Creates "string_const" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for token
      */
    public string_const(int lineNumber, AbstractSymbol a1) {
        super(lineNumber);
        token = a1;
    }
    public TreeNode copy() {
        return new string_const(lineNumber, copy_AbstractSymbol(token));
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "string_const\n");
        dump_AbstractSymbol(out, n+2, token);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_string");
	out.print(Utilities.pad(n + 2) + "\"");
	Utilities.printEscapedString(out, token.getString());
	out.println("\"");
	dump_type(out, n);
    }
    /** Generates code for this expression.  This method method is provided
      * to you as an example of code generation.
      * @param s the output stream 
      * */
    public int code(PrintStream s, int index, SymbolTable sym) {
	CgenSupport.emitLoadString(CgenSupport.ACC,
                                   (StringSymbol)AbstractTable.stringtable.lookup(token.getString()), s);

    return index;
    }

}


/** Defines AST constructor 'new_'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class new_ extends Expression {
    protected AbstractSymbol type_name;
    /** Creates "new_" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for type_name
      */
    public new_(int lineNumber, AbstractSymbol a1) {
        super(lineNumber);
        type_name = a1;
    }
    public TreeNode copy() {
        return new new_(lineNumber, copy_AbstractSymbol(type_name));
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "new_\n");
        dump_AbstractSymbol(out, n+2, type_name);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_new");
	dump_AbstractSymbol(out, n + 2, type_name);
	dump_type(out, n);
    }
    /** Generates code for this expression.  This method is to be completed 
      * in programming assignment 5.  (You may add or remove parameters as
      * you wish.)
      * @param s the output stream 
      * */
    public int code(PrintStream s, int index, SymbolTable sym) {
        /* Generates new object, if self, just stores the self in ACC */
        if(type_name.equalString("SELF_TYPE", 9) || type_name.equalString("self", 4)) {
            CgenSupport.emitMove(CgenSupport.SELF, CgenSupport.ACC, s);
            return index;
        }
        CgenSupport.emitLoadAddress(CgenSupport.ACC, type_name.getString()+CgenSupport.PROTOBJ_SUFFIX, s);
        CgenSupport.emitJal("Object.copy", s);
        CgenSupport.emitJal(type_name.getString()+CgenSupport.CLASSINIT_SUFFIX, s);
        return index;
    }


}


/** Defines AST constructor 'isvoid'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class isvoid extends Expression {
    protected Expression e1;
    /** Creates "isvoid" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for e1
      */
    public isvoid(int lineNumber, Expression a1) {
        super(lineNumber);
        e1 = a1;
    }
    public TreeNode copy() {
        return new isvoid(lineNumber, (Expression)e1.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "isvoid\n");
        e1.dump(out, n+2);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_isvoid");
	e1.dump_with_types(out, n + 2);
	dump_type(out, n);
    }
    /** Generates code for this expression.  This method is to be completed 
      * in programming assignment 5.  (You may add or remove parameters as
      * you wish.)
      * @param s the output stream 
      * */
    public int code(PrintStream s, int index, SymbolTable sym) {
        /*Checks whether acc is 0 Returns an appropriate BoolConst */
        index = e1.code(s, index, sym);
        CgenSupport.emitMove(CgenSupport.T1, CgenSupport.ACC, s);
        CgenSupport.emitLoadBool(CgenSupport.ACC, new BoolConst(true), s);
        CgenSupport.emitBeqz(CgenSupport.T1, index, s);
        CgenSupport.emitLoadBool(CgenSupport.ACC, new BoolConst(false), s);
        s.print(CgenSupport.LABEL_PREFIX+String.valueOf(index)+ CgenSupport.LABEL);
        index++;
        return index;
    }


}


/** Defines AST constructor 'no_expr'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class no_expr extends Expression {
    /** Creates "no_expr" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      */
    public no_expr(int lineNumber) {
        super(lineNumber);
    }
    public TreeNode copy() {
        return new no_expr(lineNumber);
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "no_expr\n");
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_no_expr");
	dump_type(out, n);
    }
    /** Generates code for this expression.  This method is to be completed 
      * in programming assignment 5.  (You may add or remove parameters as
      * you wish.)
      * @param s the output stream 
      * */
    public int code(PrintStream s, int index, SymbolTable sym) {
        /*Returns 0 in acc */
        CgenSupport.emitLoadInt(CgenSupport.ACC,
                                (IntSymbol)AbstractTable.inttable.lookup("0"), s);
        CgenSupport.emitJal("Object.copy", s);
        CgenSupport.emitLoadImm(CgenSupport.T1, 0, s);
        CgenSupport.emitStore(CgenSupport.T1, 3, CgenSupport.ACC, s);
        return index;
    }


}


/** Defines AST constructor 'object'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class object extends Expression {
    protected AbstractSymbol name;
    /** Creates "object" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for name
      */
    public object(int lineNumber, AbstractSymbol a1) {
        super(lineNumber);
        name = a1;
    }
    public TreeNode copy() {
        return new object(lineNumber, copy_AbstractSymbol(name));
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "object\n");
        dump_AbstractSymbol(out, n+2, name);
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_object");
	dump_AbstractSymbol(out, n + 2, name);
	dump_type(out, n);
    }
    /** Generates code for this expression.  This method is to be completed 
      * in programming assignment 5.  (You may add or remove parameters as
      * you wish.)
      * @param s the output stream 
      * */
    public int code(PrintStream s, int index, SymbolTable sym) {
        /*Checks all the symboltables (attributes and local vars/params) */
        if(name.toString().equals("self")) {
            CgenSupport.emitMove(CgenSupport.ACC, CgenSupport.SELF, s);
            return index;
        }
        StringSymbol aleph = (StringSymbol) AbstractTable.stringtable.lookup(name.toString());
        try {
         Integer f = (Integer) (sym.lookup(aleph));
         CgenSupport.emitLoad(CgenSupport.ACC, f, CgenSupport.FP, s);
         return index;
     }
     catch (java.lang.NullPointerException r) {
        Integer g = (Integer) (CgenClassTable.attr_defs.lookup(aleph));
        CgenSupport.emitLoad(CgenSupport.ACC, g, CgenSupport.SELF, s);
     }
        
        return index;
    }


}



