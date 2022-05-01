//package com.winchesters.devopsify.model;
//
//import com.fasterxml.jackson.annotation.JsonIdentityInfo;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import org.hibernate.annotations.Type;
//
//import javax.persistence.*;
//import java.util.Collection;
//
//@Entity
//@Table
//@NoArgsConstructor
//@AllArgsConstructor
//@Setter
//@Getter
//public class Role {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long roleId;
//    private String name;
//
//    @Type(type = "json")
//    @Column(columnDefinition = "jsonb")
//    private Collection<String> permissions;
//
//}
