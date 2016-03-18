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
/** This class may be used to contain the semantic information such as
 * the inheritance graph.  You may use it or not as you like: it is only
 * here to provide a container for the supplied methods.  */
class ClassTable {
    private int semantErrors;
    private PrintStream errorStream;
    protected Vector<class_c> ll_cls = new Vector<class_c>(2);
    protected Vector<class_c> bad_nodes;
    protected Vector<String> bad_node_names;

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
    }
	
    public ClassTable(Classes cls) {
	semantErrors = 0;
	errorStream = System.err;
	installBasicClasses();
	HierarchyNode root_1;
	HierarchyNode bad_root;
	Vector<String> named = new Vector<String>(0);
	/* Storing the names of all the visited classes */
	Vector<String> visited = new Vector<String>(0);
	bad_nodes = new Vector<class_c>(0);
	bad_node_names = new Vector<String>(0);
	/* Collect all of the classes into a vector */
	for(int i=0; i< cls.getLength(); i++) {
		class_c curr_elem = (class_c) cls.getNth(i);
		
		if(!named.contains(curr_elem.getName().toString())) {
		named.addElement(curr_elem.getName().toString());
		ll_cls.addElement(curr_elem);
		}
		else {
			semantErrors++;
			semantError(curr_elem);
		// curr_elem.dump_with_types(errorStream, 0);
		}
	}
	/*Find root to start building the inheritance graph */
	String root = "_no_class";
	for(Enumeration<class_c> enums1 = ll_cls.elements(); enums1.hasMoreElements();) {
		 class_c e1 = enums1.nextElement();
		 if(e1.getParent().equalString(root, root.length())) {
		 	  root_1 = new HierarchyNode(e1);
		 	  root_1 = populate_Tree(root_1, 0);
		 	  traverse_Graph(root_1, visited);
		 	  	
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
    			 bad_node_names.addElement(checking_node.getName().toString());
    			 //semantError(checking_node);
    			 //checking_node.dump_with_types(errorStream, 0);//Replace this statement with an error reporting message 
    		}
    	}
   
    for(Enumeration<class_c> bad_iters = bad_nodes.elements(); bad_iters.hasMoreElements();) {
    			class_c undefined_inherit_check = bad_iters.nextElement();
    			//System.out.println(undefined_inherit_check.getName().toString());
    		if(!bad_node_names.contains(undefined_inherit_check.getParent().toString())) {
    			
    			semantErrors++;
    			bad_nodes.remove(undefined_inherit_check);
    		}
    	}
    
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
			semantError(e1);
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
    		semantError(root.thisClassNode());
    	}
    	if(root.isLeaf()) {
    		return true;
    	}
    	Enumeration<HierarchyNode> children = root.getChildren();
    	while(children.hasMoreElements()) {
    		HierarchyNode next_one = children.nextElement();
    		boolean truth = traverse_Graph(next_one, visited);
    		if(truth == false) {
    			semantErrors++;
    			semantError(next_one.thisClassNode());
    			return false;
    		}

    	}
    	return true;

    }

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
	public HierarchyNode(class_c Node1) {
		this.NodeName = Node1.getName().toString();
		this.parent = null;
		this.classnd = Node1;
		this.children = new Vector<HierarchyNode>(0);
	}
	public HierarchyNode(class_c Node1, HierarchyNode Node2) {
		this.NodeName = Node1.getName().toString();
		this.parent = Node2;
		this.classnd = Node1;
		this.children  = new Vector<HierarchyNode>(0);
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
    
