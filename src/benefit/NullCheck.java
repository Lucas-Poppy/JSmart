package benefit;

public class NullCheck {

	/**
	 * 任意の文字列を受け取りnullならば空白を返すメソッド
	 * @param s
	 * @return 文字列もしくは空白
	 */
	public static String nullConvert(String s){

		if(s==null){
			s="";
		}
		return s;
	}

}
