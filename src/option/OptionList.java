package option;

import javax.swing.*;
import javax.swing.colorchooser.ColorSelectionModel;
import java.awt.*;
import java.util.LinkedList;
import image.Color;

/**
 * List of options for algorithms. Connects options set in the GUI with the algorithms.
 */
public class OptionList {

    /** Boolean Option Listener that updates boolean values. */
    public interface BooleanOptionListener{ void update(boolean val); }
    /** Integer Option Listener that updates integer values. */
    public interface IntegerOptionListener{ void update(int val); }
    /** Double Option Listener that updates double values. */
    public interface DoubleOptionListener{ void update(double val); }
    /** String Option Listener that updates string values. */
    public interface StringOptionListener{ void update(String val); }
    /** Color Option Listener that updates string values. */
    public interface ColorOptionListener{ void update(Color val); }
    /** Action Option Listener that updates when the user presses a button. */
    public interface ActionOptionListener{ void update(); }


    private LinkedList<Component> list;

    public OptionList(){
        list = new LinkedList<>();
    }

    /**
     * Adds a boolean option to the list, using a checkbox widget.
     * @param name name of the option
     * @param value initial value of the option
     * @param listener the listener that updates the value in the algorithm (update(...) is called when the user changes the option)
     */
    public void addOption(String name, boolean value, BooleanOptionListener listener){
        JCheckBox cb = new JCheckBox(name, value);
        cb.addChangeListener(l->listener.update(cb.isSelected()));

        list.add(cb);
    }

    /**
     * Adds an integer option to the list, using a slider widget.
     * @param name name of the option
     * @param value initial value of the option
     * @param min minimum value of the option
     * @param max maximum value of the option
     * @param listener the listener that updates the value in the algorithm (update(...) is called when the user changes the option)
     */
    public void addOption(String name, int value, int min, int max, IntegerOptionListener listener){
        JLabel lbl = new JLabel(name + ": " + value);

        JSlider sl = new JSlider(min, max, value);
        sl.addChangeListener(l -> {
            listener.update(sl.getValue());
            lbl.setText(name + ": " + sl.getValue());
        });

        list.add(lbl);
        list.add(sl);
    }

    /**
     * Adds an integer option to the list, using a spinner widget.
     * @param name name of the option
     * @param value initial value of the option
     * @param min minimum value of the option
     * @param max maximum value of the option
     * @param step step in the spinner widget
     * @param listener the listener that updates the value in the algorithm (update(...) is called when the user changes the option)
     */
    public void addOption(String name, int value, int min, int max, int step, IntegerOptionListener listener){
        JLabel lbl = new JLabel(name + ": ");

        JSpinner spin = new JSpinner(new SpinnerNumberModel(value, min, max, step));
        spin.addChangeListener(l -> listener.update((int)spin.getValue()));

        list.add(lbl);
        list.add(spin);
    }

    /**
     * Adds a double option to the list, using a slider widget. The slider probably uses integer values and converts them to double values. The precision of the slider depends on the step value.
     * @param name name of the option
     * @param value initial value of the option
     * @param min minimum value of the option
     * @param max maximum value of the option
     * @param step step in the slider widget, affects its precision
     * @param listener the listener that updates the value in the algorithm (update(...) is called when the user changes the option)
     */
    public void addOption(String name, double value, double min, double max, double step, DoubleOptionListener listener){
        JLabel lbl = new JLabel(name + ": " + value);

        double intervalSize = max - min;
        int numCells = (int)(intervalSize / step + 0.5);
        //min <-> 0, max <-> numCells

        JSlider sl = new JSlider(0, numCells, (int)( (value - min)/step +0.5));
        sl.addChangeListener(l -> {
            double val = sl.getValue()*step + min;
            listener.update(val);
            lbl.setText(name + ": " + val);
        });

        list.add(lbl);
        list.add(sl);
    }

    /**
     * Adds a string option to the list, using a textbox and a button widget. The string is updated when the button is pressed.
     * @param name name of the option
     * @param value initial value of the option
     * @param listener the listener that updates the value in the algorithm (update(...) is called when the user changes the option)
     */
    public void addOption(String name, String value, StringOptionListener listener){
        JTextField txt = new JTextField(value);
        JButton btn = new JButton(name);
        btn.addActionListener(e -> listener.update(txt.getText()));

        list.add(txt);
        list.add(btn);
    }

    /**
     * Adds a selector option to the list, using a list/selector widget. Only the strings in the values array can be selected.
     * @param name name of the option
     * @param values values that can be selected
     * @param index index of the initial value
     * @param listener the listener that updates the value in the algorithm (update(...) is called when the user changes the option)
     */
    public void addOption(String name, String[] values, int index, IntegerOptionListener listener){
        JLabel lbl = new JLabel(name + ": ");

        JComboBox<String> cb = new JComboBox<>(values);
        cb.setSelectedIndex(index);
        cb.addActionListener(l -> listener.update(cb.getSelectedIndex()));

        list.add(lbl);
        list.add(cb);
    }

    /**
     * Adds a color option to the list, using a color chooser widget.
     * @param value initial color
     * @param listener the listener that updates the value in the algorithm (update(...) is called when the user changes the option)
     */
    public void addOption(Color value, ColorOptionListener listener){
        JColorChooser c = new JColorChooser(new java.awt.Color(value.getRGB()));
        ColorSelectionModel model = c.getSelectionModel();
        model.addChangeListener(l -> listener.update(new Color(c.getColor().getRGB())));
        list.add(c);
    }

    /**
     * Adds a button widget. When the user is pressing the button, the update method is called.
     * @param name name of the option
     * @param listener the listener that is called when the user presses the button
     */
    public void addOption(String name, ActionOptionListener listener){
        JButton btn = new JButton(name);
        btn.addActionListener(l -> listener.update());

        list.add(btn);
    }

    /**
     * Adds info to display in the options using a label/text widget.
     * @param info info to display
     */
    public void addInfo(String info){
        list.add(new JLabel(info));
    }

    /**
     * Adds all options from the given list to this list.
     * @param list the other list
     */
    public void addList(OptionList list){
        this.list.addAll(list.list);
    }

    /**
     * Return the GUI list of this option list.
     * @return the gui list
     */
    public LinkedList<Component> getList(){
        return list;
    }

    public static void displayError(String message){
        JOptionPane.showConfirmDialog(null, "Error: "+message, "Error", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE);
    }

}
