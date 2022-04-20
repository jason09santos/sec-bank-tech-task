package com.example.demo;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.context.annotation.Bean;

 

public class BookService {
    BookDto bookDto;

    volatile HashMap<String, Genre> mapGenres = null;
    volatile HashMap<String, Book> mapBooks = null;
    volatile HashMap<String, String> map = null;

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

    public List<Genre> getAllGenres() {
        final LinkedList<Genre> list = new LinkedList<>();
        final Set<String> set = mapGenres.keySet();
        for (String o : set) {
        	final Genre o1 = mapGenres.get(o);
            list.add(o1);
		}
        
        if (list.isEmpty()) {
            return Collections.emptyList();
        }
        
        return list;
    }

    public Map<String, String> getBooksByGenre(String name) {
        map = new HashMap<>();
        final Set<String> set = mapGenres.keySet();
        for (String g : set) {
        	 if (name.equals(g)) {
        		for (Book b : mapBooks.values()) {
        			if (name == b.getGenre().getName()) {
                        map.put(b.getName(), name);
                    }
				}
             }
		}
        
        return map;
    }
}