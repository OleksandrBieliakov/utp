package ass12.gui;

import ass12.tasks.Status;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class StatusCellRenderer extends JLabel implements TableCellRenderer {

    public StatusCellRenderer() {
        setOpaque(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object object, boolean isSelected, boolean hasFocus, int row, int column) {
        Status status = (Status) object;
        setText(status.name());
        setBackground(status.getBackgroundColor());
        setForeground(status.getFontColor());
        return this;
    }

}
