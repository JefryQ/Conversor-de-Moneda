import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.util.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConverterGUI extends JFrame {

    private final JComboBox<String> menu1;
    private final JComboBox<String> menu2;
    private final JTextField inputField;


    public ConverterGUI() {
    	setBackground(new Color(192, 192, 192));
    	getContentPane().setBackground(new Color(192, 192, 192));

        setTitle("Convertidor de moneda");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);
        setResizable(false);
        Border mainBorder = new LineBorder(new Color(186, 186, 215), 17, false);

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(139, 147, 182));
        mainPanel.setBorder(mainBorder);


//         components
        Converter converter = new Converter();
        SortedMap<String, Double> sortedCurrencies = new TreeMap<>(converter.getConversionRates());
        menu1 = new JComboBox<>(sortedCurrencies.keySet().toArray(new String[0]));
        menu1.setBounds(17, 161, 452, 47);
        menu1.setFont(new Font("Monospaced", Font.PLAIN, 15));

        menu2 = new JComboBox<>(sortedCurrencies.keySet().toArray(new String[0]));
        menu2.setBounds(17, 255, 452, 47);
        menu2.setFont(new Font("Monospaced", Font.PLAIN, 15));
                        mainPanel.setLayout(null);
                
                        JLabel inputFieldLabel = new JLabel("Por favor ingrese valor a convertir: ");
                        inputFieldLabel.setBounds(17, 20, 452, 47);
                        inputFieldLabel.setFont(new Font("Monospaced", Font.BOLD, 18));
                        mainPanel.add(inputFieldLabel);
        
                inputField = new JTextField();
                inputField.setBounds(17, 67, 452, 47);
                mainPanel.add(inputField);

//        for (String currency : Converter.conversionRates.keySet()) {
//            menu1.addItem(currency);
//            menu2.addItem(currency);
//        }

//        LABELS
        JLabel convertLabel = new JLabel("Moneda Origen: ");
        convertLabel.setBounds(17, 114, 452, 47);
        convertLabel.setFont(new Font("Monospaced", Font.BOLD, 18));
        mainPanel.add(convertLabel);
        mainPanel.add(menu1);

        JLabel clearLabel = new JLabel("A: ");
        clearLabel.setBounds(17, 208, 452, 47);
        clearLabel.setFont(new Font("Monospaced", Font.BOLD, 18));
        mainPanel.add(clearLabel);
        mainPanel.add(menu2);

//        Dialog font
        UIManager.put("OptionPane.messageFont", new Font("Monospaced", Font.BOLD, 16));

//        BUTTONS
        JButton convertButton = new JButton("Convertir valores");
        convertButton.setBounds(17, 302, 452, 47);
        convertButton.setForeground(new Color(0, 0, 0));
        convertButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
        });
        convertButton.setFont(new Font("Monospaced", Font.BOLD, 16));


        mainPanel.add(convertButton);

        getContentPane().add(mainPanel);
//        pack();
        setVisible(true);

        convertButton.addActionListener(e -> {
            try {
                String fromCurrency = (String) menu1.getSelectedItem();
                String toCurrency = (String) menu2.getSelectedItem();
                double value;
                try {
                    try {
                        Converter.compareOptions(fromCurrency, toCurrency);
                    } catch (Exception ignored) {

                    }

                    value = converter.validateInput(inputField.getText());
                } catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(null, "Solo se permiten numeros");
                    inputField.setText("");
                    return;
                }
                double result = converter.convertCurrency(value, fromCurrency, toCurrency);
                JOptionPane.showMessageDialog(null,
                        value + " " + fromCurrency  + "s" + " son " + result + " " + toCurrency + "s");
            } catch (NumberFormatException nfe) {
                System.out.println("error");
            }

        });

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ConverterGUI::new);
    }
}