/*
Copyright (c) 2000 The Regents of the University of California.
All rights reserved.
Permission to use, copy, modify, and distribute this software for any
purpose, without fee, and without written agreement is hereby granted,
provided that the above copyright notice and the following two
paragraphs appear in all copies of this software.
IN NO EVENT SHALL THE UNIVERSITY OF CALIFORNIA BE LIABLE TO ANY PARTY FOR
DIRECT, INDIRECT, SPECIAL, INCIDENTAL, OR CONSEQUENTIAL DAMAGES ARISING OUT
OF THE USE OF THIS SOFTWARE AND ITS DOCUMENTATION, EVEN IF THE UNIVERSITY OF
CALIFORNIA HAS BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
THE UNIVERSITY OF CALIFORNIA SPECIFICALLY DISCLAIMS ANY WARRANTIES,
INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY
AND FITNESS FOR A PARTICULAR PURPOSE.  THE SOFTWARE PROVIDED HEREUNDER IS
ON AN "AS IS" BASIS, AND THE UNIVERSITY OF CALIFORNIA HAS NO OBLIGATION TO
PROVIDE MAINTENANCE, SUPPORT, UPDATES, ENHANCEMENTS, OR MODIFICATIONS.
*/

// This is a project skeleton file

import java.io.PrintStream;
import java.util.Vector;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Stack;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/** This class is used for representing the inheritance tree during code
    generation. You will need to fill in some of its methods and
    potentially extend it in other useful ways. */
class CgenClassTable extends SymbolTable {

    /** All classes in the program, represented as CgenNode */
    private Vector nds;

    /** This is the stream to which assembly instructions are output */
    private PrintStream str;

    private int stringclasstag;
    private int intclasstag;
    private int boolclasstag;
    protected static  HashMap<String, Vector<String>> virtual_disptbl;
    protected static HashMap<String, Vector<method>> method_decls;	
    protected static HashMap<String, Vector<attr>> attr_decls;
    protected static int frame_offset;
    protected static int frame_to_top_offset;
    protected static Stack frame_offset_store;
    protected static SymbolTable attr_defs;

    // The following methods emit code for constants and global
    // declarations.

    /** Emits code to start the .data segment and to
     * declare the global names.
     * */
    private void codeGlobalData() {
	// The following global names must be defined first.

	str.print("\t.data\n" + CgenSupport.ALIGN);
	str.println(CgenSupport.GLOBAL + CgenSupport.CLASSNAMETAB);
	str.print(CgenSupport.GLOBAL); 
	CgenSupport.emitProtObjRef(TreeConstants.Main, str);
	str.println("");
	str.print(CgenSupport.GLOBAL); 
	CgenSupport.emitProtObjRef(TreeConstants.Int, str);
	str.println("");
	str.print(CgenSupport.GLOBAL); 
	CgenSupport.emitProtObjRef(TreeConstants.Str, str);
	str.println("");
	str.print(CgenSupport.GLOBAL); 
	BoolConst.falsebool.codeRef(str);
	str.println("");
	str.print(CgenSupport.GLOBAL); 
	BoolConst.truebool.codeRef(str);
	str.println("");
	str.println(CgenSupport.GLOBAL + CgenSupport.INTTAG);
	str.println(CgenSupport.GLOBAL + CgenSupport.BOOLTAG);
	str.println(CgenSupport.GLOBAL + CgenSupport.STRINGTAG);

	// We also need to know the tag of the Int, String, and Bool classes
	// during code generation.

	str.println(CgenSupport.INTTAG + CgenSupport.LABEL 
		    + CgenSupport.WORD + intclasstag);
	str.println(CgenSupport.BOOLTAG + CgenSupport.LABEL 
		    + CgenSupport.WORD + boolclasstag);
	str.println(CgenSupport.STRINGTAG + CgenSupport.LABEL 
		    + CgenSupport.WORD + stringclasstag);

    }

