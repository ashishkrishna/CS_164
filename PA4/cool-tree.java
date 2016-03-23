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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
import java.util.HashMap;

/** Defines simple phylum Program */
abstract class Program extends TreeNode {
    protected Program(int lineNumber) {
        super(lineNumber);
    }
    public abstract void dump_with_types(PrintStream out, int n);
    public abstract void semant();

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
    /*public abstract void set_type(SymbolTable s); */

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
    public AbstractSymbol get_type() { return type; }           
    public Expression set_type(AbstractSymbol s) { type = s; return this; } 
    public void set_type(SymbolTable s) { return; }
    public boolean type_chk(class_c to_check, SymbolTable aleph) {return true; }
    public boolean type_chk(class_c to_check, SymbolTable gimel, HashMap<String, Vector<method>> bet, HierarchyNode root, ClassTable classTable) {return true;}
    public abstract void dump_with_types(PrintStream out, int n);
    public void dump_type(PrintStream out, int n) {
        if (type != null)
            { out.println(Utilities.pad(n) + ": " + type.getString()); }
        else
            { out.println(Utilities.pad(n) + ": _no_type"); }
    }

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
    private Vector<String> classNames;
    private SymbolTable attr_checker;
    private SymbolTable method_checker;
    private HashMap<String, Vector<method>> methods_per_class;
    /** Creates "programc" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for classes
      */
    private int semanticerr;
    private PrintStream errorStream;
    public programc(int lineNumber, Classes a1) {
        super(lineNumber);
        classes = a1;
        errorStream = System.err;
        semanticerr = 0;
        methods_per_class = new HashMap<String, Vector<method>>(0);
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
    if (classTable.errors()) {
        System.err.println("Compilation halted due to static semantic errors.");
        System.exit(1);
    }
   classNames = classTable.classNames();
   classNames.addElement("null");
   classNames.addElement("SELF_TYPE");

    /*Go through all the classes */
    SymbolTable attr_checker = new SymbolTable();
    SymbolTable method_checker = new SymbolTable();
    methods_per_class = new HashMap<String, Vector<method>>();
    HierarchyNode root_1 = classTable.goodClasses();
    annotateMethods(root_1);
    checkClasses(root_1, attr_checker, method_checker, classTable);
    if(semanticerr > 0) {
        System.err.println("Compilation halted due to static semantic errors.");
        System.exit(1);
    }
     /* some semantic analysis code may go here */
    }

    public void checkClasses(HierarchyNode root, SymbolTable symtab_1, SymbolTable symtab_2, ClassTable classTable) {
        symtab_1.enterScope();
        symtab_2.enterScope();
        class_c to_check_class = root.thisClassNode();
        Features this_classes_features = to_check_class.getFeatures();
        for(Enumeration<Feature> list_of_features = this_classes_features.getElements(); list_of_features.hasMoreElements();) {
            Feature alpha = list_of_features.nextElement();
            if(alpha.getClass().equals(attr.class)) {
                attr attr_1 = (attr) alpha;
                if(symtab_1.lookup(attr_1.name) == null) {
                    if(!attr_1.check_attr(symtab_1, to_check_class, root, methods_per_class, classTable))
                        semanticerr++;     
                    symtab_1.addId(attr_1.name, attr_1.copy());
                }
                else {
                   semanticerr++;
                   semantError(to_check_class.getFilename(), attr_1);
                   errorStream.append("Attribute " + attr_1.name + " is an attribute of an inherited class.\n");
                }
                }
            else if (alpha.getClass().equals(method.class)) {
                method method_1 = (method) alpha;
                symtab_2.addId(method_1.name, method_1.copy());
                symtab_1.enterScope();
                if(!method_1.ret_and_formal_chk(symtab_1, classNames, to_check_class)) 
                    semanticerr++;
                if(!method_1.type_chk(symtab_1, to_check_class, root, methods_per_class, classTable)) 
                    semanticerr++;
                 symtab_1.exitScope();

            }
        }
        if(root.isLeaf()) {
             symtab_1.exitScope();
             symtab_2.exitScope();
            return;
        }
        else {
            Enumeration<HierarchyNode> childclasses = root.getChildren();
            while(childclasses.hasMoreElements()) {
                HierarchyNode child_class = childclasses.nextElement();
                checkClasses(child_class, symtab_1, symtab_2, classTable);
            }
             symtab_1.exitScope();
             symtab_2.exitScope();
            return;
        }
    }

