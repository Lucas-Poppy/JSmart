package benefit;

public class InputCheck {
	/**
	 * 入力された文字列などのチェックを行うクラス
	 */

	/**
	 * 受け取った文字列が10文字以内であるかどうかをチェックするクラス
	 * @param s 任意の文字列
	 * @return ture or falseで返す
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

}
