package pathControler;

import java.io.IOException;
// TODO コメント修正、プログラム正規化

/**
 * Propertiesファイル作成クラス
 * @version 2021/01/05 1.0.0 新規作成
 * @since 1.0.0
 * @author wadamasaya
 */
public class GetPath {

  /**
   * 実行可能jarの格納フォルダパスを取得
   *
   * @return jarファイルパスを取得
   * @throws IOException
   * @version 2021/01/05 1.0.0 新規作成
   * @since 1.0.0
   * @author wadamasaya
   */
  public static String getRootPath() throws IOException {
    return ClassPathLocator.getLocation().getParent();
  }

  /**
   * 設定フォルダパスを取得
   *
   * @return 設定フォルダパスを取得
   * @throws IOException
   * @version 2021/01/05 1.0.0 新規作成
   * @since 1.0.0
   * @author wadamasaya
   */
  public static String getConfigPath() throws IOException {
    return ClassPathLocator.getLocation().getParent() + "/config";
  }
}