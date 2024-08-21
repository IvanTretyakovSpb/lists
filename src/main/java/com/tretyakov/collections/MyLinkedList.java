package com.tretyakov.collections;

// Реализовать LinkedList написать на методы JavaDoc, нельзя использовать методы Collection.
// Методы — add, remove, get, set, subList, size.

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Базовая реализация DoubleLinkedList для хранения списка.
 *
 * @author Третьяков Иван
 * @version 0.0.1
 */
public class MyLinkedList<T> implements Iterable<T> {
    private int size = 0;
    private Node<T> head = null;
    private Node<T> tail = null;

    /**
     * Внутренний класс для узлов связного списка
     *
     * @param <T> - тип хранимых данных в списке
     */
    private static class Node<T> {
        private T data;
        private Node<T> prev;
        private Node<T> next;

        public Node(T data, Node<T> prev, Node<T> next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }

        @Override
        public String toString() {
            return data.toString();
        }
    }

    /**
     * Возвращает размер списка (количество хранимых элементов)
     *
     * @return размер списка
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
    public boolean add(T element) {
        return addLast(element);
    }

    /**
     * Добавляет новый элемент в конец списка
     *
     * @param element добавляемый в список новый элемент
     * @return true, если добавление элемента прошло успешно
     */
    public boolean addLast(T element) {
        if (isEmpty()) {
            head = tail = new Node<T>(element, null, null);
        } else {
            tail.next = new Node<T>(element, tail, null);
            tail = tail.next;
        }
        size++;
        return true;
    }

    /**
     * Вставка элемента в начало списка (функционал и название метода
     * в соответствии с новым интерфейсом SequencedCollection
     *
     * @param element добавляемый новый элемент списка
     * @since version JDK 21
     */
    public boolean addFirst(T element) {
        if (isEmpty()) {
            head = tail = new Node<T>(element, null, null);
        } else {
            head.prev = new Node<T>(element, null, head);
            head = head.prev;
        }
        size++;
        return true;
    }

