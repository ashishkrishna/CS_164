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
    SymbolTable attr_checker = new SymbolTable(); //initialize attribute log
    SymbolTable method_checker = new SymbolTable(); //initialize method log
    methods_per_class = new HashMap<String, Vector<method>>(); //Annotate methods hashtable
    HierarchyNode root_1 = classTable.goodClasses();
    annotateMethods(root_1);
    if(!checkMain(methods_per_class)) { //if no main method 
        semanticerr++;
        semantError();
        errorStream.append("Main method not defined in Main class.\n");
    }
    if(!checkCopy(methods_per_class)) {
        semanticerr++;
        semantError();
        errorStream.append("Cannot redefine copy in non-basic class.\n");

    }
    checkClasses(root_1, attr_checker, method_checker, classTable);
    if(semanticerr > 0) {  //There shouldn't be any semantic errors
        System.err.println("Compilation halted due to static semantic errors.");
        System.exit(1);
    }
     /* some semantic analysis code may go here */
    }

    public void checkClasses(HierarchyNode root, SymbolTable symtab_1, SymbolTable symtab_2, ClassTable classTable) {
        symtab_1.enterScope(); 
        symtab_2.enterScope();
        class_c to_check_class = root.thisClassNode();
        Features this_classes_features = to_check_class.getFeatures(); //Check features of the class
        for(Enumeration<Feature> list_of_features = this_classes_features.getElements(); list_of_features.hasMoreElements();) { //stop when there are no more elements
            Feature alpha = list_of_features.nextElement();
            if(alpha.getClass().equals(attr.class)) {  //If it's an attribute
                attr attr_1 = (attr) alpha;
                if(symtab_1.lookup(attr_1.name) == null) { 
                    if(!attr_1.check_attr(symtab_1, to_check_class, root, methods_per_class, classTable)) //Check the attribute declaration
                        semanticerr++;     
                    symtab_1.addId(attr_1.name, attr_1.copy()); //Add attr to the symbol table
                }
                else {
                   semanticerr++;
                   semantError(to_check_class.getFilename(), attr_1);
                   errorStream.append("Attribute " + attr_1.name + " is an attribute of an inherited class.\n");
                }
                }
            }
        for(Enumeration<Feature> list_of_features_2 = this_classes_features.getElements(); list_of_features_2.hasMoreElements();) {
            Feature alpha_2 = list_of_features_2.nextElement();
            if (alpha_2.getClass().equals(method.class)) { //If it's a method
                method method_1 = (method) alpha_2;
                symtab_2.addId(method_1.name, method_1.copy()); //Add method to the symbol table
                symtab_1.enterScope();
                if(!method_1.ret_and_formal_chk(symtab_1, classNames, to_check_class)) 
                    semanticerr++;
                if(!method_1.type_chk(symtab_1, to_check_class, root, methods_per_class, classTable)) 
                    semanticerr++;
                 symtab_1.exitScope();

            }
        }
        if(root.isLeaf()) { //Climb back up the hierarchy if it's a root
             symtab_1.exitScope();
             symtab_2.exitScope();
            return;
        }
        else {
            Enumeration<HierarchyNode> childclasses = root.getChildren(); //Tree traversal
            while(childclasses.hasMoreElements()) {
                HierarchyNode child_class = childclasses.nextElement();
                checkClasses(child_class, symtab_1, symtab_2, classTable);
            }
             symtab_1.exitScope();
             symtab_2.exitScope();
            return;
        }
    }

    /*This method goes into each class and adds all of the necessary methods. This becomes useful in the dispatch type check where we evaluate whether or not
    the parameters are valid */
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
    /* Checks whether main() is in class Main */
    public boolean checkMain(HashMap<String, Vector<method>> bet) {
        Vector<method> methods = bet.get("Main");
        for(Enumeration<method> enums = methods.elements(); enums.hasMoreElements(); ) {
            method aleph = enums.nextElement();
            if(aleph.name.toString().equals("main")) {
                if(aleph.formals.getLength() == 0)
                    return true;
                else
                    return false;
            }
               

            }
            return false;
         }

    public boolean checkCopy(HashMap<String, Vector<method>> bet) {
        for(String enums : bet.keySet() ) {
            Vector<method> methods = bet.get(enums);
            for(Enumeration f = methods.elements(); f.hasMoreElements(); ) {
                method aleph = (method) f.nextElement();
                if(enums != "Object" && aleph.toString().equals("copy"))
                    return false;
            }
           }
            return true;
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

    /*Check whether class aleph_node is a subclass of aleph_name */
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

    /*Checking the return types and formal parameters of the method */
    public boolean ret_and_formal_chk(SymbolTable aleph, Vector<String> bet, class_c gimel) {
        if(!bet.contains(return_type.toString())) { /*Return type should be defined as a class defined in the file or one of the four basic classes */
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

    /*Type check for methods. Recursively calls type_check on the expr in the method */
    public boolean type_chk(SymbolTable aleph, class_c to_check, HierarchyNode root,HashMap<String, Vector<method>> hash_method, ClassTable classTable) {
        this.expr.getClass().cast(this.expr);
       if(!(this.expr.type_chk(to_check, aleph, hash_method, root, classTable))) {
        return false;
       }
       if(!(this.expr.get_type() == null)) {
        HierarchyNode this_class_node = classTable.getClassbyName(this.expr.get_type().toString(), classTable.goodClasses());
        /*Check for return type invariance.*/
        if(!return_type.toString().equals(this.expr.get_type().toString()) && (!this.expr.get_type().toString().equals("SELF_TYPE")) && !to_check.inherits_class(this_class_node, return_type.toString())) {
            semantError(to_check.getFilename(), (TreeNode) this);
            errorStream.append("Inferred return type " + this.expr.get_type() + " of method " + this.name + " does not conform to declared return type " + this.return_type + ".\n");
            return false;
       }

       }
      
       
       

     return true;
     
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
        if(init.get_type() == null) {
            init.getClass().cast(init);
        }
        if(!init.type_chk(to_check,  aleph, hash_method,  root,  classTable)){
            return false;
        }
            
        
        if(!(init.get_type()==null)) {
                HierarchyNode root_of_good_tree = classTable.goodClasses();
                HierarchyNode super_class = classTable.getClassbyName(type_decl.toString(), root_of_good_tree);
                if(!classTable.isChildClass(init.get_type().toString(), super_class)) {  
                /*Check for initialization conformity */      
                semantError(to_check.getFilename(), (TreeNode) this);
                errorStream.append("Inferred type " + this.init.get_type() + " of initialization of attribute " + this.name + " does not conform to declared type " + this.type_decl + ".\n");
                return false;
            }
        }

        
        return true;
    }

    /*Type of attribute*/
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

    /*Check the declaraiton of the formal as being valid. Ensure that it cannot have SELF_TYPE and that the type is one of the classes defined in file or one of four basic classes*/
    public boolean formal_chk(SymbolTable symtab, Vector<String> classnames, class_c to_check_class) {
        if(type_decl.toString().equals("SELF_TYPE")) {
             semantError(to_check_class.getFilename(), this);
             errorStream.append("Formal parameter " + this.name + " cannot have type TreeConstants.SELF_TYPE.\n");
            return false;
        }
        if(!classnames.contains(type_decl.toString())) {
            semantError(to_check_class.getFilename(), this);
            errorStream.append("Formal parameter type " + type_decl + " is not declared in file.\n");
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

    /*Set the type for the branch in the case expression*/
    public AbstractSymbol type_chk(class_c to_check, SymbolTable gimel, HashMap<String, Vector<method>> bet, HierarchyNode root, ClassTable classTable) {
       gimel.enterScope();
        gimel.addId(name, type_decl);
        if(expr == null) {
            expr.getClass().cast(expr);
        }
        if(expr.type_chk(to_check, gimel, bet, root, classTable)) {
            gimel.exitScope();
            return expr.get_type();
        }
        gimel.exitScope();
        return null;

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
    private PrintStream errorStream;
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
        errorStream = System.err;
    }
    public TreeNode copy() {
        return new assign(lineNumber, copy_AbstractSymbol(name), (Expression)expr.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "assign\n");
        dump_AbstractSymbol(out, n+2, name);
        expr.dump(out, n+2);
    }

    /*Check the  type of the expr that is involved in assignment. Ensure there is no mismatch*/
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
                semantError(to_check.getFilename(), (TreeNode) this);
                errorStream.append("Type " + expr.get_type().toString() + " of assigned expression does not conform to declared type "+ alepha.type_decl.toString() + " of identifier " +this.name.toString()+".\n");
                
                return false;
                }
        }
      }
        
         AbstractSymbol aleph = AbstractTable.stringtable.addString(expr.get_type().toString());
         super.set_type(aleph);
         return true;
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
    private PrintStream errorStream;
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
        errorStream = System.err;
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

    /*Check whether the semantic rules for the static dispatch are satisfied as defined in the cool manual. If there is any issue, error out*/
     public boolean type_chk(class_c to_check, SymbolTable gimel, HashMap<String, Vector<method>> bet, HierarchyNode root, ClassTable classTable) {
            expr.getClass().cast(expr);
            expr.type_chk(to_check,  gimel, bet, root,  classTable);
            HierarchyNode root_of_good_tree = classTable.goodClasses();
            HierarchyNode super_class = classTable.getClassbyName(type_name.toString(), root_of_good_tree);
                if(!expr.get_type().toString().equals("SELF_TYPE") && !classTable.isChildClass(expr.get_type().toString(), super_class)) {
                     semantError(to_check.getFilename(), (TreeNode) this);
                    errorStream.append("Static dispatch called on invalid class " + this.type_name + ".\n");
                    return false;
                }
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
                        return false; /*Error if the parameter length mismatches*/
                    }
                    // Subtype checking needs work
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
        /*Dispatch is invalid since it is not in the class hierarchy of inheritance */
        semantError(to_check.getFilename(), this);
        errorStream.append("Method " + this.name.toString() + " cannot be called by " + root.thisNode() + ".\n");
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
    /*Type check for dispatch. Check if all of the rules specified in the cool manual are satisfied. If any variation, error and return false*/
    public boolean type_chk(class_c to_check, SymbolTable gimel, HashMap<String, Vector<method>> bet, HierarchyNode root, ClassTable classTable) {
            expr.getClass().cast(expr);
            if(!expr.type_chk(to_check,  gimel, bet, root,  classTable))  {
                return false;
            }
            if(expr.get_type() == null) {
                return false;
            }

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
                        errorStream.append("Method " + this.name.toString() + " invoked with wrong number of arguments.\n"); //Wrong number of arguments. Error out here.
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
        //Dispatch not applicable for this type Error out here.
        semantError(to_check.getFilename(), this);
        errorStream.append("Method " + this.name.toString() + " cannot be called by " + root.thisNode() + ".\n");
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
    private PrintStream errorStream;
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
        errorStream = System.err;

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

      /*Type check for if then else. Evaluate each  (pred, then_exp, else_exp). Then, if else_exp evaluates to SELF_TYPE, the whole expression evals to SELF_TYPE. Otherwise, find the join of the then and else
      statements. This will be the type of the entire expression */
      public boolean type_chk(class_c checker, SymbolTable sym_1, HashMap<String, Vector<method>> bet, HierarchyNode root, ClassTable classTable) {
        pred.getClass().cast(pred);
        if(!pred.type_chk(checker, sym_1, bet, root, classTable)) {
            return false;
        }
        if(!pred.get_type().toString().equals("Bool")) {
            semantError(checker.getFilename(), this);
            errorStream.append("Predicate not a boolean.\n"); //Wrong number of arguments. Error out here.
            return false;
        }
        then_exp.getClass().cast(then_exp);
        else_exp.getClass().cast(else_exp);
        boolean a_1 = then_exp.type_chk(checker, sym_1, bet, root, classTable);
        boolean a_2 = else_exp.type_chk(checker, sym_1, bet, root, classTable);
        if(!a_1 || !a_2) {
            //If either type check evals to false, return false. Type check has errored. 
            return false;
        }
        Vector<String> classes = new Vector<String>(0);
        if(then_exp.get_type() != null) {
            if(then_exp.get_type().toString().equals("SELF_TYPE"))
                classes.addElement(checker.getName().toString());
            else
                classes.addElement(then_exp.get_type().toString());
        }
        if(else_exp.get_type() != null) {
         if(else_exp.get_type().toString().equals("SELF_TYPE")) {
            if(!else_exp.getClass().equals(object.class)) {
             AbstractSymbol delta = AbstractTable.stringtable.addString("SELF_TYPE");
             super.set_type(delta);
             return true;
         }
            else
                classes.addElement(checker.getName().toString());
        
    }
        else
            classes.addElement(else_exp.get_type().toString());
        }
        if(then_exp.get_type() == null && else_exp.get_type() == null) //Exit if the type of both then_exp and else_exp cannot be set.
            return false;
        HierarchyNode root_of_good_tree = classTable.goodClasses();
        Vector<HierarchyNode> node_list = new Vector<HierarchyNode>(0);
        node_list = classTable.find_join_outer(root_of_good_tree, classes, node_list);
        int i = node_list.size();
        if(i==0) {
            //Cannot find join, so exit
            semantError(checker.getFilename(), this);
            errorStream.append("No common subclass between these two classes.\n"); //Wrong number of arguments. Error out here.
            return false;
        }
        AbstractSymbol delta = AbstractTable.stringtable.addString(node_list.elementAt(i-1).thisNode());
        super.set_type(delta);
        return true;
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
    private PrintStream errorStream;
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
        errorStream = System.err;

    }
    public TreeNode copy() {
        return new loop(lineNumber, (Expression)pred.copy(), (Expression)body.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "loop\n");
        pred.dump(out, n+2);
        body.dump(out, n+2);
    }
    /* Type check for loop. Evaluate the type checks for pred. Ensure it's a boolean. Evaluate body. Then set the entire expression to Object*/
    public boolean type_chk(class_c checker, SymbolTable sym_1, HashMap<String, Vector<method>> bet, HierarchyNode root, ClassTable classTable) {
        pred.getClass().cast(pred);
        if(!pred.type_chk(checker, sym_1, bet, root, classTable)) 
            return false;
        body.getClass().cast(body);
        if(!body.type_chk(checker, sym_1, bet, root, classTable))
            return false;
        if(!pred.get_type().toString().equals("Bool")) {
            semantError(checker.getFilename(), this);
            errorStream.append("Predicate is not of type Bool.\n"); //Wrong number of arguments. Error out here.
            return false;
        }
        AbstractSymbol loop_sym = AbstractTable.stringtable.addString("Object");
        super.set_type(loop_sym);
        return true;
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
    private PrintStream errorStream;
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
        errorStream = System.err;
    }

    /*Type check for case expressions. Evaluate each branch (each case). Store all of the types of these cases. Then, find the join of them all. If there are any errors, exit*/
    public boolean type_chk(class_c checker, SymbolTable sym_1, HashMap<String, Vector<method>> bet, HierarchyNode root, ClassTable classTable) {
        expr.getClass().cast(expr);
        Vector<String> case_classes = new Vector<String>(0);
        expr.type_chk(checker, sym_1, bet, root, classTable);
        AbstractSymbol type_expr = expr.get_type();
        for(Enumeration<Case> case_chks = cases.getElements(); case_chks.hasMoreElements();) {
            Case case_1 = case_chks.nextElement();
            if(case_1.getClass().equals(branch.class)) {
                branch b1 = (branch) case_1;
                AbstractSymbol beta = b1.type_chk(checker, sym_1, bet, root, classTable);
                if(beta != null) {
                    if(beta.toString().equals("SELF_TYPE"))  {
                        beta = AbstractTable.stringtable.addString(checker.getName().toString());
                    }
                    case_classes.addElement(beta.toString());
                }
                else {
                    return false;
                }
            }
            else {
                return false;
            }
        }
        //Find the most common class
        HierarchyNode root_of_good_tree = classTable.goodClasses();
        Vector<HierarchyNode> node_list = new Vector<HierarchyNode>(0);
        node_list = classTable.find_join_outer(root_of_good_tree, case_classes, node_list);
        int i = node_list.size();
        AbstractSymbol delta = AbstractTable.stringtable.addString(node_list.elementAt(i-1).thisNode());
        if(node_list.elementAt(i-1).thisNode().equals(checker.getName().toString())) {
            delta = AbstractTable.stringtable.addString("SELF_TYPE");
            super.set_type(delta);
            return true;
        }
        super.set_type(delta);
        return true;
    }
    public TreeNode copy() {
        return new typcase(lineNumber, (Expression)expr.copy(), (Cases)cases.copy());
    }
    public PrintStream semantError(AbstractSymbol filename, TreeNode t) {
    errorStream.print(filename + ":" + t.getLineNumber() + ": ");
    return semantError();
    }

    public PrintStream semantError() {
    return errorStream;
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
    private PrintStream errorStream;
    /** Creates "block" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for body
      */
    public block(int lineNumber, Expressions a1) {
        super(lineNumber);
        body = a1;
        errorStream = System.err;
        
    }


    /*Type check for block. Evaluate each expression in the block. Set the type to be the same as that of the last expression in the block */
    public boolean type_chk(class_c checker, SymbolTable sym_1, HashMap<String, Vector<method>> bet, HierarchyNode root, ClassTable classTable) {
        Expression aleph_1 = (Expression) body.getNth(body.getLength()-1);
        if(!(aleph_1.get_type() == null)) {
        AbstractSymbol block_val = AbstractTable.stringtable.addString(aleph_1.get_type().toString());
        super.set_type(block_val);
    }
        int count = 0;
        for(Enumeration<Expression> statements = body.getElements(); statements.hasMoreElements();) {
                Expression statement_next = statements.nextElement();
                if(statement_next.get_type() == null) 
                    statement_next.getClass().cast(statement_next);
                if(!statement_next.type_chk(checker, sym_1, bet, root, classTable)) {
                    return false;
                }   
                if(count == body.getLength()-1) {
                    AbstractSymbol aleph = AbstractTable.stringtable.addString(statement_next.get_type().toString());
                    if(statement_next.get_type().toString().equals("SELF_TYPE"))
                        aleph = AbstractTable.stringtable.addString("SELF_TYPE");
                    super.set_type(aleph);
                    return true;
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

    public PrintStream semantError(AbstractSymbol filename, TreeNode t) {
    errorStream.print(filename + ":" + t.getLineNumber() + ": ");
    return semantError();
    }

    public PrintStream semantError() {
    return errorStream;
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
    /*Type check for let. Evaluate the type_check for init. Ensure that it conforms to type_decl. Enter a new scope in the symbol table. 
    Add the identifier and its type_decl. Now, in this new scope evaluate the body*/ 
    public boolean type_chk(class_c checker, SymbolTable sym_1, HashMap<String, Vector<method>> bet, HierarchyNode root, ClassTable classTable) {
        sym_1.enterScope();
        AbstractSymbol T_zero = type_decl;
        if(type_decl.toString().equals("SELF_TYPE"))
            T_zero = AbstractTable.stringtable.addString(checker.getName().toString());
        init.getClass().cast(init);
        if(!init.type_chk(checker, sym_1, bet, root, classTable)) {
            sym_1.exitScope();
            return false;
        }
        if(init.get_type() != null) {
            HierarchyNode root_of_good_tree = classTable.goodClasses();
            HierarchyNode root_of_small = classTable.getClassbyName(T_zero.toString(), root_of_good_tree);
            if(!classTable.isChildClass(init.get_type().toString(), root_of_small)) {
                semantError(checker.getFilename(), this);
                errorStream.append("Inferred type " + this.init.get_type().toString() + " of initialization of " + identifier.toString() + " does not conform to identifier's declared type " + this.type_decl.toString() + ".\n"); //Wrong number of arguments. Error out here.
                sym_1.exitScope();
                return false;
            }
            sym_1.addId(identifier, T_zero); 
            body.getClass().cast(body);
            if(!body.type_chk(checker, sym_1, bet, root, classTable)) {
                sym_1.exitScope();
                return false;
            }
            super.set_type(body.get_type());
            sym_1.exitScope();
            return true;
        }
        sym_1.addId(identifier, T_zero);
        body.getClass().cast(body);
        if(!body.type_chk(checker, sym_1, bet, root, classTable)) {
            sym_1.exitScope();
            return false;
        }
        super.set_type(body.get_type());
        sym_1.exitScope();
        return true;
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
    }
    public TreeNode copy() {
        return new plus(lineNumber, (Expression)e1.copy(), (Expression)e2.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "plus\n");
        e1.dump(out, n+2);
        e2.dump(out, n+2);
    }

    /*Type check for plus. Ensure that both expressions are of type Int */
    public boolean type_chk(class_c checker, SymbolTable sym_1, HashMap<String, Vector<method>> bet, HierarchyNode root, ClassTable classTable) {
        AbstractSymbol plus_val = AbstractTable.stringtable.addString("Int");
        super.set_type(plus_val);
        if(e1.get_type() == null) {
                e1.getClass().cast(e1);
            }
         e1.type_chk(checker, sym_1, bet, root, classTable);  
         if(e2.get_type() == null) {
                e2.getClass().cast(e2);
            }
        e2.type_chk(checker, sym_1, bet, root, classTable);
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
    }
    public TreeNode copy() {
        return new sub(lineNumber, (Expression)e1.copy(), (Expression)e2.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "sub\n");
        e1.dump(out, n+2);
        e2.dump(out, n+2);
    }


    /*Type check for sub. Ensure that both expressions are of type Int */
    public boolean type_chk(class_c checker, SymbolTable sym_1, HashMap<String, Vector<method>> bet, HierarchyNode root, ClassTable classTable) {
        AbstractSymbol sub_val = AbstractTable.stringtable.addString("Int");
        super.set_type(sub_val);
        if(e1.get_type() == null) {
                 e1.getClass().cast(e1);                       
            }
         e1.type_chk(checker, sym_1, bet, root, classTable);
         if(e2.get_type() == null) {
                e2.getClass().cast(e2);
            }
        e2.type_chk(checker, sym_1, bet, root, classTable);
        if(e1.get_type().toString().equals("Int") && e2.get_type().toString().equals("Int")) {
            return true;
        }
        else {
            //Print error for this case
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
    }
    public TreeNode copy() {
        return new mul(lineNumber, (Expression)e1.copy(), (Expression)e2.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "mul\n");
        e1.dump(out, n+2);
        e2.dump(out, n+2);
    }


    /*Type check for mul. Ensure that both expressions are of type Int */
    public boolean type_chk(class_c checker, SymbolTable sym_1, HashMap<String, Vector<method>> bet, HierarchyNode root, ClassTable classTable) {
         AbstractSymbol mul_val = AbstractTable.stringtable.addString("Int");
         super.set_type(mul_val);
         if(e1.get_type() == null) {
                 e1.getClass().cast(e1);
            }
         e1.type_chk(checker, sym_1, bet, root, classTable);  
         if(e2.get_type() == null) {
                e2.getClass().cast(e2);
            }
        e2.type_chk(checker, sym_1, bet, root, classTable);
        if(e1.get_type().toString().equals("Int") && e2.get_type().toString().equals("Int")) {
            return true;
        }
        else {
            //Print error for this case
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
    }
    public TreeNode copy() {
        return new divide(lineNumber, (Expression)e1.copy(), (Expression)e2.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "divide\n");
        e1.dump(out, n+2);
        e2.dump(out, n+2);
    }


    /*Type check for divide. Ensure that both expressions are of type Int */
    public boolean type_chk(class_c checker, SymbolTable sym_1, HashMap<String, Vector<method>> bet, HierarchyNode root, ClassTable classTable) {
        AbstractSymbol div_val = AbstractTable.stringtable.addString("Int");
        super.set_type(div_val);
        e1.getClass().cast(e1);
        e1.type_chk(checker, sym_1, bet, root, classTable);
        e2.getClass().cast(e2);
        e2.type_chk(checker, sym_1, bet, root, classTable);
        if(e1.get_type().toString().equals("Int") && e2.get_type().toString().equals("Int")) {
            return true;
        }
        else {
            //Print error on case
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
    private PrintStream errorStream;
    /** Creates "neg" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for e1
      */
    public neg(int lineNumber, Expression a1) {
        super(lineNumber);
        e1 = a1;
        errorStream = System.err;
    }
    public TreeNode copy() {
        return new neg(lineNumber, (Expression)e1.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "neg\n");
        e1.dump(out, n+2);
    }

    /*Type check for neg. Ensure that expression is type Int. Set type to be Int. */
     public boolean type_chk(class_c checker, SymbolTable sym_1, HashMap<String, Vector<method>> bet, HierarchyNode root, ClassTable classTable) {
        AbstractSymbol neg_type = AbstractTable.stringtable.addString("Int");
        super.set_type(neg_type);
        e1.getClass().cast(e1);
        if(!e1.type_chk(checker, sym_1, bet, root, classTable))
            return false;
        if(e1.get_type() != null && !e1.get_type().toString().equals("Int")) {
            semantError(checker.getFilename(), (TreeNode) this);
            errorStream.append("Non-Tree Constants.Int arguments: " + e1.get_type() +".\n");
            return false;
        }
        return true;
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
    private PrintStream errorStream;
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
        errorStream = System.err;
    }
    public TreeNode copy() {
        return new lt(lineNumber, (Expression)e1.copy(), (Expression)e2.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "lt\n");
        e1.dump(out, n+2);
        e2.dump(out, n+2);
    }
    /*Type check for less than. Check that both expressions are Ints. Set type to be Bool*/
    public boolean type_chk(class_c checker, SymbolTable sym_1, HashMap<String, Vector<method>> bet, HierarchyNode root, ClassTable classTable) {
        AbstractSymbol lt_val = AbstractTable.stringtable.addString("Bool");
        super.set_type(lt_val);
        e1.getClass().cast(e1);
        e2.getClass().cast(e2);
        boolean a_1 = e1.type_chk(checker, sym_1, bet, root, classTable);
        boolean a_2 = e2.type_chk(checker, sym_1, bet, root, classTable);
        if(!a_1 || !a_2) {
            return false;
        }
        if(!e1.get_type().toString().equals("Int") || !e2.get_type().toString().equals("Int")) {
            semantError(checker.getFilename(), (TreeNode) this);
            errorStream.append("Non-Tree Constants.Int arguments: " + e1.get_type() + " < " + e2.get_type() +".\n");
            return false;
        }
        return true;
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
    private PrintStream errorStream;
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
        errorStream = System.err;
    }
    public TreeNode copy() {
        return new eq(lineNumber, (Expression)e1.copy(), (Expression)e2.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "eq\n");
        e1.dump(out, n+2);
        e2.dump(out, n+2);
    }

/*Type check for eq. Check that both expressions are of the same type. Set type to Bool */
 public boolean type_chk(class_c checker, SymbolTable sym_1, HashMap<String, Vector<method>> bet, HierarchyNode root, ClassTable classTable) {
         AbstractSymbol e_val = AbstractTable.stringtable.addString("Bool");
        super.set_type(e_val);
         e1.getClass().cast(e1);
         e2.getClass().cast(e2);
        //Print error on each case
        boolean a_1  = e1.type_chk(checker, sym_1, bet, root, classTable);
        boolean a_2 = e2.type_chk(checker,sym_1, bet, root, classTable);
        if(!a_1 || !a_2)
            return false;
        Vector<String> prim_check = new Vector<String>(0);
        prim_check.addElement("Int");
        prim_check.addElement("Bool");
        prim_check.addElement("String");
        if(!(e1.get_type() == null) && !(e2.get_type() == null)) {
            if(prim_check.contains(e1.get_type().toString()) || prim_check.contains(e2.get_type().toString())) { //Check whether e1 or e2 are of any of Int, Bool, or String
                    if(!(e1.get_type().toString().equals(e2.get_type().toString()))) { //if this is the case, check equality
                         semantError(checker.getFilename(), (TreeNode) this);
                         errorStream.append("Type mismatch: " + e1.get_type() + " and " + e2.get_type() +".\n");
                         return false;
                    }
            }
            return true;
        }
        return true;
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
    private PrintStream errorStream;
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
        errorStream = System.err;
    }
    public TreeNode copy() {
        return new leq(lineNumber, (Expression)e1.copy(), (Expression)e2.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "leq\n");
        e1.dump(out, n+2);
        e2.dump(out, n+2);
    }

    /*Type check for less than or equal to. Check that both expressions are Ints. Set type to Int. */
    public boolean type_chk(class_c checker, SymbolTable sym_1, HashMap<String, Vector<method>> bet, HierarchyNode root, ClassTable classTable) {
        AbstractSymbol l_val = AbstractTable.stringtable.addString("Bool");
        super.set_type(l_val);
        e1.getClass().cast(e1);
        e2.getClass().cast(e2);
        //Print error on each case
        boolean a_1 = e1.type_chk(checker, sym_1, bet, root, classTable);
        boolean a_2 = e2.type_chk(checker, sym_1, bet, root, classTable);
        if(!a_1 || !a_2) {
            return false;
        }
        if(!e1.get_type().toString().equals("Int") || !e2.get_type().toString().equals("Int")) {
            semantError(checker.getFilename(), (TreeNode) this);
            errorStream.append("Non-Tree Constants.Int arguments: " + e1.get_type() + " <= " + e2.get_type() +".\n");
            return false;
        }
        return true;
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
    private PrintStream errorStream;
    /** Creates "comp" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for e1
      */
    public comp(int lineNumber, Expression a1) {
        super(lineNumber);
        e1 = a1;
        errorStream = System.err;
    }
    public TreeNode copy() {
        return new comp(lineNumber, (Expression)e1.copy());
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "comp\n");
        e1.dump(out, n+2);
    }

/*Type check for complement. Check that expression is Bool. Set type to Bool */
    public boolean type_chk(class_c checker, SymbolTable sym_1, HashMap<String, Vector<method>> bet, HierarchyNode root, ClassTable classTable) {
        AbstractSymbol bool_val = AbstractTable.stringtable.addString("Bool");
        super.set_type(bool_val);
        e1.getClass().cast(e1);
        if(!(e1.type_chk(checker, sym_1, bet, root, classTable)))
            return false; //Error printing needed
        if(e1.get_type() == null)
            return false;
        if(!e1.get_type().toString().equals("Bool")) {
             semantError(checker.getFilename(), (TreeNode) this);
             errorStream.append("Argument not of type Bool. Cannot execute.\n");
            return false;
        }
        return true;
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

    }
    public TreeNode copy() {
        return new int_const(lineNumber, copy_AbstractSymbol(token));
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "int_const\n");
        dump_AbstractSymbol(out, n+2, token);
    }

    /*Type check for int_const. Set type to Int */
    public boolean type_chk(class_c checker, SymbolTable sym_1, HashMap<String, Vector<method>> bet, HierarchyNode root, ClassTable classTable) {
        AbstractSymbol string_val = AbstractTable.stringtable.addString("Int");
        super.set_type(string_val);
        return true;
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
    }
    public TreeNode copy() {
        return new bool_const(lineNumber, copy_Boolean(val));
    }

    /* Type check for bool_cost. Set type to Bool */
    public boolean type_chk(class_c checker, SymbolTable sym_1, HashMap<String, Vector<method>> bet, HierarchyNode root, ClassTable classTable) {
        AbstractSymbol string_val = AbstractTable.stringtable.addString("Bool");
        super.set_type(string_val);
        return true;
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
    }
    public TreeNode copy() {
        return new string_const(lineNumber, copy_AbstractSymbol(token));
    }

    /*Type check for string_const. Set type to String */
    public boolean type_chk(class_c checker, SymbolTable sym_1, HashMap<String, Vector<method>> bet, HierarchyNode root, ClassTable classTable) {
        AbstractSymbol string_val = AbstractTable.stringtable.addString("String");
        super.set_type(string_val);
        return true;
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
    private PrintStream errorStream;
    /** Creates "new_" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for type_name
      */
    public new_(int lineNumber, AbstractSymbol a1) {
        super(lineNumber);
        type_name = a1;
        errorStream = System.err;

    }
    public TreeNode copy() {
        return new new_(lineNumber, copy_AbstractSymbol(type_name));
    }

    /* Type check for new object. Set type to type_name */
    public boolean type_chk(class_c checker, SymbolTable sym_1, HashMap<String, Vector<method>> bet, HierarchyNode root, ClassTable classTable) {
        HierarchyNode aleph = classTable.goodClasses();
        HierarchyNode type_nd = classTable.getClassbyName(type_name.toString(), aleph);
        if(type_nd == null) { //If type_name is not in class table, then error out
             semantError(checker.getFilename(), (TreeNode) this);
            errorStream.append("Type " + this.type_name + " not a declared or built-in class.\n");
            return false;
        }

        AbstractSymbol n_val = AbstractTable.stringtable.addString(type_name.toString());
        super.set_type(n_val);
        return true;
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "new_\n");
        dump_AbstractSymbol(out, n+2, type_name);
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
    private PrintStream errorStream;
    /** Creates "isvoid" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for e1
      */
    public isvoid(int lineNumber, Expression a1) {
        super(lineNumber);
        e1 = a1;
        errorStream = System.err;
    }
    public TreeNode copy() {
        return new isvoid(lineNumber, (Expression)e1.copy());
    }

    /*Type check for isvoid. Ensure that expression is type void. Set type to Bool */
    public boolean type_chk(class_c checker, SymbolTable sym_1, HashMap<String, Vector<method>> bet, HierarchyNode root, ClassTable classTable) {
        AbstractSymbol a_sym = AbstractTable.stringtable.addString("Bool");
        super.set_type(a_sym);
        if(e1.get_type() == null)
            e1.getClass().cast(e1);
        if(!(e1.type_chk(checker, sym_1, bet, root, classTable))) {
            return false; 
        }
        return true;
        }

    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "isvoid\n");
        e1.dump(out, n+2);
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
    private PrintStream errorStream;
    /** Creates "object" AST node. 
      *
      * @param lineNumber the line in the source file from which this node came.
      * @param a0 initial value for name
      */
    public object(int lineNumber, AbstractSymbol a1) {
        super(lineNumber);
        name = a1;
        errorStream = System.err;
    }
    public TreeNode copy() {
        return new object(lineNumber, copy_AbstractSymbol(name));
    }
    public void dump(PrintStream out, int n) {
        out.print(Utilities.pad(n) + "object\n");
        dump_AbstractSymbol(out, n+2, name);
    }
    
    /*Type check for object. Find the object in the symbol table. and set the type to be what is declared in the symbol table lookup */
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
            if(gimel.lookup(name).getClass().equals(formalc.class)) {
                formalc aleph = (formalc) gimel.lookup(name);
                super.set_type(aleph.type_decl);
                return true;
            }

        }
        return false;

    }
  

    public void dump_with_types(PrintStream out, int n) {
        dump_line(out, n);
        out.println(Utilities.pad(n) + "_object");
    dump_AbstractSymbol(out, n + 2, name);
    dump_type(out, n);
    }

}

