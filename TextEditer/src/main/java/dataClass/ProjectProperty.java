package dataClass;

import converter.CharConverter;

/**
 * FileInfo用メンバメソッド
 * @version 2021/01/05 1.0.0 新規作成
 * @since 1.0.0
 * @author wadamasaya
 */
public class ProjectProperty {

  private final String animeTitle; // アニメタイトル
  private final String uploadPassword; // アップロード用パスワード
  private final String contentPassword; // コンテンツ保護パスワード
  private final Boolean hiddenFlg; // 表示フラグ
  private final Boolean lockFlg; // 保護フラグ

  /**
   * コンストラクタ用コンストラクタ
   * @param animeTitle アニメタイトル
   * @param uploadPassword アップロード用パスワード
   * @param contentPassword コンテンツ保護パスワード
   * @param hiddenFlg 表示フラグ
   * @param lockFlg 保護フラグ
   * @version 2021/01/05 1.0.0 新規作成
   * @since 1.0.0
   * @author wadamasaya
   */
  public ProjectProperty(String animeTitle, String uploadPassword, String contentPassword, Boolean hiddenFlg,
      Boolean lockFlg) {
    // NULLだった場合、空文字を設定する
    this.animeTitle = CharConverter.nullMask(animeTitle);
    this.uploadPassword = CharConverter.nullMask(uploadPassword);
    this.contentPassword = CharConverter.nullMask(contentPassword);

    this.hiddenFlg = hiddenFlg;
    this.lockFlg = lockFlg;
  }

  /**
   * アニメタイトルを取得します。
   * @return アニメタイトル
   * @version 2021/01/05 1.0.0 新規作成
   * @since 1.0.0
   * @author wadamasaya
   */
  public String getAnimeTitle() {
    return animeTitle;
  }

  /**
   * アップロード用パスワードを取得します。
   * @return アップロード用パスワード
   * @version 2021/01/05 1.0.0 新規作成
   * @since 1.0.0
   * @author wadamasaya
   */
  public String getUploadPassword() {
    return uploadPassword;
  }

  /**
   * コンテンツ保護パスワードを取得します。
   * @return コンテンツ保護パスワード
   * @version 2021/01/05 1.0.0 新規作成
   * @since 1.0.0
   * @author wadamasaya
   */
  public String getContentPassword() {
    return contentPassword;
  }

  /**
   * 表示フラグを取得します。
   * @return 表示フラグ
   * @version 2021/01/05 1.0.0 新規作成
   * @since 1.0.0
   * @author wadamasaya
   */
  public Boolean getHiddenFlg() {
    return hiddenFlg;
  }

  /**
   * 保護フラグを取得します。
   * @return 表示フラグ
   * @version 2021/01/05 1.0.0 新規作成
   * @since 1.0.0
   * @author wadamasaya
   */
  public Boolean getLockFlg() {
    return lockFlg;
  }
}