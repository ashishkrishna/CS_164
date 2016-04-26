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
import java.util.*;
import java.lang.Math;
/** This class may be used to contain the semantic information such as
 * the inheritance graph.  You may use it or not as you like: it is only
 * here to provide a container for the supplied methods.  */
class ClassTable {
    private int semantErrors;
    private PrintStream errorStream;
    protected Vector<class_c> ll_cls = new Vector<class_c>(2); /* Global class container */
    protected Vector<class_c> bad_nodes; /* Container for bad nodes */
    protected Vector<String> bad_node_names; /* Container for bad node names */
    protected  Vector<class_c> undefs; /*Container for undefined inheritance nodes */
	protected Vector<class_c> redefs; /*Container for redefined nodes */
	protected Vector<class_c> cycledefs; /*Container for cycle nodes */
	protected Vector<class_c> indefs; /*Container for invalid inherit nodes */ 
	protected  Vector<String> undefs_lbls; /*Container for undefined inheritance node names */
	protected Vector<String> redefs_lbls; /*Container for redefined node names */
	protected Vector<String> cycledefs_lbls; /*Container for cycle node names */
	protected Vector<String> indefs_lbls; /*Container for invalid inheritance node names */
	protected  Vector<Integer> undefs_linenum; /* Container for undefined inheritance line nums */ 
	protected Vector<Integer> redefs_linenum; /*Container for redefined inherit line nums */ 
	protected Vector<Integer> cycledefs_linenum; /*Container for cycle node line nums*/ 
	protected Vector<Integer> indefs_linenum; /*Container for invalid node line nums */ 
	protected Vector<class_c> good_nodes; /*Container for good nodes in graph traversal */
	protected Vector<HierarchyNode> list_of_class_trees; /* Container for class trees to be used in future */
	private Vector<String> named;

    /** Creates data structures representing basic Cool classes (Object,
     * IO, Int, Bool, String).  Please note: as is this method does not
     * do anything useful; you will need to edit it to make if do what
     * you want.
     * */
    private void installBasicClasses() {
	AbstractSymbol filename 
	    = AbstractTable.stringtable.addString("<basic class>");
	
	// The following demonstrates how to create dummy parse trees to
	// refer to basic Cool classes.  There's no need for method
	// bodies -- these are already built into the runtime system.

	// IMPORTANT: The results of the following expressions are
	// stored in local variables.  You will want to do something
	// with those variables at the end of this method to make this
	// code meaningful.

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

	/* Do somethind with Object_class, IO_class, Int_class,
           Bool_class, and Str_class here */

	// NOT TO BE INCLUDED IN SKELETON
	
	// Object_class.dump_with_types(System.err, 0);
	// IO_class.dump_with_types(System.err, 0);
	// Int_class.dump_with_types(System.err, 0);
	// Bool_class.dump_with_types(System.err, 0);
	// Str_class.dump_with_types(System.err, 0);
	// Add the basic types of classes by default
	ll_cls.addElement(Object_class);
	ll_cls.addElement(IO_class);
	ll_cls.addElement(Int_class);
	ll_cls.addElement(Bool_class);
	ll_cls.addElement(Str_class);
	// named.addElement(Object_class.getName().toString());
	// named.addElement(IO_class.getName().toString());
	// named.addElement(Int_class.getName().toString());
	// named.addElement(Bool_class.getName().toString());
	// named.addElement(Str_class.getName().toString());
    }
	
