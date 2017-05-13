package benefit;

/**
 * 数値を変換またはチェックするクラス
 * @author Araki Yuki
 */
public class IntegerCheck {

	/**
	 * 任意の文字列が数値であるかどうかをチェックするメソッド
	 * @param s 任意の文字列
	 * @return 変換可能もしくは空白ならtrue 変換不可能の場合はfalseを返す
	 */
	public boolean checkParseInt(String s) {

		if (s.equals("")) {
			return true;
		} else {
			try {
				Integer.parseInt(s);
				return true;

			} catch (Exception e) {
				return false;
			}
		}

	}

	/**
	 * 任意の文字列を数値に変換するメソッド
	 * @param s
	 * @return 空白の場合、もしくは変換不可能であった場合は0 それ以外は変換後の数値を返す
	 */
	public int convertInteger(String s) {

		if (s.equals("")) {
			return 0;
		} else {
			try {
				return Integer.parseInt(s);

			} catch (Exception e) {
				return 0;
			}
		}

	}

}
