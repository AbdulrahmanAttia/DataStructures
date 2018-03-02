import java.util.ArrayList;
import java.util.List;

public class Deck {

	//private static List<Card> cards = new ArrayList<>();
	private enum Suit {
		CLUB(0), DIAMOND(1), HEART(2), SPADE(3);
		private int value;
		private Suit(int v){
			value = v;
		}
		public int getValue(){
			return value;
		}
		public static Suit getSuitfromValue(int value){
			if(value == 0)return Suit.CLUB;
			else if(value == 0)return Suit.DIAMOND;
			else if(value == 0)return Suit.HEART;
			return Suit.SPADE;
		}
		
	}
	public static void main(String args[]){
		System.out.println(Suit.getSuitfromValue(0));
	}
	

}
