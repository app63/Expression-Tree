import java.util.Stack;

public class Main {

	static int decider;
	static char checker;
	static Node root = null;

	public static void main(String[] args) {

		// Create the expression string
		String expressionString = "3+2*3";
		
		// Convert it to a character array and call it 'infix'
		char[] infix = expressionString.toCharArray();
		
		// pass the infix array in to the postfixer which is a
		// function to create the postfix form of the expression
		char[] postfix = postfixer(infix);
		//System.out.println("The postfix form is:");
		System.out.print("The postfix form is: ");
		
		// Print the postfix array on the screen
		for (int i = 0; i < postfix.length && postfix[i] != '_'; i++) {
			System.out.print(postfix[i] + " ");
		}
		System.out.println();

		// Create tree out of the postfix form
		Stack<Node> stack = new Stack<Node>();

		for (int i = 0; i < postfix.length; i++) {
			Node node = new Node(postfix[i]);
			switch (postfix[i]) {
			case '0':case '1':case '2':case '3':case '4':
			case '5':case '6':case '7':case '8':case '9':
				stack.push(node);
				//System.out.println("Pushed");
				break;
			case '+':case '-':case '/':case '*':
			    //System.out.println("Pushed");
				node.right = stack.pop();
				node.left = stack.pop();
				stack.push(node);
				break;
			default:
			}
		}

		root = stack.pop();
		//System.out.println(root.left.data);

		// Solve the Values!
		Stack<NodeInt> intStack = new Stack<NodeInt>();

		for (int i = 0; i < postfix.length; i++) {
			NodeInt nodeInt = new NodeInt(0);
			switch (postfix[i]) {
			case '0':case '1':case '2':case '3':case '4':
			case '5':case '6':case '7':case '8':case '9':
				nodeInt = new NodeInt(Character.getNumericValue(postfix[i]));
				intStack.push(nodeInt);
				//System.out.println(nodeInt.data);
				break;
			case '+':
			    //System.out.println("Pushed");
				
				double x = intStack.pop().data;
				double y = intStack.pop().data;
				
				nodeInt.data = y + x;
				
				intStack.push(nodeInt);
				
				break;

			case '-':
			    //	System.out.println("Pushed");
				
				x = intStack.pop().data;
				y = intStack.pop().data;
				
				nodeInt.data = y - x;
				
				intStack.push(nodeInt);
				
				break;

			case '/':
			    //System.out.println("Pushed");
				
				x = intStack.pop().data;
				y = intStack.pop().data;
				
				nodeInt.data = y / x;
				
				intStack.push(nodeInt);
				
				break;

			case '*':
			    //System.out.println("Pushed");
				
				x = intStack.pop().data;
				y = intStack.pop().data;
				
				nodeInt.data = y * x;
				
				intStack.push(nodeInt);
				
				break;
				
			default:
			}
			
		}


		//System.out.println(intStack.pop().data);

	}

	// function to convert postfix to infix
	public static char[] postfixer(char[] infix) {

		// create a new stack of character type
		Stack<Character> stack = new Stack<Character>();

		// create an array 'result' which is the same length as of infix and
		// type char
		char[] result = new char[infix.length];

		// initialize the value of the result array '_'
		for (int i = 0; i < result.length; i++) {
			result[i] = '_';
		}

		// set count as the counter for the operations on result
		// increment after every use
		int count = 0;

		for (int i = 0; i < infix.length; i++) {

			// System.out.println(result);

			switch (infix[i]) {
			case '0':case '1':case '2':case '3':case '4':
			case '5':case '6':case '7':case '8':case '9':
				result[count] = infix[i];
				count++;

				break;

			case '(':
				stack.add(infix[i]);

				break;

			case ')':
				while ((!stack.empty()) && (stack.peek() == '(')) {
					stack.pop();
				}

				if ((!stack.empty())) {
					result[count] = stack.pop();
					count++;
				}
				
				break;
					
			case '+':case '*':case '-':case '/':
				while ((!stack.empty()) && (stack.peek() == '(')) {
					stack.pop();
				}
				
				if ((!stack.empty())) {
					checker = stack.peek();

					// if 0, same. if 1, more. if 2, less.
					checkPrecedence(infix[i], checker);

					if (decider == 1) {
						stack.add(infix[i]);
					} else {
						result[count] = stack.pop();
						count++;
						stack.add(infix[i]);
					}
				
				} else {
					stack.add(infix[i]);
				}

				break;

			default:
				System.out.println("Invalid Data");
			}

		}

		while ((!stack.empty())) {
			result[count] = stack.pop();
			count++;
		}

		return result;
	}

		
	public static void checkPrecedence(char a, char b) {

		switch (a) {

		case '/':
			switch (b) {
			case '/':decider = 0;break;
			case '*':decider = 1;break;
			case '+':decider = 1;break;
			case '-':decider = 1;break;
			default:
			}
			break;
		case '*':
			switch (b) {
			case '/':decider = 2;break;
			case '*':decider = 0;break;
			case '+':decider = 1;break;
			case '-':decider = 1;break;
			default:
			}
			break;
		case '+':
			switch (b) {
			case '/':decider = 2;break;
			case '*':decider = 2;break;
			case '+':decider = 0;break;
			case '-':decider = 1;break;
			default:
			}
			break;
		case '-':
			switch (b) {
			case '/':decider = 2;break;
			case '*':decider = 2;break;
			case '+':decider = 2;break;
			case '-':decider = 0;break;
			default:
			}
			break;
		default:
		}

	}

}

class NodeInt {
	double data;
	NodeInt(double data) {
		this.data = data;
	}
}

class Node {
	char data;
	Node left;
	Node right;
	Node(char data) {
		this.data = data;
	}
}