    /** Emits code to start the .text segment and to
     * declare the global names.
     * */
    private void codeGlobalText() {
	str.println(CgenSupport.GLOBAL + CgenSupport.HEAP_START);
	str.print(CgenSupport.HEAP_START + CgenSupport.LABEL);
	str.println(CgenSupport.WORD + 0);
	str.println("\t.text");
	str.print(CgenSupport.GLOBAL);
	CgenSupport.emitInitRef(TreeConstants.Main, str);
	str.println("");
	str.print(CgenSupport.GLOBAL);
	CgenSupport.emitInitRef(TreeConstants.Int, str);
	str.println("");
	str.print(CgenSupport.GLOBAL);
	CgenSupport.emitInitRef(TreeConstants.Str, str);
	str.println("");
	str.print(CgenSupport.GLOBAL);
	CgenSupport.emitInitRef(TreeConstants.Bool, str);
	str.println("");
	str.print(CgenSupport.GLOBAL);
	CgenSupport.emitMethodRef(TreeConstants.Main, TreeConstants.main_meth, str);
	str.println("");
    }

    /** Emits code definitions for boolean constants. */
    private void codeBools(int classtag) {
	BoolConst.falsebool.codeDef(classtag, str);
	BoolConst.truebool.codeDef(classtag, str);
    }

    /** Generates GC choice constants (pointers to GC functions) */
    private void codeSelectGc() {
	str.println(CgenSupport.GLOBAL + "_MemMgr_INITIALIZER");
	str.println("_MemMgr_INITIALIZER:");
	str.println(CgenSupport.WORD 
		    + CgenSupport.gcInitNames[Flags.cgen_Memmgr]);

	str.println(CgenSupport.GLOBAL + "_MemMgr_COLLECTOR");
	str.println("_MemMgr_COLLECTOR:");
	str.println(CgenSupport.WORD 
		    + CgenSupport.gcCollectNames[Flags.cgen_Memmgr]);

	str.println(CgenSupport.GLOBAL + "_MemMgr_TEST");
	str.println("_MemMgr_TEST:");
	str.println(CgenSupport.WORD 
		    + ((Flags.cgen_Memmgr_Test == Flags.GC_TEST) ? "1" : "0"));
    }

    /** Emits code to reserve space for and initialize all of the
     * constants.  Class names should have been added to the string
     * table (in the supplied code, is is done during the construction
     * of the inheritance graph), and code for emitting string constants
     * as a side effect adds the string's length to the integer table.
     * The constants are emmitted by running through the stringtable and
     * inttable and producing code for each entry. */
    private void codeConstants() {
	// Add constants that are required by the code generator.
	AbstractTable.stringtable.addString("");
	AbstractTable.inttable.addString("0");
	AbstractTable.stringtable.codeStringTable(stringclasstag, str);
	AbstractTable.inttable.codeStringTable(intclasstag, str);
	codeBools(boolclasstag);
    }


