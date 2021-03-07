package dataClass;

/**
 * ProjectInfo用メンバメソッド
 * @version 2021/01/05 1.0.0 新規作成
 * @since 1.0.0
 * @author wadamasaya
 */
public class ProjectInfo {
  private final String projectName; // プロジェクト名
  private final String folderPath; // フォルダパス
  private final String projectInfoPath; // プロジェクト情報ファイルパス

  /**
   * ProjectInfo用コンストラクタ
   * @param projectName プロジェクト名
   * @param folderPath フォルダパス
   * @param projectInfoPath プロジェクト情報ファイルパス
   * @version 2021/01/05 1.0.0 新規作成
   * @since 1.0.0
   * @author wadamasaya
   */
  public ProjectInfo(String projectName, String folderPath, String projectInfoPath) {
    this.projectName = projectName;
    this.folderPath = folderPath;
    this.projectInfoPath = projectInfoPath;
  }

  /**
   * プロジェクト名を取得します。
   * @return プロジェクト名
   * @version 2021/01/05 1.0.0 新規作成
   * @since 1.0.0
   * @author wadamasaya
   */
  public String getProjectName() {
    return this.projectName;
  }

  /**
   * フォルダパスを取得します。
   * @return フォルダパス
   * @version 2021/01/05 1.0.0 新規作成
   * @since 1.0.0
   * @author wadamasaya
   */
  public String getFolderPath() {
    return this.folderPath;
  }

  /**
   * プロジェクト情報ファイルパスを取得します。
   * @return プロジェクト情報ファイルパス
   * @version 2021/01/05 1.0.0 新規作成
   * @since 1.0.0
   * @author wadamasaya
   */
  public String getProjectInfoPath() {
    return this.projectInfoPath;
  }
}