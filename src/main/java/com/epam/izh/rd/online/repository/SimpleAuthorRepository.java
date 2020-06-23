package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;

/**
 * Интерфейс репозитория для хранения данных об авторах.
 * <p>
 * Необходимо:
 * 1) Создать в этом же пакете класс SimpleAuthorRepository
 * 2) Имплементировать им данный интерфейс
 * 3) Добавить все методы (пока можно не писать реализацию)
 * 4) Добавить в SimpleAuthorRepository приватное поле "Author[] authors" для хранения авторов
 * 5) Инициалазировать его пустым массивом
 * 6) Написать в классе SimpleAuthorRepository реализацию для всех методов (коллекции не используем, работаем только с массивами)
 */
public class SimpleAuthorRepository implements AuthorRepository {
    private Author[] authors = new Author[0];
    /**
     * Метод должен сохранять автора в массив authors.
     * Массив при каждом сохранении должен увеличиваться в размере ровно на 1.
     * То есть он ровно того размера, сколько сущностей мы в него сохранили.
     * <p>
     * Если на вход для сохранения приходит автор, который уже есть в массиве (сохранен), то автор не сохраняется и
     * метод возвращает false.
     * <p>
     * Можно сравнивать только по полному имени (имя и фамилия), считаем, что двух авторов
     * с одинаковыми именем и фамилией быть не может.
     * Подсказка - можно использовать для проверки метод findByFullName.
     * <p>
     * Если сохранение прошло успешно, метод должен вернуть true.
     */
    @Override
    public boolean save(Author author)
    {
        Author[] arr = new Author[count() + 1];
        if (findByFullName(author.getName(), author.getLastName()) == null) {
            for (int i = 0; i < count(); i++) {
                arr[i] = authors[i];
            }
            arr[count()] = author;
            authors = new Author[arr.length];
            for (int i = 0; i < arr.length; i++) {
                authors[i] = arr[i];
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * Метод должен находить в массиве authors автора по имени и фамилии (считаем, что двух авторов
     * с одинаковыми именем и фамилией быть не может.)
     * <p>
     * Если автор с таким именем и фамилией найден - возвращаем его, если же не найден, метод должен вернуть null.
     * @return
     */
    @Override
    public Author findByFullName(String name, String lastname) {

        for (Author item:
             authors) {
            if (name.equals(item.getName()) && lastname.equals(item.getLastName())) {
                return item;
            }
        }
        return null;
    }

    /**
     * Метод должен удалять автора из массива authors, если он там имеется.
     * Автора опять же, можно определять только по совпадению имении и фамилии.
     * <p>
     * Важно: при удалении автора из массива размер массива должен уменьшиться!
     * То есть, если мы сохранили 2 авторов и вызвали count() (метод ниже), то он должен вернуть 2.
     * Если после этого мы удалили 1 автора, метод count() должен вернуть 1.
     * <p>
     * Если автор был найден и удален, метод должен вернуть true, в противном случае, если автор не был найден, метод
     * должен вернуть false.
     */
    @Override
    public boolean remove(Author author) {
        Author [] arr;
        if (findByFullName(author.getName(), author.getLastName()) == null) {
            return false;
            }
        int s = count() - 1;
        arr = new Author[s];
        int cnt = -1;
        for (Author elem : authors) {
            if (elem.getName().equals(author.getName()) &&
                    elem.getLastName().equals(author.getLastName())) {
                continue;
            }
            ++cnt;
            arr[cnt] = elem;
        }
        authors = new Author[s];
        for (int i = 0; i < s; i++) {
            authors[i] = arr[i];
        }
        return true;
    }

    /**
     * Метод возвращает количество сохраненных сущностей в массиве authors.
     */
    @Override
    public int count() {
        int cnt = 0;
        for (Author author:
             authors) {
            ++cnt;
        }
        return cnt;
    }
}
