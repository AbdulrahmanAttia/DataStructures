
public class OperatorNode extends Node{
	public OperatorNode(String val){
		this.val = val;
	}
	public int evaluate(){
		if(val.equals("+"))return left.evaluate() + right.evaluate();
		return left.evaluate() * right.evaluate();
	}
}