    public ClassTable(Classes cls) {
	semantErrors = 0;
	errorStream = System.err;
	installBasicClasses();
	HierarchyNode root_1;
	HierarchyNode bad_root;
	named = new Vector<String>(0);
	/* Storing the names of all the visited classes */
	Vector<String> visited = new Vector<String>(0);
	undefs = new Vector<class_c>(0);
	redefs = new Vector<class_c>(0);
	indefs = new Vector<class_c>(0);
	cycledefs = new Vector<class_c>(0);
	undefs_lbls = new Vector<String>(0);
	redefs_lbls = new Vector<String>(0);
	cycledefs_lbls = new Vector<String>(0);
	indefs_linenum = new Vector<Integer>(0);
	undefs_linenum = new Vector<Integer>(0);
	redefs_linenum = new Vector<Integer>(0);
	cycledefs_linenum = new Vector<Integer>(0);
	indefs_lbls = new Vector<String>(0);
	bad_nodes = new Vector<class_c>(0);
	bad_node_names = new Vector<String>(0);
	good_nodes = new Vector<class_c>(0);
	list_of_class_trees = new Vector<HierarchyNode>(0);
	named.addElement("Object");
	named.addElement("IO");
	named.addElement("Int");
	named.addElement("Bool");
	named.addElement("String");
	/* Collect all of the classes into a vector */
	for(int i=0; i< cls.getLength(); i++) {
		class_c curr_elem = (class_c) cls.getNth(i);
		if(curr_elem.getName().toString().equals("SELF_TYPE")) {
			semantErrors++;
			errorStream.print("0: ");
			errorStream.append("SELF_TYPE cannot be a class name!\n");
		}
		if(!named.contains(curr_elem.getName().toString())) {
		named.addElement(curr_elem.getName().toString());
		ll_cls.addElement(curr_elem);
		}
		else {
			
			semantErrors++;
			AbstractSymbol parent = curr_elem.getParent();
    		if((parent.toString().equals("String")) || (parent.toString().equals("Bool"))|| (parent.toString().equals("Int"))) {
    		semantErrors++;
    		indefs.add(curr_elem);
    		indefs_lbls.add(curr_elem.getName().toString());
    		indefs_linenum.add(curr_elem.getLineNumber());
    	}
			redefs.add(curr_elem);
			redefs_lbls.add(curr_elem.getName().toString());
			redefs_linenum.add(curr_elem.getLineNumber());
		}
	}
	if(!named.contains("Main")) {
			semantErrors++;
			errorStream.print("<basic class>:0: ");
			errorStream.append("Class Main is not defined.\n");
	}

	/*Find the root node (Object class) and build the tree of all valid classes */
	String root = "_no_class";
	for(Enumeration<class_c> enums1 = ll_cls.elements(); enums1.hasMoreElements();) {
		 class_c e1 = enums1.nextElement();
		 //System.out.println(e1.getParent());
		 if(e1.getParent().equalString(root, root.length())) {
		 	  root_1 = new HierarchyNode(e1);
		 	  root_1 = populate_Tree(root_1, 0);
		 	  traverse_Graph(root_1, visited);
		 	  list_of_class_trees.addElement(root_1);
		 	  	
		 }
		 
	}

	/*If the graph is well-structured, then every class would have been visited in the traversal; In Cool, there is 
	exactly one root node: the Object class, since all classes that do not have a explicit inheritance are still descendants
	of the Object class. So all of the classes must be visited during the traversal starting @ the Object class root node */ 

	for(Enumeration<class_c> check_nodes = ll_cls.elements(); check_nodes.hasMoreElements(); ) {
			class_c checking_node = check_nodes.nextElement();
    		if(!visited.contains(checking_node.getName().toString()))    {  
    			 semantErrors++;
    			 bad_nodes.addElement(checking_node);
    			 //System.out.println(checking_node.getName().toString());
    			 bad_node_names.addElement(checking_node.getName().toString());
    		}
    		else {
    			good_nodes.addElement(checking_node);
    		}

    	}
   /* Now we proceed with building graphs for bad nodes. First, we remove all bad nodes who do not have defined parents. Then 
   we have nodes remaining in the bad nodes that are part of cycles (these cycles can be distinct, and errors need to be returned
   	for each distinct cycle) */
	int length_before = 0;
	int length_after = 1;
	while(length_before != length_after) {
		length_before = bad_nodes.size();
    for(Enumeration<class_c> bad_iters = bad_nodes.elements(); bad_iters.hasMoreElements();) {
    			class_c undefined_inherit_check = bad_iters.nextElement();
    		if(!bad_node_names.contains(undefined_inherit_check.getParent().toString())) {
    			semantErrors++;
    			undefs.addElement(undefined_inherit_check);
    			undefs_lbls.addElement(undefined_inherit_check.getName().toString());
    			undefs_linenum.add(undefined_inherit_check.getLineNumber());
    			int index = bad_node_names.indexOf(undefined_inherit_check.getName().toString());
    			bad_nodes.remove(index);
    			bad_node_names.remove(index);


    		}
    	}
    	length_after = bad_nodes.size();
    }
    /* Detect cycles and remove the bad nodes until there are no more nodes left in bad nodes */
    while (bad_nodes.size() != 0) {
    		bad_root = new HierarchyNode(bad_nodes.elementAt(0));
    		Vector<String> to_be_added = new Vector<String>(0);
    		Vector<String> bad_visited = new Vector<String>(0);
    		populatebad_Tree(bad_root, 0, to_be_added);
    		//traverse_Graph(bad_root, bad_visited);
    		for(Enumeration<String> checked_bad_ones = to_be_added.elements(); checked_bad_ones.hasMoreElements();) {
    			String checked_one = checked_bad_ones.nextElement();
    			if(bad_node_names.contains(checked_one))  {
    				  int remove_index = bad_node_names.indexOf(checked_one);
    				  bad_node_names.remove(remove_index);
    				  bad_nodes.remove(remove_index);
    			}
    		}
    	}

 /* Print out all the errors in order of line numbers */ 
if (cycledefs.size()  > 0 || undefs.size() > 0 || redefs.size() > 0 || indefs.size() > 0) {
    int lowest_line_num;
    Enumeration<class_c> c1 = cycledefs.elements();
    Enumeration<class_c> u1 = undefs.elements();
    Enumeration<class_c> r1 = redefs.elements();
    Enumeration<class_c> i1 = indefs.elements();
    HashMap<Integer, class_c> total_errors = new HashMap<Integer, class_c>();
    while(c1.hasMoreElements()) {
    	class_c cnext = c1.nextElement();
    	total_errors.put(cnext.getLineNumber(), cnext);
    }
    while(u1.hasMoreElements()) {
    	class_c unext = u1.nextElement();
    	total_errors.put(unext.getLineNumber(), unext);
    }
    while(r1.hasMoreElements()) {
    	class_c rnext = r1.nextElement();
    	total_errors.put(rnext.getLineNumber(), rnext);
    }
    while(i1.hasMoreElements()) {
    	class_c inext = i1.nextElement();
    	total_errors.put(inext.getLineNumber(), inext);
    }
    int max = 0;
   	 max = Collections.max(total_errors.keySet(), null);
   	for(int i = 0; i < max+1; i++){
   		class_c semanter = total_errors.get(i);
   		if(semanter != null) {
   			if (cycledefs_lbls.contains(semanter.getName().toString()) &&  cycledefs_linenum.contains(semanter.getLineNumber())) {
   				semantError(semanter);
				String errnmcl = semanter.getName().toString();
				errorStream.append("Class "+errnmcl+ " or an ancestor of " + errnmcl + ", is involved in an inheritance cycle.\n");

   			}
   			if (undefs_lbls.contains(semanter.getName().toString()) && undefs_linenum.contains(semanter.getLineNumber())) {
   				 semantError(semanter);
   				 String errcl = semanter.getName().toString();
    			 String errcl_2 = semanter.getParent().toString();
    			 errorStream.append("Class " + errcl + " inherits from undefined class " + errcl_2 + ".\n");
   				
   			}
   			if (redefs_lbls.contains(semanter.getName().toString()) && redefs_linenum.contains(semanter.getLineNumber())) {
   				 semantError(semanter);
				 String errnmcl = semanter.getName().toString();
				 errorStream.append("Class "+errnmcl+ " was previously defined.\n");

   				
   			}
   			if (indefs_lbls.contains(semanter.getName().toString()) && indefs_linenum.contains(semanter.getLineNumber())) {
   				  semantError(semanter);
   				  String parerr = semanter.getParent().toString();
    			  String roerr = semanter.getName().toString();
    			  errorStream.append("Class " + roerr + " cannot inherit class " + parerr + ".\n");

   				
   			}
   		}
   	}
}
    	
    }

