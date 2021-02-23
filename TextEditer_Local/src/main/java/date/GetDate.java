package date;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 時刻取得系クラス
   * @version 2021/01/05 1.0.0 新規作成
   * @since 1.0.0
   * @author wadamasaya
 */
public class GetDate {

  /**
   * 現在時刻をyyyy-MM-dd_HH-mm-ssの書式で取得する
   * @return 日付の文字列
   * @throws IOException
   * @version 2021/01/05 1.0.0 新規作成
   * @since 1.0.0
   * @author wadamasaya
   */
  public static String getNowDate() throws IOException {
    // 現在日時情報で初期化されたインスタンスの生成
    Date dateObj = new Date();
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
    // 日時情報を指定フォーマットの文字列で取得
    String dateStr = format.format(dateObj);
    return dateStr;
  }
}