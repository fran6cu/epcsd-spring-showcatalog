package edu.uoc.epcsd.showcatalog.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;
import java.util.Date;


@Entity
@ToString
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode

public class Performance implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date")
    private Date date;

    @JsonFormat(pattern = "HH:mm")
    @Column(name = "time")
    private Time time;

    @Column(name = "streamingurl")
    private String streamingUrl;

    @Column(name = "remainingseats")
    private Integer remainingSeats;

    @Column(name = "status")
    private String status;


}
