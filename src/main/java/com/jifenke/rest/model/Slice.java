package com.jifenke.rest.model;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ZM.Wang
 */
public class Slice<T> {

    private long totalElements;
    private int totalPages;
    private boolean hasNext;
    private boolean hasPrevious;
    private boolean isFirst;
    private boolean isLast;
    private int number;
    private int size;
    private int numberOfElements;
    private List<T> content;

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public boolean isHasNext() {
        return hasNext;
    }

    public void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }

    public boolean isHasPrevious() {
        return hasPrevious;
    }

    public void setHasPrevious(boolean hasPrevious) {
        this.hasPrevious = hasPrevious;
    }

    public boolean isFirst() {
        return isFirst;
    }

    public void setFirst(boolean first) {
        isFirst = first;
    }

    public boolean isLast() {
        return isLast;
    }

    public void setLast(boolean last) {
        isLast = last;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements(int numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public <S> Slice<S> map(Converter<T, S> converter) {
        Slice<S> result = new Slice<>();
        result.setContent(getConvertedContent(converter));
        result.setFirst(this.isFirst());
        result.setLast(this.isLast());
        result.setHasPrevious(this.isHasPrevious());
        result.setHasNext(this.isHasNext());
        result.setNumber(this.getNumber());
        result.setSize(this.getSize());
        result.setNumberOfElements(this.getNumberOfElements());
        result.setTotalElements(this.getTotalElements());
        result.setTotalPages(this.getTotalPages());
        return result;
    }

    private <S> List<S> getConvertedContent(Converter<T, S> converter) {
        Assert.notNull(converter, "Converter must not be null!");
        List<S> result = new ArrayList<>(this.getContent().size());
        result.addAll(content.stream().map(converter::convert).collect(Collectors.toList()));
        return result;
    }
}
