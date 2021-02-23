package converter;
// TODO コメント修正、プログラム正規化

/**
 * 文字列変換を行うクラス
 * @version 2021/01/05 1.0.0 新規作成
 * @since 1.0.0
 * @author wadamasaya
 */
public class CharConverter {

  /**
   * 頭文字以外の文字列に*でマスクをかけて変換する
   * @param convertTargetStr 変換対象文字列
   * @return 変換後文字列
   * @version 2021/01/05 1.0.0 新規作成
   * @since 1.0.0
   * @author wadamasaya
   */
  public static String stringMask(String convertTargetStr) {
    Integer charLength = convertTargetStr.length();
    String asteriskStr = "";
    for (Integer i = 1; i < charLength; i++) {
      asteriskStr = asteriskStr + "*";
    }
    return convertTargetStr.charAt(0) + asteriskStr;
  }
}