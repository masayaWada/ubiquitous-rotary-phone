package count;

import java.io.UnsupportedEncodingException;

/**
* EditFile画面のコントローラー
* @version 2021/01/05 1.0.0 新規作成
* @since 1.0.0
* @author wadamasaya
*/
public class ByteCounter {
  /**
   * 指定した文字エンコーディングでの文字列のバイト数を取得
   *
   * @param value 処理対象となる文字列
   * @param enc 文字エンコード("Shift_JIS", "UTF-8" etc...)
   * @return 文字列のバイト数
   */
  public static int getByte(String value, String enc) {
    if (value == null || value.length() == 0)
      return 0;
    int ret = 0;
    try {
      ret = value.getBytes(enc).length;
    } catch (UnsupportedEncodingException e) {
      ret = 0;
    }
    return ret;
  }
}