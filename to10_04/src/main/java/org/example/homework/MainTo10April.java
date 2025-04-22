package org.example.homework;

import org.example.homework.domain.Employee;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * to10_04.
 *
 * @author Lina_Dautova
 */
public class MainTo10April {
    public static void main(String[] args) {

        List<Integer> integers = List.of(5, 2, 10, 9, 4, 3, 10, 1, 13);
        // Найдите в списке целых чисел 3-е наибольшее число (пример: 5 2 10 9 4 3 10 1 13 => 10)
        System.out.println("\n---- 1 ----");
        integers.stream()
                .sorted(Comparator.reverseOrder())
                .limit(3)
                .skip(2)
                .findFirst()
                .ifPresent(System.out::println);

        // Найдите в списке целых чисел 3-е наибольшее «уникальное» число (пример: 5 2 10 9 4 3 10 1 13 => 9, в отличие от прошлой задачи здесь разные 10 считает за одно число)
        System.out.println("\n---- 2 ----");
        integers.stream()
                .sorted(Comparator.reverseOrder())
                .distinct()
                .limit(3)
                .skip(2)
                .findFirst()
                .ifPresent(System.out::println);

        // Имеется список объектов типа Сотрудник (имя, возраст, должность), необходимо получить список имен 3 самых старших
        // сотрудников с должностью «Инженер», в порядке убывания возраста
        var employees = List.of(new Employee("Vasiliy", 34, "Инженер"),
                new Employee("Mihail", 34, "Бухгалтер"),
                new Employee("Petya", 45, "Инженер"),
                new Employee("Masha", 28, "Инженер"),
                new Employee("Leonid", 30, "Бухгалтер"),
                new Employee("Bogdan", 45, "Инженер"),
                new Employee("Boris", 46, "Инженер")
        );
        System.out.println("\n---- 3 ----");
        var employeesBySortAgeAndFilterPosition =
                employees
                        .stream()
                        .filter(employee -> "Инженер".equals(employee.getPosition()))
                        .sorted(Comparator.comparing(Employee::getAge).reversed())
                        .limit(3)
                        .map(Employee::getName)
                        .toList();
        System.out.println(employeesBySortAgeAndFilterPosition);

        System.out.println("\n---- 4 ----");
        //  Имеется список объектов типа Сотрудник (имя, возраст, должность), посчитайте средний возраст сотрудников с должностью «Инженер»
        Double ageAvg = employees.stream()
                .filter(employee -> "Инженер".equals(employee.getPosition()))
                .map(Employee::getAge)
                .collect(Collectors.averagingInt(x -> x));
        System.out.println(ageAvg);

        System.out.println("\n---- 5 ----");
        //Найдите в списке слов самое длинное
        Stream.of("first", "second", "third", "fourth", "fifth", "sixth22222", "seventh", "eighth", "ninth")
                .max(Comparator.comparing(String::length))
                .ifPresent(System.out::println);

        System.out.println("\n---- 6 ----");
        //  Имеется строка с набором слов в нижнем регистре, разделенных пробелом. Постройте хеш-мапы,
        //  в которой будут хранится пары: слово - сколько раз оно встречается во входной строке
        Map<String, Long> resultMap =
                Arrays.stream("первый второй первый третий первый третий второй второй второй второй"
                                .split(" "))
                        .collect(Collectors.groupingBy(x -> x, Collectors.counting()));
        System.out.println(resultMap);

        System.out.println("\n---- 7 ----");
        //Отпечатайте в консоль строки из списка в порядке увеличения длины слова, если слова имеют одинаковую длины, то должен быть сохранен алфавитный порядок
        List<String> listString = Stream.of("first", "second", "third", "fourth222", "fifth", "fourth221", "sixth22222", "seventh", "eighth", "ninth")
                .sorted(Comparator.comparing(String::length).thenComparing(x -> x))
                .toList();
        System.out.println(listString);

        System.out.println("\n---- 8 ----");
        // Имеется массив строк, в каждой из которых лежит набор из 5 слов, разделенных пробелом,
        // найдите среди всех слов самое длинное, если таких слов несколько, получите любое из них
        Stream.of("первое второй третье четвертое пятое",
                        "Жили были у бабуси два",
                        "Кабы не было весны летом")
                .map(x -> x.split(" "))
                .filter(x -> x.length == 5)
                .flatMap(Arrays::stream)
                .max(Comparator.comparing(String::length))
                .stream().findFirst()
                .ifPresent(System.out::println);

    }
}