    /**
     * Удаляет первый элемент списка
     *
     * @return возвращает значение удаляемого элемента
     */
    public T removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("Empty list");
        }
        T data = head.data;
        head = head.next;
        --size;

        if (isEmpty()) {
            tail = null;
        } else {
            head.prev = null;
        }

        return data;
    }

    /**
     * Удаляет последний элемент списка
     *
     * @return возвращает значение удаляемого элемента
     */
    public T removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("Empty list");
        }

        T data = tail.data;
        tail = tail.prev;
        --size;

        if (isEmpty()) {
            head = null;
        } else {
            tail.next = null;
        }

        return data;
    }

    /**
     * Вспомогательный приватный метод для удаления конкретной ноды из списка
     *
     * @param node - удаляемая нода
     * @return - данные, хранящиеся в удаляемой ноде
     */
    private T remove(Node<T> node) {
        if (node.prev == null) {
            return removeFirst();
        }
        if (node.next == null) {
            return removeLast();
        }

        node.next.prev = node.prev;
        node.prev.next = node.next;
        T data = node.data;

        node.data = null;
        node = node.prev = node.next = null;
        --size;

        return data;
    }

    /**
     * Удаляет элемент списка по указанному индексу
     *
     * @param index - индекс удаляемого элемента
     * @return - значение удаляемого элемента
     */
    public T removeAt(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException();
        }

        int i;
        Node<T> trav;

        if (index < size / 2) {
            for (i = 0, trav = head; i != index; i++) {
                trav = trav.next;
            }
        } else {
            for (i = size - 1, trav = tail; i != index; i--) {
                trav = trav.prev;
            }
        }
        return remove(trav);
    }

    /**
     * Удаляет элемент списка по его значению
     *
     * @param obj - удаляемый элемент
     * @return true, если удаление прошло успешно
     */
    public boolean remove(Object obj) {
        Node<T> trav = head;

        if (obj == null) {
            for (trav = head; trav != null; trav = trav.next) {
                if (trav.data == null) {
                    remove(trav);
                    return true;
                }
            }
        } else {
            for (trav = head; trav != null; trav = trav.next) {
                if (obj.equals(trav.data)) {
                    remove(trav);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Возвращает первый элемент списка
     *
     * @return первый элемент списка
     * @throws NoSuchElementException если список пустой (не содержит элементов)
     */
    public T getFirst() {
        final Node<T> h = head;
        if (h == null)
            throw new NoSuchElementException();
        return h.data;
    }

    /**
     * Возвращает последний элемент списка
     *
     * @return последний элемент списка
     * @throws NoSuchElementException если список пустой (не содержит элементов)
     */
    public T getLast() {
        final Node<T> t = tail;
        if (t == null)
            throw new NoSuchElementException();
        return t.data;
    }

    /**
     * Возвращает элемент из указанной позиции в списке
     *
     * @param index индекс возвращаемого элемента
     * @return элемент списка, расположенный на указанной позиции
     * @throws IndexOutOfBoundsException если указанный индекс < 0 или >= size (размер списка)
     */
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(
                    String.format("Incorrect index: %d, LinkedList size: %d", index, size));
        }
        return nodeByIndex(index).data;
    }

    /**
     * Служебный метод для получения ноды по заданному индексу
     *
     * @param index индекс искомой ноды
     * @return нода, расположенная по указанному индексу
     */
    private Node<T> nodeByIndex(int index) {
        if (index < (size >> 1)) {
            Node<T> x = head;
            for (int i = 0; i < index; i++)
                x = x.next;
            return x;
        } else {
            Node<T> x = tail;
            for (int i = size - 1; i > index; i--)
                x = x.prev;
            return x;
        }
    }

    /**
     * Возвращает индекс элемента в списке
     *
     * @param obj значение искомого элемента
     * @return индекс элемента, если элемент отсутствует, то возвращается - 1
     */
    public int indexOf(Object obj) {
        int index = 0;
        Node<T> trav = head;

        if (obj == null) {
            for (; trav != null; trav = trav.next, index++) {
                if (trav.data == null) {
                    return index;
                }
            }
        } else {
            for (; trav != null; trav = trav.next, index++) {
                if (obj.equals(trav.data)) {
                    return index;
                }
            }
        }
        return -1;
    }

    /**
     * Заменяет элемент списка в указанной позиции на новый элемент
     *
     * @param index   индекс элемента для замены
     * @param element новое значение элемента в указанной позиции
     * @return элемент списка, ранее расположенный в указанной позиции
     * @throws IndexOutOfBoundsException если указанный индекс < 0 или >= size (размер списка)
     */
    public T set(int index, T element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(
                    String.format("Incorrect index: %d, LinkedList size: %d", index, size));
        }
        Node<T> x = nodeByIndex(index);
        T oldValue = x.data;
        x.data = element;
        return oldValue;
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
    public MyLinkedList<T> subList(int fromIndex, int toIndex) {
        if (fromIndex < 0 || toIndex > size) {
            throw new IndexOutOfBoundsException();
        }
        if (fromIndex > toIndex) {
            throw new IllegalArgumentException();
        }
        MyLinkedList<T> sublist = new MyLinkedList<>();
        for (int i = fromIndex; i < toIndex; i++)
            sublist.add(this.get(i));
        return sublist;
    }

    /**
     * Проверяет, содержится ли указанные элемент в списке
     *
     * @param obj значение проверяемого элемента
     * @return true, если список содержит проверяемый элемент
     */
    public boolean contains(Object obj) {
        return indexOf(obj) != -1;
    }

    /**
     * Возвращает итератор для указанного списка
     *
     * @return - итератор для данного списка
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> trav = head;

            @Override
            public boolean hasNext() {
                return trav != null;
            }

            @Override
            public T next() {
                T data = trav.data;
                trav = trav.next;
                return data;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    /**
     * Возвращает строковое представление данного списка
     *
     * @return строковое представление списка
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        Node<T> trav = head;
        while (trav != null) {
            sb.append(trav.data).append(", ");
            trav = trav.next;
        }
        sb.append(" ]");
        return sb.toString();
    }
}
