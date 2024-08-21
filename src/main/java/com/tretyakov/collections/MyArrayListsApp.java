package com.tretyakov.collections;

public class MyArrayListsApp {
    public static void main(String[] args) {
        MyArrayList<Integer> arrayList = new MyArrayList<>();
        arrayList.add(77);
        arrayList.add(10);
        arrayList.add(-9);
        System.out.println(arrayList);

        arrayList.add(1, 55);
        arrayList.addFirst(-20);
        System.out.println(arrayList);

        System.out.println("Первый элемент списка: " + arrayList.getFirst());
        System.out.println("Последний элемент списка: " + arrayList.getLast());
        System.out.println("Элемент списка с индексом 2: " + arrayList.get(2));

        System.out.println("Размер списка: " + arrayList.size());
        System.out.println("Список пустой? Ответ: " + arrayList.isEmpty());

        arrayList.trimToSize();
        System.out.println("После trimToSize(): " + arrayList);

        arrayList.set(1, 999);
        System.out.println(arrayList);

        System.out.println("Удален элемент с индексом 1: " + arrayList.remove(1));
        System.out.println(arrayList);

        arrayList.add(60);
        arrayList.add(30);
        arrayList.add(90);
        arrayList.add(44);
        arrayList.add(88);
        arrayList.add(111);
        System.out.println(arrayList);
        System.out.println("subList(2, 7): " + arrayList.subList(2, 7));
    }
}
