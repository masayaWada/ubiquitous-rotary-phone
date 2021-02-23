package dataClass.javaFxTreeView;

/**
 * CharCssInfo用メンバメソッド
 * @version 2021/01/05 1.0.0 新規作成
 * @since 1.0.0
 * @author wadamasaya
 */
public class CharInfo {
  private final String charName; // キャラ名
  private final String charInitial; // キャラ頭文字
  private final String cssClassName; // CSSのClass名
  private final String charColor; // キャラ色

  /**
   * CharCssInfo用コンストラクタ
   * @param charName キャラ名
   * @param charInitial キャラ頭文字
   * @param cssClassName CSSのClass名
   * @param colorStr キャラ色
   * @version 2021/01/05 1.0.0 新規作成
   * @since 1.0.0
   * @author wadamasaya
   */
  public CharInfo(String charName, String charInitial, String cssClassName, String colorStr) {
    this.charName = charName;
    this.charInitial = charInitial;
    this.cssClassName = cssClassName;
    this.charColor = colorStr;
  }

  /**
   * キャラ名を取得します。
   * @return キャラ名
   * @version 2021/01/05 1.0.0 新規作成
   * @since 1.0.0
   * @author wadamasaya
   */
  public String getCharName() {
    return this.charName;
  }

  /**
   * キャラ頭文字を取得します。
   * @return キャラ頭文字
   * @version 2021/01/05 1.0.0 新規作成
   * @since 1.0.0
   * @author wadamasaya
   */
  public String getCharInitial() {
    return this.charInitial;
  }

  /**
   * CSSのClass名を取得します。
   * @return CSSのClass名
   * @version 2021/01/05 1.0.0 新規作成
   * @since 1.0.0
   * @author wadamasaya
   */
  public String getCssClassName() {
    return this.cssClassName;
  }

  /**
   * キャラ色を取得します。
   * @return キャラ色
   * @version 2021/01/05 1.0.0 新規作成
   * @since 1.0.0
   * @author wadamasaya
   */
  public String getCharColor() {
    return this.charColor;
  }
}