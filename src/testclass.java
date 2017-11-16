public class testclass {
	/**
	 * tip you may wish to hard code tree nodes until you get the load from file method working as needed
	 */
	public static void main (String[] args) {
		Queue<Integer> linkedQ = new Queue<Integer>();
		Stack<String> linkedSt = new Stack<String>();
		StackADT<String> newGuy = new Stack<String>();
		GenealogyTree bigTree = new GenealogyTree();
					//** Big Boi Test 1 **//
		
		//**All of the new tree nodes we will be adding to our G-Tree.**
		TreeNode<String> t1 = new TreeNode<String>("A");
		TreeNode<String> t2 = new TreeNode<String>("B");
		TreeNode<String> t3 = new TreeNode<String>("C");
		TreeNode<String> t4 = new TreeNode<String>("D");
		TreeNode<String> t5 = new TreeNode<String>("E");
		TreeNode<String> t6 = new TreeNode<String>("F");
		TreeNode<String> t7 = new TreeNode<String>("G");
		
		//**Getting the root of the G-Tree.**
		TreeNode<String> rootBeer = bigTree.getRoot(); 
		
		//** rootBeer now points to "A". rootBeer is the root of the G-Tree.
		rootBeer = t1;
		
//		System.out.println(rootBeer.getData()); //Prints a instead of null-check. -Not checked.
		
		//**Adding children to the G-Tree's root node.**
		rootBeer.addChild(t2);
		rootBeer.addChild(t3);
		rootBeer.addChild(t4);
		rootBeer.addChild(t5);
		rootBeer.addChild(t6);
		rootBeer.addChild(t7);
		
		StackADT<String> stack = new Stack<String>(); 
		
		//Seems fine here.
		StackADT<String> bigStack = bigTree.getAncestorStack(stack, rootBeer, t3.getData());
		
//		System.out.println(bigStack.peek()); //good... so far...
		
		for (int i = 0; i < 1; i++) {
			System.out.println("-------");
			System.out.println(bigStack.pop());
		}
		
//		System.out.println(bigStack.isEmpty()); //Now that's a problem with your getAncestorClass() code.
		//**Calling on the G-Tree's ancestorStack() method**
//		stackOneill.peek();
//		StackADT<String> shack = bigTree.getAncestorStack("B"); //Getting the stack returned from the G-Tree.
		
//		System.out.println(shack.peek());
		//**QUEUE for sure worksss.**
//		Queue<TreeNode<String>> bigQ = new Queue<TreeNode<String>>();
		
//		bigQ.enqueue(t1); //FIFO
//		bigQ.enqueue(t2);
//		bigQ.enqueue(t3);
//		bigQ.enqueue(t4);
//		bigQ.enqueue(t5);
//		bigQ.enqueue(t6);
//		bigQ.enqueue(t7);
//		
//		for (int i  = 0; i < 10; i++) {
//			linkedQ.enqueue(i);
//		}

//		LinkedListIterator<TreeNode<String>> itr = bigQ.iterator();
//
//		while (itr.hasNext()) {
//			TreeNode<String> num = itr.next();
//			String xanax = num.getData();
//			System.out.print(xanax);		//check
//		}
//		bigTree.getAncestorStack(t2.getData());      
		
//		System.out.println(linkedQ.isEmpty()); //check
//		linkedSt.push("a");
//		linkedSt.push("b");
//		linkedSt.push("c");
//		
//		LinkedListIterator<String> itr2 = linkedSt.iterator();
//		while(itr2.hasNext()) { //Should display "cba"
//			String st = itr2.next();
//			System.out.print(st);	
//		}
//		System.out.println();
//
//		newGuy = linkedSt.reverse(); //check
//		for (int i = 0; i < 3; i++){	//Should display "abc"
//			System.out.print(newGuy.pop());	//popping from the top. top is "a", then "b", then "c" if list is reversed.
//		}
//		System.out.println();
//		System.out.println(newGuy.isEmpty()); //Check
////		LinkedListIterator<String> itr3 = newGuy.iterator();
////		while (newGuy.hasNext()) {
////			String st1 = itr3.next();
////			System.out.print(st1);
////		}
//		
//		System.out.println();
//		linkedQ.enqueue(233);
//		linkedQ.enqueue(1);
//		linkedQ.enqueue(22);
////		System.out.println(linkedQ.isEmpty()); //check
//		
////		while(itr.hasNext()) {
////			Integer num = itr.next();
////			System.out.println(num);		//check
////		}
////		linkedQ.dequeue();			//check
//		System.out.println(linkedQ.element());	//check
//		
//		linkedQ.dequeue();			//check
//		System.out.println();
//		LinkedListIterator<Integer> itr = linkedQ.iterator();
//		while(itr.hasNext()) {
//			Integer num = itr.next();
//			System.out.println(num);		//check
//		}
//		
	}
}
