'''
1.  The purpose of the exercise is to practice building and traversing an expression
tree; so I want you to build a tree to hold the expression, and to evaluate the expression
by traversing the tree.  you have to build the expression tree, then traverse it to evaluate
the expression.   for each expression, you must produce
-   a graphical representation of the tree,
-   the numeric value of the expression.

2.  Please use the following syntax for the arithmetic expressions that I want you to process:

<expression>  ::=  <term>  |  <term> + <expression> | <term> - <expression>

<term>           ::=  <factor>  |  <factor> * <term>  |  <factor> / <term>

<factor>          ::=  <constant>  |  ( <expression> )

<constant>     ::=  0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9

You can make it slightly more complex by allowing greater integers than 9,
but you can let your constants range from 0 to 9 only.

3.  Make sure you understand how this syntax defines operator precedence and associativity
rule.
operator precedence:  3*5+4 is interpreted as (3*5)+4 rather than 3*(5+4).
associativity rule:  3-5-4 is interpreted as 3-(5-4) rather than (3-5)-4.

'''