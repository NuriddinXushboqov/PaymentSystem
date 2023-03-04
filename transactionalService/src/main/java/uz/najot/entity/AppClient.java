package uz.najot.entity;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class AppClient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String appName;
    @Column(nullable = false)
    private String ipAddress;
    @Column(nullable = false)
    private String token;
}
