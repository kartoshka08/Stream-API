import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        //Из созданной коллекции persons для каждого задания создавайте новый стрим методом stream() и
        // далее применяйте к нему ряд промежуточных операций и одну терминальную:

        //1. Найти количество несовершеннолетних (т.е. людей младше 18 лет).
        //Для поиска несовершеннолетних используйте промежуточный метод filter() и терминальный метод count().

        long countAge = persons.stream().filter(x -> x.getAge() < 18).count();
        System.out.println("количество несовершеннолетних: " + countAge);


        //2. Получить список фамилий призывников (т.е. мужчин от 18 и до 27 лет).
        //Для получения списка призывников потребуется применить несколько промежуточных методов filter(),
        // а также для преобразования данных из Person в String (так как нужны только фамилии) используйте метод map().
        // Так как требуется получить список List<String> терминальным методом будет collect(Collectors.toList()).

        System.out.println("список фамилий призывников: ");
        persons.stream()
                .filter(s -> s.getSex() == Sex.MAN)
                .filter(x -> x.getAge() > 17).filter(x -> x.getAge() < 28)
                .map((Person t) -> t.getFamily().toString())
                .collect(Collectors.toList())
                .forEach(System.out::println);


        //3. Получить отсортированный по фамилии список потенциально работоспособных людей с высшим образованием в
        // выборке (т.е. людей с высшим образованием от 18 до 60 лет для женщин и до 65 лет для мужчин).
        //Для получения отсортированного по фамилии списка потенциально работоспособных людей
        //с высшим образованием в выборке (т.е. людей с высшим образованием от 18 до 60 лет
        //для женщин и до 65 лет для мужчин).
        //Для получения отсортированного по фамилии списка потенциально работоспособных людей с высшим образованием
        // необходимо применить ряд промежуточных методов filter(), метод sorted() в который нужно будет положить
        // компаратор по фамилиям Comparator.comparing(). Завершить стрим необходимо методом collect().


        System.out.println("список работоспособных: ");

        persons.stream()
                .filter(e -> e.getEducation() == Education.HIGHER)
                .filter(x -> x.getAge() > 17)
                .filter(s -> (Sex.MAN == s.getSex() && s.getAge() <= 65) || (Sex.WOMAN == s.getSex() && s.getAge() <= 60))
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList())
                .forEach(System.out::println);


        System.out.println("ALL PEOPLE: ");
        for (int i = 0; i < persons.size(); i++) System.out.println(persons.toArray()[i]);

    }

//    public static List<String> merge(List<String> a, List<String> b) {
//        List<String> fin = new ArrayList<>();
//        for (int i = 0; i < a.size(); i++) {
//            fin.add(a.get(i));
//        }
//        for (int j = 0; j < b.size(); j++) {
//            fin.add(b.get(j));
//        }
//        fin = fin.stream()
//
//        return fin;
//    }

}

//создадим два потока, для женщин
//        List<String> surnamesWoman = persons.stream()
//                .filter(x -> x.getAge() > 17)
//                .filter(educ -> educ.getEducation() == Education.HIGHER)
//                .filter(s -> s.getSex() == Sex.WOMAN)
//                .filter(x -> x.getAge() < 61)
//
//                .collect(Collectors.toList());
//
//        //и для мужчин
//        List<String> surnamesMan = persons.stream()
//                .filter(x -> x.getAge() > 17)
//                .filter(educ -> educ.getEducation() == Education.HIGHER)
//                .filter(s -> s.getSex() == Sex.MAN)
//                .filter(x -> x.getAge() < 66)
//                .map((Person t) -> t.getFamily().toString())
//                .collect(Collectors.toList());
//
//        //объединим два потока в один, с помощью метода merge (в нем же и отсортируем по фамилиям)
//        List<String> surnamesAll = merge(surnamesMan, surnamesWoman);