package com.buseni.adminpwa.books;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "books", path = "books")
public interface BookRepo extends PagingAndSortingRepository<Book, Long> {

}
