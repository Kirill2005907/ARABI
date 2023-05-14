public class kata {
}import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        try {
            String result = calc(input);
            System.out.println(result);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static String calc(String input) throws Exception {
        String[] parts = input.split(" ");
        if (parts.length != 3) {
            throw new Exception("Неверный формат ввода");
        }
        String operand1 = parts[0];
        String operand2 = parts[2];
        String operator = parts[1];
        if (!isNumber(operand1) || !isNumber(operand2)) {
            throw new Exception("Неверный тип чисел");
        }
        int num1 = Integer.parseInt(operand1);
        int num2 = Integer.parseInt(operand2);
        if (num1 < 1 || num1 > 10 || num2 < 1 || num2 > 10) {
            throw new Exception("Число должно быть от 1 до 10 включительно");
        }
        int result;
        switch (operator) {
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "/":
                result = num1 / num2;
                break;
            default:
                throw new Exception("Неверный оператор");
        }
        if (isRomanNumeral(operand1) && isRomanNumeral(operand2)) {
            if (result < 1) {
                throw new Exception("Результат не может быть меньше единицы");
            }
            return toRomanNumeral(result);
        }
        return Integer.toString(result);
    }

    private static boolean isNumber(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean isRomanNumeral(String str) {
        String romanNumeralPattern = "^(?=[MDCLXVI])M*(C[MD]|D?C{0,3})(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$";
        return str.matches(romanNumeralPattern);
    }

    private static String toRomanNumeral(int number) {
        if (number < 1 || number > 3999) {
            return "";
        }
        String[] romanNumerals = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < romanNumerals.length; i++) {
            while (number >= values[i]) {
                number -= values[i];
                sb.append(romanNumerals[i]);
            }
        }
        return sb.toString();
    }
}