    /** Creates data structures representing basic Cool classes (Object,
     * IO, Int, Bool, String).  Please note: as is this method does not
     * do anything useful; you will need to edit it to make if do what
     * you want.
     * */
    private void installBasicClasses() {
	AbstractSymbol filename 
	    = AbstractTable.stringtable.addString("<basic class>");
	
	// A few special class names are installed in the lookup table
	// but not the class list.  Thus, these classes exist, but are
	// not part of the inheritance hierarchy.  No_class serves as
	// the parent of Object and the other special classes.
	// SELF_TYPE is the self class; it cannot be redefined or
	// inherited.  prim_slot is a class known to the code generator.

	addId(TreeConstants.No_class,
	      new CgenNode(new class_c(0,
				      TreeConstants.No_class,
				      TreeConstants.No_class,
				      new Features(0),
				      filename),
			   CgenNode.Basic, this));

	addId(TreeConstants.SELF_TYPE,
	      new CgenNode(new class_c(0,
				      TreeConstants.SELF_TYPE,
				      TreeConstants.No_class,
				      new Features(0),
				      filename),
			   CgenNode.Basic, this));
	
	addId(TreeConstants.prim_slot,
	      new CgenNode(new class_c(0,
				      TreeConstants.prim_slot,
				      TreeConstants.No_class,
				      new Features(0),
				      filename),
			   CgenNode.Basic, this));

	// The Object class has no parent class. Its methods are
	//        cool_abort() : Object    aborts the program
	//        type_name() : Str        returns a string representation 
	//                                 of class name
	//        copy() : SELF_TYPE       returns a copy of the object

	class_c Object_class = 
	    new class_c(0, 
		       TreeConstants.Object_, 
		       TreeConstants.No_class,
		       new Features(0)
			   .appendElement(new method(0, 
					      TreeConstants.cool_abort, 
					      new Formals(0), 
					      TreeConstants.Object_, 
					      new no_expr(0)))
			   .appendElement(new method(0,
					      TreeConstants.type_name,
					      new Formals(0),
					      TreeConstants.Str,
					      new no_expr(0)))
			   .appendElement(new method(0,
					      TreeConstants.copy,
					      new Formals(0),
					      TreeConstants.SELF_TYPE,
					      new no_expr(0))),
		       filename);

	installClass(new CgenNode(Object_class, CgenNode.Basic, this));
	
	// The IO class inherits from Object. Its methods are
	//        out_string(Str) : SELF_TYPE  writes a string to the output
	//        out_int(Int) : SELF_TYPE      "    an int    "  "     "
	//        in_string() : Str            reads a string from the input
	//        in_int() : Int                "   an int     "  "     "

	class_c IO_class = 
	    new class_c(0,
		       TreeConstants.IO,
		       TreeConstants.Object_,
		       new Features(0)
			   .appendElement(new method(0,
					      TreeConstants.out_string,
					      new Formals(0)
						  .appendElement(new formalc(0,
								     TreeConstants.arg,
								     TreeConstants.Str)),
					      TreeConstants.SELF_TYPE,
					      new no_expr(0)))
			   .appendElement(new method(0,
					      TreeConstants.out_int,
					      new Formals(0)
						  .appendElement(new formalc(0,
								     TreeConstants.arg,
								     TreeConstants.Int)),
					      TreeConstants.SELF_TYPE,
					      new no_expr(0)))
			   .appendElement(new method(0,
					      TreeConstants.in_string,
					      new Formals(0),
					      TreeConstants.Str,
					      new no_expr(0)))
			   .appendElement(new method(0,
					      TreeConstants.in_int,
					      new Formals(0),
					      TreeConstants.Int,
					      new no_expr(0))),
		       filename);

	CgenNode IO_node = new CgenNode(IO_class, CgenNode.Basic, this);
	installClass(IO_node);

	// The Int class has no methods and only a single attribute, the
	// "val" for the integer.

	class_c Int_class = 
	    new class_c(0,
		       TreeConstants.Int,
		       TreeConstants.Object_,
		       new Features(0)
			   .appendElement(new attr(0,
					    TreeConstants.val,
					    TreeConstants.prim_slot,
					    new no_expr(0))),
		       filename);

	installClass(new CgenNode(Int_class, CgenNode.Basic, this));

	// Bool also has only the "val" slot.
	class_c Bool_class = 
	    new class_c(0,
		       TreeConstants.Bool,
		       TreeConstants.Object_,
		       new Features(0)
			   .appendElement(new attr(0,
					    TreeConstants.val,
					    TreeConstants.prim_slot,
					    new no_expr(0))),
		       filename);

	installClass(new CgenNode(Bool_class, CgenNode.Basic, this));

	// The class Str has a number of slots and operations:
	//       val                              the length of the string
	//       str_field                        the string itself
	//       length() : Int                   returns length of the string
	//       concat(arg: Str) : Str           performs string concatenation
	//       substr(arg: Int, arg2: Int): Str substring selection

	class_c Str_class =
	    new class_c(0,
		       TreeConstants.Str,
		       TreeConstants.Object_,
		       new Features(0)
			   .appendElement(new attr(0,
					    TreeConstants.val,
					    TreeConstants.Int,
					    new no_expr(0)))
			   .appendElement(new attr(0,
					    TreeConstants.str_field,
					    TreeConstants.prim_slot,
					    new no_expr(0)))
			   .appendElement(new method(0,
					      TreeConstants.length,
					      new Formals(0),
					      TreeConstants.Int,
					      new no_expr(0)))
			   .appendElement(new method(0,
					      TreeConstants.concat,
					      new Formals(0)
						  .appendElement(new formalc(0,
								     TreeConstants.arg, 
								     TreeConstants.Str)),
					      TreeConstants.Str,
					      new no_expr(0)))
			   .appendElement(new method(0,
					      TreeConstants.substr,
					      new Formals(0)
						  .appendElement(new formalc(0,
								     TreeConstants.arg,
								     TreeConstants.Int))
						  .appendElement(new formalc(0,
								     TreeConstants.arg2,
								     TreeConstants.Int)),
					      TreeConstants.Str,
					      new no_expr(0))),
		       filename);

	installClass(new CgenNode(Str_class, CgenNode.Basic, this));
    }
	
