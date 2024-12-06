package com.nanapapais.names_autocomplete.domain.valueobjects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.BiPredicate;

public class MinHeap<T> {
    private List<T> heap;
    private BiPredicate<T, T> compare;

    public MinHeap(BiPredicate<T, T> compare) {
        this.heap = new ArrayList<>();
        this.compare = compare;
    }

    public MinHeap(BiPredicate<T, T> compare, List<T> initialData) {
        this.heap = new ArrayList<>();
        this.compare = compare;
        this.fromList(initialData);
    }

    public void push(T element) {
        this.heap.add(element);
        this.heapifyUp(this.heap.size() - 1);
    }

    public T pop() {
        if (this.heap.size() == 0) return null;
        var result = this.heap.get(0);
        var last = this.heap.get(this.heap.size() - 1);
        this.heap.remove(this.heap.size() - 1);
        if (this.heap.size() > 0) {
            this.heap.set(0, last);
            this.heapifyDown(0);
        }
        return result;
    }

    public int size() {
        return this.heap.size();
    }

    public List<T> toList() {
        List<T> result = new ArrayList<>();
        while (true) {
            var el = this.pop();
            if (el == null) break;
            result.add(el);
        }
        return result;
    }

    private void fromList(List<T> elements) {
        for (int index = 0; index < elements.size(); index++) {
            this.push(elements.get(index));
        }
    }

    private void heapifyUp(Integer index) {
        while (index > 0) {
            Integer parentIndex = (index - 1) / 2;
            if (this.compare.test(this.heap.get(index), this.heap.get(parentIndex))) break;
            Collections.swap(heap, index, parentIndex);
            index = parentIndex;
        }
    }

    private void heapifyDown(Integer index) {
        var length = this.heap.size();
        var heap = this.heap;
        while (true) {
            var left = 2 * index + 1;
            var right = 2 * index + 2;
            var smallest = index;
            if (left < length && this.compare.test(heap.get(smallest), heap.get(left))) {
                smallest = left;
            }
            if (right < length && this.compare.test(heap.get(smallest), heap.get(right))) {
                smallest = right;
            }
            if (smallest == index) break;
            Collections.swap(heap, index, smallest);
            index = smallest;
        }
    }
}
