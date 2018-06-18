package com.reaktorlabs.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author ernst
 */
@Entity
@Table(name = "movierating")
public class MovieRating implements Serializable {
    //id, movieid, userid

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    
    
}