    // The following creates an inheritance graph from
    // a list of classes.  The graph is implemented as
    // a tree of `CgenNode', and class names are placed
    // in the base class symbol table.
    
    private void installClass(CgenNode nd) {
	AbstractSymbol name = nd.getName();
	if (probe(name) != null) return;
	nds.addElement(nd);
	addId(name, nd);
    }

    private void installClasses(Classes cs) {
        for (Enumeration e = cs.getElements(); e.hasMoreElements(); ) {
	    installClass(new CgenNode((Class_)e.nextElement(), 
				       CgenNode.NotBasic, this));
        }
    }

    private void buildInheritanceTree() {
	for (Enumeration e = nds.elements(); e.hasMoreElements(); ) {
	    setRelations((CgenNode)e.nextElement());
	}
    }

    private void setRelations(CgenNode nd) {
	CgenNode parent = (CgenNode)probe(nd.getParent());
	nd.setParentNd(parent);
	parent.addChild(nd);
    }

    private int setNumChildren(CgenNode nd, int total) {
    	if(!nd.getChildren().hasMoreElements()) {
    		nd.num_children = 0;
    		return 0;
    	}
    	for(Enumeration m = nd.getChildren(); m.hasMoreElements();) {
    		CgenNode nxt_cgn = (CgenNode) m.nextElement();
    		total = total + setNumChildren(nxt_cgn, 0) + 1;
    	}
    	nd.num_children = total;
    	return total;
    }

    /** Constructs a new class table and invokes the code generator */
    public CgenClassTable(Classes cls, PrintStream str) {
	nds = new Vector();
	this.str = str;
	stringclasstag = 5;
	intclasstag =    3;
	boolclasstag =   4;
	enterScope();
	if (Flags.cgen_debug) System.out.println("Building CgenClassTable");
	installBasicClasses();
	installClasses(cls);
	buildInheritanceTree();
	CgenNode rt_1 = root();
	setNumChildren(rt_1, 0);
	CgenClassTable.attr_defs = new SymbolTable();
	code();
	exitScope();
    }

    /** This method is the meat of the code generator.  It is to be
        filled in programming assignment 5 */
    public void code() {
    CgenClassTable.virtual_disptbl = new HashMap<String, Vector<String>>(0);
    CgenClassTable.method_decls = new HashMap<String, Vector<method>>(0);
    CgenClassTable.attr_decls = new HashMap<String, Vector<attr>>(0);
    CgenNode rt = root();
    frame_offset_store = new Stack();
	if (Flags.cgen_debug) System.out.println("coding global data");
	codeGlobalData();
	if (Flags.cgen_debug) System.out.println("choosing gc");
	codeSelectGc();
	if (Flags.cgen_debug) System.out.println("coding constants");
	codeConstants();
	str.print(CgenSupport.CLASSNAMETAB + CgenSupport.LABEL);
	CgenNode start = root();
	build_class_nameTab(start);
	str.print(CgenSupport.CLASSOBJTAB + CgenSupport.LABEL);
	objclasstab_creation(start);
	virtual_disptbl = build_all_dispatch_trees(start, CgenClassTable.virtual_disptbl);
	CgenNode tree_base = root();
	build_all_proto_objs(tree_base, 0);
	if (Flags.cgen_debug) System.out.println("coding global text");
	codeGlobalText();
	int index = 0;
	// SymbolTable var_defs = new SymbolTable();
	// var_defs.enterScope();
	index = initialize_all_classes(start, index);
	//code_methods(start, 0, var_defs);
	}

