package ru.urfu.task2;

import org.apache.commons.lang3.tuple.Pair;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * Класс для работы с текстовым файлом.
 * Класс собирает все встречающиеся слова и считает для каждого из них количество раз, сколько слово встретилось.
 * Морфологию не учитывает.
 * Также считает наиболее используемые (TOP) 10 слов и наименее используемые (LAST) 10 слов
 */
public class WordsCounter
{
    //Словарь, где ключ = слово, а значение = количество раз, сколько слово встретилось
    private Map<String, Integer> wordsCount;

    //Очередь с приоритетом по наиболее встречаемым словам.
    private Queue<Pair<String, Integer>> maxTop;

    //Очередь с приоритетом по наименее встречаемым словам.
    private Queue<Pair<String, Integer>>  minTop;

    //Название файла
    private String fileName;

    public WordsCounter(String fileName)
    {
        this.fileName = fileName;
        wordsCount = new HashMap<>();

        maxTop = new PriorityQueue<>((a, b) -> a.getRight() - b.getRight());
        minTop = new PriorityQueue<>((a, b) -> b.getRight() - a.getRight());
    }

    /**
     * Главная функция
     * Считает символы из файла, преобразует их в слова, считает количество раз, сколько слово встретилось
     * Считает наиболее встречаемые слова
     * Считает наименее встречаемые слова
     *
     * Сложность O(n + 2*n*log(n)) n - считывание всех символов, преобразовние их в слова и подсчет количества раз, сколько слово встретилось
     * Подсчет количества делается за O(1) - т.к. работаем с HashSet, где основные операции O(1).
     * После итерируемся по всем словам и засовываем их в очередь с приоритетом для наиболее и наименее встречаюшихся слов = n * 2 * log(n)
     * Добавление в очередь с приоритетом(O(log(n)))
     */
    public void readWords()
    {
        try
        {
            Files.lines(Paths.get(fileName))
                    .flatMap(line -> parseLineToWords(line).stream())
                    .forEach(w -> wordsCount.put(w, wordsCount.containsKey(w) ? wordsCount.get(w) + 1 : 1));

            wordsCount.keySet().stream().forEach(k -> {
                Pair<String, Integer> p = Pair.of(k, wordsCount.get(k));
                maxTop.add(p);
                minTop.add(p);
            });

        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    /**
     * Функция принимает строку считанную из файла и парсит её на слова(сложность O(n))
     * @param line - строка из файла
     * @return коллекция слов
     */
    private Collection<String> parseLineToWords(String line)
    {
        List<String> result = new ArrayList<>();
        StringBuffer stringBuffer = new StringBuffer();

        for (int i = 0; i < line.length() ; i++)
        {
            if(Character.isLetter(line.charAt(i)))
            {
                stringBuffer.append(line.charAt(i));
            }
            else if(stringBuffer.length() != 0)
            {
                result.add(stringBuffer.toString().toLowerCase());
                stringBuffer.setLength(0);
            }
        }

        if(stringBuffer.length() != 0)
        {
            result.add(stringBuffer.toString().toLowerCase());
        }

        return result;
    }

    /**
     * Получение списка 10 самых используемых слов
     * @return список
     */
    public List<String> getTopMax()
    {
        return getTop(maxTop);
    }

    /**
     * Получение списка 10 наименее используемых слов
     * @return список
     */
    public List<String> getTopMin()
    {
        return getTop(minTop);
    }

    /**
     * Получение списко 10 наименее/наиболее используемых слов (вынес в отдельный метод из-за дублирования)
     * @param queue - очередь с наиболее{@link WordsCounter#maxTop}/наименее{@link WordsCounter#minTop} используемыми словами
     * @return - список
     */
    private List<String> getTop(Queue<Pair<String, Integer>> queue)
    {
        List<String> result = new ArrayList<>();
        Iterator<Pair<String, Integer>> it = queue.iterator();

        for (int i = 0; i < 10 && it.hasNext(); i++)
        {
            result.add(it.next().getLeft());
        }

        return result;
    }
}
