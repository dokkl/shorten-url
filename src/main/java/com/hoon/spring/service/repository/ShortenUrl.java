package com.hoon.spring.service.repository;

import lombok.*;

import javax.persistence.*;

/**
 * Created by babybong on 2018. 1. 16..
 */
@Table(name = "shortenUrls")
@Entity
@Setter
@Getter
@ToString
public class ShortenUrl {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_seq")
    @SequenceGenerator(name="id_seq", sequenceName = "ID_SEQ", allocationSize = 1)
    private Long id;
    private String shortenUrl;
    private String originUrl;
}
