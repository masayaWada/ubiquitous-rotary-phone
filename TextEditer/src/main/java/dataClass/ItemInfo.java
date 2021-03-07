package dataClass;

/**
 * ItemInfo用メンバメソッド
 * @version 2021/01/05 1.0.0 新規作成
 * @since 1.0.0
 * @author wadamasaya
 */
public class ItemInfo {
  private final String fileName; // ファイル名
  private final String parentFolderPath; // 親フォルダパス
  private final String filePath; // ファイルパス

  /**
   * ItemInfo用コンストラクタ
   * @param fileName ファイル名
   * @param parentFolderPath 親フォルダパス
   * @param filePath ファイルパス
   * @version 2021/01/05 1.0.0 新規作成
   * @since 1.0.0
   * @author wadamasaya
   */
  public ItemInfo(String fileName, String parentFolderPath, String filePath) {
    this.fileName = fileName;
    this.parentFolderPath = parentFolderPath;
    this.filePath = filePath;
  }

  /**
   * ファイル名を取得します。
   * @return ファイル名
   * @version 2021/01/05 1.0.0 新規作成
   * @since 1.0.0
   * @author wadamasaya
   */
  public String getFileName() {
    return this.fileName;
  }

  /**
   * 親フォルダパスを取得します。
   * @return 親フォルダパス
   * @version 2021/01/05 1.0.0 新規作成
   * @since 1.0.0
   * @author wadamasaya
   */
  public String getParentFolderPath() {
    return this.parentFolderPath;
  }

  /**
   * ファイルパスを取得します。
   * @return ファイルパス
   * @version 2021/01/05 1.0.0 新規作成
   * @since 1.0.0
   * @author wadamasaya
   */
  public String getFilePath() {
    return this.filePath;
  }
}