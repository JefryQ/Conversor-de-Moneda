import javax.swing.*;
import java.text.*;
import java.util.*;


public class Converter {


    private Map<String, Double> conversionR;

    public Map<String, Double> getConversionRates() {
        return conversionR;
    }

    Converter() {
        conversionR = new HashMap<>();
        conversionR.put("Dólar estadounidense", 1.0);
        conversionR.put("Peso colombiano", 4217.50);
        conversionR.put("Euro", 0.96);
        conversionR.put("Libra esterlina", 0.83);
        conversionR.put("Yen japonés", 149.19);
        conversionR.put("Won surcoreano", 1360.18);
    }


    
    public double validateInput(String number) throws NumberFormatException {
        if (number != null) {
            return Double.parseDouble(number);
        } else {
            throw new NumberFormatException("valor invalido");
        }
    }

    public static void compareOptions(String fromCurrency, String toCurrency) {
        if (Objects.equals(fromCurrency, toCurrency)) {
            JOptionPane.showMessageDialog(null, "No se puede convertir");

        }
    }


    public double convertCurrency(double value, String fromCurrency, String toCurrency) {
        double fromValue = conversionR.get(fromCurrency);
        double toValue = conversionR.get(toCurrency);
        double convertedValue = (value / fromValue) * toValue;

        DecimalFormat decimalFormat = new DecimalFormat("#.000");
        String formattedValue = decimalFormat.format(convertedValue);
        return Double.parseDouble(formattedValue);
    }
}