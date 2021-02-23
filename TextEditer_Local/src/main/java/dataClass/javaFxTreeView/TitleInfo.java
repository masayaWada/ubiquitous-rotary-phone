package dataClass.javaFxTreeView;

/**
 * TitleInfo用メンバメソッド
 * @version 2021/01/05 1.0.0 新規作成
 * @since 1.0.0
 * @author wadamasaya
 */
public class TitleInfo {
  private final String title; // タイトル
  private final String filePath; // CSSファイルパス

  /**
   * TitleInfo用コンストラクタ
   * @param fileName タイトル
   * @param filePath CSSファイルパス
   * @version 2021/01/05 1.0.0 新規作成
   * @since 1.0.0
   * @author wadamasaya
   */
  public TitleInfo(String title, String filePath) {
    this.title = title;
    this.filePath = filePath;
  }

  /**
   * タイトルを取得します。
   * @return タイトル
   * @version 2021/01/05 1.0.0 新規作成
   * @since 1.0.0
   * @author wadamasaya
   */
  public String getTitle() {
    return this.title;
  }

  /**
   * CSSファイルパスを取得します。
   * @return CSSファイルパス
   * @version 2021/01/05 1.0.0 新規作成
   * @since 1.0.0
   * @author wadamasaya
   */
  public String getFilePath() {
    return this.filePath;
  }
}