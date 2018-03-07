package com.reaier.engking.domain.view;


import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;


@Entity
@Immutable
@Subselect("select * from source_words_view")
public class SourceWords {
    @Id
    Integer id;

}