	public int code_methods(CgenNode start, int index, SymbolTable var_defs) {
	if(start.basic_status == CgenNode.NotBasic) {
	Vector<method> mains = CgenClassTable.method_decls.get(start.getName().toString()); //Need to find for all classes
	int param = -12;
	int formal_length = 0;
	for(Enumeration e = mains.elements(); e.hasMoreElements();) {
		var_defs.enterScope();
		param = -12;
		method next_method = (method) e.nextElement();
		str.print(start.getName().toString()+"."+next_method.name.toString()+CgenSupport.LABEL);
		CgenSupport.emitMethodInit(param, str);
		formal_length = next_method.formals.getLength();
		int count = formal_length-1;
		int count_2 = 0;
		 while(count >= 0) {
		 	formalc next_form = (formalc) next_method.formals.getNth(count);
		 	AbstractSymbol aleph = AbstractTable.stringtable.addString(next_form.name.toString());
		 	var_defs.addId(aleph, count_2);
		 	count--;
		 	count_2++;
		 }
		CgenClassTable.frame_offset = 12 +  4*formal_length;
		CgenClassTable.frame_to_top_offset = -16;
		Expression shin = (Expression) next_method.expr;
		shin.getClass().cast(shin);
		CgenNode this_root = root();
		String this_class_string = start.getName().toString();
		shin.this_class = this_class_string;
		shin.set_root(this_root);
		index = shin.code(str, index, var_defs);
		var_defs.exitScope();
		CgenSupport.emitMethodEnd(CgenClassTable.frame_offset, str);
	}
}
	// for(Enumeration m = start.getChildren(); m.hasMoreElements(); ) {
	// 	CgenNode child_node = (CgenNode) m.nextElement();
	// 	code_methods(child_node, index, var_defs);
	// }
	return index;
    }


    public void build_class_nameTab(CgenNode base) {
    		AbstractSymbol aleph = AbstractTable.stringtable.lookup(base.getName().toString());
    		StringSymbol gimel = (StringSymbol) aleph;
    		str.print(CgenSupport.WORD); gimel.codeRef(str); str.println("");
    		if(!base.getChildren().hasMoreElements()) {
    		return;
    		}
    		for( Enumeration children = base.getChildren(); children.hasMoreElements(); ) {
    		CgenNode nxt = (CgenNode) children.nextElement();
    		build_class_nameTab(nxt);
    		}
    		return;
    }


    public void objclasstab_creation(CgenNode base) {
    		str.print(CgenSupport.WORD + base.getName().toString()+CgenSupport.PROTOBJ_SUFFIX); str.println("");
    		str.print(CgenSupport.WORD + base.getName().toString()+CgenSupport.CLASSINIT_SUFFIX); str.println("");
    		if(!base.getChildren().hasMoreElements()) {
    		return;
    		}
    		for(Enumeration children = base.getChildren(); children.hasMoreElements(); ) {
    		CgenNode nxt = (CgenNode) children.nextElement();
    		objclasstab_creation(nxt);
    		}
    		return;

    }

    public int build_all_proto_objs(CgenNode rbase, int classtag) {
    	build_proto(rbase, rbase.getName().toString(), classtag);
    	for(Enumeration f = rbase.getChildren(); f.hasMoreElements();) {
    		classtag++;
    		CgenNode nxt_node = (CgenNode) f.nextElement();
    		classtag = build_all_proto_objs(nxt_node, classtag);
    	}
    	return classtag;
    }

