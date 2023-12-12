package absens.test.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

//package absens.test.entity;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.util.ArrayList;
//import java.util.List;
//
//
////@Data
////@AllArgsConstructor
////@Entity
////@NoArgsConstructor
////@Table(name = "user")
////public class User {
////
////    @Id
////    @GeneratedValue(strategy = GenerationType.IDENTITY)
////
////    public int npm;
////
////    private String name;
////
////    public String typemagang;
////    public String kapal;
////    public String cabang;
////    public String divisi;
////
//////    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//////    private List<Absensi> absensiList = new ArrayList<>();
////}
//@Data
//@AllArgsConstructor
//@Entity
//@NoArgsConstructor
//@Table(name = "user")
//public class User {
//
//    @Id
//    @Column(name = "npm")
//    private int npm;
//
//    @Column(name = "name")
//    private String name;
//
//    @Column(name = "typemagang")
//    private String typemagang;
//
//    @Column(name = "kapal")
//    private String kapal;
//
//    @Column(name = "cabang")
//    private String cabang;
//
//    @Column(name = "divisi")
//    private String divisi;
//
//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<Absensi> absensiList = new ArrayList<>();
//}
//
//
@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
@Table(name = "user")
public class User {

    @Id
    @Column(name = "npm")
    private int npm;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Absensi> absensiList = new ArrayList<>();
}

