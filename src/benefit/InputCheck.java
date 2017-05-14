package benefit;

public class InputCheck {
	/**
	 * 入力された文字列などのチェックを行うクラス
	 */

	/**
	 * 受け取った文字列が10文字以内であるかどうかをチェックするメソッド
	 * @param s 任意の文字列
	 * @return 10文字以内ならtureそれ以外ならfalseで返す
	 */
	public static boolean inputMaxTen(String s){
		boolean bool=true;
		if(s.length()<11){
			bool = true;
		}else{
			bool = false;
		}
		return bool;
	}

	/**
	 * 受け取った文字列が空白であるかどうかを返すメソッド
	 *
	 * @param s 任意の文字列
	 * @return 受け取った文字列が空白ならfalse それ以外ならtrueを返す
	 */
	public static boolean inputEmpty(String s){
		boolean bool=true;
		if(s.equals("")){
			bool = false;
		}else{
			bool = true;
		}
		return bool;
	}

}