    public void build_proto(CgenNode rbase, String classtab,  int classtag) {
    	int size = 3;
    	rbase.class_tag = classtag;
    	str.println(CgenSupport.WORD + "-1");
    	str.print(rbase.getName().toString() + CgenSupport.PROTOBJ_SUFFIX + CgenSupport.LABEL); 
	    str.print(CgenSupport.WORD + String.valueOf(classtag)); str.println("");
    	CgenNode start_1 = rbase;
    	while(start_1 != null) {
	    	if(CgenClassTable.attr_decls.get(start_1.getName().toString()) != null) {
		    	Vector<attr> attr_class = CgenClassTable.attr_decls.get(start_1.getName().toString());
		    	size = size + attr_class.size();
		    }
	    	start_1 = start_1.getParentNd();
	    }
	    str.print(CgenSupport.WORD + String.valueOf(size)); str.println("");
	    str.print(CgenSupport.WORD + classtab+CgenSupport.DISPTAB_SUFFIX); str.println("");
	    CgenNode start_2 = rbase;
	    while(start_2 != null) {
		    if(CgenClassTable.attr_decls.get(start_2.getName().toString()) != null) {
			    Vector<attr> attr_class = CgenClassTable.attr_decls.get(start_2.getName().toString());
			    	for(Enumeration f = attr_class.elements(); f.hasMoreElements(); ) {
			    		attr nt_attr = (attr) f.nextElement();
			    		str.print(CgenSupport.WORD + String.valueOf(0)); str.println("");
			    	}
		    }
	    start_2 = start_2.getParentNd();
	   }
    	return;


    }

   public int initialize_all_classes(CgenNode base, int index) {
    		AbstractTable.stringtable.addString(base.getName().toString()+CgenSupport.CLASSINIT_SUFFIX);
    		str.print(base.getName().toString()+CgenSupport.CLASSINIT_SUFFIX+CgenSupport.LABEL);
    		String parent = "";
    		parent = base.getParentNd().getName().toString()+CgenSupport.CLASSINIT_SUFFIX;
    		if(base.getName().toString().equals("Object"))
    			parent = "null";

    		CgenSupport.emitInitializerRef(parent, str);
    		int offset =  0; //CgenClassTable.attr_decls.get(base.getName().toString()).size()-1;
    		int offset_2 = 0;
    		CgenNode trav_up = base;
    		CgenClassTable.attr_defs.enterScope();
    		while(trav_up != null) {
    			if(CgenClassTable.attr_decls.get(trav_up.getName().toString()) != null) {
		    		Vector<attr> attrs = CgenClassTable.attr_decls.get(trav_up.getName().toString());
		    		for(Enumeration f = attrs.elements(); f.hasMoreElements(); ){
		    			attr next_attr = (attr) f.nextElement();
		    			AbstractSymbol aleph1 = AbstractTable.stringtable.addString(next_attr.name.toString());
		    			int shifter = 3+offset;
		    			CgenClassTable.attr_defs.addId(aleph1, shifter);
		    			offset++;
		    		}
		    		CgenClassTable.frame_offset = 12 + 4*attrs.size();
					CgenClassTable.frame_to_top_offset = -12;
	
		    		for(Enumeration g = attrs.elements(); g.hasMoreElements(); ){
		    		attr next_attr = (attr) g.nextElement();
		    		next_attr.init.this_class = trav_up.getName().toString();
		    		if(base.basic_status == CgenNode.NotBasic) {
		    		SymbolTable alepha = new SymbolTable();
		    		alepha.enterScope();
		    		index = next_attr.init.code(str, index, alepha);
		    		alepha.exitScope();
		    		//CgenSupport.emitStore(CgenSupport.ACC, offset_2, CgenSupport.FP, str);
		    		CgenSupport.emitStore(CgenSupport.ACC, 3+offset_2, CgenSupport.SELF, str);
		    	}
		    		offset_2++;
		    	}
		    }
    	  trav_up = trav_up.getParentNd();
    	}
    		CgenSupport.emitEndRef(parent, 12, str);
    		SymbolTable var_defs = new SymbolTable();
    		index = code_methods(base, 0, var_defs);
    		CgenClassTable.attr_defs.exitScope();
    		if(!base.getChildren().hasMoreElements()) {
    		return index;
    		}
    		for(Enumeration children = base.getChildren(); children.hasMoreElements(); ) {
    		CgenNode nxt = (CgenNode) children.nextElement();
    		index = initialize_all_classes(nxt, index);
    		}
    		return index;
    }



