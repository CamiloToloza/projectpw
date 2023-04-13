package com.northsouthairline.projectpw.entities;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "booking")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    private BookingStatus bookingStatus;

    @OneToOne(mappedBy = "booking")
    @JoinColumn(name="flight_id")
    private Flight outboundFlight;

    @Column(nullable = false)
    private Boolean checkedIn;

    @ManyToMany(mappedBy = "users")
    @JoinTable(name="Users_Bookings",
            joinColumns=@JoinColumn(name="booking_id", referencedColumnName="id" ),
            inverseJoinColumns=@JoinColumn(name="usert_id", referencedColumnName="id"))
    private Set<User> customers = new HashSet<>();

    @Column(nullable = false)
    private String createdAt;

    @Column (nullable = false, unique=true)
    private String bookingReference;

    public void addCustomer(User user){
        customers.add(user);
    }

}
