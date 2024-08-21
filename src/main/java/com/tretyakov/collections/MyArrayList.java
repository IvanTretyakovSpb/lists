package com.tretyakov.collections;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

// Реализовать ArrayList написать на методы JavaDoc, нельзя использовать методы Collection.
// Методы — add, remove, get, set, subList, size.

/**
 * Базовая реализация ArrayList с изменяемым размером внутреннего массива для хранения списка.
 * Допускается хранение элементов null.
 *
 * @author Третьяков Иван
 * @version 0.0.1
 */
public class MyArrayList<E> implements Iterable<E> {
    // Default initial capacity
    public static final int INITIAL_CAPACITY = 10;
    public static final double COEFFICIENT_GROW_CAPACITY = 1.5;
    private int size;
    private E[] elements;

    /**
     * Конструктор по умолчанию для инициализации пустого списка
     */
    @SuppressWarnings("unchecked")
    public MyArrayList() {
        elements = (E[]) new Object[INITIAL_CAPACITY];
    }

    /**
     * Конструктор для создания списка заданной вместимости
     *
     * @param capacity вместимость создаваемого списка (целое неотрицательное число)
     * @throws IllegalArgumentException в случае некорректного указания размерности списка
     */
    @SuppressWarnings("unchecked")
    public MyArrayList(int capacity) {
        if (capacity >= 0) {
            elements = (E[]) new Object[capacity];
        } else {
            throw new IllegalArgumentException(String.format("Incorrect capacity: %d", capacity));
        }
    }

    /**
     * Возвращает размер списка, т.е. количество элементов в указанном списке
     *
     * @return количество элементов в списке (int)
     */
    public int size() {
        return size;
    }

    /**
     * Проверяет список на отсутствие элементов
     *
     * @return true, если список не содержит элементов, иначе false
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Добавляет новый элемент в конец списка
     *
     * @param element добавляемый в список новый элемент
     * @return true, если добавление элемента прошло успешно
     */
    public boolean add(E element) {
        if (size == elements.length) {
            int newCapacity = (int) (elements.length * COEFFICIENT_GROW_CAPACITY + 1);
            ensureCapacity(newCapacity);
        }
        elements[size++] = element;
        return true;
    }

    /**
     * Вспомогательный приватный метод для увеличения емкости списка
     * с копированием ранее содержащихся элементов в новый массив, лежащий в основе списка
     *
     * @param newCapacity новая размерность (вместимость) списка
     */
    private void ensureCapacity(int newCapacity) {
        elements = Arrays.copyOf(elements, newCapacity);
    }

    /**
     * Добавление (вставка) нового элемента в конкретную позицию списка со сдвигом вправо предыдущих элементов
     *
     * @param index   индекс для вставки нового элемента в список
     * @param element вставляемый в список элемент
     */
    public void add(int index, E element) {
        if (size == elements.length) {
            int newCapacity = (int) (elements.length * COEFFICIENT_GROW_CAPACITY + 1);
            ensureCapacity(newCapacity);
        }
        System.arraycopy(elements, index,
                elements, index + 1,
                size - index);
        elements[index] = element;
        size++;
    }

    /**
     * Вставка элемента в начало списка (функционал и название метода
     * в соответствии с новым интерфейсом SequencedCollection
     *
     * @param element добавляемый новый элемент списка
     * @since version JDK 21
     */
    public void addFirst(E element) {
        add(0, element);
    }

    /**
     * Вставка элемента в конец списка (функционал и название метода
     * в соответствии с новым интерфейсом SequencedCollection
     *
     * @param element добавляемый элемент
     * @since version JDK 21
     */
    public void addLast(E element) {
        add(element);
    }

    /**
     * Возвращает элемент из указанной позиции в списке
     *
     * @param index индекс возвращаемого элемента
     * @return элемент списка, расположенный на указанной позиции
     * @throws IndexOutOfBoundsException если указанный индекс < 0 или >= size (размер списка)
     */
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(
                    String.format("Incorrect index: %d, ArrayList size: %d", index, size));
        }
        return elements[index];
    }

    /**
     * Возвращает первый элемент списка
     *
     * @return первый элемент списка
     * @throws NoSuchElementException если список пустой (не содержит элементов)
     */
    public E getFirst() {
        if (size == 0) {
            throw new NoSuchElementException();
        } else {
            return elements[0];
        }
    }

    /**
     * Возвращает последний элемент списка
     *
     * @return последний элемент списка
     * @throws NoSuchElementException если список пустой (не содержит элементов)
     */
    public E getLast() {
        int last = size - 1;
        if (last < 0) {
            throw new NoSuchElementException();
        } else {
            return elements[last];
        }
    }

    /**
     * Заменяет элемент списка в указанной позиции на новый элемент
     *
     * @param index индекс элемента для замены
     * @param value новое значение элемента в указанной позиции
     * @return элемент списка, ранее расположенный в указанной позиции
     * @throws IndexOutOfBoundsException если указанный индекс < 0 или >= size (размер списка)
     */
    public E set(int index, E value) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(
                    String.format("Incorrect index: %d, ArrayList size: %d", index, size));
        }
        E oldValue = elements[index];
        elements[index] = value;
        return oldValue;
    }

    /**
     * Удаляет элемент с указанным индексом из списка со сдвигом последующих элементов влево
     *
     * @param index индекс удаляемого элемента
     * @return удаленный из списка элемент
     * @throws IndexOutOfBoundsException если указанный индекс < 0 или >= size (размер списка)
     */
    public E remove(int index) {
        Objects.checkIndex(index, size);
        E removedItem = elements[index];
        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }
        size--;
        return removedItem;
    }

    /**
     * Возвращает новый список из части элементов указанного списка
     * с fromIndex (включительно) до toIndex (не включается)
     *
     * @param fromIndex начальный индекс подсписка
     * @param toIndex   индекс, до которого будет создаваться подсписок
     * @return список, содержащий элементы первоначального списка в указанном интервале
     * @throws IndexOutOfBoundsException если указаны некорректные индексы
     *                                   (fromIndex < 0 || toIndex > size || fromIndex > toIndex)
     * @throws IllegalArgumentException  если индексы указаны в неверном порядке (fromIndex > toIndex)
     */
    public MyArrayList<E> subList(int fromIndex, int toIndex) {
        if (fromIndex < 0 || toIndex > size) {
            throw new IndexOutOfBoundsException();
        }
        if (fromIndex > toIndex) {
            throw new IllegalArgumentException();
        }
        MyArrayList<E> newList = new MyArrayList<E>(toIndex - fromIndex);
        System.arraycopy(elements, fromIndex,
                newList.elements, 0,
                toIndex - fromIndex);
        return newList;
    }

    /**
     * Уменьшает вместимость списка до количества хранящихся в нём элементов
     */
    public void trimToSize() {
        if (size < elements.length) {
            ensureCapacity(size);
        }
    }

    /**
     * Возвращает итератор для данного списка
     *
     * @return итератор для указанного списка
     */
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int current;

            public boolean hasNext() {
                return current < size;
            }

            public E next() {
                if (!hasNext()) throw new java.util.NoSuchElementException();
                return elements[current++];
            }

            public void remove() {
                MyArrayList.this.remove(--current);
            }
        };
    }

    /**
     * Возвращает строковое представление списка
     *
     * @return строковое представление списка элементов
     */
    @Override
    public String toString() {
        return "MyArrayList " + Arrays.toString(elements);
    }
}
