
public class OperandNode extends Node {
	
	public OperandNode(String val){
		this.val = val;
	}
	public int evaluate(){
		return Integer.parseInt(val);
	}
	
}


