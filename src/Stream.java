import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Stream {
    public static void main(String[] args) {
        int[] arr = {1, 22, 333, 4444, 55555};
        IntStream s = Arrays.stream(arr);
        //System.out.println(s.sum());
        OptionalInt max = s.max();
        System.out.println(max);

        Optional<String> res = get();
        if (res.isPresent())
            System.out.println(res/*.get()*/);
        else System.out.println("Она пуста");

        List<String> list = new ArrayList<>();
        list.add("Petya");
        list.add("Masha");
        list.add("Anya");


        boolean isGood = list.stream()
                .allMatch(s1 -> Character.isUpperCase(s1.charAt(0)));
        System.out.println(isGood);
        /*list.stream().forEach(s1 -> System.out.println(s1));*/
        /*        list.stream().forEach(System.out::println);*/
        // list.stream().count(); //количество элементов

        /*list.stream().filter(s1->Character.isUpperCase(s1.charAt(0))).forEach(System.out::println);
         */
/*        list.stream()
                .filter(s1 -> Character.isUpperCase(s1.charAt(0)))
                .filter(s1 -> s1.length()%2==0)
                .forEach(System.out::println);*/


        list.stream()
                .filter(s1 -> Character.isUpperCase(s1.charAt(0)))
                .map(s1 -> s1 + " = " + s1.length())
                .forEach(System.out::println);

        // Filter
        //Для фильтрации элементов в стриме применяется метод filter .Метод фильтра принимает Predicate, который вызывается для
        // каждого элемента в стриме:
        // ●если элемент должен быть включен в результирующий стрим, Predicate должен вернуть значение true.
        // ●если элемент не должен быть включен, Predicate должен вернуть false.
        List<String> list1 = Arrays.asList("Moscow", "NY", "Tokyo");
        list1.stream()
                .filter(value -> value.length() >= 3)
                .filter(value -> value.contains("M"))
                .forEach(System.out::println);

        //Map
        // Для фильтрации элементов в стриме применяется метод filter .Метод фильтра принимает Predicate, который вызывается для
        // каждого элемента в стриме:
        // ●если элемент должен быть включен в результирующий стрим, Predicate должен вернуть значение true.
        // ●если элемент не должен быть включен, Predicate должен вернуть false.
        List<String> list2 = Arrays.asList("Moscow", "NY", "Tokyo");
        list2.stream()
                .map(String::toUpperCase)
                .map(value -> value + " <3")
                .forEach(System.out::println);


        //FlatMap
        //Метод flatMap отображает один элемент в виде нескольких элементов. Идея состоит в том,
        // что flatMap «сплющивает» каждый элемент из сложной структуры, состоящей из нескольких
        // внутренних элементов, в «плоский» стрим, состоящий только из этих внутренних элементов.
        List<String> list3 = new ArrayList<>();
        list3.add("И долго буду тем любезен я народу");
        list3.add("Что чувства добрые я лирой пробуждал");
        list3.add("Что чувства добрые я лирой пробуждал");
        list3.add("Что чувства добрые я лирой пробуждал");
        long count = list3.stream()
                .flatMap(value -> Arrays.stream(value.split(" ")))
                .count();
        System.out.println(count);

        //Distinct
        //Метод distinct применяется для удаления дубликатов.Метод distinct является нетерминальной операцией,
        //которая возвращает новый стрим, который будет содержать только уникальные элементы из исходного стрима.
        // Любые дубликаты будут исключены.
        List<String> list4 = Arrays.asList("one", "two", "three", "one", "two");
        list4 = list4.stream().distinct().collect(Collectors.toList());
        System.out.println(list4);


        //Limit
        //Метод limit применяется для ограничения количества элементов в стриме.
        // Метод limit возвращает новый стрим, который будет максимально
        // содержать заданное в качестве аргумента количество элементов.
        List<String> list5 = Arrays.asList("one", "two", "three", "one", "two");
        list5 = list5.stream()
                .limit(2)
                .collect(Collectors.toList());
        System.out.println(list5);

        //Sorted
        //Метод sorted применяется для сортировки элементов источника стрима.
        //В зависимости от типа используемого компаратора, можно получить различный результат:
        List<String> list6 = Arrays.asList("9", "A", "Z", "1", "B", "Y", "4", "a", "c");
        //naturalOrder – сортировка элементов в естественном порядке;
        list6.stream()
                .sorted(Comparator.naturalOrder())
                .forEach(System.out::print); // 149ABYZac
        //reverseOrder – сортировка элементов в обратном порядк
        list6.stream()
                .sorted(Comparator.reverseOrder())
                .forEach(System.out::print); // caZYBA941


        //Match
        // Терминальные операции match применяются для проверки наличия совпадающего объекта в источнике стрима.
        // В качестве аргумента используется предикат. Matchзапускает внутреннюю итерацию стрима и
        // применяет параметр предиката к каждому элементу.
        // ●Метод anyMatch возвращает true, если предикат возвращает true для любого из элементов.
        // ●Метод allMatch возвращает true, если предикат возвращает true для всех элементов.
        // ●Метод noneMath возвращает true, если предикат возвращает false для всех элементов.
        List<String> list7 = Arrays.asList("one", "two", "three");
        boolean anyMatch = list7.stream()
                .anyMatch(value -> value.startsWith("o"));
        System.out.println(anyMatch); // true


        //Collect
        //Метод collect является терминальной операцией, которая запускает
        //внутреннюю итерацию элементов и собирает элементы стрима в коллекцию.
        List<String> list8 = Arrays.asList("one", "two", "three");
        List<String> uppercaseList = list8.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        System.out.println(uppercaseList);


        //Count
        //Метод count является терминальной операцией, которая запускает внутреннюю итерацию элементов и определяет количество элементов
        List<String> list9 = new ArrayList<>();
        list9.add("И долго буду тем любезен я народу");
        list9.add("Что чувства добрые я лирой пробуждал");
        list9.add("Что в мой жестокий век восславил я Свободу");
        list9.add("И милость к падшим призывал");
        long count1 = list9.stream().
                flatMap(value -> Arrays.stream(value.split(" ")))
                .count();
        System.out.println(count1); // 26


        //Find
        // Метод find является терминальной операцией, которая производит поиск элементов в стриме.
        // ●метод findAny может найти один элемент из стрима. Найденный элемент может быть из любой точки стрима.
        // ●метод findFirst вернет первый элемент, если таковой существует.
        List<String> strings = Arrays.asList("Java Script", "Java 8", "Java 11", "Android", "Spring");
        Optional<String> result = strings.parallelStream().filter(s1 -> s1.contains("Java")).findFirst();
        result.ifPresentOrElse(System.out::println, () -> System.out.println("There is no Java :(")); // Java Script
        //Обратите внимание, что методы find возвращают тип Optional.
        //Если стрим будет пустой, то метод не вернет никакого результата. С помощью метода ifPresent проверим был ли найден результат в Optional.


        //Методы min и max это терминальные операции, которые возвращают наименьший и наибольший элемент стрима.
        //Определение наименьшего и наибольшего элемента происходит с помощью передачи определенной имплементации компаратора в методы min и max.
        List<Integer> intList = Arrays.asList(1, 2, 5, 10);
        Optional<Integer> min = intList.stream().min(Integer::compareTo);
        if (min.isPresent()) {
            Integer minString = min.get();
            System.out.println(minString);
        }


        //Reduce
        //Метод reduce это терминальная операция, которая может свести все элементы в стриме к одному элементу.
        // Reduce возвращает тип Optional. Этот необязательный параметр содержит значение (если оно есть),
        // возвращаемое лямбда-выражением, переданным методу reduce. Получить значение можно с помощью get.
        List<String> stringList = new ArrayList<String>();
        stringList.add("one");
        stringList.add("two");
        stringList.add("three");
        Optional<String> reduced = stringList.stream().reduce((value, combinedValue) -> combinedValue + " + " + value);
        reduced.ifPresent(System.out::println); // one + three + two


        //TASK1
        //https://github.com/netology-code/jd-homeworks/tree/master/streams/task1
        List<Integer> intList1 = Arrays.asList(1, 2, 5, 16, -1, -2, 0, 32, 3, 5, 8, 23, 4);
        java.util.stream.Stream<Integer> stream = intList1.stream();
        stream.filter(x -> x > 0)
                .filter(x -> x % 2 == 0)
                .sorted(Comparator.naturalOrder()).forEach(System.out::println);


        //TASK2
        //https://github.com/netology-code/jd-homeworks/tree/master/streams/task2
        

    }


    public static Optional<String> get() {
        return Optional.of("Hello");
    }
}
