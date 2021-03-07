package dataClass;

/**
 * TreeItemData用メンバメソッド
 * @version 2021/01/05 1.0.0 新規作成
 * @since 1.0.0
 * @author wadamasaya
 */
public class TreeItemData {
  private final String name; // 名前
  private final Type type; // 種類(グループ/アイテム)

  /**
   * TreeItemData用コンストラクタ
   * @param name 名前
   * @version 2021/01/05 1.0.0 新規作成
   * @since 1.0.0
   * @author wadamasaya
   */
  public TreeItemData(String name) {
    this(name, Type.ITEM);
  }

  /**
   * TreeItemData用コンストラクタ
   * @param name 名前
   * @param type 種類
   * @version 2021/01/05 1.0.0 新規作成
   * @since 1.0.0
   * @author wadamasaya
   */
  public TreeItemData(String name, Type type) {
    this.name = name;
    this.type = type;
  }

  /**
   * 名前を取得します。
   * @return 名前
   * @version 2021/01/05 1.0.0 新規作成
   * @since 1.0.0
   * @author wadamasaya
   */
  public String getName() {
    return this.name;
  }

  /**
   * 種類を取得します。
   * @return 種類
   * @version 2021/01/05 1.0.0 新規作成
   * @since 1.0.0
   * @author wadamasaya
   */
  public Type getType() {
    return this.type;
  }

  /**
   * TreeItemの種類です。
   * @version 2021/01/05 1.0.0 新規作成
   * @since 1.0.0
   * @author wadamasaya
   */
  public enum Type {
    /**グループです。*/
    GROUP,
    /**アイテムです。*/
    ITEM,
  }
}