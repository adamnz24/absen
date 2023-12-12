package absens.test.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
//
//@Data
//@AllArgsConstructor
//@Entity
//@NoArgsConstructor
//@Table(name = "absensi")
//public class Absensi {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "npm", referencedColumnName = "npm")
//    private User user;
//
//    private String status; // "checkin" atau "checkout"
//
//    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
//    private LocalDateTime timestamp;
//
//    // Tambahan atribut sesuai kebutuhan
//    private String location;
//}
//
//@Data
//@AllArgsConstructor
//@Entity
//@NoArgsConstructor
//@Table(name = "absensi")
//public class Absensi {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "npm", referencedColumnName = "npm")
//    private User user;
//
//    @Column(name = "status")
//    private String status; // "checkin" atau "checkout"
//
//    @Column(name = "timestamp", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
//    private LocalDateTime timestamp;
//
//    @Column(name = "location")
//    private String location;
//
//
//}
@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
@Table(name = "absensi")
//public class Absensi {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "npm", referencedColumnName = "npm")
//
//    private User user;
//
//
//    @Column(name = "status")
//    private String status; // "checkin" atau "checkout"
//
//    @Column(name = "location")
//    private String location;
//
//    @Column(name = "timestamp", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
//    private LocalDateTime timestamp;
//}

public class Absensi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "npm", referencedColumnName = "npm")
    public User user;


    public String status; // "checkin" atau "checkout"


    public LocalDateTime timestamp;


    public String location;

    // Tambahkan kolom checkoutStatus

    // Tambahkan kolom-kolom tambahan

    public String name;


    public String typemagang;


    public String kapal;


    public String divisi;


    public LocalDateTime waktuMasuk;


    public LocalDateTime waktuKeluar;
}


