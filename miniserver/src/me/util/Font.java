package me.util;
import me.m.mswing.style.Style;

import javax.swing.*;

public class Font {

    public static void loadIndyFont(){
        java.awt.Font font=Source.jfOpenTTF().deriveFont(12f);
        UIManager.put("CheckBox.font",font);
        UIManager.put("Tree.font", font);
        UIManager.put("Viewport.font", font);
        UIManager.put("ProgressBar.font", font);
        UIManager.put("RadioButtonMenuItem.font", font);
        UIManager.put("FormattedTextField.font", font);
        UIManager.put("Panel.font",font);
        UIManager.put("TextArea.font",font);

        UIManager.put("Menu.font", font);
        UIManager.put("RadioButtonMenuItem.acceleratorFont",font);
        UIManager.put("Menu.acceleratorFont",font);
        UIManager.put("CheckBoxMenuItem.acceleratorFont",font);
        UIManager.put("TableHeader.font", font);
        UIManager.put("TextField.font", font);
        UIManager.put("OptionPane.font", font);
        UIManager.put("MenuBar.font", font);
        UIManager.put("Button.font", font);
        UIManager.put("Label.font", font);
        UIManager.put("PasswordField.font",font);
        UIManager.put("OptionPane.buttonFont", font);
        UIManager.put("ScrollPane.font", font);
        UIManager.put("MenuItem.font", font);
        UIManager.put("ToolTip.font", font);
        UIManager.put("List.font", font);
        UIManager.put("OptionPane.messageFont",font);
        UIManager.put("EditorPane.font", font);
        UIManager.put("Table.font", font);
        UIManager.put("TabbedPane.font",font);
        UIManager.put("RadioButton.font", font);
        UIManager.put("CheckBoxMenuItem.font",font);
        UIManager.put("TextPane.font", font);
        UIManager.put("PopupMenu.font", font);
        UIManager.put("TitledBorder.font", font);
        UIManager.put("ComboBox.font", font);
    }
}
