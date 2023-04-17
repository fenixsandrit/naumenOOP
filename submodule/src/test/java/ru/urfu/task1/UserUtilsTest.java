package ru.urfu.task1;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Тесты для {@link UserUtils#findDuplicates(Collection, Collection)}
 */
public class UserUtilsTest
{
    /**
     * Проверка того, что результат двух пустых коллекций, пустая коллекция
     */
    @Test
    public void emptyCollectionsTest()
    {
        assert UserUtils.findDuplicates(Collections.emptyList(), Collections.emptyList()).size() == 0;
    }

    /**
     * Проверка того, что размер результирующего коллекции равен 1, когда на входе две одноэлементные колекции с одиновым
     * пользователем{@link User}
     */
    @Test
    public void oneElementTheSametTest()
    {
        User u1 = new User("test", "test", new byte[]{1,2,3});
        User u2 = new User("test", "test", new byte[]{1,2,3});

        assert UserUtils.findDuplicates(Collections.singleton(u1), Collections.singleton(u2)).size() == 1;
    }

    /**
     * Проверка того, что размер результирующей коллекции равен 0, когда на входе одна коллекци пустая, другая нет
     */
    @Test
    public void firstCollectionEmptySecondCollectionFilledTest()
    {
        assert UserUtils.findDuplicates(Collections.emptyList(),
                Collections.singleton(new User("test", "test", new byte[]{1,2,3}))).size() == 0;
    }

    /**
     * Проверка того, что размер результирующей коллекции равен 0, когда на входе одноэлементные коллекции но параметры
     * пользователя{@link User} разные
     */
    @Test
    public void oneElemenNotTheSameTest()
    {
        User u1 = new User("test", "test", new byte[]{1,2,3});
        User u2 = new User("test1", "test1", new byte[]{1,2,3,4});

        assert UserUtils.findDuplicates(Collections.singleton(u1), Collections.singleton(u2)).size() == 0;
    }

    /**
     * Проверка того, что размер результирующей коллекции равен 1, когда на входе многоэлементные коллекции, в этих
     * коллекциях только один пользователь {@link User} одинаковы, остальные разные
     */
    @Test
    public void manyElementInBothCollectionButOneTheSameTest()
    {
        List<User> coll1 = new ArrayList<>();
        List<User> coll2 = new ArrayList<>();

        User sameUserForColl1 = new User("test", "test", new byte[]{1,2,3});
        User sameUserForColl2 = new User("test", "test", new byte[]{1,2,3});

        for (int i = 0; i < 100; i++)
        {
            coll1.add(new User("coll1 " + i,"coll1 " + i, new byte[]{1}));
            coll2.add(new User("coll2 " + i,"coll3 " + i, new byte[]{1}));
        }

        coll1.add(sameUserForColl1);
        coll2.add(sameUserForColl2);

        List<User> resultList = UserUtils.findDuplicates(coll1, coll2);

        assert resultList.size() == 1;
        assert resultList.get(0).equals(sameUserForColl1);
        assert resultList.get(0).equals(sameUserForColl2);
    }

    /**
     * Проверка того, что размер результирующей коллекции равен размеру коллекций которые подаются на входе,
     * в обеих входных коллекциях одиноковое количество пользователей{@link User} и они все равны
     */
    @Test
    public void manyElementInBothCollectionAllTheSameTest()
    {
        List<User> coll1 = new ArrayList<>();
        List<User> coll2 = new ArrayList<>();

        for (int i = 0; i < 100; i++)
        {
            coll1.add(new User("user " + i,"user " + i, new byte[]{1}));
            coll2.add(new User("user " + i,"user " + i, new byte[]{1}));
        }

        List<User> resultList = UserUtils.findDuplicates(coll1, coll2);

        assert resultList.size() == coll1.size();
        assert resultList.size() == coll2.size();
    }

    /**
     * Проверка того, что размер результирующей коллекции равен 0, когда на входе одноэлементные коллекции,
     * с одним и тем же пользователем, но хэши пароля разные
     */
    @Test
    public void sigletonCollectionsNotEqualsBecausePasswordHashNotTheSameTest()
    {
        User u1 = new User("test", "test", new byte[]{1,2});
        User u2 = new User("test", "test", new byte[]{1,2,3});

        assert UserUtils.findDuplicates(Collections.singleton(u1), Collections.singleton(u2)).size() == 0;
    }

    /**
     * Проверка того, что размер результирующей коллекции равен 0, когда на входе одноэлементные коллекции,
     * с одним и тем же пользователем, но почты разные
     */
    @Test
    public void sigletonCollectionsNotEqualsBecauseEmailNotTheSameTest()
    {
        User u1 = new User("test", "test", new byte[]{1,2,3});
        User u2 = new User("test", "test123", new byte[]{1,2,3});

        assert UserUtils.findDuplicates(Collections.singleton(u1), Collections.singleton(u2)).size() == 0;
    }

    /**
     * Проверка того, что размер результирующей коллекции равен 0, когда на входе одноэлементные коллекции,
     * с одним и тем же пользователем, но имена разные
     */
    @Test
    public void sigletonCollectionsNotEqualsBecauseUsernameNotTheSameTest()
    {
        User u1 = new User("test", "test", new byte[]{1,2,3});
        User u2 = new User("test1", "test", new byte[]{1,2,3});

        assert UserUtils.findDuplicates(Collections.singleton(u1), Collections.singleton(u2)).size() == 0;
    }
}