    public HashMap<String, Vector<String>> build_all_dispatch_trees(CgenNode base, HashMap<String, Vector<String>> virtual_disptbl) {
    	Vector<String> disp_tbl = dispatch_table_builder(base);
    	Vector<String> reversal = new Vector<String>(0);
    	while(disp_tbl.size() > 0) {
    		int inherit_flag = 0;
    		String method_to_put = disp_tbl.lastElement();
    		disp_tbl.removeElementAt(disp_tbl.size()-1);
    		Vector<Integer> inherited_methods = new Vector<Integer>(0);
    		String pattern = "(([aA-zZ]([aA-zZ]|[\\d])*))\\.(([aA-zZ]([aA-zZ]|[\\d])*))"; //Grab the pattern [Class].[method]
			Pattern r = Pattern.compile(pattern);   
			Matcher m = r.matcher(method_to_put);
			if(m.find()) {  //Should always find a match   
				int cnt = 0;                  
				//Goes throug the disp_tbl and checks the indices of all methods that have the same name
				for(Enumeration q = reversal.elements(); q.hasMoreElements();) { 
					String nxt_str_method = (String) q.nextElement();
					if(nxt_str_method.endsWith(m.group(4))) {
						inherit_flag = 1;
						reversal.set(cnt, method_to_put);
						break;	
					}
					cnt++;
				}
				if(inherit_flag == 0) {
					reversal.addElement(method_to_put);
				}
		}
			else { System.exit(1); }
    	}
    	virtual_disptbl.put(base.getName().toString(), reversal);
    	print_dispatch_tbl(base, reversal);
    	if(!base.getChildren().hasMoreElements()) {
    		return virtual_disptbl;
    	}
    	for(Enumeration children = base.getChildren(); children.hasMoreElements(); ) {
    		CgenNode nxt = (CgenNode) children.nextElement();
    		virtual_disptbl = build_all_dispatch_trees(nxt, virtual_disptbl);
    	}
    	return virtual_disptbl;
    }


    public void print_dispatch_tbl(CgenNode base, Vector<String> reversal) {
    	str.print(base.getName().toString() + CgenSupport.DISPTAB_SUFFIX+CgenSupport.LABEL); 
    	for(Enumeration g = reversal.elements(); g.hasMoreElements();) {
    		String next_str = (String) g.nextElement();
    		str.print(next_str); str.println("");
    	}
    }
    /** Building the dispatch table **/
    public Vector<String> dispatch_table_builder(CgenNode class_1) {
    	CgenNode start = class_1;
    	Vector<String> disp_tbl = new Vector<String>(0);
    	while(start != null) { 
    	Vector<method> this_class = new Vector<method>(0);
    	Vector<attr> attr_class = new Vector<attr>(0);
    	for( Enumeration e = start.getFeatures().getElements(); e.hasMoreElements(); ) {
    			Feature next_feature = (Feature) e.nextElement();
    			if(next_feature.getClass().equals(method.class)) {
    				method add_method = (method) next_feature;
    				this_class.addElement(add_method);
    				disp_tbl.addElement(CgenSupport.WORD + start.getName().toString()+"."+add_method.name.toString()); 
    			}
    			if(next_feature.getClass().equals(attr.class)) {
    				attr add_attr = (attr) next_feature;
    				attr_class.addElement(add_attr);
    			}
    	}
    	if(class_1.getName().toString() == start.getName().toString())  {
    		CgenClassTable.method_decls.put(start.getName().toString(), this_class);
    		CgenClassTable.attr_decls.put(start.getName().toString(), attr_class);
    	}

    	start = start.getParentNd();
    }  	
    	return disp_tbl;
    }


    /** Gets the root of the inheritance tree */
    public CgenNode root() {
	return (CgenNode)probe(TreeConstants.Object_);
    }
}

			  
    
