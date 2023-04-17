package ru.urfu.task1;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Утилитарный класс для работы с {@link User}
 */
public class UserUtils
{
    /**
     * Метод, который возвращает дубликаты: пользователей{@link User}, которые есть в обеих коллекциях.
     * Одинаковыми считаем пользователей, у которых совпадают все 3 поля: username, email, passwordHash.
     * Дубликаты внутри коллекций collA, collB можно не учитываются
     *
     * Сложность O(n + m + min(n,m)), где n - размер collA, m - размер CollB
     *
     * n - считывание collA в HastSet. Добавление в HashSet, работает за O(1),
     * если у добавляемого объекта грамотна написаны методы equals() и hashCode(), в нашем случае эти методы сгенерированы средой разработки,
     * соответственно написаны грамотно, следственно вероятность коллизиции крайне мала, следственно O(1).
     *
     * m - итерация по collB и проверка на содержание в collA(O(1) - т.к. HashSet)
     * Проверка на содержание элемента в HashSet работает за O(1), т.к. читать выше(аналогично добавлению)
     *
     * min(n,m) - преобразование в List
     *
     * @param collA - 1-ая коллекция пользователей{@link User}
     * @param collB - 2-ая коллекция пользователей{@link User}
     * @return список пользователей, которые есть в обеих коллекциях
     */
    public static List<User> findDuplicates(Collection<User> collA, Collection<User> collB)
    {
        Set<User> result = new HashSet<>(collA);

        return collB.stream().filter(result::contains).collect(Collectors.toList());
    }
}
