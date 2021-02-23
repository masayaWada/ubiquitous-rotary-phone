package converter;
// TODO コメント修正、プログラム正規化

import java.net.MalformedURLException;
import java.net.URL;

/**
 * ファイルパス変換を行うクラス
 * @version 2021/01/05 1.0.0 新規作成
 * @since 1.0.0
 * @author wadamasaya
 */
public class PathConverter {

  /**
   * 引数(String)のファイルパスをURL形式に変換する
   * @param convertTargetStr 対象ファイルパス
   * @return 変換後URL
   * @version 2021/01/05 1.0.0 新規作成
   * @since 1.0.0
   * @author wadamasaya
   * @throws MalformedURLException
   */
  public static URL toUrl(String FilePath) throws MalformedURLException {
    return new URL(FilePath);
  }
}