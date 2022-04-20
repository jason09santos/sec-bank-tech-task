package com.example.demo;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.springframework.context.annotation.Bean;

public class BookService {
    BookDto bookDto;

    volatile Hashtable mapGenres = null;
    volatile Hashtable mapBooks = null;
    volatile Hashtable map = null;

    @Bean
    BookService service() {
        return new BookService(dto());
    }

    @Bean
    BookDto dto() {
        return new BookDto();
    }

    public BookService(BookDto bookDto) {
        this.bookDto = bookDto;
    }

    public List getAllGenres() {
        final LinkedList list = new LinkedList<>();
        final Set set = mapGenres.keySet();
        for (Iterator iterator = set.iterator(); iterator.hasNext(); ) {
            Object o = iterator.next();
            final Object o1 = mapGenres.get(o);
            list.add(o1);
        }
        if (list.isEmpty()) {
            return null;
        }
        return list;
    }

    public Hashtable getBooksByGenre(String name) {
        map = new Hashtable();
        final Set set = mapGenres.keySet();
        for (Iterator iterator = set.iterator(); iterator.hasNext(); ) {
            Object g = iterator.next();
            if (name.equals(g)) {
                for (Iterator iter = mapBooks.values().iterator(); iter.hasNext(); ) {
                    Object b = iter.next();
                    if (name == ((Book) b).getGenre().getName()) {
                        map.put(((Book) b).getName(), name);
                    }
                }
            }
        }
        return map;
    }

    private void getBooks() {
        mapBooks = new Hashtable();
        List<Book> books = bookDto.getBooks();
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            mapBooks.put(book.getName(), book);
        }
    }

    private void getGenres() {
        mapGenres = new Hashtable();
        List<Genre> genres = bookDto.getGenres();
        for (int i = 0, genresSize = genres.size(); i < genresSize; i++) {
            Genre genre = genres.get(i);
            mapGenres.put(genre.getName(), genre);
        }
    }
}