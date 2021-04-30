package converter;

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
    // 対象文字列の文字数取得
    Integer charLength = convertTargetStr.length();
    // 変換後の文字列格納用変数
    String asteriskStr = "";

    // 1文字目以降の文字列を*に置換する
    for (Integer i = 1; i < charLength; i++) {
      asteriskStr = asteriskStr + "*";
    }

    // 対象文字列の1文字目 + 変換後文字列を返却する
    return convertTargetStr.charAt(0) + asteriskStr;
  }

  /**
   * 変換対象がNULLの場合に空白を返却する
   *
   * @param convertTargetStr 変換対象文字列
   * @return 変換後文字列
   * @version 2021/01/05 1.0.0 新規作成
   * @since 1.0.0
   * @author wadamasaya
   */
  public static String nullMask(String convertTargetStr) {
    // 対象文字列がnullであるかの判断
    if (convertTargetStr == null) {
      // 空白を返却する
      return "";
    } else {
      // 対象文字列をそのまま返却する
      return convertTargetStr;
    }
  }
}