    /* Building the inheritance graph, going through each class and checking the parent-child relationship */ 
    public HierarchyNode populate_Tree(HierarchyNode root, int n) {
    	for(Enumeration<class_c> enums1 = ll_cls.elements(); enums1.hasMoreElements();) {
		 class_c e1 = enums1.nextElement();
		 if(e1.getParent().equalString(root.thisNode(), root.thisNode().length())) {
		 		HierarchyNode child = new HierarchyNode(e1, root);
		 		root.addChild(child);
		 		populate_Tree(child, n+1);
		}
    	
    }
    return root;
}
	/*Building the inheritance graph for bad nodes */
	public HierarchyNode populatebad_Tree(HierarchyNode root, int n, Vector<String> added) {
    	for(Enumeration<class_c> enums1 = bad_nodes.elements(); enums1.hasMoreElements();) {
		 class_c e1 = enums1.nextElement();
		 
		 
		 if(e1.getParent().equalString(root.thisNode(), root.thisNode().length()) && !added.contains(e1.getName().toString())) {
		 		added.addElement(e1.getName().toString());
		 		HierarchyNode child = new HierarchyNode(e1, root);
		 		root.addChild(child);
		 		populatebad_Tree(child, n+1, added);
		}
		else {
			if (e1.getParent().equalString(root.thisNode(), root.thisNode().length()) && added.contains(e1.getName().toString())) {
			semantErrors++;
			cycledefs_lbls.addElement(root.getParent().thisNode());
			cycledefs.addElement(root.getParent().thisClassNode());
			cycledefs_linenum.addElement(root.getParent().thisClassNode().getLineNumber());
			return root;
			
		}
	}
		
    	
    }
    return root;
}


