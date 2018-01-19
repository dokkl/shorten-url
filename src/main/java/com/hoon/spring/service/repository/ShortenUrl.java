package com.hoon.spring.service.repository;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by babybong on 2018. 1. 16..
 */
@Table(name = "shortenUrls")
@Entity
@Setter
@Getter
@ToString
@SequenceGenerator(name="id_seq", sequenceName = "ID_SEQ", initialValue = 100000001, allocationSize = 1)
public class ShortenUrl {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_seq")
    private Long id;

    @Column(length = 10)
    private String shortenUrl;

    @Column(length = 255)
    private String originUrl;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedAt;
}