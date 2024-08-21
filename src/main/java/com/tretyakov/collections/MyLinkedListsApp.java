package com.tretyakov.collections;

public class MyLinkedListsApp {
    public static void main(String[] args) {
        MyLinkedList<Long> linkedList = new MyLinkedList<>();
        linkedList.add(77L);
        linkedList.add(10L);
        linkedList.add(-9L);
        System.out.println(linkedList);

        linkedList.addFirst(55L);
        linkedList.addLast(11L);
        System.out.println(linkedList);

        System.out.println("Первый элемент списка: " + linkedList.getFirst());
        System.out.println("Последний элемент списка: " + linkedList.getLast());
        System.out.println("Элемент списка с индексом 2: " + linkedList.get(2));

        System.out.println("Размер списка: " + linkedList.size());
        System.out.println("Список пустой? Ответ: " + linkedList.isEmpty());


        linkedList.set(1, 999L);
        System.out.println(linkedList);
        linkedList.addLast(200L);
        System.out.println("Содержит элемент 999? Ответ: " + linkedList.contains(999L));


        System.out.println("Удален элемент 999: " + linkedList.remove(999L));
        System.out.println(linkedList);

        linkedList.add(60L);
        linkedList.add(30L);
        linkedList.add(90L);
        linkedList.add(44L);
        linkedList.add(88L);
        linkedList.add(111L);
        System.out.println(linkedList);
        System.out.println("subList(2, 7): " + linkedList.subList(2, 7));

        System.out.println("Удален элемент с индексом 2: " + linkedList.removeAt(2));
        System.out.println("Содержит элемент 9? Ответ: " + linkedList.contains(9L));
        System.out.println("Содержит элемент 10? Ответ: " + linkedList.contains(10L));
    }
}
