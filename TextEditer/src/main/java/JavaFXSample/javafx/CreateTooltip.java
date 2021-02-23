package JavaFXSample.javafx;

import javafx.scene.control.Tooltip;

/**
 * ツールチップ作成用クラス
 *
 * @version 2021/01/05 1.0.0 新規作成
 * @since 1.0.0
 * @author wadamasaya
 */
public class CreateTooltip {

  /**
   * ツールチップを作成する
   *
   * @param tootipStr 表示する文字列
   * @return 作成したToolTip
   * @version 2021/01/05 1.0.0 新規作成
   * @since 1.0.0
   * @author wadamasaya
   */
  @SuppressWarnings("exports")
  public Tooltip createToolTip(final String tootipStr) {
    // ToolTipを作成
    final Tooltip tooltip = new Tooltip();
    tooltip.setText(tootipStr);

    // 作成したToolTipを返却
    return tooltip;
  }
}