package com.jifenke.rest.model;

import java.util.List;

/**
 * @author ZM.Wang
 */
public class Collection<T> {

    private final int numberOfElements;
    private final List<T> content;

    public Collection(List<T> list) {
        this.content = list;
        this.numberOfElements = list.size();
    }

    public List<T> getContent() {
        return content;
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }
}