	/*Boolean check doesn't really mean anything here-- Cool does not allow for multiple inheritance, this is more 
	just a step taken to ensure the completion of the traversal, and uniformity in data types */ 

    public boolean traverse_Graph(HierarchyNode root, Vector<String> visited) {
    	for(Enumeration<String> marked_down = visited.elements(); marked_down.hasMoreElements(); ) {
    		if(root.thisNode().equals(marked_down.nextElement())) {
    			return false;
    		}

    	}
    	visited.addElement(root.thisNode());
    	HierarchyNode parent = root.getParent();
    	if(parent != null && (parent.thisNode().equals("String") || parent.thisNode().equals("Bool") || parent.thisNode().equals("Int"))) {
    		semantErrors++;
    		indefs.add(root.thisClassNode());
    		indefs_lbls.add(root.thisNode());
    		indefs_linenum.add(root.thisClassNode().getLineNumber());

    	}
    	if(root.isLeaf()) {
    		return true;
    	}
    	Enumeration<HierarchyNode> children = root.getChildren();
    	while(children.hasMoreElements()) {
    		HierarchyNode next_one = children.nextElement();
    		boolean truth = traverse_Graph(next_one, visited);
    		if(truth == false) {
    			// semantErrors++;
    			// semantError(next_one.thisClassNode());
    			return false;
    		}

    	}
    	return true;

    }

    public Vector<HierarchyNode> find_join_outer(HierarchyNode root, Vector<String>class_list, Vector<HierarchyNode> node_list) {
    	if(!find_join_inner(root, class_list)) {
    		return node_list;
    	}
    	node_list.addElement(root);
    	if(root.isLeaf()) {
    		return node_list;
    	}
    	Enumeration<HierarchyNode> children  = root.getChildren();
    	while(children.hasMoreElements()) {
    		HierarchyNode next_one = children.nextElement();
    		node_list = find_join_outer(next_one, class_list, node_list);
    	}

    	return node_list;
    }

    public boolean find_join_inner(HierarchyNode root, Vector<String> class_list) {
    	Vector<String> visited = new Vector<String>(0);
    	traverse_Graph(root, visited);
    	for(Enumeration<String> nodes = class_list.elements(); nodes.hasMoreElements();){
    		if(!(visited.contains(nodes.nextElement()))) {
    			return false;
    		}
    	}
    	return true;
    }

