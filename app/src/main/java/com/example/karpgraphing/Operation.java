package com.example.karpgraphing;
/***************************************************************************
             _   __                   _____                 _     _             
            | | / /                  |  __ \               | |   (_)            
           | |/ /  __ _ _ __ _ __   | |  \/_ __ __ _ _ __ | |__  _ _ __   __ _ 
          |    \ / _` | '__| '_ \  | | __| '__/ _` | '_ \| '_ \| | '_ \ / _` |
         | |\  \ (_| | |  | |_) | | |_\ \ | | (_| | |_) | | | | | | | | (_| | 
        |_| \_/\__,_|_|  | .__/   \____/_|  \__,_| .__/|_| |_|_|_| |_|\__, |
                        | |                     | |                   __/ |
                        |_|                     |_|                  |___/ 

***************************************************************************/


/* 
    Operator syntax:
        add: a+b
        sub: a-b
        mul: a*b
        div: a/b
        pow: a^b
        log: a!b
            I.E log a(b) --> log4(2) = 4!2
*/
public class Operation extends Node{
    private char op;
    private int precedence;
	public Operation(char op)
	{
        this.op = op;
		if (op == '+' || op == '-')
			precedence = 1;
		else if (op == '*' || op == '/')
			precedence = 2;
		else if (op == '^' || op =='!')
			precedence = 3;
        else if(op == '(' || op == ')')
            precedence = 0;
		else
			precedence = -1;
	}

    public String toString(){
		return Character.toString(op);
	}

    public boolean compare(Operation other){
        return other.precedence == precedence ||precedence > other.precedence;
    }

    public char getOp(){
        return this.op;
    }

    public double calc(Number a, Number b) {
        switch(op){
            case '+': return a.getNumber()+b.getNumber();
            case '-': return a.getNumber()-b.getNumber();
            case '*': return a.getNumber()*b.getNumber();
            case '/': return a.getNumber()/b.getNumber();
            case '^': return Math.pow(a.getNumber(),b.getNumber());
            case '!': return logCalc(a, b);
        }
        return 0;     
    }

    //calculate logarithm value.
    public double logCalc(Number a, Number b){
        if(a.getNumber() == 10)
            return Math.log10(b.getNumber());
        else
            return Math.log10(b.getNumber())/Math.log10(a.getNumber()); 
    }       
}