    public void  annotateMethods(HierarchyNode root) {
        class_c to_check_class = root.thisClassNode();
        Vector<method> class_methods = new Vector<method>();
         Features this_classes_features = to_check_class.getFeatures();
         for(Enumeration<Feature> enums = this_classes_features.getElements(); enums.hasMoreElements(); ) {
            Feature alpha = enums.nextElement();
            if(alpha.getClass().equals(method.class)) {
                method alpha_1 = (method) alpha;
                class_methods.addElement(alpha_1);

            }
         }
         methods_per_class.put(to_check_class.getName().toString(), class_methods);
         if(root.isLeaf()) {
            return;
        }
        else {
            Enumeration<HierarchyNode> childclasses = root.getChildren();
            while(childclasses.hasMoreElements()) {
                HierarchyNode child_class = childclasses.nextElement();
                annotateMethods(child_class);
            }
            return;
        }

    


    }
    
    public PrintStream semantError(AbstractSymbol filename, TreeNode t) {
    errorStream.print(filename + ":" + t.getLineNumber() + ": ");
    return semantError();
    }

    public PrintStream semantError() {
    return errorStream;
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
    private PrintStream errorStream;
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
        errorStream = System.err;
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

    public boolean inherits_class(HierarchyNode aleph_node, String aleph_name) {
        while(!(aleph_node == null)) {
            String bet = aleph_node.thisNode();
            if(bet.equals(aleph_name)) {
                return true;
            }
            aleph_node = aleph_node.getParent();
        }
        return false;
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
    private PrintStream errorStream;
    private SymbolTable method_scoping;
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
        errorStream = System.err;
        method_scoping = new SymbolTable();
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

    public boolean ret_and_formal_chk(SymbolTable aleph, Vector<String> bet, class_c gimel) {
        if(!bet.contains(return_type.toString())) {
             semantError(gimel.getFilename(), (TreeNode) this);
            errorStream.append("Undefined return type " + this.return_type + " in method " + this.name +  ".\n");
            return false;
         }
         for (Enumeration<formalc> formals_enum = formals.getElements(); formals_enum.hasMoreElements();) {
            formalc next_formal = formals_enum.nextElement();
            if(!next_formal.formal_chk(aleph, bet, gimel)) {
                return false;
            }
            if(!(aleph.probe(next_formal.name) == null)) {
                semantError(gimel.getFilename(), (TreeNode) this);
                errorStream.append("Formal parameter " + next_formal.name.toString() + " is multiply defined.\n");
                return false;
            }
            aleph.addId(next_formal.name, next_formal.copy());

        }
        return true;
    }

    public boolean type_chk(SymbolTable aleph, class_c to_check, HierarchyNode root,HashMap<String, Vector<method>> hash_method, ClassTable classTable) {
        if(!(this.expr.get_type() == null)) {
        if(this.expr.getClass().equals(divide.class) || this.expr.getClass().equals(mul.class) || this.expr.getClass().equals(plus.class) || this.expr.getClass().equals(sub.class)) {
            if(!this.expr.type_chk(to_check, aleph, hash_method, root, classTable)) {
                return false;
            }
            return true;
        }

        if(!return_type.equals(this.expr.get_type()) && (!this.expr.get_type().toString().equals("SELF_TYPE")) && !to_check.inherits_class(root, return_type.toString())) {
            semantError(to_check.getFilename(), (TreeNode) this);
            errorStream.append("Inferred return type " + this.expr.get_type() + " of method " + this.name + " does not conform to declared return type " + this.return_type + ".\n");
            return false;
        }
        return true;
    }

    else {
        this.expr.getClass().cast(this.expr);
       if(!(this.expr.type_chk(to_check, aleph, hash_method, root, classTable))) {
        return false;
       }
     return true;
     }
    }

    public PrintStream semantError(AbstractSymbol filename, TreeNode t) {
    errorStream.print(filename + ":" + t.getLineNumber() + ": ");
    return semantError();
    }

    public PrintStream semantError() {
    return errorStream;
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
    protected PrintStream errorStream;
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
        errorStream = System.err;
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

    public boolean check_attr(SymbolTable aleph, class_c to_check, HierarchyNode root,HashMap<String, Vector<method>> hash_method, ClassTable classTable) {
        /*Verify the type */
        if( init.get_type() == null) {
            init.getClass().cast(init);
            init.type_chk(to_check,  aleph, hash_method,  root,  classTable);
            return true;
        }
        else {
            if(!init.get_type().equals(type_decl)) {         
                semantError(to_check.getFilename(), (TreeNode) this);
                errorStream.append("Inferred type " + this.init.get_type() + " of initialization of attribute " + this.name + " does not conform to declared type " + this.type_decl + ".\n");
                return false;
            }

        }
        return true;
    }

    public AbstractSymbol get_type() {
        return this.type_decl;
    }
    public PrintStream semantError(AbstractSymbol filename, TreeNode t) {
    errorStream.print(filename + ":" + t.getLineNumber() + ": ");
    return semantError();
    }

    public PrintStream semantError() {
    return errorStream;
    }
}


/** Defines AST constructor 'formalc'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class formalc extends Formal {
    protected AbstractSymbol name;
    protected AbstractSymbol type_decl;
    protected PrintStream errorStream;
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
        errorStream = System.err;
    }

    public TreeNode copy() {
        return new formalc(lineNumber, copy_AbstractSymbol(name), copy_AbstractSymbol(type_decl));
    }

    public boolean formal_chk(SymbolTable symtab, Vector<String> classnames, class_c to_check_class) {
        if(type_decl.toString().equals("SELF_TYPE")) {
             semantError(to_check_class.getFilename(), this);
             errorStream.append("Formal parameter " + this.name + " cannot have type TreeConstants.SELF_TYPE.\n");
            return false;
        }
        if(!classnames.contains(type_decl.toString())) {
            return false;
        }
        return true;
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

    public PrintStream semantError(AbstractSymbol filename, TreeNode t) {
    errorStream.print(filename + ":" + t.getLineNumber() + ": ");
    return semantError();
    }

    public PrintStream semantError() {
    return errorStream;
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

    public boolean type_chk(class_c to_check, SymbolTable gimel, HashMap<String, Vector<method>> bet, HierarchyNode root, ClassTable classTable) {
        if(expr.get_type() == null) {
            expr.getClass().cast(expr);
        }
        expr.type_chk(to_check, gimel, bet, root, classTable);
        if(gimel.lookup(name) != null) {
            if(gimel.lookup(name).getClass().equals(attr.class)) {
                HierarchyNode root_of_good_tree = classTable.goodClasses();
                attr alepha = (attr) gimel.lookup(name);
                HierarchyNode super_class = classTable.getClassbyName(alepha.type_decl.toString(), root_of_good_tree);
                if(!classTable.isChildClass(expr.get_type().toString(), super_class)) {
                    return false;
                    //Print error to be added
                }
        }
          
    }

         AbstractSymbol aleph = AbstractTable.stringtable.addString(expr.get_type().toString());
         super.set_type(aleph);
         return true;
    }
    

    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_assign");
        dump_AbstractSymbol(out, n + 2, name);
    expr.dump_with_types(out, n + 2);
    dump_type(out, n);
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

}


/** Defines AST constructor 'dispatch'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class dispatch extends Expression {
    protected Expression expr;
    protected AbstractSymbol name;
    protected Expressions actual;
    private PrintStream errorStream;
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
        errorStream = System.err;
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

    public boolean type_chk(class_c to_check, SymbolTable gimel, HashMap<String, Vector<method>> bet, HierarchyNode root, ClassTable classTable) {
            if(expr.get_type() == null) {
                expr.getClass().cast(expr);
            }
            expr.type_chk(to_check,  gimel, bet, root,  classTable);
            HierarchyNode root_of_good_tree = classTable.goodClasses();
            String class_name = expr.get_type().toString();
            if(class_name.equals("SELF_TYPE")) {
                class_name = to_check.getName().toString();
            }
            HierarchyNode root_1 = classTable.getClassbyName(class_name, root_of_good_tree);
            while(root_1 != null) {
            Vector<method> chck = bet.get(root_1.thisClassNode().getName().toString());
            for(Enumeration<method> iterchck= chck.elements(); iterchck.hasMoreElements(); ) {
                method iter1 = iterchck.nextElement();
                if(iter1.name.toString().equals(this.name.toString())) {
                    if(!(iter1.formals.getLength() == this.actual.getLength())) {
                        semantError(to_check.getFilename(), this);
                        errorStream.append("Method " + this.name.toString() + " invoked with wrong number of arguments.\n");
                        return false;
                    }
                    for(Enumeration<Expression> element_check = actual.getElements(); element_check.hasMoreElements();) {
                        Expression next_elem_expr = element_check.nextElement();
                        next_elem_expr.getClass().cast(next_elem_expr);
                        next_elem_expr.type_chk(to_check, gimel, bet, root, classTable);
                    }
                    if(iter1.return_type.toString().equals("SELF_TYPE")) {
                        AbstractSymbol aleph = AbstractTable.stringtable.addString(expr.get_type().toString());
                        super.set_type(aleph);
                        return true;
                    }

                    AbstractSymbol aleph = AbstractTable.stringtable.addString(iter1.return_type.toString());
                    super.set_type(aleph);
                    return true;
                }
            }
            root_1 = root_1.getParent();
        }
        return false;

    }

    public PrintStream semantError(AbstractSymbol filename, TreeNode t) {
    errorStream.print(filename + ":" + t.getLineNumber() + ": ");
    return semantError();
    }

    public PrintStream semantError() {
    return errorStream;
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
        Expression aleph = (Expression) a1.getNth(a1.getLength()-1);
        if(!(aleph.get_type() == null)) {
        AbstractSymbol block_val = AbstractTable.stringtable.addString(aleph.get_type().toString());
        super.set_type(block_val);
    }
    }

    public boolean type_chk(class_c checker, SymbolTable sym_1, HashMap<String, Vector<method>> bet, HierarchyNode root, ClassTable classTable) {
        int count = 0;
        for(Enumeration<Expression> statements = body.getElements(); statements.hasMoreElements();) {
                Expression statement_next = statements.nextElement();
                if(statement_next.get_type() == null) {
                    statement_next.getClass().cast(statement_next);
                    statement_next.type_chk(checker, sym_1, bet, root, classTable);     
                    if(count == body.getLength()-1) {
                        AbstractSymbol aleph = AbstractTable.stringtable.addString(statement_next.get_type().toString());
                        super.set_type(aleph);
                    }
                }
                count++;
        }
        return true;
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

}


/** Defines AST constructor 'let'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class let extends Expression {
    protected AbstractSymbol identifier;
    protected AbstractSymbol type_decl;
    protected Expression init;
    protected Expression body;
    private PrintStream errorStream;
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
        errorStream = System.err;
    }

    public boolean type_chk(class_c checker, SymbolTable sym_1, HashMap<String, Vector<method>> bet, HierarchyNode root, ClassTable classTable) {
        //sym_1.addId(identifier, type_decl);
        if(init.get_type() == null) {
                init.getClass().cast(init);            
            }  
        init.type_chk(checker, sym_1, bet, root, classTable);
        sym_1.addId(this.identifier, this.type_decl);
         if(body.get_type() == null) {
                body.getClass().cast(body);
            }
        body.type_chk(checker, sym_1, bet, root, classTable);
        if(body.get_type().toString().equals("SELF_TYPE")){
            AbstractSymbol alepha = AbstractTable.stringtable.addString("SELF_TYPE");
            super.set_type(alepha);
            return true;
        }
        if(init.get_type().toString().equals(type_decl.toString()) && body.get_type().toString().equals(type_decl.toString()) && init.get_type().toString().equals(body.get_type().toString())) {
            super.set_type(type_decl);
            return true;
        }
        else {
            semantError(checker.getFilename(), (TreeNode) this);
            errorStream.append("Non-Tree Constants.Int arguments: " + body.get_type() + " + " + init.get_type() +".\n");
            return false;
        }
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

    public PrintStream semantError(AbstractSymbol filename, TreeNode t) {
    errorStream.print(filename + ":" + t.getLineNumber() + ": ");
    return semantError();
    }

    public PrintStream semantError() {
    return errorStream;

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

}


/** Defines AST constructor 'plus'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class plus extends Expression {
    protected Expression e1;
    protected Expression e2;
    private PrintStream errorStream;
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
        AbstractSymbol plus_val = AbstractTable.stringtable.addString("Int");
        super.set_type(plus_val);
    }
    public TreeNode copy() {
        return new plus(lineNumber, (Expression)e1.copy(), (Expression)e2.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "plus\n");
        e1.dump(out, n+2);
        e2.dump(out, n+2);
    }

    public boolean type_chk(class_c checker, SymbolTable sym_1, HashMap<String, Vector<method>> bet, HierarchyNode root, ClassTable classTable) {
        if(e1.get_type() == null) {
                e1.getClass().cast(e1);
                e1.type_chk(checker, sym_1, bet, root, classTable);
                       
            }
            
         if(e2.get_type() == null) {
                e2.getClass().cast(e2);
                e2.type_chk(checker, sym_1, bet, root, classTable);
            }
        if(e1.get_type().toString().equals("Int") && e2.get_type().toString().equals("Int")) {
            return true;
        }
        else {
            semantError(checker.getFilename(), (TreeNode) this);
            errorStream.append("Non-Tree Constants.Int arguments: " + e1.get_type() + " + " + e2.get_type() +".\n");
            return false;
        }
    }

    public PrintStream semantError(AbstractSymbol filename, TreeNode t) {
    errorStream.print(filename + ":" + t.getLineNumber() + ": ");
    return semantError();
    }

    public PrintStream semantError() {
    return errorStream;

    }

    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_plus");
    e1.dump_with_types(out, n + 2);
    e2.dump_with_types(out, n + 2);
    dump_type(out, n);
    }

}


/** Defines AST constructor 'sub'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class sub extends Expression {
    protected Expression e1;
    protected Expression e2;
    private PrintStream errorStream;
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
        errorStream = System.err;
        AbstractSymbol sub_val = AbstractTable.stringtable.addString("Int");
        super.set_type(sub_val);
    }
    public TreeNode copy() {
        return new sub(lineNumber, (Expression)e1.copy(), (Expression)e2.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "sub\n");
        e1.dump(out, n+2);
        e2.dump(out, n+2);
    }

    public boolean type_chk(class_c checker, SymbolTable sym_1, HashMap<String, Vector<method>> bet, HierarchyNode root, ClassTable classTable) {
        if(e1.get_type() == null) {
                 e1.getClass().cast(e1);
                e1.type_chk(checker, sym_1, bet, root, classTable);
                       
            }
            
         if(e2.get_type() == null) {
                e2.getClass().cast(e2);
                e2.type_chk(checker, sym_1, bet, root, classTable);
            }
        if(e1.get_type().toString().equals("Int") && e2.get_type().toString().equals("Int")) {
            return true;
        }
        else {
            semantError(checker.getFilename(), (TreeNode) this);
            errorStream.append("Non-Tree Constants.Int arguments: " + e1.get_type() + " - " + e2.get_type() +".\n");
            return false;
        }
    }

    public PrintStream semantError(AbstractSymbol filename, TreeNode t) {
    errorStream.print(filename + ":" + t.getLineNumber() + ": ");
    return semantError();
    }

    public PrintStream semantError() {
    return errorStream;

    }
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_sub");
    e1.dump_with_types(out, n + 2);
    e2.dump_with_types(out, n + 2);
    dump_type(out, n);
    }

}


/** Defines AST constructor 'mul'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class mul extends Expression {
    protected Expression e1;
    protected Expression e2;
    private PrintStream errorStream;
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
        errorStream = System.err;
        AbstractSymbol mul_val = AbstractTable.stringtable.addString("Int");
        super.set_type(mul_val);
    }
    public TreeNode copy() {
        return new mul(lineNumber, (Expression)e1.copy(), (Expression)e2.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "mul\n");
        e1.dump(out, n+2);
        e2.dump(out, n+2);
    }

    public boolean type_chk(class_c checker, SymbolTable sym_1, HashMap<String, Vector<method>> bet, HierarchyNode root, ClassTable classTable) {
         if(e1.get_type() == null) {
                 e1.getClass().cast(e1);
                e1.type_chk(checker, sym_1, bet, root, classTable);
                       
            }
            
         if(e2.get_type() == null) {
                e2.getClass().cast(e2);
                e2.type_chk(checker, sym_1, bet, root, classTable);
            }
        if(e1.get_type().toString().equals("Int") && e2.get_type().toString().equals("Int")) {
            return true;
        }
        else {
            semantError(checker.getFilename(), (TreeNode) this);
            errorStream.append("Non-Tree Constants.Int arguments: " + e1.get_type() + " * " + e2.get_type() +".\n");
            return false;
        }
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_mul");
    e1.dump_with_types(out, n + 2);
    e2.dump_with_types(out, n + 2);
    dump_type(out, n);
    }

    public PrintStream semantError(AbstractSymbol filename, TreeNode t) {
    errorStream.print(filename + ":" + t.getLineNumber() + ": ");
    return semantError();
    }

    public PrintStream semantError() {
    return errorStream;


}
}


/** Defines AST constructor 'divide'.
    <p>
    See <a href="TreeNode.html">TreeNode</a> for full documentation. */
class divide extends Expression {
    protected Expression e1;
    protected Expression e2;
    private PrintStream errorStream;
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
        errorStream = System.err;
        AbstractSymbol div_val = AbstractTable.stringtable.addString("Int");
        super.set_type(div_val);
    }
    public TreeNode copy() {
        return new divide(lineNumber, (Expression)e1.copy(), (Expression)e2.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "divide\n");
        e1.dump(out, n+2);
        e2.dump(out, n+2);
    }

    public boolean type_chk(class_c checker, SymbolTable sym_1, HashMap<String, Vector<method>> bet, HierarchyNode root, ClassTable classTable) {
         if(e1.get_type() == null) {
                 e1.getClass().cast(e1);
                e1.type_chk(checker, sym_1, bet, root, classTable);
                       
            }
            
         if(e2.get_type() == null) {
                e2.getClass().cast(e2);
                e2.type_chk(checker, sym_1, bet, root, classTable);
            }
        if(e1.get_type().toString().equals("Int") && e2.get_type().toString().equals("Int")) {
            return true;
        }
        else {
            semantError(checker.getFilename(), (TreeNode) this);
            errorStream.append("Non-Tree Constants.Int arguments: " + e1.get_type() + " / " + e2.get_type() +".\n");
            return false;
        }
    }

    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_divide");
    e1.dump_with_types(out, n + 2);
    e2.dump_with_types(out, n + 2);
    dump_type(out, n);
    }

    public PrintStream semantError(AbstractSymbol filename, TreeNode t) {
    errorStream.print(filename + ":" + t.getLineNumber() + ": ");
    return semantError();
    }

    public PrintStream semantError() {
    return errorStream;

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
        AbstractSymbol lt_val = AbstractTable.stringtable.addString("Bool");
        super.set_type(lt_val);
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
        AbstractSymbol e_val = AbstractTable.stringtable.addString("Bool");
        super.set_type(e_val);
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
        AbstractSymbol l_val = AbstractTable.stringtable.addString("Bool");
        super.set_type(l_val);
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
        AbstractSymbol bool_val = AbstractTable.stringtable.addString("Bool");
        super.set_type(bool_val);
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
        AbstractSymbol int_val = AbstractTable.stringtable.addString("Int");
        super.set_type(int_val);

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
        AbstractSymbol bool_val = AbstractTable.stringtable.addString("Bool");
        super.set_type(bool_val);
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
        AbstractSymbol string_val = AbstractTable.stringtable.addString("String");
        super.set_type(string_val);
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
        AbstractSymbol n_val = AbstractTable.stringtable.addString(type_name.toString());
        super.set_type(n_val);

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
    
     public boolean type_chk(class_c to_check, SymbolTable gimel, HashMap<String, Vector<method>> bet, HierarchyNode root, ClassTable classTable) {
        if(name.toString().equals("self")) {
            AbstractSymbol obj_value = AbstractTable.stringtable.addString("SELF_TYPE");
            super.set_type(obj_value);
            return true;
        }

        if(gimel.lookup(name)!= null) {
            if(gimel.lookup(name).getClass().equals(attr.class)) {
                attr obj_chk = (attr) gimel.lookup(name);
                Expression obj_chk_1 = (Expression) obj_chk.init;
                AbstractSymbol obj_value = AbstractTable.stringtable.addString(obj_chk.get_type().toString());
                super.set_type(obj_value);
                return true;
            }

            //Let expression variables or local variables
            if(gimel.lookup(name).getClass().equals(IdSymbol.class)) {
                IdSymbol aleph = (IdSymbol) gimel.lookup(name);
                super.set_type(aleph);
                return true;
            }

        }
        return true;

    }
    
    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_object");
    dump_AbstractSymbol(out, n + 2, name);
    dump_type(out, n);
    }

}