    public HierarchyNode goodClasses() {
    	return list_of_class_trees.elementAt(0);
    }

    public Vector<String> classNames() {
    	return named;
    }

    public HierarchyNode getClassbyName(String className, HierarchyNode root) {
    	if(root.thisNode().equals(className)) {
    		return root;
    	}
    	else {
    	if(root.isLeaf()) {
    		return null;
    	}
    	Enumeration<HierarchyNode> children = root.getChildren();
    		while(children.hasMoreElements()) {
    		HierarchyNode next_one = children.nextElement();
    		HierarchyNode to_return = getClassbyName(className, next_one);
    		if((to_return!= null)) {
    			return to_return;
    		}

    	}
    	return null;
    }

    }

    public boolean isChildClass(String className, HierarchyNode start) {
    	if(start == null) {
    		return false;
    	}
    	if(start.thisNode().equals(className)) {
    		return true;
    	}
    	else {
    	if(start.isLeaf()) {
    		return false;
    	}
    	Enumeration<HierarchyNode> children = start.getChildren();
    		while(children.hasMoreElements()) {
    		HierarchyNode next_one = children.nextElement();
    		boolean to_return = isChildClass(className, next_one);
    		if((to_return!= false)) {
    			return true;
    		}

    	}
    	return false;
    }

    }
    /** Return the good_nodes for further type checking */

   
    /** Prints line number and file name of the given class.
     *
     * Also increments semantic error count.
     *
     * @param c the class
     * @return a print stream to which the rest of the error message is
     * to be printed.
     *
     * */
    public PrintStream semantError(class_c c) {
	return semantError(c.getFilename(), c);
    }

    /** Prints the file name and the line number of the given tree node.
     *
     * Also increments semantic error count.
     *
     * @param filename the file name
     * @param t the tree node
     * @return a print stream to which the rest of the error message is
     * to be printed.
     *
     * */
    public PrintStream semantError(AbstractSymbol filename, TreeNode t) {
	errorStream.print(filename + ":" + t.getLineNumber() + ": ");
	return semantError();
    }

    /** Increments semantic error count and returns the print stream for
     * error messages.
     *
     * @return a print stream to which the error message is
     * to be printed.
     *
     * */
    public PrintStream semantError() {
	semantErrors++;
	return errorStream;
    }

    /** Returns true if there are any static semantic errors. */
    public boolean errors() {
	return semantErrors != 0;
    }

    // NOT TO BE INCLUDED IN SKELETON
    public static void main(String[] args) {
	new ClassTable(null).installBasicClasses();
    }
}

/* Class used to build the  Class inheritance graph. Really just a tree. */
 class HierarchyNode {
	protected HierarchyNode parent = null;
	protected Vector<HierarchyNode> children;
	protected String NodeName;
	protected class_c classnd;
	protected int hits;
	public HierarchyNode(class_c Node1) {
		this.NodeName = Node1.getName().toString();
		this.parent = null;
		this.classnd = Node1;
		this.children = new Vector<HierarchyNode>(0);
		this.hits = 0;
	}
	public HierarchyNode(class_c Node1, HierarchyNode Node2) {
		this.NodeName = Node1.getName().toString();
		this.parent = Node2;
		this.classnd = Node1;
		this.children  = new Vector<HierarchyNode>(0);
		this.hits = 0;
	}
	public String thisNode() {
		return this.NodeName;
	}

	public class_c thisClassNode() {
		return this.classnd;
	}

	public boolean isLeaf() {
		if (children.size() == 0) {
			return true;
		}
		return false;
	} 

	public void incHits() {
		hits++;
	}
	public int returnHits() {
		return hits;
	}

	public void clearHits() {
		this.hits = 0;
	}

	public void addChild(HierarchyNode child) {
		this.children.addElement(child);
	}
	public Enumeration<HierarchyNode> getChildren() {
		return this.children.elements();
	}

	public HierarchyNode getParent() {
		return this.parent;
	}
}			  
    
