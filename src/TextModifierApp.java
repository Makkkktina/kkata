import java.util.Scanner;

public class TextModifierApp {
    public static String textModifier() {
        Scanner scanner = new Scanner(System.in); // Создаем объект Scanner для считывания текста, введённого пользователем
        System.out.print("Введите текст: ");
        String input = scanner.nextLine();

        // Шаг А: Удаление лишних пробелов
        StringBuilder result = new StringBuilder();
        boolean lastWasSpace = false; // Флаг для отслеживания предыдущего символа пробела

        for (char ch : input.toCharArray()) {// Проходим по каждому символу во введённой строке
            if (ch == ' ') { // Если текущий символ - пробел
                if (!lastWasSpace) {// Если предыдущий символ не был пробелом, добавляем пробел
                    result.append(ch);
                    lastWasSpace = true; // Устанавливаем флаг, что текущий символ - пробел
                }
            } else {
                result.append(ch); // Добавляем текущий символ в результат
                lastWasSpace = false;  // Сбрасываем флаг, так как текущий символ - не пробел
            }
        }

        // Шаг B: Обмен символов вокруг знака '-' и удаление самого знака '-'
        for (int i = 1; i < result.length() - 1; i++) {
            if (result.charAt(i) == '-') { // Если текущий символ - '-'
                char left = result.charAt(i - 1); // Символ слева от '-'
                char right = result.charAt(i + 1); // Символ справа от '-'
                result.setCharAt(i - 1, right); // Меняем местами левый и правый символы
                result.setCharAt(i + 1, left);
                result.deleteCharAt(i); // Удаляем символ '-'
                i--; // Смещаем индекс назад для корректной обработки изменённой строки
            }
        }

        // Шаг C: Замена '+' на '!'
        for (int i = 0; i < result.length(); i++) {
            if (result.charAt(i) == '+') {
                result.setCharAt(i, '!'); // Заменяем '+' на '!'
            }
        }

        // Шаг D: Удаление цифр, подсчёт их суммы и добавление суммы в конец результата
        int sum = 0;
        StringBuilder finalResult = new StringBuilder();

        for (int i = 0; i < result.length(); i++) { // Проходим по каждому символу в обработанной строке
            char ch = result.charAt(i);
            if (Character.isDigit(ch)) {
                sum += ch - '0'; // Добавляем значение цифры к общей сумме
            } else {
                finalResult.append(ch); // Если символ - не цифра, добавляем его в финальный результат
            }
        }

        if (sum > 0) { // Если сумма цифр больше 0, добавляем её в конец результата с пробелом перед ней
            finalResult.append(" ").append(sum);
        }

        return finalResult.toString(); // Возвращаем финальный результат
    }

    public static void main(String[] args) {
        System.out.println(textModifier());  // Выводим результат выполнения метода textModifier
    }
}