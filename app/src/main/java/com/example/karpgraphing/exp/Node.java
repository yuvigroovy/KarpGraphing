package com.example.karpgraphing.exp;

public class Node {
    private BaseExpression rootVal;
    private BaseExpression parent;
    private BaseExpression rp; //right pointer
    private BaseExpression lp; //left pointer

    public Node(BaseExpression parent, BaseExpression rootVal, BaseExpression rp, BaseExpression lp){
        this.parent = parent;
        this.rootVal = rootVal;
        this.rp = rp;
        this.lp = lp;
    }

    public Node(BaseExpression parent, BaseExpression rootVal){
        this.parent = parent;
        this.rootVal = rootVal;
    }

    public BaseExpression getRootVal(){
        return this.rootVal;
    }

    public BaseExpression getRp(){
        return this.rp;
    }

    public BaseExpression getLp(){
        return this.lp;
    }

    public BaseExpression getParent(){
        return this.parent;
    }

    //set thr right pointer of node
    public void setLp(BaseExpression exp){
        this.lp = exp;
    }

    //set the right pointer of node
    public void selRp(BaseExpression exp){
        this.rp = exp;
    }
}
